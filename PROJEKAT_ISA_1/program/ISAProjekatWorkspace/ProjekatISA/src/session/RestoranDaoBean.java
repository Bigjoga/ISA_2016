package session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entity.Restoran;

@Stateless
@Local(RestoranDaoLocal.class)
public class RestoranDaoBean extends GenericDaoBean<Restoran, Integer> implements RestoranDaoLocal {

}
