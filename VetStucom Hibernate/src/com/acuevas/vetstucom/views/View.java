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
		DELETE_ID_RECORD("Insert the ID of the record you want to delete."), DELETED_SUCESS("Deleted successfully."),
		ASK_MATRICULA("Please insert the user's username."), ASK_PASSWORD("Please insert the password"),
		ASK_TIPO("Plese insert the type: aux(1),vet(2),admin(3)"), DNI_USER("Please insert the dni"),
		SURNAME_USER("Please insert the surname"), NAME_USER("Please insert the name"),
		DELETE_USERNAME_USER("Insert the username of the user you want to delete."), GOODBYE("Goodbye!"),
		LOADING("--------------LOADING-------------"), COMPLETE("--------------COMPLETE-------------"),
		SEE_YOU("See you!"), SEPARATOR("***************************************"),
		ASK_RECORD("Insert the ID of the record you want to modify"), WRONG_ID("Wrong ID, Object doesn't exist"),
		ASK_MODIFY("What do you want to modify?"), ASK_DATA("Insert the new value"),
		ASK_USER("Insert the username of the user you want to modify.");

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
		ID_ALREADY_EXISTS("This id already exists."), NO_SUCH_OPTION("There's no such option"),
		CRITICAL("CRITICAL ERROR, CLOSING NOW"), NO_SUCH_USER("THERE'S NO SUCH USER"),
		WRONG_TYPE("Wrong type, values must be between 1-3"), CANNOT_DELETE_SELF("You can't delete yourself."),
		TOO_LONG("Value is too long");

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

	public static void printRecordProperties() {
		System.out.println("1. User");
		System.out.println("2. Name");
		System.out.println("3. Surname");
		System.out.println("4. Dni");
		System.out.println("5. Postal code");
		System.out.println("6. Phone");
		System.out.println("7. Pet number");
		System.out.println("8. Exit");
	}

	public static void printUserProperties() {
		System.out.println("1. Name");
		System.out.println("2. Surname");
		System.out.println("3. Dni");
		System.out.println("4. Password");
		System.out.println("5. Type");
		System.out.println("6. Exit");
	}

	public static void printError(String error) {
		System.err.println(error);
	}

	public static void printError(ViewError error) {
		System.err.println(error.toString());
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
