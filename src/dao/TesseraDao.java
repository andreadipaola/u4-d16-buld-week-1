package dao;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.Abbonamento;
import entities.Tessera;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class TesseraDao {
	private EntityManager em;

	public TesseraDao() {
		em = JpaUtil.getEntityManager();
	}

	public void salvaTessera(Tessera tessera) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(tessera);

		t.commit();
		log.info("Tessera salvata correttamente");
	}

	public void controllaValiditaTessera() {
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query query = em.createNamedQuery("controllo_validita_tessera");
		query.executeUpdate();
		t.commit();
	}

	// RECUPERARE DALLA TESSERA I DATI DELL' UTENTE
	public void recuperaDatiTessera(UUID id) {
		Tessera te = em.find(Tessera.class, id);

		if (te == null) {
			log.error("ATTENZIONE!!! La tessera con id: " + id + " non è presente nel nostro database");
			return;
		}

		log.info("Tessera con id: " + id);
		log.info("Data emissione: " + te.getDataEmissione().toString() + "Data scadenza: "
				+ te.getDataScadenza().toString());

		boolean validita = te.isValidita();

		if (validita == true) {
			log.info("Tessera in corso di validità");
		} else {
			log.error("ATTENZIONE!!! Tessera scaduta, si prega di effettuare il rinnovo");
		}

		try {
			boolean validitaAbbonamento = te.getAbbonamento().isValidita();

			if (validitaAbbonamento == true) {
				log.info("Lei ha un abbonamento attivo con scadenza il: " + te.getAbbonamento().getDataScadenza());
			} else {
				log.error("ATTENZIONE!!! Lei non ha alcun abbonamento attivo");
			}
		} catch (Exception ex) {
			log.error("ATTENZIONE!!! Al momento non risulta alcun abbonamento attivo" + ex);
		}
	}

	public void rinnovaTessera(UUID id) {

		Tessera te = em.find(Tessera.class, id);

		if (te == null) {
			log.error("ATTENZIONE!!! La tessera con id: " + id + " non è presente nel nostro database");
			return;
		}

		boolean validita = te.isValidita();

		if (validita == true) {
			log.error("ATTENZIONE!!! La tessera che sta cercando di rinnovare è in corso di validita fino al: "
					+ te.getDataScadenza());
		} else {
			EntityTransaction t = em.getTransaction();
			te.setValidita(true);
			te.setDataScadenza(LocalDate.now().plusYears(1));

			t.begin();
			em.persist(te);
			t.commit();

			log.info("CONGRATULAZIONI!!! La sua tessera è stata rinnovata con successo");
			log.info("La nuova data di scadenza è:" + te.getDataScadenza());
		}
	}

	// DA CONTROLLARE
	public void aggiornaValiditaAbbonamento(UUID id, Abbonamento abbonamento) {

		Tessera te = em.find(Tessera.class, id);

		if (te == null) {
			log.error("Attenzione!!! La tessera con id: " + id + " non è presente nel nostro database");
			return;
		}

		try {
			EntityTransaction t = em.getTransaction();
			te.setAbbonamento(abbonamento);
			t.begin();
			em.persist(te);
			t.commit();
		} catch (Exception ex) {
			log.error("ATTENZIONE!!! Abbonamento già attivo" + ex);
		}
	}

	// DA CONTROLLARE
	public Tessera trovaTessera(UUID id) {
		Tessera te = em.find(Tessera.class, id);

		if (te == null) {
			log.error("La tessera con id: " + id + " non è presente nel nostro database");
		}
		return te;
	}

}