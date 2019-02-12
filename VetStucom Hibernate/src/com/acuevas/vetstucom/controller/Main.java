package com.acuevas.vetstucom.controller;

import com.acuevas.vetstucom.exceptions.ApplicationException;
import com.acuevas.vetstucom.exceptions.UserException;
import com.acuevas.vetstucom.utils.HibernateUtil;
import com.acuevas.vetstucom.views.View;

public class Main {

	public static void main(String[] args) {

//		Usuarios user = DAO.getUser("alx");
//		System.out.println("DNI1: " + user.getDni());

		try {
			Controller.logIn();
			Controller.showMenu();
			Controller.menuSelector();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserException e) {
			View.printError(e.getMessage());
		}
		HibernateUtil.close();
	}
}
