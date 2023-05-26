//package utils;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//public class JpaUtil {
//
//	protected static EntityManagerFactory emf;
//	protected static final EntityManager em = emf.createEntityManager();
//	protected static final EntityTransaction t = em.getTransaction();
//
//	static {
//		try {
//			emf = Persistence.createEntityManagerFactory("u4-d16-buld-week-1");
//		} catch (Throwable ex) {
//			System.err.println("Initial EntityManagerFactory creation failed." + ex);
//			throw new ExceptionInInitializerError(ex);
//		}
//	}
//
//	public static EntityManagerFactory getEntityManagerFactory() {
//		return emf;
//	}
//
//}
package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaUtil {

	private static EntityManagerFactory emf;
	private static final ThreadLocal<EntityManager> threadLocalEntityManager = new ThreadLocal<>();

	public static void initializeEntityManagerFactory() {
		try {
			emf = Persistence.createEntityManagerFactory("u4-d16-buld-week-1");
		} catch (Throwable ex) {
			System.err.println("Initial EntityManagerFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager() {
		EntityManager entityManager = threadLocalEntityManager.get();
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = emf.createEntityManager();
			threadLocalEntityManager.set(entityManager);
		}
		return entityManager;
	}

	public static EntityTransaction getEntityTransaction() {
		return getEntityManager().getTransaction();
	}

	public static void closeEntityManager() {
		EntityManager entityManager = threadLocalEntityManager.get();
		if (entityManager != null) {
			entityManager.close();
			threadLocalEntityManager.remove();
		}
	}

	public static void closeEntityManagerFactory() {
		if (emf != null) {
			emf.close();
		}
	}
}
