package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.PuntoDiEmissione;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class PuntoDiEmissioneDao {
	private EntityManager em;

	public PuntoDiEmissioneDao() {
		em = JpaUtil.getEntityManager();
	}

	public void salvaPuntoEmissione(PuntoDiEmissione pe) {
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist(pe);
			t.commit();
			log.info("Punto di emissione salvato correttamente");
		} catch (Exception ex) {
			log.error("ATTENZIONE!!! C'é stato un errore nell'inserimento del punto di emissione " + ex);
		}
	}

	public void bigliettiEmessi(long id) {
		PuntoDiEmissione pe = em.find(PuntoDiEmissione.class, id);
		int bigliettiEmessi = pe.getBigliettiEmessi() + 1;
		try {
			EntityTransaction t = em.getTransaction();
			pe.setBigliettiEmessi(bigliettiEmessi);
			t.begin();
			em.persist(pe);
			t.commit();
		} catch (Exception ex) {
			log.error("ATTENZIONE!!! C'é stato un errore nel conteggio dei biglietti emessi" + ex);
		}

	}

	public void abbonamentiEmessi(long id) {
		PuntoDiEmissione pe = em.find(PuntoDiEmissione.class, id);
		int abbonamentiEmessi = pe.getAbbonamentiEmessi() + 1;
		try {
			EntityTransaction t = em.getTransaction();
			pe.setAbbonamentiEmessi(abbonamentiEmessi);
			t.begin();
			em.persist(pe);
			t.commit();
		} catch (Exception ex) {
			log.error("ATTENZIONE!!! C'é stato un errore nel conteggio degli abbonamenti emessi" + ex);
		}

	}

	public void getTitoliEmessi(long id) {
		PuntoDiEmissione pe = em.find(PuntoDiEmissione.class, id);

		int bigliettiEmessi = pe.getBigliettiEmessi();
		int abbonamentiEmessi = pe.getAbbonamentiEmessi();

		log.info("Il punto di emissione con id:  " + id + " ha emesso: " + bigliettiEmessi + " biglietti e "
				+ abbonamentiEmessi);
	}

}
