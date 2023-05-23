package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "tratte")
public class Tratta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String zonaDiPartenza;
	private String capolinea;
	private double tempoMedioDiPercorrenza;
	private LocalDate oraDiPartenza;
	private LocalDate oraDiArrivoEffettiva;

	public Tratta(String zonaDiPartenza, String capolinea, double tempoMedioDiPercorrenza, LocalDate oraDiPartenza,
			LocalDate oraDiArrivoEffettiva) {
		this.zonaDiPartenza = zonaDiPartenza;
		this.capolinea = capolinea;
		this.tempoMedioDiPercorrenza = tempoMedioDiPercorrenza;
		this.oraDiPartenza = oraDiPartenza;
		this.oraDiArrivoEffettiva = oraDiArrivoEffettiva;
	}
}
