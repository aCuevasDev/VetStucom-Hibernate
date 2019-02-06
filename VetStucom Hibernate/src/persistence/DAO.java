package persistence;

import java.io.Serializable;
import java.util.logging.Level;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Usuarios;
import utils.HibernateUtil;

public abstract class DAO {
	static {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
	}

	public static Usuarios getUser(Serializable key) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Usuarios where MATRICULA = '" + key + "'"); // try Usuarios u where
																							// u.MATRICULA
		Usuarios user = (Usuarios) query.uniqueResult();
		session.close();
		return user;
	}

	@Deprecated
	public static Usuarios getUserByKey(Serializable key) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Usuarios user = getByKey(Usuarios.class, "MATRICULA", key);
		session.close();
		return user;
	}

	@Deprecated
	private static <T> T getByKey(Class<T> objClass, String columnName, Serializable key) {
		CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(objClass);
		Root<T> from = query.from(objClass);
		query.where(criteriaBuilder.equal(from.get(columnName), key));

		return HibernateUtil.getSessionFactory().getCurrentSession().createQuery(query).getSingleResult();
	}

}
