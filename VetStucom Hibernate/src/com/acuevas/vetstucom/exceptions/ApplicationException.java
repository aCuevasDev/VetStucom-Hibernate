package com.acuevas.vetstucom.exceptions;

/**
 * Exceptions occurred within the application. Some of them critical.
 * 
 * @author Alex
 *
 */
public class ApplicationException extends Exception {
	private static final long serialVersionUID = -1661306408156309190L;

	public enum AppErrors {
		TYPE_DOESNT_EXIST("FATAL ERROR: THIS USER PROFILE DOESN'T EXIST");

		private String message;

		private AppErrors(String message) {
			this.message = message;
		}

	}

	public ApplicationException(AppErrors error) {
		super(error.message);
	}

}
