package com.acuevas.vetstucom.persistence;

import java.io.Serializable;
import java.util.logging.Level;

import com.acuevas.vetstucom.exceptions.DBException;
import com.acuevas.vetstucom.exceptions.DBException.DBErrors;
import com.acuevas.vetstucom.exceptions.UserException;
import com.acuevas.vetstucom.libs.HibernateLib;
import com.acuevas.vetstucom.model.Storeable;
import com.acuevas.vetstucom.model.Usuarios;

public abstract class DAO extends HibernateLib {
	static {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
	}

	/**
	 * Gets a user from the db based on their {@link Usuarios#matricula} property
	 * 
	 * @param key
	 * @return
	 */
	public static Usuarios getUser(Serializable key) {
		return find(Usuarios.class, "matricula", key);
	}

	// TODO REMOVE STATICS

	/**
	 * Checks if a {@link Storeable} object persists in the database.
	 * 
	 * @param <T>
	 * @throws UserException
	 * @throws DBException
	 */
	public static Storeable isStored(Storeable obj) throws DBException {
		if (obj != null)
			return obj;
		throw new DBException(DBErrors.NOT_STORED);
	}

}
