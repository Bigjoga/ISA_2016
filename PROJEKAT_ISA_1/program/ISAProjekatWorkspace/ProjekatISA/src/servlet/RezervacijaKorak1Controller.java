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

public class RezervacijaKorak1Controller extends HttpServlet {

	private static final long serialVersionUID = -8720094904442088993L;

	private static Logger log = Logger.getLogger(RezervacijaKorak1Controller.class);
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("posetilac")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			System.out.println("************korak 1 rezervacije zapocet");
			Korisnik posetilac = (Korisnik)request.getSession().getAttribute("posetilac");
			System.out.println("posetilac: " + posetilac);
			
			Restoran res = restoranDao.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("res", res);
			
			System.out.println("parametri za korak 1 rezervacije prosledjeni na jsp");
			getServletContext().getRequestDispatcher("/rezervacijaKorak1.jsp").forward(request, response);
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
