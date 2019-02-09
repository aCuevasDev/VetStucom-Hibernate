package com.acuevas.vetstucom.libs;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.acuevas.vetstucom.utils.HibernateUtil;

public abstract class HibernateLib {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> T find(Class<T> objClass, String keyColumn, Serializable key) {
		// Using deprecated method because Hibernate at the moment uses JPA to create
		// Criteria, and this exercise is about using "raw" Hibernate.
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(objClass);
		// use JPA instead?
		T result = (T) criteria.add(Restrictions.eq(keyColumn, key)).uniqueResult();
		session.close();
		return result;
	}

//TODO ADD TO DOCUMENTATION: IM SAVING THE RESULT IN A VARIABLE BECAUSE I CAN'T CLOSE THE SESION AND THEN CALL QUERY.LIST
	public static <T> List<T> find(Class<T> objClass) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<T> query = session.createQuery("from " + objClass.getSimpleName());
		List<T> result = query.list();
		session.close();
		return result;
	}

	public static <T> List<T> find(Class<T> objClass, String whereCondition) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<T> query = session.createQuery("from " + objClass.getSimpleName() + " where " + whereCondition);
		List<T> result = query.list();
		session.close();
		return result;
	}

	public static <T> T getByKey(Class<T> objClass, Serializable key) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		T result = session.get(objClass, key);
		return result;
	}
}
