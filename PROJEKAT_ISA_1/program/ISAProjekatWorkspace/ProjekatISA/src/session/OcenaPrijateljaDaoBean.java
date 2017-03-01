package session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entity.OcenaPrijatelja;

@Stateless
@Local(OcenaPrijateljaDaoLocal.class)
public class OcenaPrijateljaDaoBean extends GenericDaoBean<OcenaPrijatelja, Integer> implements OcenaPrijateljaDaoLocal {

}
