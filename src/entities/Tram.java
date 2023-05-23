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
@DiscriminatorValue(value = "Tram")
public class Tram extends Mezzo {
	// int capienza = 100;

	public Tram(int capienza, boolean inServizio) {
		super(capienza, inServizio);
	}
}
