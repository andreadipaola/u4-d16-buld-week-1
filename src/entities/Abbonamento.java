package entities;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import enums.Periodicita;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "abbonamenti")
@DiscriminatorValue(value = "Abbonamento")
@NoArgsConstructor
@Setter
@Getter
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@NamedQuery(name = "controllaValiditaAbbonamento", query = "UPDATE Abbonamento SET validita = false WHERE dataScadenza < CURRENT_DATE")
public class Abbonamento extends TitoloDiViaggio {

	private boolean validita = true;
	@Enumerated(EnumType.STRING)
	private Periodicita periodicita;
	@OneToOne(mappedBy = "abbonamento")
	private Tessera tessera;

	public Abbonamento(Tessera tessera, LocalDate dataEmissione, LocalDate dataScadenza, boolean validita,
			Periodicita periodicita) {
		super(dataEmissione, dataScadenza);
		this.tessera = tessera;
		this.validita = validita;
		this.periodicita = periodicita;
	}
}
