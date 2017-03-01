package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.Korisnik;
import entity.PozivPrijatelja;
import session.KorisnikDaoLocal;
import session.PozivPrijateljaDaoLocal;
import session.PrijateljiDaoLocal;
import session.UlogaKorisnikaDaoLocal;

public class PrijateljiReadController extends HttpServlet {

	private static final long serialVersionUID = 793729134745960684L;
	
	private static Logger log = Logger.getLogger(PrijateljiReadController.class);
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private PozivPrijateljaDaoLocal pozivPrijateljaDao;
	
	@EJB
	private PrijateljiDaoLocal prijateljiDao;
	
	@EJB
	private UlogaKorisnikaDaoLocal ulogaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("posetilac")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			System.out.println("************prikaz prijatelja posetioca zapocet");
			Korisnik posetilac = (Korisnik)request.getSession().getAttribute("posetilac");
			System.out.println("posetilac: " + posetilac);
			
			ArrayList<Korisnik> prijatelji = new ArrayList<Korisnik>();
			ArrayList<Integer> listaPoseta = new ArrayList<Integer>();
			for (Korisnik pr : prijateljiDao.findPrijateljiKorisnika(posetilac)) {
				System.out.println("prijatelj mu je: " + pr);
				prijatelji.add(pr);
				//********pozivanje upita kojim se odredjuje koliko poseta je imao korisnik@@@@@@
				//EVENTUALNO UBACITI I PROVERU DA LI JE TA POSETA PROSLA ILI TEK SLEDI!!!
				//@@@@@@@@@@@@@@@@RACUNATI ONE POSETE CIJI SE POCETAK DESIO U PROSLOSTI@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				//brojPosetaPr = pozivPrijateljaDao.findPoseteKorisnika(pr).size();
				Integer brPosetaPr = 0;
				for (PozivPrijatelja pp : pozivPrijateljaDao.findPoseteKorisnika(pr)) {
					System.out.println("poseta prijatelja: " + pp);
					if (pp.getRezervacija().getDatumVreme().compareTo(new Date())<0)
						brPosetaPr++;
				}
				
				//request.setAttribute("brojPoseta", brPosetaPr);
				//@@@@@@@@@@@@@@@@RACUNATI ONE POSETE CIJI SE POCETAK DESIO U PROSLOSTI@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				listaPoseta.add(brPosetaPr);
				//*************************************************************************
			}
			request.setAttribute("prijatelji", prijatelji);
			request.setAttribute("listaPoseta", listaPoseta);
			
			//korisnici u ulozi posetioca
			ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
			for (Korisnik kor : korisnikDao.findAll()) {
				if (kor.getUloga().getId().toString().equals(ulogaDao.findUlogaPoImenu(2).getId().toString())
						&& !kor.getId().toString().equals(posetilac.getId().toString()))
					korisnici.add(kor);
			}
			request.setAttribute("korisnici", korisnici);
			if (request.getParameter("upoz")==null) {
				System.out.println("nije stigao parametar upoz");
				request.setAttribute("upoz", 0);
			} else {
				if (request.getParameter("upoz").equals("1"))
					request.setAttribute("upoz", 1);
				else
					request.setAttribute("upoz", 0);
			}
			System.out.println("parametri za prikaz prijatelja posetioca prosledjeni na jsp");
			getServletContext().getRequestDispatcher("/prijateljiUpdate.jsp").forward(request, response);
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
