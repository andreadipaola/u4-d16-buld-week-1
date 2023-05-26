package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tram")
@DiscriminatorValue(value = "Tram")
@NoArgsConstructor
@Setter
@Getter
public class Tram extends Mezzo {

	public Tram(String numero, boolean inServizio, Tratta tratta) {
		super(numero, inServizio, tratta, 70);
	}
}
