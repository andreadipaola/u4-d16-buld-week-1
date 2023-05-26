package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "biglietti")
//@PrimaryKeyJoinColumn(referencedColumnName = "id")
@NoArgsConstructor
@Setter
@Getter

public class Biglietto extends TitoloDiViaggio {
	private boolean timbrato = false;
	private LocalDate dataTimbratura;
	@OneToOne
	@JoinColumn(name = "mezzo_id", referencedColumnName = "id")
	private Mezzo mezzo;

	public Biglietto(LocalDate dataEmissione, LocalDate dataScadenza, boolean timbrato, LocalDate dataTimbratura) {
		super(dataEmissione, dataScadenza);
		this.timbrato = timbrato;
		this.dataTimbratura = dataTimbratura;
	}
}
