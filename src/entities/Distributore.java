package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "distributore")
public class Distributore extends PuntoDiEmissione {
	
	private boolean inAttivita;
	

}
