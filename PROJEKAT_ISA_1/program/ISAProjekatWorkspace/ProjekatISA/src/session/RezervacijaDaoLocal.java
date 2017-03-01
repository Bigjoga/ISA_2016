package session;

import java.util.ArrayList;

import entity.Rezervacija;
import entity.Sto;

public interface RezervacijaDaoLocal extends GenericDaoLocal<Rezervacija, Integer> {
	public ArrayList<Rezervacija> findRezervacijeZaSto(Sto sto);
}
