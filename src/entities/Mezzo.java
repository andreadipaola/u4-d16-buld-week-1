package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mezzi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_di_mezzo", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Setter
@Getter
public abstract class Mezzo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	protected int capienza;
	@Column(name = "in_servizio")
	private boolean inServizio;
	@OneToMany(mappedBy = "mezzo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@OrderBy("dataVidimazione DESC")
	private Set<Biglietto> biglietti;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "mezzo_tratta", joinColumns = @JoinColumn(name = "mezzo_id"), inverseJoinColumns = @JoinColumn(name = "tratta_id"))
//	private Set<Tratta> tratte;
	private Tratta tratta;

	private String numero;

	@Column(name = "biglietti_vidimati")
	private int bigliettiVidimati;

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

//	@Override
//	public String toString() {
//		return "Mezzo [id=" + id + ", capienza=" + capienza + ", inServizio=" + inServizio + ", biglietti=" + biglietti
//				+ ", tratte=" + tratte + "]";
//	}

}
