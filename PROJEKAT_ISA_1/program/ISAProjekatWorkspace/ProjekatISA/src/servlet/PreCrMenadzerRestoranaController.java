package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
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

public class PreCrMenadzerRestoranaController extends HttpServlet {

	private static final long serialVersionUID = -8116901667540844248L;

	private static Logger log = Logger.getLogger(PreCrMenadzerRestoranaController.class);
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private UlogaKorisnikaDaoLocal ulogaDao;
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("admin")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			System.out.println("************priprema dodavanja menadzera restorana zapoceta");
			Korisnik admin = (Korisnik)request.getSession().getAttribute("admin");
			System.out.println("admin: " + admin);
			
			String restoranId = request.getParameter("id");
			if (restoranId == null) {
				getServletContext().getRequestDispatcher("/PocetnaAdminController").forward(request, response);	
				//response.sendRedirect(response.encodeURL("./jelovniciMenadzer.jsp"));
				return;
			}
			
			//****priprema - dodavanje menadzera restorana restorana**********
			ArrayList<Korisnik> posetioci = new ArrayList<Korisnik>();
			for (Korisnik korisnik : korisnikDao.findAll()) {
				System.out.println("korisnik: "+ korisnik);
				if (korisnik.getUloga().getId().toString().equals(ulogaDao.findUlogaPoImenu(2).getId().toString())) {
					posetioci.add(korisnik);
					System.out.println("korisnik je posetilac");
				}
			}
			Restoran res = restoranDao.findById(Integer.parseInt(restoranId));
			request.setAttribute("posetioci", posetioci);
			request.setAttribute("restoran", res);
			//request.setAttribute("id", restoranId);
			System.out.println("podaci za dodavanje menadzera prosledjeni na jsp");
			/*
			 * 
			 * URADITI JSP createMenadzer.jsp
			 * URADITI MenadzerCreateController!!!!
			 * 
			 */
			//*****************************
			
			getServletContext().getRequestDispatcher("/menadzerRestoranaCreate.jsp").forward(request, response);
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
