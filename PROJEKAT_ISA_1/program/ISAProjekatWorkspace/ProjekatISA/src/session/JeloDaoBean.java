package session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entity.Jelo;

@Stateless
@Local(JeloDaoLocal.class)
public class JeloDaoBean extends GenericDaoBean<Jelo, Integer> implements JeloDaoLocal {

}
