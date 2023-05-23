package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity

@Table(name = "tessere_")
public class Tessera {
	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private LocalDate dataEmissione;
	@OneToOne(mappedBy = "tessera")
	private Utente utente;

	public LocalDate dataScadenzaFun(LocalDate dataEmissione) {

		LocalDate dataScadenza = dataEmissione.plusYears(1);
		return dataScadenza;

	}
}
