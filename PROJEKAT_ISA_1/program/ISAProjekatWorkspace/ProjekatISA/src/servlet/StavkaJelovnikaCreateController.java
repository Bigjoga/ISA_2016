package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import entity.Jelo;
import entity.Jelovnik;
import entity.Restoran;
import entity.StavkaJelovnika;
import session.JeloDaoLocal;
import session.JelovnikDaoLocal;
import session.StavkaJelovnikaDaoLocal;

public class StavkaJelovnikaCreateController extends HttpServlet {

	private static final long serialVersionUID = 7133360693830505080L;

	private static Logger log = Logger.getLogger(StavkaJelovnikaCreateController.class);
	
	@EJB
	private JelovnikDaoLocal jelovnikDao;
	
	@EJB
	private JeloDaoLocal jeloDao;
	
	@EJB
	private StavkaJelovnikaDaoLocal stavkaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("menadzer")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}

			HttpSession session = request.getSession(true);
			Restoran restoran = (Restoran)session.getAttribute("restoran");
			

			System.out.println("************dodavanje stavke jelovnika zapoceto");
			//dodavanje jelovnika**********************************************
			if (request.getParameter("jelovnikId")!=null) {
				System.out.println("******dodavanje stavke za tacno odredjeni jelovnik zapoceto");
				//dodavanje stavke za tacno odredjeni jelovnik
				Integer jelovnikId = Integer.parseInt(request.getParameter("jelovnikId"));
				Double cena = Double.parseDouble(request.getParameter("cena"));
				Integer jeloId = Integer.parseInt(request.getParameter("jeloId"));
				StavkaJelovnika novaStavka = new StavkaJelovnika();
				Jelovnik jelovnik = jelovnikDao.findById(jelovnikId);
				System.out.println("jelovnik nove stavke: " + jelovnik);
				Jelo jelo = jeloDao.findById(jeloId);
				System.out.println("jelo nove stavke: " + jelo);
				novaStavka.setCena(cena);
				novaStavka.setJelo(jelo);
				novaStavka.setJelovnik(jelovnik);
				stavkaDao.persist(novaStavka);
				getServletContext().getRequestDispatcher("/PreUpJelovnikController?id="+jelovnikId).forward(request, response);
				return;
			}
			/*
			 * ovde ce ici deo kada se dodaje stavka za bilo koji jelovnik
			 * to moze da radi samo admin sistema, ne moze menadzer restorana
			 * 
			 * [[[[[ mozda moze da se objedine ova 2 kreiranja stavke
			 * 		posto svakako dobijam jelovnikId kao request-parameter]]]]]
			 */
			
			
			
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
