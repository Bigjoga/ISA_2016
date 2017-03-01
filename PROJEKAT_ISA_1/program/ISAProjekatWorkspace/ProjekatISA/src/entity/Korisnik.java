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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Korisnik")
@NamedQueries({
	@NamedQuery(name = "findKorisnikSaEmailomILozinkom", query = "SELECT k FROM Korisnik k WHERE k.email like :email AND k.sifra LIKE :lozinka"),
	@NamedQuery(name = "findMenadzereRestorana", query = "SELECT k FROM Korisnik k WHERE k.restoran = :restoran"),
	@NamedQuery(name = "findKorisnikRegistracija", query = "SELECT k FROM Korisnik k WHERE k.email like :email")
})
public class Korisnik implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9188093096106819298L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_korisnika", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "Email_korisnika", unique = true, nullable = false)
	private String email;

	@Column(name = "Sifra_korisnika", unique = false, nullable = false)
	private String sifra;
	
	@Column(name = "Ime_korisnika", unique = false, nullable = false)
	private String ime;
	
	@Column(name = "Adresa_korisnika", unique = false, nullable = true)
	private String adresa;
	
	//*********KUMULATIVAN ZBIR PRAVIH POSETA
	@Column(name = "Broj_poseta", unique = false, nullable = false)
	private Integer brojPoseta;
	//***************************************
	
	@ManyToOne
	@JoinColumn(name = "Uloga_ID", referencedColumnName = "ID_uloge", nullable = false)
	private UlogaKorisnika uloga;
	
	@ManyToOne
	@JoinColumn(name = "Restoran_ID", referencedColumnName = "ID_restorana", nullable = true)
	private Restoran restoran;
	
	
	//********************************************************************************//
	//********************************************************************************//
	//*******listu prijatelja raditi preko NamedQuery-a u tabeli prijatelji!!!********//
	//********************************************************************************//
	//********************************************************************************//
	
	@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "korisnik")
	private Set<Rezervacija> rezervacije = new HashSet<Rezervacija>();

	public void add(Rezervacija r) {
		if (r.getKorisnik() != null)
			r.getKorisnik().getRezervacije().remove(r);
		r.setKorisnik(this);
		rezervacije.add(r);
	}

	public void remove(Rezervacija r) {
		r.setKorisnik(null);
		rezervacije.remove(r);
	}
	
	@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "korisnik")
	private Set<PozivPrijatelja> pozvanNaRezervacije = new HashSet<PozivPrijatelja>();

	public void add(PozivPrijatelja pp) {
		if (pp.getKorisnik() != null)
			pp.getKorisnik().getPozvanNaRezervacije().remove(pp);
		pp.setKorisnik(this);
		pozvanNaRezervacije.add(pp);
	}

	public void remove(PozivPrijatelja pp) {
		pp.setKorisnik(null);
		pozvanNaRezervacije.remove(pp);
	}
	
	@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "korisnik")
	private Set<OcenaPrijatelja> ocenePrijatelja = new HashSet<OcenaPrijatelja>();

	public void add(OcenaPrijatelja op) {
		if (op.getKorisnik() != null)
			op.getKorisnik().getOcenePrijatelja().remove(op);
		op.setKorisnik(this);
		ocenePrijatelja.add(op);
	}

	public void remove(OcenaPrijatelja op) {
		op.setKorisnik(null);
		ocenePrijatelja.remove(op);
	}
	
	
	public Korisnik() {
		super();
		this.brojPoseta = 0;
	}

	public Korisnik(String email, String sifra, String ime, String adresa,
			UlogaKorisnika uloga, Restoran restoran) {
		super();
		this.email = email;
		this.sifra = sifra;
		this.ime = ime;
		this.adresa = adresa;
		this.uloga = uloga;
		this.restoran = restoran;
		//restoran.add(this);
		this.brojPoseta = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Integer getBrojPoseta() {
		return brojPoseta;
	}

	public void setBrojPoseta(Integer brojPoseta) {
		this.brojPoseta = brojPoseta;
	}

	public UlogaKorisnika getUloga() {
		return uloga;
	}

	public void setUloga(UlogaKorisnika uloga) {
		this.uloga = uloga;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Set<PozivPrijatelja> getPozvanNaRezervacije() {
		return pozvanNaRezervacije;
	}

	public void setPozvanNaRezervacije(Set<PozivPrijatelja> pozvanNaRezervacije) {
		this.pozvanNaRezervacije = pozvanNaRezervacije;
	}

	public Set<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Set<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public Set<OcenaPrijatelja> getOcenePrijatelja() {
		return ocenePrijatelja;
	}

	public void setOcenePrijatelja(Set<OcenaPrijatelja> ocenePrijatelja) {
		this.ocenePrijatelja = ocenePrijatelja;
	}

	public String toString() {
		return "(Korisnik)[id=" + id + ",ime korisnika=" + ime + ",email korisnika=" + email + "]";
	}
	
}
