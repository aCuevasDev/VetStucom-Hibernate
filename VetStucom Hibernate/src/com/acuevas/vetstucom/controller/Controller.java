package com.acuevas.vetstucom.controller;

import com.acuevas.vetstucom.exceptions.DBException;
import com.acuevas.vetstucom.exceptions.UserException;
import com.acuevas.vetstucom.exceptions.UserException.UserErrors;
import com.acuevas.vetstucom.model.Usuarios;
import com.acuevas.vetstucom.persistence.DAO;
import com.acuevas.vetstucom.views.View;
import com.acuevas.vetstucom.views.View.ViewMessage;

public abstract class Controller {

	private Usuarios loggedInUser = null;

// TODO WHEN A WRONG_CREDENTIALS ERROR IS THROWN SAY TO THE USER TO BE CAREFULL WITH CAPS
	public void logIn() throws UserException {
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

	}

	private void checkCredentials(String matricula, String pswrd, Usuarios storedUser) throws UserException {
		if (matricula.equals(storedUser.getMatricula()))
			if (pswrd.equals(storedUser.getPass())) {
				loggedInUser = storedUser;
				return;
			}
		throw new UserException(UserErrors.WRONG_CREDENTIALS);
	}

}
