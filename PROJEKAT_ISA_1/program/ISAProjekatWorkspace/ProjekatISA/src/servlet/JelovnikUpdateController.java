package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import entity.Jelovnik;
import entity.Restoran;
import entity.StavkaJelovnika;
import session.JelovnikDaoLocal;
import session.StavkaJelovnikaDaoLocal;

public class JelovnikUpdateController extends HttpServlet {

	private static final long serialVersionUID = -5125927117309788353L;
	
	private static Logger log = Logger.getLogger(JelovnikUpdateController.class);

	@EJB
	private StavkaJelovnikaDaoLocal stavkaDao;
	
	@EJB
	private JelovnikDaoLocal jelovnikDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("menadzer")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}

			HttpSession session = request.getSession(true);
			Restoran restoran = (Restoran)session.getAttribute("restoran");
			

			System.out.println("************izmena jelovnika zapoceta");
			//citanje jelovnika************************************************
			String jelovnikId = request.getParameter("id");
			if (jelovnikId == null) {
				getServletContext().getRequestDispatcher("/JelovniciMenadzerController").forward(request, response);	
				//response.sendRedirect(response.encodeURL("./jelovniciMenadzer.jsp"));
				return;
			}
			Jelovnik jelovnik = jelovnikDao.findById(Integer.parseInt(jelovnikId));
			System.out.println("jelovnik koji se menja: " + jelovnik);
			String naziv = request.getParameter("naziv");
			
			//**********NOVI UPDATE ZA JELOVNIK**************************************
			Jelovnik jlk = new Jelovnik();
			jlk.setId(new Integer(jelovnik.getId()));
			System.out.println("novi naziv jelovnika: " + naziv);
			jlk.setNazivJelovnika(naziv);
			jlk.setRestoran(restoran);
			System.out.println("pre merge jelovnika");
			jelovnikDao.merge(jlk);
			System.out.println("posle merge jelovnika");
			
			ArrayList<StavkaJelovnika> stavke = stavkaDao.findStavkeJelovnika(jelovnik);
			for (StavkaJelovnika staraStavka : stavke) {
				StavkaJelovnika novaStavka = new StavkaJelovnika();
				Double cena = Double.parseDouble(request.getParameter("cena"+staraStavka.getId()));
				System.out.println("stara stavka: " + staraStavka);
				novaStavka.setId(new Integer(staraStavka.getId()));
				System.out.println("nova cena: " + cena);
				novaStavka.setCena(cena);
				System.out.println("setovana cena za novu stavku");
				novaStavka.setJelo(staraStavka.getJelo());
				System.out.println("setovano jelo za novu stavku");
				novaStavka.setJelovnik(jlk);
				System.out.println("setovan jelovnik za novu stavku");
				stavkaDao.merge(novaStavka);
				System.out.println("posle merge nove stavke");
			}
			//***********************************************************************
			
			
			getServletContext().getRequestDispatcher("/JelovniciMenadzerController").forward(request, response);
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
