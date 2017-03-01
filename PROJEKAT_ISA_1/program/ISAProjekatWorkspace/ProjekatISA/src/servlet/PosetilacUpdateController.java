package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.Korisnik;
import session.KorisnikDaoLocal;

public class PosetilacUpdateController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3825828009402162381L;
	
	private static Logger log = Logger.getLogger(PosetilacUpdateController.class);
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("posetilac")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			System.out.println("************izmena posetioca zapoceta");
			Korisnik posetilac = (Korisnik)request.getSession().getAttribute("posetilac");
			System.out.println("posetilac: " + posetilac);
			
			//***izmena posetioca zapoceta**********
			posetilac.setIme(request.getParameter("ime"));
			posetilac.setAdresa(request.getParameter("adresa"));
			korisnikDao.merge(posetilac);
			//**************************************
			
			response.sendRedirect(response.encodeRedirectURL("./PosetilacReadController"));
			return;
			
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
