package entities;

import java.util.UUID;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "punto_di_emissione")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_di_rivenditore", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
public abstract class PuntoDiEmissione {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "punto_emissione")
//	@SequenceGenerator(name = "punto_emissione", sequenceName = "punto_emissione", allocationSize = 1)
	private UUID id;
	private boolean inAttivita;

	public PuntoDiEmissione(boolean inAttivita) {
		this.inAttivita = inAttivita;
	}

}
