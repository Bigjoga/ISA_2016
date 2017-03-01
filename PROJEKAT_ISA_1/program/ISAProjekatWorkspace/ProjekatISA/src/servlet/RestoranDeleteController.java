package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.Korisnik;
import entity.Restoran;
import session.KorisnikDaoLocal;
import session.RestoranDaoLocal;
import session.UlogaKorisnikaDaoLocal;

public class RestoranDeleteController extends HttpServlet {

	private static final long serialVersionUID = -4281065533601001888L;
	
	private static Logger log = Logger.getLogger(RestoranDeleteController.class);
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private UlogaKorisnikaDaoLocal ulogaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("admin")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			System.out.println("************brisanje restorana zapoceto");
			Korisnik admin = (Korisnik)request.getSession().getAttribute("admin");
			System.out.println("admin: " + admin);;
			
			//****brisanje restorana**********
			Restoran restoranZaBrisanje = restoranDao.findById(Integer.parseInt(request.getParameter("id")));
			System.out.println("restoran za brisanje: " + restoranZaBrisanje);
			//**********setovanje restorana u menadzerima na null******
			ArrayList<Korisnik> menadzeri = korisnikDao.findMenadzereRestorana(restoranZaBrisanje);
			for (Korisnik kor : menadzeri) {
				//kor.setRestoran(null);
				System.out.println("****izmena menadzera restorana: " + kor);
				restoranZaBrisanje.remove(kor);
				System.out.println("restoran menadzera: " + kor.getRestoran());
				kor.setUloga(ulogaDao.findUlogaPoImenu(2));
				System.out.println("uloga menadzera: " + kor.getUloga());
				korisnikDao.merge(kor);
			}
			//*********************************************************
			
			//System.out.println("upit: DELETE FROM Restoran WHERE id=" + Integer.parseInt(request.getParameter("id")));
			//em.createQuery("DELETE FROM Restoran WHERE id=" + Integer.parseInt(request.getParameter("id")));
			/*Restoran noviRestoran = (Restoran)em.createQuery("SELECT r FROM Restoran r WHERE r.id=" + Integer.parseInt(request.getParameter("id"))).getSingleResult();
			System.out.println("restoran dobijen direktnim upitom: " + noviRestoran);*/
			restoranDao.remove(restoranZaBrisanje);
			/*
			 * 
			 * pre ovog remove pokupiti menadzere
			 * pa od tih menadzera KREIRATI NOVE POSETIOCE sa ISTIM id-evima!!!
			 * 
			 */
			System.out.println("obrisan restoran");
			//*****************************
			
			response.sendRedirect(response.encodeRedirectURL("./PocetnaAdminController"));
		
		/*} catch (ServletException e) {
			log.error(e);
			throw e;
		*/} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
	

}
