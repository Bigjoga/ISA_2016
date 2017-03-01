package session;

import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.Korisnik;
import entity.Restoran;
import entity.Sto;

@Stateless
@Local(StoDaoLocal.class)
public class StoDaoBean extends GenericDaoBean<Sto, Integer> implements StoDaoLocal {

	@Override
	public ArrayList<Sto> findStoloviRestorana(Restoran res) {
		ArrayList<Sto> ret = new ArrayList<Sto>();
		Query q = em
				.createNamedQuery("findStoloviRestorana");
		q.setParameter("restoran", res);
		for (Object o : q.getResultList()) {
			ret.add((Sto)o);
		}
		return ret;
	}
	
}
