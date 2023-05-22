package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "rivenditore")
public class Rivenditore extends PuntoDiEmissione {
	

}
