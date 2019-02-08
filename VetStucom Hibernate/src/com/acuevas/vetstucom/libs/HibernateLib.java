package com.acuevas.vetstucom.libs;

import java.io.Serializable;

import org.hibernate.Criteria;

import com.acuevas.vetstucom.utils.HibernateUtil;

public class HibernateLib {

	public <T> T find(Class<T> objClass, Serializable key, String keyColumn) {
		Criteria criteria = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(objClass); // use JPA instead? but i'll need to put the lib?
		criteria.add(eq())
		
	}

}
