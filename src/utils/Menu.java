package utils;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import dao.MezzoDao;
import dao.PuntoDiEmissioneDao;
import dao.TesseraDao;
import dao.TitoloDiViaggioDao;
import dao.TrattaDao;
import dao.UtenteDao;
import entities.Abbonamento;
import entities.Biglietto;
import entities.Distributore;
import entities.Mezzo;
import entities.Rivenditore;
import entities.Tessera;
import entities.Utente;
import enums.Periodicita;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Menu {

	static Scanner sc = new Scanner(System.in);
	static int selezione;
	static int selectMezzo;
	static long nTessera;
	static long mezzoId;
	static Mezzo v;

	public static void runApp() {
		boolean attivo;

		do {
			TesseraDao tesseraDao = new TesseraDao();
			tesseraDao.controllaValiditaTessera();

			MezzoDao mezzoDao = new MezzoDao();
			mezzoDao.settaMezzoInManutenzione();
			mezzoDao.settaMezzoInServizio();

			TitoloDiViaggioDao titoloDao = new TitoloDiViaggioDao();
			titoloDao.controllaValiditaAbbonamento();

			try {
				System.out.println("-------------------------------------------");
				System.out.println("Seleziona il tuo punto di partenza: ");
				System.out.println(" 1 - Piazza Mancini");
				System.out.println(" 2 - Piazza Thorwaldsen");
				System.out.println(" 3 - Piazza Venezia");
				selezione = sc.nextInt();

				switch (selezione) {
				case (1):
					System.out.println("Benvenuto dal punto vendita di Piazza Mancini");
					break;
				case (2):
					System.out.println("Benvenuto dal punto vendita di Piazza Thorwaldsen");
					break;
				case (3):
					System.out.println("Benvenuto dal punto vendita di Piazza Venezia");
					break;
				default:
					log.error("Valore non presente nella lista!");
					System.exit(0);
					break;
				}

				System.out.println(" 1  Gestione tessera");
				System.out.println(" 2  Acquista titolo di viaggio");
				System.out.println(" 3  Scegli un mezzo");
				System.out.println("Cosa scegli di fare?");

				int selezione2 = sc.nextInt();
				sc.nextLine();

				switch (selezione2) {
				case (1):
					gestioneTessera();
					break;
				case (2):
					acquistaTitoloDiViaggio();
					break;
				case (3):
					scegliMezzo();
					break;
				default:
					log.error("Valore non presente nella lista!");
					System.exit(0);
					break;
				}
			} catch (InputMismatchException ex) {
				log.error("Inserisci un valore corretto");
			}

			System.out.println("-------------------------------------------");
			System.out.println("Digita 'S' per continuare o qualunque altro carattere per uscire");
			sc.nextLine();
			String input = sc.nextLine();
			attivo = input.equalsIgnoreCase("S");
		} while (attivo);
	}

	// METODO CREAZIONE DISTRIBUTORE
	public static Distributore saveDistributore() {
		Distributore d = new Distributore();
		d.setInServizio(true);
		d.setLuogo("Piazza Mancini");

		PuntoDiEmissioneDao ped = new PuntoDiEmissioneDao();
		ped.salvaPuntoEmissione(d);
		return d;
	}

	// METODO CREAZIONE RIVENDITORE
	public static Rivenditore saveRivenditore() {
		Rivenditore r = new Rivenditore();
		r.setLuogo("Piazza Venezia");

		PuntoDiEmissioneDao ped = new PuntoDiEmissioneDao();
		ped.salvaPuntoEmissione(r);
		return r;
	}

	// MEDOTI DI GESTIONE TESSERA (ACQUISTO, VERIFICA DATI E RINNOVO)
	public static void gestioneTessera() {
		System.out.println("GESTIONE TESSERA");
		System.out.println(" 1 - Crea tessera");
		System.out.println(" 2 - Verifica dati tessera");
		System.out.println(" 3 - Rinnova tessera");
		System.out.println("Cosa scegli di fare?");
		int selezione4 = sc.nextInt();

		switch (selezione4) {
		case (1):
			acquistaTessera();
			break;
		case (2):
			verificaTessera();
			break;
		case (3):
			rinnovaTessera();
			break;
		default:
			log.error("Valore errato. Inserisci un valore corretto");
			break;
		}
	}

	public static void acquistaTessera() {
		System.out.println("Inserisci il tuo nome: ");
		String nome = sc.next();

		System.out.println("Inserisci il tuo cognome: ");
		String cognome = sc.next();

		System.out.println("Inserisci il tuo recapito telefonico: ");
		String telefono = sc.next();

		salvaTessera(LocalDate.now(), LocalDate.now().plusYears(1));
	}

	public static Tessera salvaTessera(LocalDate dataEmissione, LocalDate dataScadenza) {
		Tessera t = new Tessera(dataEmissione, dataScadenza);

		TesseraDao td = new TesseraDao();
		td.salvaTessera(t);
		return t;
	}

	public static void verificaTessera() {
		System.out.println("Inserisci il numero di tessera:");
		long numeroTessera = sc.nextLong();
		sc.nextLine(); // Consuma il new line dopo nextLong()

		TesseraDao td = new TesseraDao();
		td.recuperaDatiTessera(numeroTessera);

	}

	public static void rinnovaTessera() {
		System.out.println("Inserisci numero tessera da rinnovare:");
		long numeroTessera = sc.nextLong();
		sc.nextLine(); // Consuma il new line dopo nextLong()

		TesseraDao td = new TesseraDao();
		td.rinnovaTessera(numeroTessera);
	}

	// METODO GESTIONE TITOLI DI VIAGGIO
	public static void acquistaTitoloDiViaggio() {
		System.out.println("1 - Biglietto ordinario");
		System.out.println("2 - Abbonamento");
		System.out.println("Scegli cosa comprare");
		int selezione3 = sc.nextInt();

		switch (selezione3) {
		case (1):
			log.error("hai selezioanto: " + selezione3);
			saveBiglietto();
			break;
		case (2):
			log.error("hai selezioanto: " + selezione3);
			acquistaAbbonamento();
			break;
		default:
			log.error("Valore non presente nella lista");
			break;
		}
	}

	// METODI GESTIONE UTENTE (CREAZIONE, MODIFICA, RECUPERO DA ID)
	public static Utente saveUtente() {
		Utente u = new Utente("Mario", "Rossi", "333-123456");

		UtenteDao utenteDao = new UtenteDao();
		utenteDao.salvaUtente(u);
		return u;
	}

	public static void aggiornaAbbonamento(long numeroTessera, Abbonamento abbonamento) {
		TesseraDao tesseraDao = new TesseraDao();
		tesseraDao.aggiornaValiditaAbbonamento(numeroTessera, abbonamento);
	}

	public static Tessera trovaTessera(long id) {
		TesseraDao td = new TesseraDao();
		return td.trovaTessera(id);
	}

	// METODO CREAZIONE BIGLIETTO
	public static void saveBiglietto() {
		Biglietto b = new Biglietto();
		b.setDataEmissione(LocalDate.now());
		b.setDataScadenza(LocalDate.now().plusDays(1));

		TitoloDiViaggioDao tvd = new TitoloDiViaggioDao();
		tvd.salvaTitoloDiViaggio(b);

		PuntoDiEmissioneDao ped = new PuntoDiEmissioneDao();
		ped.bigliettiEmessi(selezione);
	}

//	 METODI ABBONAMENTO (CREAZIONE, VERIFICA ABBONAMENTO ATTIVO)
	public static void acquistaAbbonamento() {
		System.out.println("Inserisci il tuo numero tessera");
		long nTessera = sc.nextLong();
		sc.nextLine();

		TitoloDiViaggioDao tvd = new TitoloDiViaggioDao();
		tvd.controllaValiditaTessera(nTessera);
		creaAbbonamento();
	}

	public static void saveAbbonamento(Tessera tessera, LocalDate dataEmissione, LocalDate dataScadenza,
			boolean validita, Periodicita periodicita) {
		Abbonamento a = new Abbonamento(tessera, dataEmissione, dataScadenza, validita, periodicita);

		TitoloDiViaggioDao tvd = new TitoloDiViaggioDao();
		tvd.salvaTitoloDiViaggio(a);

		aggiornaAbbonamento(nTessera, a);
		PuntoDiEmissioneDao ped = new PuntoDiEmissioneDao();
		ped.abbonamentiEmessi(selezione);
	}

	public static void creaAbbonamento() {
		EntityManager em = JpaUtil.getEntityManager();

		Tessera te = em.find(Tessera.class, nTessera);
		if (te != null) {
			if (te.getAbbonamento() != null) {
				boolean validita = te.getAbbonamento().isValidita();
				if (validita == true) {
					System.out.println("Hai già un abbonamento attivo, impossibile crearne un altro");
					System.exit(0);
				} else {
					long id = te.getAbbonamento().getId();

					System.out.println("Rinnova Abbonamento: ");
					System.out.println(" 1 - Settimanale");
					System.out.println(" 2 - Mensile");
					int periodo = sc.nextInt();

					switch (periodo) {
					case (1):
						EntityTransaction t = em.getTransaction();
						Abbonamento a = em.find(Abbonamento.class, id);
						a.setDataEmissione(LocalDate.now());
						a.setDataScadenza(LocalDate.now().plusWeeks(1));
						a.setValidita(true);
						a.setPeriodicita(Periodicita.SETTIMANALE);

						t.begin();
						em.persist(a);
						t.commit();

						aggiornaAbbonamento(nTessera, a);
						break;
					case (2):
						Abbonamento ab = em.find(Abbonamento.class, id);
						ab.setDataEmissione(LocalDate.now());
						ab.setDataScadenza(LocalDate.now().plusMonths(1));
						ab.setValidita(true);
						ab.setPeriodicita(Periodicita.MENSILE);

						EntityTransaction t2 = em.getTransaction();
						t2.begin();
						em.persist(ab);
						t2.commit();

						aggiornaAbbonamento(nTessera, ab);
						break;
					default:
						log.error("Valore non presente nella lista!");
						break;
					}
					System.out.println("Abbonamento rinnovata!");
				}
			} else if (te.getAbbonamento() == null) {
				System.out.println(" 1 - Settimanale");
				System.out.println(" 2 - Mensile");
				int periodo = sc.nextInt();

				switch (periodo) {
				case (1):
					saveAbbonamento(trovaTessera(nTessera), LocalDate.now(), LocalDate.now().plusWeeks(1), true,
							Periodicita.SETTIMANALE);
					break;
				case (2):
					saveAbbonamento(trovaTessera(nTessera), LocalDate.now(), LocalDate.now().plusMonths(1), true,
							Periodicita.MENSILE);
					break;
				default:
					log.error("Valore non presente nella lista!");
					break;
				}
			}
		} else {
			log.error("C'è stato un errore nel reperimento della tessera!");
		}
	}

	// METODO GESTIONE MEZZI
	public static void scegliMezzo() {
		EntityManager em = JpaUtil.getEntityManager();
		if (selezione == 1) {
			String jpql = "SELECT m FROM Mezzo m JOIN m.tratta t WHERE t.partenza = :partenza AND m.inServizio = true";

			TypedQuery<Mezzo> query = em.createQuery(jpql, Mezzo.class);
			query.setParameter("partenza", "Piazza Mancini");

			List<Mezzo> mezzi = query.getResultList();

			System.out.println("Mezzi in servizio:");

			for (int i = 0; i < mezzi.size(); i++) {
				v = mezzi.get(i);
				mezzoId = v.getId();
				System.out.println(mezzoId + " - " + v);
			}

			System.out.println("Scegli su che mezzo viaggiare");
			selectMezzo = sc.nextInt();
			boolean mezzoEsistente = true;

			for (int i = 0; i < mezzi.size(); i++) {
				v = mezzi.get(i);
				mezzoId = v.getId();

				if (selectMezzo == mezzoId) {
					mezzoEsistente = true;
					break;
				}
			}

			if (mezzoEsistente) {
				Mezzo m = em.find(Mezzo.class, (long) selectMezzo);
				System.out.println("Sei salito su " + m.getClass().getSimpleName().toUpperCase() + " " + m.getNumero());
				convalidaTitolo();
				TrattaDao td = new TrattaDao();
				td.calcolaTempoCorsaEffettivo(selectMezzo);
			} else {
				System.out.println("Mezzo non esistente");
			}
		} else if (selezione == 2) {
			String jpql = "SELECT m FROM Mezzo m JOIN m.tratta t WHERE t.partenza = :partenza  AND m.inServizio = true";

			TypedQuery<Mezzo> query = em.createQuery(jpql, Mezzo.class);
			query.setParameter("partenza", "Piazza venezia");

			List<Mezzo> mezzi = query.getResultList();

			System.out.println("Mezzi in servizio: ");

			for (int i = 0; i < mezzi.size(); i++) {
				v = mezzi.get(i);
				mezzoId = v.getId();
				System.out.println(mezzoId + " - " + v);
			}

			System.out.println("Scegli su che mezzo viaggiare");
			selectMezzo = sc.nextInt();
			boolean mezzoEsistente = false;

			for (int i = 0; i < mezzi.size(); i++) {
				v = mezzi.get(i);
				mezzoId = v.getId();

				if (selectMezzo == mezzoId) {
					mezzoEsistente = true;
					break;
				}
			}

			if (mezzoEsistente) {
				Mezzo m = em.find(Mezzo.class, (long) selectMezzo);
				System.out.println("Sei salito su " + m.getClass().getSimpleName().toUpperCase() + " " + m.getNumero());
				convalidaTitolo();
				TrattaDao td = new TrattaDao();
				td.calcolaTempoCorsaEffettivo(selectMezzo);
			} else {
				System.out.println("mezzo non esistente.");
			}
		}
	}

	// METODO PER OBLITERARE IL BIGLIETTO O CONTROLLARE L'ABBONAMENTO
	public static void convalidaTitolo() {
		System.out.println("Inserisci il codice biglietto/abbonamento");
		int select = sc.nextInt();

		TitoloDiViaggioDao tvd = new TitoloDiViaggioDao();
		tvd.controllaTitololDiViaggio(select, selectMezzo);
	}

}