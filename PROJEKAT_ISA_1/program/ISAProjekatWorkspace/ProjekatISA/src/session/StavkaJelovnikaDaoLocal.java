package session;

import java.util.ArrayList;

import entity.Jelovnik;
import entity.StavkaJelovnika;

public interface StavkaJelovnikaDaoLocal extends GenericDaoLocal<StavkaJelovnika, Integer> {
	public ArrayList<StavkaJelovnika> findStavkeJelovnika(Jelovnik jel);
}
