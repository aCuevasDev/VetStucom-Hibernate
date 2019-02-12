package com.acuevas.vetstucom.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.acuevas.vetstucom.exceptions.ApplicationException;
import com.acuevas.vetstucom.exceptions.ApplicationException.AppErrors;
import com.acuevas.vetstucom.exceptions.DBException;
import com.acuevas.vetstucom.exceptions.UserException;
import com.acuevas.vetstucom.exceptions.UserException.UserErrors;
import com.acuevas.vetstucom.model.Expedientes;
import com.acuevas.vetstucom.model.UserPerfil;
import com.acuevas.vetstucom.model.Usuarios;
import com.acuevas.vetstucom.persistence.VetDAO;
import com.acuevas.vetstucom.utils.MenuOption;
import com.acuevas.vetstucom.utils.MenuService;
import com.acuevas.vetstucom.views.View;
import com.acuevas.vetstucom.views.View.ViewError;
import com.acuevas.vetstucom.views.View.ViewMessage;

public abstract class Controller {

	private static Usuarios loggedInUser = null;
	private static List<MenuOption> listMenu = null;

	public static void welcome() throws ApplicationException {
		if (loggedInUser == null)
			throw new ApplicationException(AppErrors.USER_MUST_BE_LOGGED);

		View.printMessage(ViewMessage.WELCOME + " " + loggedInUser.getNombre());
	}

	private static void showMenu() throws ApplicationException {
		listMenu = MenuService.getMenuList(loggedInUser);
		View.printMenu(listMenu);
	}

	public static void menuSelector() throws UserException, ApplicationException {
		boolean exit = false;

		do {
			showMenu();
			View.printMessage(ViewMessage.INSERT_OPTION);
			int input = InputAsker.pedirEntero("");
			MenuOption selectedOption = listMenu.get(input - 1);
			// An unauthorized action should not appear on the list in the first place, but
			// just to make sure I check it nonetheless.
			if (UserPerfil.isAllowed(loggedInUser, selectedOption.getProfile())) {
				try {
					switch (selectedOption.getAction()) {
					case CREATE_RECORD:
						createRecord();
						break;
					case DELETE_RECORD:
						deleteRecord();
						break;
					case EDIT_RECORD:
						editRecord();
						break;
					case CREATE_USER:
						createUser();
						break;
					case DELETE_USER:
						deleteUser();
						break;
					case EDIT_USER:
						// TODO THIS
						break;
					case LOGOUT:
						View.printMessage(ViewMessage.SEE_YOU);
						exit = true;
						break;
					case VIEW_RECORDS:
						viewRecords();
						break;
					case VIEW_USERS:
						viewUsers();
						break;

					default:
						break;
					}
				} catch (DBException e) {
					View.printError(e.getMessage());
				}
			} else
				throw new UserException(UserErrors.OPTION_NOT_VALID);
		} while (!exit);
	}

	private static void editRecord() {
		Expedientes record;
		do {
			View.printMessage(ViewMessage.ASK_RECORD);
			int id = InputAsker.pedirEntero("");
			record = VetDAO.getRecord(id);
			if (record == null)
				View.printMessage(ViewMessage.WRONG_ID);
		} while (record == null);

		View.printMessage(ViewMessage.SEPARATOR);
		View.printMessage(record.toString());
		View.printMessage(ViewMessage.SEPARATOR);

		recordPropertiesSelector(record);

	}

	private static void recordPropertiesSelector(Expedientes record) {
		int option;
		String data;
		int num;
		do {
			View.printMessage(ViewMessage.ASK_MODIFY);
			View.printRecordProperties();

			option = InputAsker.pedirEntero("");
			switch (option) {
			case 1:
				View.printMessage(ViewMessage.ASK_MATRICULA);
				String matricula = InputAsker.pedirCadena("");
				Usuarios user = VetDAO.getUser(matricula);
				if (user != null) {
					record.setUsuarios(user);
				} else
					View.printError(ViewError.NO_SUCH_USER);
				break;
			case 2:
				View.printMessage(ViewMessage.ASK_DATA);
				data = InputAsker.pedirCadena("");
				record.setNombre(data);
				break;
			case 3:
				View.printMessage(ViewMessage.ASK_DATA);
				data = InputAsker.pedirCadena("");
				record.setApellidos(data);
				break;
			case 4:
				View.printMessage(ViewMessage.ASK_DATA);
				data = InputAsker.pedirCadena("");
				record.setDni(data);
				break;
			case 5:
				View.printMessage(ViewMessage.ASK_DATA);
				num = InputAsker.pedirEntero("");
				record.setCp("" + num);
				break;
			case 6:
				View.printMessage(ViewMessage.ASK_DATA);
				num = InputAsker.pedirEntero("");
				record.setTelefono("" + num);
				break;
			case 7:
				View.printMessage(ViewMessage.ASK_DATA);
				num = InputAsker.pedirEntero("");
				record.setNMascotas(num);
				break;
			case 8:
				VetDAO.store(record);
				break;
			default:
				View.printError(ViewError.NO_SUCH_OPTION);
				break;
			}
		} while (option != 8);
	}

	/**
	 * Shows all the users in the DB.
	 */
	private static void viewUsers() {
		View.printMessage(ViewMessage.SEPARATOR);
		List<Usuarios> usuarios = VetDAO.find(Usuarios.class);
		usuarios.forEach(usuario -> {
			View.printMessage(usuario.toString());
		});
		View.printMessage(ViewMessage.SEPARATOR);
	}

	/**
	 * Shows all the records in the DB.
	 */
	private static void viewRecords() {
		View.printMessage(ViewMessage.SEPARATOR);
		List<Expedientes> expedientes = VetDAO.find(Expedientes.class);
		expedientes.forEach(expediente -> {
			View.printMessage(expediente.toString());
		});
		View.printMessage(ViewMessage.SEPARATOR);
	}

	/**
	 * Deletes a user from the DB calling the DAO.
	 * 
	 * @throws DBException
	 */
	private static void deleteUser() throws DBException {
		View.printMessage(ViewMessage.DELETE_USERNAME_USER);
		String matricula = InputAsker.pedirCadena("");
		VetDAO.deleteUser(matricula);
		View.printMessage(ViewMessage.DELETED_SUCESS);
	}

	/**
	 * Creates a new user and stores it.
	 * 
	 * @throws UserException
	 */
	private static void createUser() throws UserException {

		View.printMessage(ViewMessage.NAME_USER);
		String name = InputAsker.pedirCadena("");
		View.printMessage(ViewMessage.SURNAME_USER);
		String surname = InputAsker.pedirCadena("");
		View.printMessage(ViewMessage.DNI_USER);
		String dni = InputAsker.pedirCadena("");
		View.printMessage(ViewMessage.ASK_MATRICULA);
		String matricula = InputAsker.pedirCadena("");

		if (VetDAO.getUser(matricula) == null) {

			Date curdate = new Date(); // last access
			View.printMessage(ViewMessage.ASK_PASSWORD);
			String pass = InputAsker.pedirCadena("");
			View.printMessage(ViewMessage.ASK_TIPO);
			Integer tipo = InputAsker.pedirEntero("");

			Usuarios user = new Usuarios(name, surname, dni, matricula, pass, tipo, curdate,
					new HashSet<Expedientes>());
			VetDAO.store(user);
		} else
			throw new UserException(UserErrors.USER_ALREADY_EXISTS);

	}

	/**
	 * Deletes a record from the DB making calls to DAO.
	 * 
	 * @throws DBException
	 */
	private static void deleteRecord() throws DBException {
		View.printMessage(ViewMessage.DELETE_ID_RECORD);
		int id = InputAsker.pedirEntero("");
		VetDAO.delete(Expedientes.class, id);
		View.printMessage(ViewMessage.DELETED_SUCESS);
	}

	/**
	 * Checks if an object exists in the DB
	 * 
	 * @param objClass
	 * @param id
	 * @return
	 */
	public static <T> boolean checkIdExists(Class<T> objClass, Serializable id) {
		if (VetDAO.isStored(objClass, id)) {
			View.printError(ViewError.ID_ALREADY_EXISTS);
			View.printMessage(ViewMessage.PRESS_TO_EXIT);
			return true;
		}
		return false;

	}

	/**
	 * Creates a new record.
	 */
	private static void createRecord() {
		boolean error = false;
		boolean exit = false;

		if (!exit) {
			View.printMessage(ViewMessage.NAME_CLIENT);
			String name = InputAsker.pedirCadena("");
			View.printMessage(ViewMessage.SURNAME_CLIENT);
			String surname = InputAsker.pedirCadena("");
			View.printMessage(ViewMessage.DNI_CLIENT);
			String dni = InputAsker.pedirCadena("");
			View.printMessage(ViewMessage.POSTAL_CODE);
			int cp = InputAsker.pedirEntero("");
			Date curdate = new Date();
			View.printMessage(ViewMessage.PHONE_CLIENT);
			int phone = InputAsker.pedirEntero("");
			View.printMessage(ViewMessage.PETS);
			int pets = InputAsker.pedirEntero("");

			Expedientes expediente = new Expedientes(loggedInUser, name, surname, dni, "" + cp, curdate, "" + phone,
					pets);
			VetDAO.store(expediente);
		}
	}

// TODO WHEN A WRONG_CREDENTIALS ERROR IS THROWN SAY TO THE USER TO BE CAREFULL WITH CAPS
	/**
	 * Logs the user into the system asking for his credentials.
	 * 
	 * @throws UserException
	 * @throws ApplicationException
	 */
	public static void logIn() throws UserException, ApplicationException {
		View.printMessage(ViewMessage.INSERT_MATRICULA);
		String matricula = InputAsker.pedirCadena("");
		View.printMessage(ViewMessage.INSERT_PASSWORD);
		String pswrd = InputAsker.pedirCadena("");
		View.printMessage(ViewMessage.LOADING);
		Usuarios storedUser = VetDAO.getUser(matricula);
		try {
			VetDAO.isStored(storedUser);
		} catch (DBException e) {
			throw new UserException(UserErrors.WRONG_CREDENTIALS);
		}

		checkCredentials(matricula, pswrd, storedUser);
		loggedInUser = storedUser;
		View.printMessage(ViewMessage.COMPLETE);
		welcome();
	}

	/**
	 * Checks if the credentials inserted by the user are correct, if they're not a
	 * {@link UserException} is thrown, therefore not allowing {@link #logIn()} to
	 * continue.
	 * 
	 * @param matricula
	 * @param pswrd
	 * @param storedUser
	 * @throws UserException
	 */
	private static void checkCredentials(String matricula, String pswrd, Usuarios storedUser) throws UserException {
		if (matricula.equals(storedUser.getMatricula()))
			if (pswrd.equals(storedUser.getPass()))
				return;

		throw new UserException(UserErrors.WRONG_CREDENTIALS);
	}

}
