package entities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
	private boolean inServizio;

	@OneToMany(mappedBy = "mezzo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("dataVidimazione DESC")
	private Set<Biglietto> biglietti;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "mezzo_tratta", joinColumns = @JoinColumn(name = "mezzo_id"), inverseJoinColumns = @JoinColumn(name = "tratta_id"))
	private Set<Tratta> tratte;

	public Mezzo(int capienza, boolean inServizio) {
		this.capienza = capienza;
		this.inServizio = inServizio;
	}

//	@Override
//	public String toString() {
//		return "Mezzo [id=" + id + ", capienza=" + capienza + ", inServizio=" + inServizio + ", biglietti=" + biglietti
//				+ ", tratte=" + tratte + "]";
//	}

}
