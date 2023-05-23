package dao;

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
		log.info("Elemento salvato correttamente");
	}
}
