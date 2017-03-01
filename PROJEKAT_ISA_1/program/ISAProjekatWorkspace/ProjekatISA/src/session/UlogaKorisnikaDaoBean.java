package session;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.UlogaKorisnika;

@Stateless
@Local(UlogaKorisnikaDaoLocal.class)
public class UlogaKorisnikaDaoBean extends GenericDaoBean<UlogaKorisnika, Integer> implements UlogaKorisnikaDaoLocal {

	public UlogaKorisnika findUlogaPoImenu(int x) {
		UlogaKorisnika ret;
		String nazivUloge = "";
		if (x==0) {
			nazivUloge = "administrator";
		} else if (x==1) {
			nazivUloge = "menadzer";
		} else if (x==2) {
			nazivUloge = "posetilac";
		}
		Query q = em
				.createNamedQuery("findUlogaPoImenu");
		q.setParameter("nazivUloge", nazivUloge);
		ret = (UlogaKorisnika)q.getSingleResult();
		return ret;
	}

}
