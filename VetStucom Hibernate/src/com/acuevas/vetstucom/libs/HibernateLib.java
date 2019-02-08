package com.acuevas.vetstucom.libs;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.acuevas.vetstucom.utils.HibernateUtil;

public abstract class HibernateLib {

	@SuppressWarnings("unchecked")
	public static <T> T find(Class<T> objClass, String keyColumn, Serializable key) {
		// Using deprecated method because Hibernate at the moment uses JPA to create
		// Criteria, and this exercise is about using "raw" Hibernate.
		@SuppressWarnings("deprecation")
		Criteria criteria = HibernateUtil.getSessionFactory().openSession().createCriteria(objClass);
		// use JPA instead?
		return (T) criteria.add(Restrictions.eq(keyColumn, key)).uniqueResult();
	}

	public <T> List<T> find(Class<T> objClass) {
		Query<T> query = HibernateUtil.getSessionFactory().openSession().createQuery("from " + objClass);
		return query.list();
	}

	public <T> List<T> find(Class<T> objClass, String whereCondition) {
		Query<T> query = HibernateUtil.getSessionFactory().openSession()
				.createQuery("from " + objClass + " where " + whereCondition);
		return query.list();
	}

	public <T> T getByKey(Class<T> objClass, Serializable key) {
		return HibernateUtil.getSessionFactory().getCurrentSession().get(objClass, key);
	}
}
