package entities;

import java.util.UUID;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_di_mezzo")
@NoArgsConstructor
//@Setter
//@Getter
public abstract class Mezzo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private int capienza;
	private boolean inServizio;

	public Mezzo(int capienza, boolean inServizio) {
		this.capienza = capienza;
		this.inServizio = inServizio;
	}

}
