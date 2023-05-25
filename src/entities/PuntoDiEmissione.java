package entities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "punto_di_emissione")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_di_rivenditore", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Setter
@Getter
@NamedQuery(name = "controllo_servizio", query = "UPDATE PuntoDiEmissione SET inAttivita = false WHERE dataScadenza < CURRENT_DATE")
public abstract class PuntoDiEmissione {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "punto_emissione")
//	@SequenceGenerator(name = "punto_emissione", sequenceName = "punto_emissione", allocationSize = 1)
	private UUID id;
	private boolean inAttivita;
	private int bigliettiEmessi;
	private int abbonamentiEmessi;

	@OneToMany(mappedBy = "puntoDiEmissione", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("puntoDiEmissione DESC")
	private Set<TitoloDiViaggio> titoliDiViaggio;

	public PuntoDiEmissione(boolean inAttivita) {
		this.inAttivita = inAttivita;
		this.bigliettiEmessi = 0;
		this.abbonamentiEmessi = 0;
	}

}
