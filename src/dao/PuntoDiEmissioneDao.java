package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.PuntoDiEmissione;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PuntoDiEmissioneDao {
	private final EntityManager em;
	public PuntoDiEmissioneDao(EntityManager em) {
		this.em=em;
		
	}
	public void salvaPuntoEmissione (PuntoDiEmissione pe) {
		EntityTransaction t= em.getTransaction();
		t.begin();
		em.persist(pe);
		t.commit();
		log.info("Elemento salvato correttamente");
	}

}
