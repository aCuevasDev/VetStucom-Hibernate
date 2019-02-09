package com.acuevas.vetstucom.controller;

import com.acuevas.vetstucom.model.Usuarios;
import com.acuevas.vetstucom.persistence.DAO;
import com.acuevas.vetstucom.utils.HibernateUtil;

public class Main {

	public static void main(String[] args) {

		Usuarios user = DAO.getUser("alx");
		System.out.println("DNI1: " + user.getDni());

		HibernateUtil.close();
	}
}
