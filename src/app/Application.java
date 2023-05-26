package app;

import java.time.LocalDate;

import dao.MezzoDao;
import dao.TitoloDiViaggioDao;
import entities.Abbonamento;
import utils.GestioneTrasporti;
import utils.JpaUtil;
import utils.Menu;

public class Application {

	public static void main(String[] args) {
		JpaUtil.initializeEntityManagerFactory();
		GestioneTrasporti.creaReteTrasporto();
		Menu.saveDistributore();
		Menu.saveRivenditore();
		Menu.runApp();

		TitoloDiViaggioDao tvd = new TitoloDiViaggioDao();
		tvd.contaTitoliEmessi(Abbonamento.class, LocalDate.now(), LocalDate.now().plusDays(30));
//		PuntoDiEmissioneDao.getTitoliEmessi(1);
		MezzoDao md = new MezzoDao();
		md.contaBigliettiTimbrati(LocalDate.now(), LocalDate.now().plusDays(30));

	}
}
