package com.acuevas.vetstucom.views;

import java.util.List;

import com.acuevas.vetstucom.model.Usuarios;
import com.acuevas.vetstucom.utils.MenuOption;

public class View {

	public enum ViewMessage {

		WELCOME("Welcome"), INSERT_MATRICULA("Please insert your username:"),
		INSERT_PASSWORD("Please insert your password:"), INSERT_OPTION("Please insert an option."),
		INSERT_ID("Please insert the id:"), NAME("Please insert your name:"),
		NAME_CLIENT("Please insert the name of the client:"),
		SURNAME_CLIENT("Please insert the surname of the client:"), DNI_CLIENT("Please insert the DNI of the client:"),
		POSTAL_CODE("Please insert the postal code of the client:"),
		PHONE_CLIENT("Please insert the phone of the client:"),
		PETS(("Please insert the number of pets the client has:")), PRESS_TO_EXIT("Insert -1 to exit"),
		DELETE_ID_RECORD("Insert the ID of the record you want to delete."), DELETED_SUCESS("Deleted successfully.");

		private String message;

		private ViewMessage(String message) {
			this.message = message;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return message;
		}

	}

	public enum ViewError {
		ID_ALREADY_EXISTS("This id already exists.");

		private String message;

		private ViewError(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return super.toString();
		}
	}

	public static void printMenu(List<MenuOption> options) {
		int index = 1;
		for (MenuOption menuOption : options) {
			System.out.println(index++ + ". " + menuOption.getAction().toString());
		}
	}

	public static void printError(String error) {
		System.err.println(error);
	}

	public static void printError(ViewError error) {
		System.err.println(error);
	}

	public static void printMessage(String message) {
		System.out.println(message);
	}

	public static void printMessageInLine(String message) {
		System.out.print(message);
	}

	public static void printMessage(ViewMessage message) {
		System.out.println(message);
	}

	public static void printUsername(ViewMessage message, Usuarios user) {
		System.out.println(message + " " + user.getMatricula());
	}

	public static void printMinus() {
		System.out.println("---");
	}

	public static void nextLine() {
		System.out.println();
	}
}
