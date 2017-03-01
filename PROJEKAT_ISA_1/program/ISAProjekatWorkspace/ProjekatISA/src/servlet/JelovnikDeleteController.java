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
import session.JelovnikDaoLocal;

public class JelovnikDeleteController extends HttpServlet {

	private static final long serialVersionUID = 7683296753217048843L;

	private static Logger log = Logger.getLogger(JelovnikDeleteController.class);
	
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
			

			System.out.println("************brisanje jelovnika zapoceto");
			//brisanje jelovnika************************************************
			request.setAttribute("jelovnici", jelovnikDao.findJelovniciRestorana(restoran));
			String jelovnikId = request.getParameter("id");
			if (jelovnikId == null) {
				getServletContext().getRequestDispatcher("/JelovniciMenadzerController").forward(request, response);	
				//response.sendRedirect(response.encodeURL("./jelovniciMenadzer.jsp"));
				return;
			}
			Jelovnik jelovnik = jelovnikDao.findById(Integer.parseInt(jelovnikId));
			System.out.println("brise se jelovnik: "  + jelovnik);
			jelovnikDao.remove(jelovnik);
			
			System.out.println("obrisan jelovnik: " + jelovnik);
			
			System.out.println("preziveli jelovnici:");
			
			for (Jelovnik j : jelovnikDao.findJelovniciRestorana(restoran)) {
				System.out.println(j);
			}
			getServletContext().getRequestDispatcher("/JelovniciMenadzerController").forward(request, response);	
			//getServletContext().getRequestDispatcher("/jelovniciMenadzer.jsp").forward(request, response);
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
