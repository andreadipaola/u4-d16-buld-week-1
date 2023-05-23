package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Mezzo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MezzoDao {
	private final EntityManager em;

	public MezzoDao(EntityManager em) {
		this.em = em;

	}

	public void salvaMezzo(Mezzo me) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(me);
		t.commit();
		log.info("Mezzo salvato correttamente");
	}

}
