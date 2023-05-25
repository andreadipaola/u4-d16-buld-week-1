package entities;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "utenti")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID codiceFiscale;
	private String nome;
	private String cognome;
	private int eta;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tessera_id", referencedColumnName = "id")
	private Tessera tessera;

	public Utente(String nome, String cognome, int eta) {
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;

	}
}
