package com.acuevas.vetstucom.exceptions;

public class DBException extends Exception {

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
