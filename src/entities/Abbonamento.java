package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import enums.Periodicita;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "abbonamenti")
@NoArgsConstructor
@Setter
@Getter
@ToString
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Abbonamento extends TitoloDiViaggio {
	@Enumerated(EnumType.STRING)
	private Periodicita periodicita;

	public Abbonamento(LocalDate dataEmissione, Periodicita periodicita) {
		super(dataEmissione);
		this.periodicita = periodicita;
	}
}
