package entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "titoli_di_viaggio")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_di_titolo", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Getter
@Setter
public abstract class TitoloDiViaggio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "data_emissione")
	private LocalDate dataEmissione;
	@Column(name = "data_scadenza")
	private LocalDate dataScadenza;
	@ManyToOne
	@JoinColumn(name = "punto_di_emissione_id", referencedColumnName = "id", nullable = false)
	private PuntoDiEmissione puntoDiEmissione;

	public TitoloDiViaggio(LocalDate dataEmissione, LocalDate dataScadenza) {
		this.dataEmissione = dataEmissione;
		this.dataScadenza = dataScadenza;
	}
}
