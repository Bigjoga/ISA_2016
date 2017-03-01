package entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Jelo")
public class Jelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5004504403986688330L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_jela", unique = true, nullable = false)
	private Integer id;

	@Column(name = "Naziv_jela", unique = false, nullable = false)
	private String nazivJela;
	
	@Column(name = "Opis_jela", unique = false, nullable = false)
	private String opisJela;
	
	@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "jelo")
	private Set<StavkaJelovnika> stavkeJelovnika = new HashSet<StavkaJelovnika>();

	public void add(StavkaJelovnika s) {
		if (s.getJelo() != null)
			s.getJelo().getStavkeJelovnika().remove(s);
		s.setJelo(this);
		stavkeJelovnika.add(s);
	}

	public void remove(StavkaJelovnika s) {
		s.setJelovnik(null);
		stavkeJelovnika.remove(s);
	}
	
	public Jelo() {
		super();
	}

	public Jelo(String nazivJela, String opisJela) {
		super();
		this.nazivJela = nazivJela;
		this.opisJela = opisJela;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivJela() {
		return nazivJela;
	}

	public void setNazivJela(String nazivJela) {
		this.nazivJela = nazivJela;
	}

	public String getOpisJela() {
		return opisJela;
	}

	public void setOpisJela(String opisJela) {
		this.opisJela = opisJela;
	}

	public Set<StavkaJelovnika> getStavkeJelovnika() {
		return stavkeJelovnika;
	}

	public void setStavkeJelovnika(Set<StavkaJelovnika> stavkeJelovnika) {
		this.stavkeJelovnika = stavkeJelovnika;
	}
	
	public String toString() {
		return "(Jelo)[id=" + id + ",naziv jela=" + nazivJela+ "]";
	}
	
}
