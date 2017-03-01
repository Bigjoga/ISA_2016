package session;

import entity.UlogaKorisnika;

public interface UlogaKorisnikaDaoLocal extends GenericDaoLocal<UlogaKorisnika, Integer> {
	public UlogaKorisnika findUlogaPoImenu(int x);
}
