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
@DiscriminatorValue(value = "Distributori")
public class Distributore extends PuntoDiEmissione {
	public Distributore(boolean inAttivita) {
		super(inAttivita);
	}
}
