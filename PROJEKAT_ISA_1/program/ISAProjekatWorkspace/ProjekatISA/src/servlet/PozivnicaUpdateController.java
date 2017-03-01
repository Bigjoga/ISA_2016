package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.Korisnik;
import entity.PozivPrijatelja;
import session.KorisnikDaoLocal;
import session.PozivPrijateljaDaoLocal;

public class PozivnicaUpdateController extends HttpServlet {

	private static final long serialVersionUID = 7024190328270516646L;
	
	private static Logger log = Logger.getLogger(PozivnicaUpdateController.class);
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private PozivPrijateljaDaoLocal pozivPrijateljaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("posetilac")) == null) {
				//getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			System.out.println("************izmena pozivnice zapoceta");
			Korisnik posetilac = (Korisnik)request.getSession().getAttribute("posetilac");
			System.out.println("posetilac: " + posetilac);
			
			String pozivnicaId = request.getParameter("id");
			if (pozivnicaId == null) {
				response.sendRedirect(response.encodeURL("./PosetePosetilacController"));
				return;
			}
			
			PozivPrijatelja pp = pozivPrijateljaDao.findById(Integer.parseInt(pozivnicaId));
			if (pp.getPotvrdio()) {
				response.sendRedirect(response.encodeURL("./PosetePosetilacController"));
				return;
			}
			
			request.setAttribute("poziv", pp);
			System.out.println("pozivnica: " + pp);
			//samo korisnik kome je pozivnica namenjena moze da je vidi
			if (!pp.getKorisnik().getId().toString().equals(posetilac.getId().toString())) {
				response.sendRedirect(response.encodeURL("./PosetePosetilacController"));
				return;
			}
			
			pp.setPotvrdio(true);
			pozivPrijateljaDao.merge(pp);

			System.out.println("izmena pozivnice zavrsena");
			response.sendRedirect(response.encodeURL("./PosetePosetilacController"));
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
