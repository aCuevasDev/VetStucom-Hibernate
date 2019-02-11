package com.acuevas.vetstucom.controller;

import java.util.List;

import com.acuevas.vetstucom.exceptions.ApplicationException;
import com.acuevas.vetstucom.exceptions.DBException;
import com.acuevas.vetstucom.exceptions.UserException;
import com.acuevas.vetstucom.exceptions.UserException.UserErrors;
import com.acuevas.vetstucom.model.Usuarios;
import com.acuevas.vetstucom.persistence.DAO;
import com.acuevas.vetstucom.utils.MenuOption;
import com.acuevas.vetstucom.utils.MenuService;
import com.acuevas.vetstucom.views.View;
import com.acuevas.vetstucom.views.View.ViewMessage;

public abstract class Controller {

	private static Usuarios loggedInUser = null;

	public static void showMenu() throws ApplicationException {
		List<MenuOption> listMenu = MenuService.getMenuList(loggedInUser);
		View.printMenu(listMenu);
		View.printMessage(ViewMessage.WELCOME + " " + loggedInUser.getNombre());
	}

	public static void menuSelector() {
		boolean exit = false;

		do {
			View.printMessage(ViewMessage.INSERT_OPTION);
			int input = InputAsker.pedirEntero("");
			switch (input) {
			case 1:

				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;

			default:
				break;
			}
		} while (exit); // TODO CHANGE THIS BOOLEAN, AND THE VALUE IS WRONG ON PURPOSE
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

		Usuarios storedUser = DAO.getUser(matricula);
		try {
			DAO.isStored(storedUser);
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
