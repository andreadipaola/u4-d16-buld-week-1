package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "distributori")
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
		super(bigliettiEmessi, abbonamentiEmessi, luogo);
		this.inServizio = inServizio;
	}
}
