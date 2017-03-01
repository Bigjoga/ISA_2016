package session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entity.Gorivo;

@Stateless
@Local(GorivoDaoLocal.class)
public class GorivoDaoBean extends GenericDaoBean<Gorivo, Integer> implements
		GorivoDaoLocal {

}
