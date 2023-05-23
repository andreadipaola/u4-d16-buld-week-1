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
@DiscriminatorValue(value = "Autobus")
public class Autobus extends Mezzo {
	// int capienza = 50;

	public Autobus(int capienza, boolean inServizio) {
		super(capienza, inServizio);
	}
}
