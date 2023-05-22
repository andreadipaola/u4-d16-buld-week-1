package entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_di_rivenditore")
@AllArgsConstructor
@NoArgsConstructor
public abstract class PuntoDiEmissione {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	

}
