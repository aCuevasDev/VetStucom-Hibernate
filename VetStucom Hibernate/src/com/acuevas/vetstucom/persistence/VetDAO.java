package com.acuevas.vetstucom.persistence;

import java.io.Serializable;
import java.util.logging.Level;

import com.acuevas.vetstucom.exceptions.DBException;
import com.acuevas.vetstucom.exceptions.DBException.DBErrors;
import com.acuevas.vetstucom.exceptions.UserException;
import com.acuevas.vetstucom.libs.HibernateLib;
import com.acuevas.vetstucom.model.Storeable;
import com.acuevas.vetstucom.model.Usuarios;

public abstract class VetDAO extends HibernateLib {
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

	/**
	 * Saves or updates an object into the db.
	 * 
	 * @param obj
	 */
	public static void store(Storeable obj) {
		saveOrUpdate(obj);
	}

	/**
	 * Checks if a {@link Storeable} object persists in the database.
	 * 
	 * 
	 * @param <T>
	 * @throws UserException
	 * @throws DBException
	 */
	public static <T> boolean isStored(Class<T> objClass, Serializable id) {
		Storeable obj = (Storeable) getByKey(objClass, id);
		if (obj != null)
			return true;
		return false;
	}

	public static <T> void delete(Class<T> objClass, Serializable id) throws DBException {
		Storeable obj = (Storeable) getByKey(objClass, id);
		if (obj != null)
			erase(obj);
		else
			throw new DBException(DBErrors.NOT_STORED);
	}

	/**
	 * Checks if a {@link Storeable} object persists in the database.
	 * 
	 * @param obj
	 * @return
	 * @throws DBException
	 */
	public static Storeable isStored(Storeable obj) throws DBException {
		if (obj != null)
			return obj;
		throw new DBException(DBErrors.NOT_STORED);
	}

}
