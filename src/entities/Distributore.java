package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("Distributore")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Distributore extends PuntoDiEmissione {
	@Column(name = "in_servizio")
	private boolean inServizio;

	public Distributore(int bigliettiEmessi, String luogo, int counterBiglietti, int abbonamentiEmessi,
			boolean inServizio) {
		super(bigliettiEmessi, abbonamentiEmessi);
		this.inServizio = inServizio;
	}
}
