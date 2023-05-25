package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "Tram")
@NoArgsConstructor
@Setter
@Getter
public class Tram extends Mezzo {

	public Tram(boolean inServizio) {
		super(50, inServizio);
	}
}
