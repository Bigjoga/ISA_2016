package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.Restoran;
import session.KorisnikDaoLocal;
import session.PozivPrijateljaDaoLocal;
import session.RestoranDaoLocal;

public class PocetnaAdminController extends HttpServlet {

	private static final long serialVersionUID = 5830962456890516542L;
	
	private static Logger log = Logger.getLogger(PocetnaAdminController.class);
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private PozivPrijateljaDaoLocal pozivPrijateljaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("admin")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}

			//******************************************
			//dodavanje menadzera u restorane
			/*System.out.println("pre nalazenja svih restorana");;
			for (Restoran res : restoranDao.findAll()) {
				System.out.println("pre clear-a menadzera za restoran");
				if (res.getMenadzeri() == null){
					System.out.println("menadzeri restorana" + res + " su null");
					res.setMenadzeri(new HashSet<Korisnik>());
					restoranDao.persist(res);
					if (res.getMenadzeri() == null){
						System.out.println("i dalje su menadzeri u " + res + " su null");
						res.setMenadzeri(new HashSet<Korisnik>());
					}
				}else {
					res.getMenadzeri().clear();
				}
				System.out.println("pre fora za menadzere");
				for (Korisnik men : korisnikDao.findMenadzereRestorana(res)) {
					System.out.println("u foru za men, pre res.add; men je " + men);
					res.add(men);
					System.out.println("u foru za menadzere; menadzer je " + men);
				}
				restoranDao.persist(res);
				System.out.println("nakon persistovanja restorana " + res);
			}*/
			//*******************************************
			request.setAttribute("restorani", restoranDao.findAll());
			System.out.println("setovani atribut restorani na request");
			for (Restoran res : restoranDao.findAll()) {
				if (res.getMenadzeri()==null) {
					System.out.println("menadzeri su null za restoran " + res);
				} else {
					System.out.println("za restoran " + res + "broj menadzera je " + res.getMenadzeri().size());
				} 
			}
			
			//@@@@@@@@@@@@@@ DODATI LISTU PRAVIH PROSECNIH OCENA RESTORANA @@@@@@@@@
			ArrayList<String> ocene = new ArrayList<String>();
			for (Restoran res : restoranDao.findAll()) {
				Double prOcena = pozivPrijateljaDao.findProsecnaOcenaRestorana(res);
				if (prOcena == 0.0) {
					ocene.add("bez ocene");
				}
				else {
					ocene.add(prOcena.toString());
				}
			}
			request.setAttribute("ocene", ocene);
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			
			getServletContext().getRequestDispatcher("/pocetnaAdmin.jsp").forward(request, response);
		
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
