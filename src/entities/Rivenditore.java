package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rivenditori")
@DiscriminatorValue(value = "Rivenditore")
@NoArgsConstructor
@Setter
@Getter
public class Rivenditore extends PuntoDiEmissione {

	public Rivenditore(int bigliettiEmessi, String luogo, int counterBiglietti, int abbonamentiEmessi) {
		super(bigliettiEmessi, abbonamentiEmessi, luogo);
	}
}
