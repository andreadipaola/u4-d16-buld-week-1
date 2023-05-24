package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "autobus")
@DiscriminatorValue(value = "Autobus")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Autobus extends Mezzo {
	// int capienza = 50;

	public Autobus(int capienza, boolean inServizio) {
		super(capienza, inServizio);
	}
}
