package entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mezzi")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_di_mezzo", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Setter
@Getter
public abstract class Mezzo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	protected int capienza;
	@Column(name = "in_servizio")
	private boolean inServizio;

	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "tratte_id", referencedColumnName = "tratta_id")
	private Tratta tratta;

	private String numero;

	@Column(name = "biglietti_timbrati")
	private int bigliettiTimbrati;

	@Column(name = "numero_corse")
	private int numeroCorse;

	@Column(name = "inizio_manutenzione")
	private LocalDate inizioManutenzione;

	@Column(name = "fine_manutenzione")
	private LocalDate fineManutenzione;

	public Mezzo(String numero, boolean inServizio, Tratta tratta, int capienza) {
		this.numero = numero;
		this.inServizio = inServizio;
		this.tratta = tratta;
		this.capienza = capienza;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName().toUpperCase() + " " + numero + " - Partenza: " + tratta.getPartenza()
				+ " - Capolinea: " + tratta.getCapolinea() + " - Durata media della corsa: "
				+ tratta.getTempoMedioPercorrenza();
	}

}
