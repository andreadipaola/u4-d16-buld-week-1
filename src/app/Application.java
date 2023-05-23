package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.PuntoDiEmissioneDao;
import entities.Distributore;
import entities.Rivenditore;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class Application {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		PuntoDiEmissioneDao pe = new PuntoDiEmissioneDao(em);
		Rivenditore rivenditore = new Rivenditore(false);
		Distributore distributore = new Distributore(true);

		pe.salvaPuntoEmissione(rivenditore);
		pe.salvaPuntoEmissione(distributore);
		em.close();
		emf.close();
	}
}
