package entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "punto_di_emissione")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_di_rivenditore", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Setter
@Getter
public abstract class PuntoDiEmissione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "biglietti_emessi")
	private int bigliettiEmessi;
	@Column(name = "abbonamenti_emessi")
	private int abbonamentiEmessi;
	String luogo;

	@OneToMany(mappedBy = "puntoDiEmissione", cascade = CascadeType.ALL)
	@OrderBy("puntoDiEmissione DESC")
	private Set<TitoloDiViaggio> titoliDiViaggio;

	public PuntoDiEmissione(int bigliettiEmessi, int abbonamentiEmessi, String luogo) {
		this.bigliettiEmessi = bigliettiEmessi;
		this.abbonamentiEmessi = abbonamentiEmessi;
		this.luogo = luogo;
	}

}
