package session;

import java.util.ArrayList;

import entity.Restoran;
import entity.Sto;

public interface StoDaoLocal extends GenericDaoLocal<Sto, Integer> {
	public ArrayList<Sto> findStoloviRestorana(Restoran res);
}
