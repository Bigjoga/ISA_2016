package session;

import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.Jelovnik;
import entity.StavkaJelovnika;

@Stateless
@Local(StavkaJelovnikaDaoLocal.class)
public class StavkaJelovnikaDaoBean extends GenericDaoBean<StavkaJelovnika, Integer> implements StavkaJelovnikaDaoLocal {
	public ArrayList<StavkaJelovnika> findStavkeJelovnika(Jelovnik jel) {
		ArrayList<StavkaJelovnika> ret = new ArrayList<StavkaJelovnika>();
		Query q = em.createNamedQuery("findStavkeJelovnika");
		q.setParameter("jelovnik", jel);
		for (Object o : q.getResultList()) {
			ret.add((StavkaJelovnika)o);
		}
		return ret;
	}
}
