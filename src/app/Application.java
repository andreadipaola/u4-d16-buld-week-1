package app;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

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
import entities.PuntoDiEmissione;
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
	static boolean inServizio = true;

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		// DAO
		UtenteDao ut = new UtenteDao(em);
		TesseraDao te = new TesseraDao(em);
		PuntoDiEmissioneDao pe = new PuntoDiEmissioneDao(em);
		TitoloDiViaggioDao tv = new TitoloDiViaggioDao(em);
		MezzoDao me = new MezzoDao(em);
		TrattaDao tr = new TrattaDao(em);

		// ISTANZE DI CLASSI (OGGETTI)
		Utente utente1 = new Utente("Gianluca", "Praticò", 29);
		ut.salvaUtente(utente1);

		Tessera tessera1 = new Tessera(LocalDate.parse("1998-11-12"));
		te.salvaTessera(tessera1);
		te.verificaValidita();

		utente1.setTessera(tessera1);

		Rivenditore rivenditore1 = new Rivenditore(false);
		pe.salvaPuntoEmissione(rivenditore1);

		Distributore distributore1 = new Distributore(true);
		pe.salvaPuntoEmissione(distributore1);

		Autobus autobus1 = new Autobus(inServizio);
		me.salvaMezzo(autobus1);

		Biglietto biglietto1 = new Biglietto(rivenditore1, LocalDate.parse("2012-11-12"), true,
				LocalDate.parse("2012-12-11"), autobus1);
		tv.salvaTitoloDiViaggio(biglietto1);

		Abbonamento abbonamento1 = new Abbonamento(rivenditore1, LocalDate.parse("2011-11-12"), Periodicita.MENSILE,
				tessera1);
		tv.salvaTitoloDiViaggio(abbonamento1);

		// *********************************************
		Tram tram1 = new Tram(inServizio);
		me.salvaMezzo(tram1);
		int capienza1 = tram1.getCapienza();
		boolean isTramInServizio1 = tram1.isInServizio();
		log.info("Il valore di capienza è: {}", capienza1);
		log.info("Il valore di isTramInServizio1 è: {}", isTramInServizio1);

		Autobus autobus2 = new Autobus(inServizio);
		me.salvaMezzo(autobus2);
		int capienza2 = autobus2.getCapienza();
		boolean isTramInServizio2 = autobus2.isInServizio();
		log.info("Il valore di capienza è: {}", capienza2);
		log.info("Il valore di isTramInServizio2 è: {}", isTramInServizio2);
		// *********************************************

		Tratta tratta1 = new Tratta("Roma Termini", "Colosseo", 12.5, LocalDate.parse("2012-11-12"),
				LocalDate.parse("2012-11-12"));
		tr.salvaTratta(tratta1);
		Tratta tratta2 = new Tratta("Aeroporto di Fiumicino", "Piazza di Spagna", 25.5, LocalDate.parse("1988-10-12"),
				LocalDate.parse("2021-08-12"));
		tr.salvaTratta(tratta2);
		Tratta tratta3 = new Tratta("Stazione Tiburtina", "Fori Romani", 13, LocalDate.parse("2012-11-12"),
				LocalDate.parse("2012-11-12"));
		tr.salvaTratta(tratta3);

		tram1.setTratte(new HashSet<>(Arrays.asList(tratta1, tratta2, tratta3)));
		tratta1.setMezzi(new HashSet<>(Arrays.asList(tram1, autobus2)));

		PuntoDiEmissione pfound = pe.findById("498b75b0-c55f-4a0d-a57d-36a3e3f4720a");
		// Tessera tfound = te.findById("f880fe9e-593d-4cb0-8739-0c6634306469");

		UUID id = UUID.fromString("498b75b0-c55f-4a0d-a57d-36a3e3f4720a");
		int abbonamentiEmessi = pe.abbonamentiEmessi(id);
		log.info("gli abbonamenti emessi sono: {}", abbonamentiEmessi);
		int bigliettiEmessi = pe.bigliettiEmessi(id);
		log.info("i biglietti emessi sono: {}", bigliettiEmessi);

		tram1.getTratte().stream().forEach(t -> log.info(t.toString()));
		log.info("------------------------------------------------------");
		tratta1.getMezzi().stream().forEach(m -> log.info(m.toString()));
		UUID idTessera = UUID.fromString("06620da0-21d3-4577-946f-8a52cfa178a9");
		te.aggiornaValidita(idTessera);
//		if (pfound != null) {
//		Biglietto biglietto1 = new Biglietto(pfound, LocalDate.parse("2012-11-12"), true, LocalDate.parse("2012-12-11"), autobus1);
//			
//			Abbonamento abbonamento1 = new Abbonamento(pfound, LocalDate.parse("2011-11-12"), Periodicita.MENSILE,
//					tfound);
//
//			tv.salvaTitoloDiViaggio(biglietto1);
//
//			tv.salvaTitoloDiViaggio(abbonamento1);
//		}

//		pfound.getTitoliDiViaggio().stream().forEach(t -> log.info(t.toString()));
//		tfound.getAbbonamenti().stream().forEach(t -> log.info(t.toString()));

		em.close();
		emf.close();
	}
}
