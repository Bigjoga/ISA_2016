package session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.Jelovnik;
import entity.Restoran;

@Stateless
@Local(JelovnikDaoLocal.class)
public class JelovnikDaoBean extends GenericDaoBean<Jelovnik, Integer> implements JelovnikDaoLocal {

	public List<Jelovnik> findJelovniciRestorana(Restoran res) {
		//ArrayList<Jelovnik> ret = new ArrayList<Jelovnik>();
		Query q = em.createNamedQuery("findJelovniciRestorana");
		q.setParameter("restoran", res);
		/*for (Object o : q.getResultList()) {
			ret.add((Jelovnik)o);
		}*/
		List<Jelovnik> ret = q.getResultList();
		return ret;
	}

}
