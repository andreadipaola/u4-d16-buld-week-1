package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Utente {
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private int eta;
	@OneToOne
	private Tessera tessera;

	public Utente(String codiceFiscale, String nome, String cognome, int eta) {
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;

	}
}
