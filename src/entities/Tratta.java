package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tratte")
@NoArgsConstructor
@Setter
@Getter
public class Tratta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String partenza;
	private String capolinea;
	private double tempoMedioDiPercorrenza;
	private LocalDate oraDiPartenza;
	private LocalDate oraDiArrivoEffettiva;
	@ManyToMany(mappedBy = "tratte", fetch = FetchType.EAGER)
	private Set<Mezzo> mezzi;

	public Tratta(String partenza, String capolinea, double tempoMedioDiPercorrenza, LocalDate oraDiPartenza,
			LocalDate oraDiArrivoEffettiva) {
		this.partenza = partenza;
		this.capolinea = capolinea;
		this.tempoMedioDiPercorrenza = tempoMedioDiPercorrenza;
		this.oraDiPartenza = oraDiPartenza;
		this.oraDiArrivoEffettiva = oraDiArrivoEffettiva;
	}
}
