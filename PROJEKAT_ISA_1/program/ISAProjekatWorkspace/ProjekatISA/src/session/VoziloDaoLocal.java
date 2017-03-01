package session;

import java.util.List;

import entity.Vozilo;

public interface VoziloDaoLocal extends GenericDaoLocal<Vozilo, Integer> {

	public List<Vozilo> findVozilaSaKlimom();

}
