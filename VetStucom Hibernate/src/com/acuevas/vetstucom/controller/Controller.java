package com.acuevas.vetstucom.controller;

import java.util.Date;
import java.util.List;

import com.acuevas.vetstucom.exceptions.ApplicationException;
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

	public static void showMenu() throws ApplicationException {
		listMenu = MenuService.getMenuList(loggedInUser);
		View.printMenu(listMenu);
		View.printMessage(ViewMessage.WELCOME + " " + loggedInUser.getNombre());
	}

	public static void menuSelector() throws UserException, ApplicationException {
		boolean exit = false;

		do {
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
						break;
					case CREATE_USER:
						break;
					case DELETE_USER:
						break;
					case EDIT_USER:
						break;
					case LOGOUT:
						break;
					case VIEW_RECORDS:
						break;
					case VIEW_USERS:
						break;

					default:
						break;
					}
				} catch (DBException e) {
					View.printError(e.getMessage());
				}
			} else
				throw new UserException(UserErrors.OPTION_NOT_VALID);
		} while (exit); // TODO CHANGE THIS BOOLEAN, AND THE VALUE IS WRONG ON PURPOSE
	}

	private static void deleteRecord() throws DBException {
		View.printMessage(ViewMessage.DELETE_ID_RECORD);
		int id = InputAsker.pedirEntero("");
		VetDAO.delete(Expedientes.class, id);
		View.printMessage(ViewMessage.DELETED_SUCESS);
	}

	private static void createRecord() {
		boolean error = false;
		boolean exit = false;

		do {
			View.printMessage(ViewMessage.INSERT_ID);
			int id = InputAsker.pedirEntero("");
			if (id == -1)
				exit = true;
			if (VetDAO.isStored(Expedientes.class, id)) {
				error = true;
				View.printError(ViewError.ID_ALREADY_EXISTS);
				View.printMessage(ViewMessage.PRESS_TO_EXIT);
			}
		} while (error && !exit);

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
	 */
	public static void logIn() throws UserException {
		View.printMessage(ViewMessage.INSERT_MATRICULA);
		String matricula = InputAsker.pedirCadena("");
		View.printMessage(ViewMessage.INSERT_PASSWORD);
		String pswrd = InputAsker.pedirCadena("");

		Usuarios storedUser = VetDAO.getUser(matricula);
		try {
			VetDAO.isStored(storedUser);
		} catch (DBException e) {
			throw new UserException(UserErrors.WRONG_CREDENTIALS);
		}

		checkCredentials(matricula, pswrd, storedUser);
		loggedInUser = storedUser;

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
