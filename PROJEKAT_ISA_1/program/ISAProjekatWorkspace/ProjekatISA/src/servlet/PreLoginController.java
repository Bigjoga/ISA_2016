package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import session.KorisnikDaoLocal;

public class PreLoginController extends HttpServlet {

	private static final long serialVersionUID = 296558778326511043L;

	private static Logger log = Logger.getLogger(PreLoginController.class);

	@EJB
	private KorisnikDaoLocal korisnikDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			
			String pozivnicaId = request.getParameter("id");
			System.out.println("id koji se dobija od maila zbog pozivnice: " + pozivnicaId);

			HttpSession session = request.getSession();
			session.invalidate();
			
			request.setAttribute("id", pozivnicaId);
			
			if (request.getParameter("upoz")==null) {
				System.out.println("nije stigao parametar upoz");
				request.setAttribute("upoz", 0);
			} else {
				if (request.getParameter("upoz").equals("1"))
					request.setAttribute("upoz", 1);
				else
					request.setAttribute("upoz", 0);
			}
			
			//response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			
		} catch (EJBException e) {
			if (e.getCause().getClass().equals(NoResultException.class)) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
			} else {
				throw e;
			}
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
