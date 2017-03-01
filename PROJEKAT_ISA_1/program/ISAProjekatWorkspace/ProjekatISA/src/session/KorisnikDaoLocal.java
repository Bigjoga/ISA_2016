package session;

import java.util.ArrayList;

import entity.Korisnik;
import entity.Restoran;

public interface KorisnikDaoLocal extends GenericDaoLocal<Korisnik, Integer> {

	public Korisnik findKorisnikSaEmailomILozinkom(
			String email, String lozinka);
	public ArrayList<Korisnik> findMenadzereRestorana(Restoran res);
	public Korisnik findKorisnikRegistracija(String email);
}
