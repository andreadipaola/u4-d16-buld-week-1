package dao;

import java.util.Random;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Mezzo;
import entities.Tratta;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class TrattaDao {

	private EntityManager em;

	public TrattaDao() {
		em = JpaUtil.getEntityManager();

	}

	public void salvaTratta(Tratta tr) {
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist(tr);
			t.commit();
			log.info("Mezzo salvato correttamente");
		} catch (Exception ex) {
			log.error("ATTENZIONE!!! C'é stato un errore nell'inserimento della tratta " + ex);
		}
	}

	public void calcolaTempoCorsaEffettivo(UUID id) {
		Mezzo m = em.find(Mezzo.class, id);
		double numero = m.getTratta().getTempoMedioPercorrenza();
		double rangeMinimo = 0.1;
		double rangeMassimo = 0.5;
		Random random = new Random();
		double percentuale = rangeMinimo + (rangeMassimo - rangeMinimo) * random.nextDouble();
		double incremento = numero * percentuale;
		double risultato = numero + incremento;

		System.out.println("La corsa da " + m.getTratta().getPartenza() + " a " + m.getTratta().getCapolinea()
				+ " ha impiegato " + risultato + " minuti");
	}
}
