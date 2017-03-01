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
import session.StavkaJelovnikaDaoLocal;

public class PreUpJelovnikController extends HttpServlet {

	private static final long serialVersionUID = 5353919207477616192L;

	private static Logger log = Logger.getLogger(PreUpJelovnikController.class);
	
	@EJB
	private JelovnikDaoLocal jelovnikDao;
	
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
			

			System.out.println("************priprema za izmenu jelovnika zapoceta");
			//priprema izmene jelovnika************************************************
			request.setAttribute("jelovnici", jelovnikDao.findJelovniciRestorana(restoran));
			String jelovnikId = request.getParameter("id");
			if (jelovnikId == null) {
				getServletContext().getRequestDispatcher("/JelovniciMenadzerController").forward(request, response);	
				//response.sendRedirect(response.encodeURL("./jelovniciMenadzer.jsp"));
				return;
			}
			Jelovnik jelovnik = jelovnikDao.findById(Integer.parseInt(jelovnikId));
			System.out.println("popunjavanje za izmenu jelovnika: "  + jelovnik);
			
			request.setAttribute("jelovnik", jelovnik);
			request.setAttribute("stavkeJelovnika", stavkaDao.findStavkeJelovnika(jelovnik));
			
			System.out.println("pripremljene info za izmenu jelovnika: " + jelovnik);
			
			getServletContext().getRequestDispatcher("/jelovnikUpdate.jsp").forward(request, response);
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
