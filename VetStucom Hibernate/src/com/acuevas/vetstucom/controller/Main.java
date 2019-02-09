package com.acuevas.vetstucom.controller;

import java.util.List;

import com.acuevas.vetstucom.model.Usuarios;
import com.acuevas.vetstucom.persistence.DAO;
import com.acuevas.vetstucom.utils.HibernateUtil;

public class Main {

	public static void main(String[] args) {

		Usuarios user = DAO.getUser("alx");
		System.out.println("DNI1: " + user.getDni());
		List<Usuarios> user2 = DAO.test("matricula", "alx");
		user2.forEach(System.out::println);
		HibernateUtil.close();
	}

}
