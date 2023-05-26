package dao;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.Abbonamento;
import entities.Biglietto;
import entities.Mezzo;
import entities.Tessera;
import entities.TitoloDiViaggio;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class TitoloDiViaggioDao {
	private EntityManager em;

	public TitoloDiViaggioDao() {
		em = JpaUtil.getEntityManager();
	}

	public void salvaTitoloDiViaggio(TitoloDiViaggio tv) {
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist(tv);
			t.commit();
			log.info("Titolo di viaggio salvato correttamente");
		} catch (Exception ex) {
			log.error("ATTENZIONE!!! C'é stato un errore nell'inserimento del titolo di viaggio" + ex);
		}

	}

	public void controllaValiditaAbbonamento() {
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query query = em.createNamedQuery("controllaValiditaAbbonamento");
		query.executeUpdate();
		t.commit();
	}

	public void controllaValiditaTessera(UUID id) {
		Tessera te = em.find(Tessera.class, id);
		if (te == null) {
			log.error("ATTENZIONE!!! La tessera con id: " + id + " non è presente nel nostro database");
			System.exit(0);
		}
		boolean validita = te.isValidita();
		if (validita == false) {
			log.error(
					"ATTEZNIONE!!! La tessera cercata è scaduta, per poter acquistare un nuovo abbomamento, per favore rinnovare prima la tessera");
			System.exit(0);
		}
	}

	public void contaTitoliEmessi(Class<?> classe, LocalDate dataInizioControllo, LocalDate dataFineControllo) {
		Query query = em.createQuery("SELECT COUNT(*) FROM " + classe.getName()
				+ " WHERE dataEmissione BETWEEN :dataInizioControllo AND :dataFineControllo");
		query.setParameter("dataInizioControllo", dataInizioControllo);
		query.setParameter("dataFineControllo", dataFineControllo);

		String titoliEmessi = query.getSingleResult().toString();

		log.info("Durante il periodo compreso tra i: " + dataInizioControllo + " e il: " + dataFineControllo
				+ " sono stati emessi: " + titoliEmessi + classe.getSimpleName());
	}

	public void controllaTitolDiViaggio(UUID idTitolo, UUID idMezzo) {
		EntityTransaction t = em.getTransaction();
		TitoloDiViaggio tv = em.find(TitoloDiViaggio.class, idTitolo);

		if (tv == null) {
			log.info("Il titolo numero " + idTitolo + " non è stato trovato!");
			System.exit(0);
		}

		Class<?> tipo = tv.getClass();

		if (tipo == Biglietto.class) {
			Biglietto b = em.find(Biglietto.class, idTitolo);
			boolean validita = b.isTimbrato();

			if (validita == false) {
				b.setDataTimbratura(LocalDate.now());
				b.setTimbrato(true);
				Mezzo m = em.find(Mezzo.class, idMezzo);
				b.setMezzo(m);
				int bigliettiTimbrati = m.getBigliettiTimbrati() + 1;
				m.setBigliettiTimbrati(bigliettiTimbrati);

				t.begin();
				em.persist(b);
				em.persist(m);
				t.commit();

				log.info("Biglietto timbrato correttamente. Le auguriamo buon viaggio");
				MezzoDao mezzoDao = new MezzoDao();
				mezzoDao.aggiornaNumeroCorse(idMezzo);

			} else {
				log.error(
						"ATTEZNIONE!!! Biglietto già utilizzato. Per effettuare la corsa ha bisogno di un nuovo biglietto o di un abbonamento in corso di validità");
			}
		} else if (tipo == Abbonamento.class) {
			Abbonamento a = em.find(Abbonamento.class, idTitolo);
			LocalDate validita = a.getDataScadenza();

			if (validita.isAfter(LocalDate.now())) {
				log.info("Abbonamento in corso di validità. Le auguriamo buon viaggio");
				MezzoDao mezzoDao = new MezzoDao();
				mezzoDao.aggiornaNumeroCorse(idMezzo);
			} else {
				log.error(
						"ATTEZNIONE!!! Abbonamento non valido. Per effettuare la corsa ha bisogno di un nuovo biglietto o di un abbonamento in corso di validità");
				System.exit(0);
			}
		}
	}
}
