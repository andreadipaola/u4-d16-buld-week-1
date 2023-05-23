package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.MezzoDao;
import dao.PuntoDiEmissioneDao;
import dao.TesseraDao;
import dao.TitoloDiViaggioDao;
import dao.TrattaDao;
import dao.UtenteDao;
import entities.Abbonamento;
import entities.Autobus;
import entities.Biglietto;
import entities.Distributore;
import entities.Rivenditore;
import entities.Tessera;
import entities.Tram;
import entities.Tratta;
import entities.Utente;
import enums.Periodicita;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class Application {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		UtenteDao ut = new UtenteDao(em);
		TesseraDao te = new TesseraDao(em);
		PuntoDiEmissioneDao pe = new PuntoDiEmissioneDao(em);
		TitoloDiViaggioDao tv = new TitoloDiViaggioDao(em);
		MezzoDao me = new MezzoDao(em);
		TrattaDao tr = new TrattaDao(em);

		Utente utente1 = new Utente("pel15hsdd065t4hh", "Gianluca", "Praticò", 29);
		Tessera tessera1 = new Tessera(LocalDate.parse("2015-11-12"));
		Rivenditore rivenditore = new Rivenditore(false);
		Distributore distributore = new Distributore(true);
		Biglietto biglietto1 = new Biglietto(LocalDate.parse("2012-11-12"), true, LocalDate.parse("2012-12-11"));
		Abbonamento abbonamento1 = new Abbonamento(LocalDate.parse("2011-11-12"), Periodicita.MENSILE);
		Autobus autobus1 = new Autobus(50, true);
		Tram tram1 = new Tram(70, true);
		Tratta tratta1 = new Tratta("Roma Termini", "Colosseo", 12.5, LocalDate.parse("2012-11-12"),
				LocalDate.parse("2012-11-12"));

		ut.salvaUtente(utente1);
		te.salvaTessera(tessera1);
		pe.salvaPuntoEmissione(rivenditore);
		pe.salvaPuntoEmissione(distributore);
		tv.salvaTitoloDiViaggio(abbonamento1);
		tv.salvaTitoloDiViaggio(biglietto1);
		me.salvaMezzo(tram1);
		me.salvaMezzo(autobus1);
		tr.salvaTratta(tratta1);

		em.close();
		emf.close();
	}
}
