package com.acuevas.vetstucom.views;

import java.util.List;

import com.acuevas.vetstucom.model.Usuarios;
import com.acuevas.vetstucom.utils.MenuOption;

public class View {

	public enum ViewMessage {

		WELCOME("Welcome"), INSERT_MATRICULA("Please insert your username"),
		INSERT_PASSWORD("Please insert your password"), INSERT_OPTION("Please insert an option.");

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
		// TODO
		TODO("");

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
			System.out.println(index++ + ". " + menuOption.getMessage());
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
