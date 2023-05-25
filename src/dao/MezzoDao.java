package dao;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.Mezzo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MezzoDao {
	private final EntityManager em;

	public MezzoDao(EntityManager em) {
		this.em = em;

	}

	public void salvaMezzo(Mezzo me) {
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist(me);
			t.commit();
			log.info("Mezzo salvato correttamente");
		} catch (Exception ex) {
			log.error("ATTENZIONE!!! C'é stato un errore nell'inserimento del mezzo " + ex);
		}
	}

	public void contaNumeroCorse(UUID id) {
		EntityTransaction t = em.getTransaction();
		Mezzo m = em.find(Mezzo.class, id);
		int corse = m.getNumeroCorse() + 1;
		m.setNumeroCorse(corse);
		t.begin();
		em.persist(m);
		t.commit();
	}

	public void settaMezzoInManutenzione() {
		EntityTransaction t = em.getTransaction();
		t.begin();

		Query query = em.createQuery(
				"UPDATE Mezzo m SET m.inServizio = false, m.inizioManutenzione = :dataInizioManutenzione, m.fineManutenzione = :dataFinemanutenzione, m.numeroCorse = 0 WHERE m.numeroCorse = 13");
		query.setParameter("dataInizioManutenzione", LocalDate.now());
		query.setParameter("dataFinemanutenzione", LocalDate.now().plusDays(7));
		query.executeUpdate();
	}

	public void settaMezzoInServizio() {
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query query = em.createQuery(
				"UPDATE Mezzo m SET m.inServizio = true, m.inizioManutenzione = NULL, m.fineManutenzione = NULL WHERE m.fineManutenzione <= CURRENT_DATE");
		query.executeUpdate();
		t.commit();
	}

}
