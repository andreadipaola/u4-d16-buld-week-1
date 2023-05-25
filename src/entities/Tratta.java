package entities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Column(name = "tempo_medio_percorrenza")
	private double tempoMedioPercorrenza;
	@OneToMany(mappedBy = "tratta", fetch = FetchType.EAGER)
	private Set<Mezzo> mezzi;

	public Tratta(String partenza, String capolinea, double tempoMedioPercorrenza) {
		this.partenza = partenza;
		this.capolinea = capolinea;
		this.tempoMedioPercorrenza = tempoMedioPercorrenza;
	}
}
