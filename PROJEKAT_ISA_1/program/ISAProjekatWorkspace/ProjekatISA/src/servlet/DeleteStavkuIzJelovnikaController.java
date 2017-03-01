package servlet;

import java.io.IOException;

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

public class DeleteStavkuIzJelovnikaController extends HttpServlet {

	private static final long serialVersionUID = 5729414729466578679L;

	private static Logger log = Logger.getLogger(DeleteStavkuIzJelovnikaController.class);
	
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
			

			System.out.println("************brisanje stavke iz jelovnika zapoceto");
			//brisanje stavke iz jelovnika************************************************
			String stavkaId = request.getParameter("id");
			if (stavkaId == null) {
				getServletContext().getRequestDispatcher("/JelovniciMenadzerController").forward(request, response);	
				//response.sendRedirect(response.encodeURL("./jelovniciMenadzer.jsp"));
				return;
			}
			
			StavkaJelovnika stavka = stavkaDao.findById(Integer.parseInt(stavkaId));
			String jelovnikId = stavka.getJelovnik().getId().toString();
			
			stavkaDao.remove(stavka);
			System.out.println("obrisana stavka iz jelovnika");
			
			//getServletContext().getRequestDispatcher("/JelovniciMenadzerController").forward(request, response);
			response.sendRedirect(response.encodeRedirectURL("./PreUpJelovnikController?id="+jelovnikId));
			//getServletContext().getRequestDispatcher("/PreUpJelovnikController?id="+jelovnikId).forward(request, response);
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
