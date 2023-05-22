package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue(value = "rivenditore")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Rivenditore extends PuntoDiEmissione {
	public Rivenditore (long id) {
		super (id);
	}
	
	

}
