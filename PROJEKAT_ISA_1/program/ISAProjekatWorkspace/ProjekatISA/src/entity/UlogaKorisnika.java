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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="UlogaKorisnika")
@NamedQueries({
	@NamedQuery(name = "findUlogaPoImenu", query = "SELECT u FROM UlogaKorisnika u WHERE u.nazivUloge like :nazivUloge")
})
public class UlogaKorisnika implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3053780978632732745L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_uloge", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "Naziv_uloge", unique = false, nullable = false)
	private String nazivUloge;
	
	@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "uloga")
	private Set<Korisnik> korisnici = new HashSet<Korisnik>();

	public void add(Korisnik k) {
		if (k.getUloga() != null)
			k.getUloga().getKorisnici().remove(k);
		k.setUloga(this);
		korisnici.add(k);
	}

	public void remove(Korisnik k) {
		k.setUloga(null);
		korisnici.remove(k);
	}
	
	public UlogaKorisnika() {
		super();
	}

	public UlogaKorisnika(String nazivUloge) {
		super();
		this.nazivUloge = nazivUloge;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivUloge() {
		return nazivUloge;
	}

	public void setNazivUloge(String nazivUloge) {
		this.nazivUloge = nazivUloge;
	}
	
	public Set<Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(Set<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}
	
	public String toString() {
		return "(Uloga korisnika)[id=" + id + ",naziv uloge=" + nazivUloge + "]";
	}
	
}
