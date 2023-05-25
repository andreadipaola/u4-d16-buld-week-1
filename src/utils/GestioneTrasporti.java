package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.MezzoDao;
import dao.TrattaDao;
import entities.Autobus;
import entities.Tram;
import entities.Tratta;

public class GestioneTrasporti {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void creaReteTrasporto() {
		EntityManager em = emf.createEntityManager();
		MezzoDao md = new MezzoDao(em);
		TrattaDao td = new TrattaDao(em);

		Tratta tr1 = new Tratta("Piazza Mancini", "Piazzale Flaminio", 30);
		Tratta tr2 = new Tratta("Piazza Thorwaldsen", "Trastevere", 45);
		Tratta tr3 = new Tratta("Piazza Venezia", "Trastevere", 15);

		td.salvaTratta(tr1);
		td.salvaTratta(tr2);
		td.salvaTratta(tr3);

		Autobus a1 = new Autobus("3", true, tr1);
		Autobus a2 = new Autobus("11", true, tr2);
		Autobus a3 = new Autobus("17", false, tr3);
		md.salvaMezzo(a1);
		md.salvaMezzo(a2);
		md.salvaMezzo(a3);

		Tram t1 = new Tram("2", true, tr3);
		Tram t2 = new Tram("4", true, tr1);
		Tram t3 = new Tram("8", true, tr2);
		md.salvaMezzo(t1);
		md.salvaMezzo(t2);
		md.salvaMezzo(t3);
	}
}
