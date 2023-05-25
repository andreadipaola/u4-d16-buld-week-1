package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "Autobus")
@Setter
@Getter
//@ToString
@NoArgsConstructor
public class Autobus extends Mezzo {

	public Autobus(boolean inServizio) {
		super(54, inServizio);
	}
}
