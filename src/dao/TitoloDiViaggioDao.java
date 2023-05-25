package dao;

import java.time.LocalDate;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.TitoloDiViaggio;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TitoloDiViaggioDao {
	private final EntityManager em;

	public TitoloDiViaggioDao(EntityManager em) {
		this.em = em;
	}

	public void salvaTitoloDiViaggio(TitoloDiViaggio tv) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(tv);
		t.commit();
		log.info("Titolo di viaggio salvato correttamente");

	}

	public void ConteggioTitoliDiViaggio(LocalDate dataInizio, LocalDate dataFine) {
		Query conteggio = em.createQuery("SELECT COUNT(*) FROM titoli_di_viaggio WHERE ")
	}

	// SELECT * FROM titoli_di_viaggio JOIN punto_di_emissione
	// ON titoli_di_viaggio.punto_di_emissione_id = punto_di_emissione.id
	// WHERE dataemissione BETWEEN '2011-10-23' AND '2012-01-12'

}
