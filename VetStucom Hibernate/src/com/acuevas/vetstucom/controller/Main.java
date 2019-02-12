package com.acuevas.vetstucom.controller;

import java.util.List;

import com.acuevas.vetstucom.exceptions.ApplicationException;
import com.acuevas.vetstucom.exceptions.UserException;
import com.acuevas.vetstucom.utils.HibernateUtil;
import com.acuevas.vetstucom.utils.MenuOption;
import com.acuevas.vetstucom.utils.MenuService;
import com.acuevas.vetstucom.views.View;
import com.acuevas.vetstucom.views.View.ViewError;
import com.acuevas.vetstucom.views.View.ViewMessage;

public class Main {

	public static void main(String[] args) {
		try {
			selector();
		} catch (ApplicationException e) {
			View.printError(e.getMessage());
			View.printError(ViewError.CRITICAL);
			System.exit(0);
		} finally {
			HibernateUtil.close();
		}
	}

	public static void selector() throws ApplicationException {
		int option;
		do {
			List<MenuOption> options = MenuService.getLoginMenu();
			View.printMenu(options);
			View.printMessage(ViewMessage.INSERT_OPTION);
			option = InputAsker.pedirEntero("");
			switch (option) {
			case 1:

				try {
					Controller.logIn();
					Controller.menuSelector();
				} catch (UserException e) {
					View.printError(e.getMessage());
				}
				break;
			case 2:
				View.printMessage(ViewMessage.GOODBYE);
				System.exit(0);
			default:
				View.printError(ViewError.NO_SUCH_OPTION);
				break;
			}
		} while (option != 2);
	}

}
