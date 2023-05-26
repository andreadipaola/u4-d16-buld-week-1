package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Utente;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class UtenteDao {
	private EntityManager em;

	public UtenteDao() {
		em = JpaUtil.getEntityManager();
	}

	public void salvaUtente(Utente utente) {
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.persist(utente);
			t.commit();
			log.info("Utente salvato correttamente");
		} catch (Exception ex) {
			log.error("ATTENZIONE!!! C'é stato un errore nell'inserimento dell'utente" + ex);
		}
	}
}