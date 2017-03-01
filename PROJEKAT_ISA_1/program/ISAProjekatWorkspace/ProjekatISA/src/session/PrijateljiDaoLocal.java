package session;

import java.util.ArrayList;

import entity.Korisnik;
import entity.Prijatelji;

public interface PrijateljiDaoLocal extends GenericDaoLocal<Prijatelji, Integer> {
	public ArrayList<Korisnik> findPrijateljiKorisnika(Korisnik kor);
	public Boolean checkIfPrijatelji(Korisnik kor1, Korisnik kor2);
}
