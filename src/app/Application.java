package app;

import java.time.LocalDate;

import dao.MezzoDao;
import dao.TitoloDiViaggioDao;
import entities.Abbonamento;
import utils.GestioneTrasporti;
import utils.Menu;

public class Application {

	public static void main(String[] args) {
		GestioneTrasporti.creaReteTrasporto();
		Menu.saveDistributore();
		Menu.saveRivenditore();
		Menu.runApp();

		TitoloDiViaggioDao.contaTitoliEmessi(Abbonamento.class, LocalDate.now(), LocalDate.now().plusDays(30));
//		PuntoDiEmissioneDao.getTitoliEmessi(1);
		MezzoDao.contaBigliettiTimbrati(LocalDate.now(), LocalDate.now().plusDays(30));

	}
}
