package entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="StavkaJelovnika")
@NamedQueries({
	@NamedQuery(name = "findStavkeJelovnika", query = "SELECT s FROM StavkaJelovnika s WHERE s.jelovnik = :jelovnik")
})
public class StavkaJelovnika implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3093889161394218980L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_stavke_jelovnika", unique = true, nullable = false)
	private Integer id;

	@Column(name = "Cena_stavke_jelovnika", unique = false, nullable = false)
	private Double cena;
	
	@ManyToOne
	@JoinColumn(name = "Jelovnik_ID", referencedColumnName = "ID_jelovnika", nullable = false)
	private Jelovnik jelovnik;
	
	@ManyToOne
	@JoinColumn(name = "Jelo_ID", referencedColumnName = "ID_jela", nullable = false)
	private Jelo jelo;

	public StavkaJelovnika() {
		super();
	}

	public StavkaJelovnika(Double cena, Jelovnik jelovnik, Jelo jelo) {
		super();
		this.cena = cena;
		this.jelovnik = jelovnik;
		this.jelo = jelo;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Jelovnik getJelovnik() {
		return jelovnik;
	}

	public void setJelovnik(Jelovnik jelovnik) {
		this.jelovnik = jelovnik;
	}

	public Jelo getJelo() {
		return jelo;
	}

	public void setJelo(Jelo jelo) {
		this.jelo = jelo;
	}
	
	public String toString() {
		return "(Stavka jelovnika)[id=" + id + ",id jela=" + jelo.getId() + "]";
	}
	
}