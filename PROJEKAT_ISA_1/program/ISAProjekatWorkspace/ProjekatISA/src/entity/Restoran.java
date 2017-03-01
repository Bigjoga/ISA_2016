package entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
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
@Table(name="Restoran")
public class Restoran implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3539600946611134095L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_restorana", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "Naziv_restorana", unique = false, nullable = false)
	private String nazivRestorana;
	
	@Column(name = "Opis_restorana", unique = false, nullable = true)
	private String opisRestorana;
	
	@Column(name = "DimX_restorana", unique = false, nullable = false)
	private Integer dimX;
	
	@Column(name = "DimY_restorana", unique = false, nullable = false)
	private Integer dimY;
	
	@Column(name = "ProsecnaOcena", unique = false, nullable = false)
	private Double prosecnaOcena;
	
	@Column(name = "UkupanBrojOcena", unique = false, nullable = false)
	private Integer ukupanBrojOcena;

	@Column(name = "Adresa_restorana", unique = false, nullable = false)
	private String adresa;
	
	/*@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "restoran")
	private Set<Korisnik> menadzeri = new HashSet<Korisnik>();*/
	@OneToMany(cascade = { PERSIST }, fetch = EAGER, mappedBy = "restoran")
	private Set<Korisnik> menadzeri = new HashSet<Korisnik>();
	
	public void add(Korisnik k) {
		if (k.getRestoran() != null)
			k.getRestoran().getMenadzeri().remove(k);
		k.setRestoran(this);
		menadzeri.add(k);
	}

	public void remove(Korisnik k) {
		k.setRestoran(null);
		menadzeri.remove(k);
	}
	
	@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "restoran")
	private Set<Jelovnik> jelovnici = new HashSet<Jelovnik>();

	public void add(Jelovnik j) {
		if (j.getRestoran() != null)
			j.getRestoran().getJelovnici().remove(j);
		j.setRestoran(this);
		jelovnici.add(j);
	}

	public void remove(Jelovnik j) {
		j.setRestoran(null);
		jelovnici.remove(j);
	}
	
	@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "restoran")
	private Set<Sto> stolovi = new HashSet<Sto>();

	public void add(Sto s) {
		if (s.getRestoran() != null)
			s.getRestoran().getStolovi().remove(s);
		s.setRestoran(this);
		stolovi.add(s);
	}

	public void remove(Sto s) {
		s.setRestoran(null);
		stolovi.remove(s);
	}
	
	@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "restoran")
	private Set<OcenaPrijatelja> ocenePrijatelja = new HashSet<OcenaPrijatelja>();

	public void add(OcenaPrijatelja op) {
		if (op.getRestoran() != null)
			op.getRestoran().getOcenePrijatelja().remove(op);
		op.setRestoran(this);
		ocenePrijatelja.add(op);
	}

	public void remove(OcenaPrijatelja op) {
		op.setRestoran(null);
		ocenePrijatelja.remove(op);
	}
	
	@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "restoran")
	private Set<Rezervacija> rezervacije = new HashSet<Rezervacija>();

	public void add(Rezervacija r) {
		if (r.getRestoran() != null)
			r.getRestoran().getRezervacije().remove(r);
		r.setRestoran(this);
		rezervacije.add(r);
	}

	public void remove(Rezervacija r) {
		r.setRestoran(null);
		rezervacije.remove(r);
	}
	
	public Restoran() {
		super();
	}

	public Restoran(String nazivRestorana, String opisRestorana, Integer dimX,
			Integer dimY, Double prosecnaOcena, Integer ukupanBrojOcena, String adresa) {
		super();
		this.nazivRestorana = nazivRestorana;
		this.opisRestorana = opisRestorana;
		this.dimX = dimX;
		this.dimY = dimY;
		this.prosecnaOcena = prosecnaOcena;
		this.ukupanBrojOcena = ukupanBrojOcena;
		this.adresa = adresa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivRestorana() {
		return nazivRestorana;
	}

	public void setNazivRestorana(String nazivRestorana) {
		this.nazivRestorana = nazivRestorana;
	}

	public String getOpisRestorana() {
		return opisRestorana;
	}

	public void setOpisRestorana(String opisRestorana) {
		this.opisRestorana = opisRestorana;
	}

	public Integer getDimX() {
		return dimX;
	}

	public void setDimX(Integer dimX) {
		this.dimX = dimX;
	}

	public Integer getDimY() {
		return dimY;
	}

	public void setDimY(Integer dimY) {
		this.dimY = dimY;
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Set<Korisnik> getMenadzeri() {
		return menadzeri;
	}

	public void setMenadzeri(Set<Korisnik> menadzeri) {
		this.menadzeri = menadzeri;
	}

	public Set<Jelovnik> getJelovnici() {
		return jelovnici;
	}

	public void setJelovnici(Set<Jelovnik> jelovnici) {
		this.jelovnici = jelovnici;
	}

	public Set<Sto> getStolovi() {
		return stolovi;
	}

	public void setStolovi(Set<Sto> stolovi) {
		this.stolovi = stolovi;
	}

	public Integer getUkupanBrojOcena() {
		return ukupanBrojOcena;
	}

	public void setUkupanBrojOcena(Integer ukupanBrojOcena) {
		this.ukupanBrojOcena = ukupanBrojOcena;
	}

	public Set<OcenaPrijatelja> getOcenePrijatelja() {
		return ocenePrijatelja;
	}

	public void setOcenePrijatelja(Set<OcenaPrijatelja> ocenePrijatelja) {
		this.ocenePrijatelja = ocenePrijatelja;
	}
	
	public Set<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Set<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String toString() {
		return "(Restoran)[id=" + id + ",naziv restorana=" + nazivRestorana + "]";
	}
	
	
}
