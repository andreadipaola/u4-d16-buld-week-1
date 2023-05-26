package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "autobus")
@DiscriminatorValue(value = "Autobus")
@Setter
@Getter
@NoArgsConstructor
public class Autobus extends Mezzo {

	public Autobus(String numero, boolean inServizio, Tratta tratta) {
		super(numero, inServizio, tratta, 54);
	}
}
