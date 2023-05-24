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
import lombok.ToString;

@Entity
@Table(name = "tessere")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Tessera {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private LocalDate dataEmissione;
//	@OneToOne(mappedBy = "tessera")
//	private Utente utente;

	public LocalDate dataScadenzaFun(LocalDate dataEmissione) {

		LocalDate dataScadenza = dataEmissione.plusYears(1);
		return dataScadenza;

	}

	public Tessera(LocalDate dataEmissione) {
		this.dataEmissione = dataEmissione;
	}
}