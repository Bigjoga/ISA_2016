package servlet;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.Jelovnik;
import entity.Korisnik;
import entity.Restoran;
import session.JelovnikDaoLocal;
import session.RestoranDaoLocal;




public class JelovniciMenadzerController extends HttpServlet {

	private static final long serialVersionUID = -8155478165948226357L;

	private static Logger log = Logger.getLogger(JelovniciMenadzerController.class);
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private JelovnikDaoLocal jelovnikDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("menadzer")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			System.out.println("************prikaz svih jelovnika zapocet");
			Korisnik menadzer = (Korisnik)request.getSession().getAttribute("menadzer");
			System.out.println("menadzer: " + menadzer);
			Restoran restoran = menadzer.getRestoran();
			System.out.println("restoran: " + restoran);
			request.setAttribute("jelovnici", jelovnikDao.findJelovniciRestorana(restoran));
			System.out.println("jelovnici restorana");
			/*for (Jelovnik j : jelovnikDao.findJelovniciRestorana(restoran)) {
				System.out.println(j);
			}*/
			//request.setAttribute("restoran", restoran);
			
			getServletContext().getRequestDispatcher("/jelovniciMenadzer.jsp").forward(request, response);
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
