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
import lombok.ToString;

@Entity
@Table(name = "biglietti")
@Setter
@Getter
@ToString
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@NoArgsConstructor
public class Biglietto extends TitoloDiViaggio {
	private boolean vidimato;
	private LocalDate dataVidimazione;
	@ManyToOne
	@JoinColumn(name = "mezzo_id")
	private Mezzo mezzo;

	public Biglietto(LocalDate dataEmissione, boolean vidimato, LocalDate dataVidimazione) {
		super(dataEmissione);
		this.vidimato = vidimato;
		this.dataVidimazione = dataVidimazione;
	}
}
