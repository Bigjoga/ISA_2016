package session;

import java.util.ArrayList;

import entity.Korisnik;
import entity.PozivPrijatelja;
import entity.Restoran;
import entity.Rezervacija;

public interface PozivPrijateljaDaoLocal extends GenericDaoLocal<PozivPrijatelja, Integer> {
	public ArrayList<PozivPrijatelja> findPoseteKorisnika(Korisnik korisnik);
	public Double findProsecnaOcenaRestorana(Restoran res);
	public ArrayList<PozivPrijatelja> findPoseteRestoranu(Restoran res);
	public ArrayList<Korisnik> findDrugiPosetiociRezervacije(Rezervacija rez, Korisnik kor);
}
