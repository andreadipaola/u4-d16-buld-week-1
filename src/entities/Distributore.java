package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@DiscriminatorValue(value = "distributore")
public class Distributore extends PuntoDiEmissione {
	private boolean inAttivita;

	public Distributore(boolean inAttivita) {
		this.inAttivita = inAttivita;
	}
}
