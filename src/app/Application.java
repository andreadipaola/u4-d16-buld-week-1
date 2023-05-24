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

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		UtenteDao ut = new UtenteDao(em);
		TesseraDao te = new TesseraDao(em);
		PuntoDiEmissioneDao pe = new PuntoDiEmissioneDao(em);
		TitoloDiViaggioDao tv = new TitoloDiViaggioDao(em);
		MezzoDao me = new MezzoDao(em);
		TrattaDao tr = new TrattaDao(em);

		Utente utente1 = new Utente("idl15gsdki095t4h0", "Gianluca", "Praticò", 29);
		Tessera tessera1 = new Tessera(LocalDate.parse("2015-11-12"));
		Rivenditore rivenditore = new Rivenditore(false);
		Distributore distributore = new Distributore(true);
		Autobus autobus1 = new Autobus(50, true);
		Tram tram1 = new Tram(70, true);
		Tratta tratta1 = new Tratta("Roma Termini", "Colosseo", 12.5, LocalDate.parse("2012-11-12"),
				LocalDate.parse("2012-11-12"));

		utente1.setTessera(tessera1);
		PuntoDiEmissione found = pe.findById("1c1dd74f-6321-4e50-9960-6ecaf8bfba2f");
		if (found != null) {
			Biglietto biglietto1 = new Biglietto(found, LocalDate.parse("2012-11-12"), true,
					LocalDate.parse("2012-12-11"));
			Abbonamento abbonamento1 = new Abbonamento(found, LocalDate.parse("2011-11-12"), Periodicita.MENSILE);

//			BlogPost java = new BlogPost("JPA", "bellissimo", found);
			// bd.saveBlog(java);
			tv.salvaTitoloDiViaggio(biglietto1);
			tv.salvaTitoloDiViaggio(abbonamento1);

		}
//		found.getBlogPosts().stream().forEach(b -> log.info(b.toString()));
		found.getTitoliDiViaggio().stream().forEach(t -> log.info(t.toString()));

//		ut.salvaUtente(utente1);
//		te.salvaTessera(tessera1);
//		pe.salvaPuntoEmissione(rivenditore);
//		pe.salvaPuntoEmissione(distributore);
//		me.salvaMezzo(tram1);
//		me.salvaMezzo(autobus1);
//		tr.salvaTratta(tratta1);

		em.close();
		emf.close();
	}
}
