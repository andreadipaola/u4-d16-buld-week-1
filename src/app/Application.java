package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.PuntoDiEmissioneDao;
import dao.TesseraDao;
import dao.TitoloDiViaggioDao;
import dao.UtenteDao;
import entities.Abbonamento;
import entities.Biglietto;
import entities.Distributore;
import entities.Rivenditore;
import entities.Tessera;
import entities.Utente;
import enums.Periodicita;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class Application {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		PuntoDiEmissioneDao pe = new PuntoDiEmissioneDao(em);
		Rivenditore rivenditore = new Rivenditore(false);
		Distributore distributore = new Distributore(true);

		TitoloDiViaggioDao tv = new TitoloDiViaggioDao(em);
		UtenteDao ut = new UtenteDao(em);
		TesseraDao te = new TesseraDao(em);

		Biglietto biglietto1 = new Biglietto(LocalDate.parse("2012-11-12"), true, LocalDate.parse("2012-12-11"));
		Abbonamento abbonamento1 = new Abbonamento(LocalDate.parse("2011-11-12"), Periodicita.MENSILE);
		Utente utente1 = new Utente("prtglc94e06h224o", "Gianluca", "Praticò", 29);
		Tessera tessera1 = new Tessera(LocalDate.parse("2015-11-12"));

		tv.salvaTitoloDiViaggio(abbonamento1);
		tv.salvaTitoloDiViaggio(biglietto1);
		ut.salvaUtente(utente1);
		te.salvaTessera(tessera1);

		pe.salvaPuntoEmissione(rivenditore);
		pe.salvaPuntoEmissione(distributore);
		em.close();
		emf.close();
	}
}
