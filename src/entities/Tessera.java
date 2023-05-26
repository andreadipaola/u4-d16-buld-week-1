package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tessere")
@NoArgsConstructor
@Setter
@Getter
@NamedQuery(name = "controllo_validita_tessera", query = "UPDATE Tessera SET validita = false WHERE dataScadenza < CURRENT_DATE")
public class Tessera {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(name = "data_emissione")
	private LocalDate dataEmissione;
	@Column(name = "data_scadenza")
	private LocalDate dataScadenza;
	private boolean validita;
	@OneToOne(mappedBy = "tessera")
	private Utente utente;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@OrderBy("tessera DESC")
	private Abbonamento abbonamento;

	public Tessera(LocalDate dataEmissione, LocalDate dataScadenza) {
		this.dataEmissione = dataEmissione;
		this.dataScadenza = dataEmissione;
		this.validita = true;
	}
}