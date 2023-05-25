package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tessere")
@NoArgsConstructor
@Setter
@Getter
//@ToString
@NamedQuery(name = "controllo_validita_tessera", query = "UPDATE Tessera SET validita = false WHERE dataScadenza < CURRENT_DATE")
public class Tessera {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private LocalDate dataEmissione;
	private LocalDate dataScadenza;
	private boolean validita;

	@OneToOne(mappedBy = "tessera")
	private Utente utente;
	@OneToMany(mappedBy = "tessera", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("tessera DESC")
	private Set<Abbonamento> abbonamenti;

	public Tessera(LocalDate dataEmissione) {
		this.dataEmissione = dataEmissione;
		this.dataScadenza = dataEmissione.plusYears(1);
		this.validita = true;
	}

	@Override
	public String toString() {
		return "Tessera [id=" + id + ", dataEmissione=" + dataEmissione + ", utente=" + utente + ", abbonamenti="
				+ abbonamenti + "]";
	}

}