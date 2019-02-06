package controller;

import model.Usuarios;
import persistence.DAO;
import utils.HibernateUtil;

public class Main {

	public static void main(String[] args) {

		Usuarios user = DAO.getUser("alx");
		System.out.println(user.getDni());
		HibernateUtil.close();
	}

}
