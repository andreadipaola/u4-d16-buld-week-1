package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Tessera;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TesseraDao {
	private final EntityManager em;

	public TesseraDao(EntityManager em) {
		this.em = em;
	}

	public void salvaTessera(Tessera tessera) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(tessera);
		t.commit();
		log.info("Tessera salvata correttamente");
	}

	public Tessera findById(String id) {
		Tessera found = em.find(Tessera.class, UUID.fromString(id));
		return found;

	}
}
