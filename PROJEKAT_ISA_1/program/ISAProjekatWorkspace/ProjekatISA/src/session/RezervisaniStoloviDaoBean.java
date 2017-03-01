package session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entity.RezervisaniStolovi;

@Stateless
@Local(RezervisaniStoloviDaoLocal.class)
public class RezervisaniStoloviDaoBean extends GenericDaoBean<RezervisaniStolovi, Integer> implements RezervisaniStoloviDaoLocal {

}
