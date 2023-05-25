package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

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

//		Query query = em.createNamedQuery("controllo_validita_tessera");
//		query.executeUpdate();

		t.commit();
		log.info("Tessera salvata correttamente");
	}

	public void aggiornaValidita() {
		EntityTransaction t = em.getTransaction();
		t.begin();

		Query validita = em.createNamedQuery("controllo_validita_tessera");
		validita.executeUpdate();

		t.commit();
		log.info("Validità tessera aggiornata correttamente");
	}

	public Tessera findById(String id) {
		Tessera found = em.find(Tessera.class, UUID.fromString(id));
		return found;

	}
}
