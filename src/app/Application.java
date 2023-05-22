package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class Application {
	private static EntityManagerFactory emf=JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		 EntityManager em=emf.createEntityManager();
		 em.close();
		 emf.close();
		

	}

}
