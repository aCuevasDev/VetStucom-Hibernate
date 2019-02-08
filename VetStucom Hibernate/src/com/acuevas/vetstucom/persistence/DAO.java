package com.acuevas.vetstucom.persistence;

import java.io.Serializable;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.acuevas.vetstucom.libs.HibernateLib;
import com.acuevas.vetstucom.model.Usuarios;
import com.acuevas.vetstucom.utils.HibernateUtil;

public abstract class DAO extends HibernateLib {
	static {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
	}

	public static Usuarios getUser(Serializable key) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Usuarios> query = session.createQuery("from Usuarios where MATRICULA = '" + key + "'");
		Usuarios user = query.uniqueResult();
		session.close();
		return user;
	}

	// TODO REMOVE STATICS
	public static Usuarios test(String columnName, Serializable key) {
		return find(Usuarios.class, columnName, key);
	}

//	@Deprecated
//	public static Usuarios getUserByKey(Serializable key) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Usuarios user = getByKey(Usuarios.class, "MATRICULA", key);
//		session.close();
//		return user;
//	}
//
//	@Deprecated
//	private static <T> T getByKey(Class<T> objClass, String columnName, Serializable key) {
//		CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().getCriteriaBuilder();
//		CriteriaQuery<T> query = criteriaBuilder.createQuery(objClass);
//		Root<T> from = query.from(objClass);
//		query.where(criteriaBuilder.equal(from.get(columnName), key));
//
//		return HibernateUtil.getSessionFactory().getCurrentSession().createQuery(query).getSingleResult();
//	}

}
