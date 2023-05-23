package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Tratta;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrattaDao {
	private final EntityManager em;

	public TrattaDao(EntityManager em) {
		this.em = em;

	}

	public void salvaTratta(Tratta tr) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(tr);
		t.commit();
		log.info("Mezzo salvato correttamente");
	}

}
