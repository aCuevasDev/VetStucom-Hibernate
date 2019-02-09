package com.acuevas.vetstucom.exceptions;

@SuppressWarnings("serial")
public class UserException extends Exception {
	public enum UserErrors {
		WRONG_CREDENTIALS("Username or password incorrect.");

		private String message;

		private UserErrors(String message) {
			this.message = message;
		}

	}

	public UserException(UserErrors error) {
		super(error.message);
	}

}
