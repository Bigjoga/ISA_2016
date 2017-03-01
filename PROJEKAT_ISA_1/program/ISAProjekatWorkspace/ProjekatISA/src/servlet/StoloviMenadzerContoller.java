package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.Korisnik;
import entity.Restoran;
import session.RestoranDaoLocal;
import session.StoDaoLocal;

public class StoloviMenadzerContoller extends HttpServlet {

	private static final long serialVersionUID = -68814211129473141L;

	private static Logger log = Logger.getLogger(StoloviMenadzerContoller.class);
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private StoDaoLocal stoDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("menadzer")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			System.out.println("************prikaz svih stolova zapocet");
			Korisnik menadzer = (Korisnik)request.getSession().getAttribute("menadzer");
			System.out.println("menadzer: " + menadzer);
			Restoran restoran = menadzer.getRestoran();
			System.out.println("restoran: " + restoran);
			
			request.setAttribute("sirina", restoran.getDimX());
			request.setAttribute("visina", restoran.getDimY());
			
			
			request.setAttribute("stolovi", stoDao.findStoloviRestorana(restoran));
			System.out.println("stolovi prebaceni na jsp");
			
			getServletContext().getRequestDispatcher("/stoloviMenadzer.jsp").forward(request, response);
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
