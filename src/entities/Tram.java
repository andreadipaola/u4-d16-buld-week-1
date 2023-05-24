package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "Tram")
@Setter
@Getter
@NoArgsConstructor
public class Tram extends Mezzo {
//	int capienza = 100;

	public Tram(int capienza, boolean inServizio) {
		super(capienza, inServizio);
	}
}
