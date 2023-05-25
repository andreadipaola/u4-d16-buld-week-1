package dao;

import java.time.LocalDate;
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

		t.commit();
		log.info("Tessera salvata correttamente");
	}

	public void verificaValidita() {
		EntityTransaction t = em.getTransaction();
		t.begin();

		Query validita = em.createNamedQuery("controllo_validita_tessera");
		validita.executeUpdate();

		t.commit();
		log.info("Validità tessera effettuata correttamente");
	}

	/*
	 * public Tessera findById(String id) { Tessera trovata = em.find(Tessera.class,
	 * UUID.fromString(id)); return trovata;
	 * 
	 * }
	 */

	public void aggiornaValidita(UUID id) {
		EntityTransaction t = em.getTransaction();

		Tessera trovata = em.find(Tessera.class, id);
		if (trovata != null) {
			boolean validita = trovata.isValidita();
			if (validita == true) {
				log.info("tessera gia valida");
				log.info("tessera trovata");

			} else {
				trovata.setValidita(true);
				trovata.setDataScadenza(LocalDate.now().plusYears(1));
				LocalDate dataScadenza = trovata.getDataScadenza();
				t.begin();
				em.persist(trovata);
				t.commit();
				log.info("tessera rinnovata correttamente.La nuova data di scadenza è : {}", dataScadenza);
			}
		} else {
			log.info("tessera non trovata");
			return;
		}

	}
}