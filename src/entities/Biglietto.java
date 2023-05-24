package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "biglietti")
@Setter
@Getter
//@ToString
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@NoArgsConstructor

public class Biglietto extends TitoloDiViaggio {
	private boolean vidimato;
	private LocalDate dataVidimazione;
	@ManyToOne
	@JoinColumn(name = "mezzo_id", referencedColumnName = "id", nullable = false)
	private Mezzo mezzo;

	public Biglietto(PuntoDiEmissione puntoDiEmissione, LocalDate dataEmissione, boolean vidimato,
			LocalDate dataVidimazione, Mezzo mezzo) {
		super(puntoDiEmissione, dataEmissione);
		this.vidimato = vidimato;
		this.dataVidimazione = dataVidimazione;
		this.mezzo = mezzo;
	}

	@Override
	public String toString() {
		return "Biglietto [vidimato=" + vidimato + ", dataVidimazione=" + dataVidimazione + ", mezzo=" + mezzo + "]";
	}

}
