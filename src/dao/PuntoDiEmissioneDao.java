package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.PuntoDiEmissione;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PuntoDiEmissioneDao {
	private final EntityManager em;

	public PuntoDiEmissioneDao(EntityManager em) {
		this.em = em;

	}

	public void salvaPuntoEmissione(PuntoDiEmissione pe) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(pe);
		t.commit();
		log.info("Punto di emissione salvato correttamente");
	}

	public void bigliettiEmessi(UUID id) {
		EntityTransaction t = em.getTransaction();
		PuntoDiEmissione pe = em.find(PuntoDiEmissione.class, id);
		int bigliettiEmessi = pe.getBigliettiEmessi() + 1;
		pe.setBigliettiEmessi(bigliettiEmessi);
		t.begin();
		em.persist(pe);
		t.commit();
		log.info("biglietto aggiunto correttamente");
	}

	public void abbonamentiEmessi(UUID id) {
		EntityTransaction t = em.getTransaction();
		PuntoDiEmissione pe = em.find(PuntoDiEmissione.class, id);
		int abbonamentiEmessi = pe.getAbbonamentiEmessi() + 1;
		pe.setAbbonamentiEmessi(abbonamentiEmessi);
		t.begin();
		em.persist(pe);
		t.commit();
		log.info("abbonamento aggiunto correttamente");
	}

	public PuntoDiEmissione findById(String id) {
		PuntoDiEmissione found = em.find(PuntoDiEmissione.class, UUID.fromString(id));
		return found;
	}

}
