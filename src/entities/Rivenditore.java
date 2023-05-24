package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "Rivenditori")
@Setter
@Getter
//@ToString
@NoArgsConstructor
public class Rivenditore extends PuntoDiEmissione {
	public Rivenditore(boolean inAttivita) {
		super(inAttivita);
	}
}
