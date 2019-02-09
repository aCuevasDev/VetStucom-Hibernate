package com.acuevas.vetstucom.exceptions;

public class DBException extends Exception {
	private static final long serialVersionUID = -7143360810865698647L;

	public enum DBErrors {
		NOT_STORED("Obj not stored in db");

		private String message;

		private DBErrors(String message) {
			this.message = message;
		}

	}

	public DBException(DBErrors error) {
		super(error.message);
	}

}
