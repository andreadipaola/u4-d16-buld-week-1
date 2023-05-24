package entities;

import java.time.LocalDate;
import java.util.UUID;

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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "titoli_di_viaggio")
@NoArgsConstructor
@Getter
@Setter
public abstract class TitoloDiViaggio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private LocalDate dataEmissione;
	@ManyToOne
//	@JoinColumn(name = "punto_di_emissione_id")
	@JoinColumn(name = "punto_di_emissione_id", referencedColumnName = "id", nullable = false)
	private PuntoDiEmissione puntoDiEmissione;

	public TitoloDiViaggio(PuntoDiEmissione puntoDiEmissione, LocalDate dataEmissione) {
		this.puntoDiEmissione = puntoDiEmissione;
		this.dataEmissione = dataEmissione;
	}
}
