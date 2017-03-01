package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.Korisnik;
import entity.PozivPrijatelja;
import entity.Restoran;
import session.KorisnikDaoLocal;
import session.PozivPrijateljaDaoLocal;
import session.PrijateljiDaoLocal;
import session.RestoranDaoLocal;

public class RestoraniPosetilacController extends HttpServlet {

	private static final long serialVersionUID = 2574244078457284080L;
	
	private static Logger log = Logger.getLogger(RestoraniPosetilacController.class);
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private PozivPrijateljaDaoLocal pozivPrijateljaDao;
	
	@EJB
	private PrijateljiDaoLocal prijateljiDao;
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("posetilac")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			System.out.println("************prikaz restorana sa ocenama zapocet");
			Korisnik posetilac = (Korisnik)request.getSession().getAttribute("posetilac");
			System.out.println("posetilac: " + posetilac);
			ArrayList<Restoran> restorani = new ArrayList<Restoran>();
			ArrayList<String> ocene = new ArrayList<String>();
			ArrayList<String> prOcene = new ArrayList<String>();
			for (Restoran res : restoranDao.findAll()) {
				restorani.add(res);
				//ocene.add(pozivPrijateljaDao.findProsecnaOcenaRestorana(res).toString());
				Double oc = pozivPrijateljaDao.findProsecnaOcenaRestorana(res);
				if (oc == 0.0) {
					ocene.add("bez ocene");
				}
				else {
					ocene.add(oc.toString());
				}
				//***racunanje prosecne ocene restorana od strane prijatelja posetioca***********
				double suma = 0.0;
				int brojac = 0;
				for (PozivPrijatelja pp : pozivPrijateljaDao.findPoseteRestoranu(res)) {
					if (prijateljiDao.checkIfPrijatelji(posetilac, pp.getKorisnik())) {
						if (pp.getOcena()>=1.0) {
							suma+=pp.getOcena();
							brojac++;
						}
					} else if (pp.getKorisnik().getId().toString().equals(posetilac.getId().toString())) {
						//uracunavanje ocene POSETIOCA u ocene prijatelja!!!
						if (pp.getOcena()>=1.0) {
							suma+=pp.getOcena();
							brojac++;
						}
					}
				}
				if (brojac == 0) {
					prOcene.add("bez ocene");
				} else {
					double value = suma/brojac;
					///////**************ZAOKRUZIVANJE NA 2 DECIMALE!!!
					BigDecimal bd = new BigDecimal(value);
				    bd = bd.setScale(2, RoundingMode.HALF_UP);
					prOcene.add(((Double)bd.doubleValue()).toString());
				    ///////**************
				}
				//*******************************************************************************
			}
			request.setAttribute("restorani", restorani);
			request.setAttribute("ocene", ocene);
			request.setAttribute("prOcene", prOcene);
			//@@@@@@@@@@prosledjujem posetioca da bi racunao udaljenost@@@@
			request.setAttribute("kor", posetilac);
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			
			
			System.out.println("parametri za prikaz restorana sa ocenama prosledjeni na jsp");
			getServletContext().getRequestDispatcher("/restoraniPosetilac.jsp").forward(request, response);
			return;
			
		} catch (ServletException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
