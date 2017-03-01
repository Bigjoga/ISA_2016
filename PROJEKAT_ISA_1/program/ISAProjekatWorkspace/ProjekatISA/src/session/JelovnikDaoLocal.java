package session;

import java.util.List;

import entity.Jelovnik;
import entity.Restoran;

public interface JelovnikDaoLocal extends GenericDaoLocal<Jelovnik, Integer> {
	public List<Jelovnik> findJelovniciRestorana(Restoran res);
}
