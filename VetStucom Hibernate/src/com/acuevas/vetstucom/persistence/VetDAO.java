package com.acuevas.vetstucom.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.acuevas.vetstucom.exceptions.DBException;
import com.acuevas.vetstucom.exceptions.DBException.DBErrors;
import com.acuevas.vetstucom.exceptions.UserException;
import com.acuevas.vetstucom.libs.HibernateLib;
import com.acuevas.vetstucom.model.Expedientes;
import com.acuevas.vetstucom.model.Storeable;
import com.acuevas.vetstucom.model.Usuarios;
import com.acuevas.vetstucom.utils.HibernateUtil;
import com.acuevas.vetstucom.views.View;

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
	 * Gets a record from the db by their {@link Expedientes#id}
	 * 
	 * @param key
	 * @return
	 */
	public static Expedientes getRecord(Serializable key) {
		return getByKey(Expedientes.class, key);
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

	/**
	 * Deletes an object from the db.
	 * 
	 * @param objClass
	 * @param id
	 * @throws DBException
	 */
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

	/**
	 * Deletes a user from the db deleting all the records before.
	 * 
	 * @param matricula
	 */
	public static void deleteUser(String matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Usuarios user = VetDAO.getUser(matricula);
			List<Expedientes> expedientes = VetDAO.find(Expedientes.class, "usuarios = '" + user.getId() + "'");
			expedientes.forEach(expediente -> {
				VetDAO.erase(expediente);
			});
			VetDAO.erase(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			View.printError(e.getMessage());
		} finally {
			session.close();
		}
	}

}
