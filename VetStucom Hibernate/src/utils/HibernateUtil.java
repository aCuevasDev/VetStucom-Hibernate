package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author mfontana
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry serviceRegistry;

	static {
		try {
			serviceRegistry = new StandardServiceRegistryBuilder().configure() // configures settings from
																				// hibernate.cfg.xml
					.build();

			sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();

		} catch (Throwable ex) {
			// Log the exception.
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// Método para cerrar el hilo
	public static void close() {
		StandardServiceRegistryBuilder.destroy(serviceRegistry);
	}
}