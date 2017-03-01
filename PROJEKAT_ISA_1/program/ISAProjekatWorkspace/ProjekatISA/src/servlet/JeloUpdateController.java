package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.Jelo;
import entity.Korisnik;
import session.JeloDaoLocal;

public class JeloUpdateController extends HttpServlet {

	private static final long serialVersionUID = -8102800956683392387L;

	private static Logger log = Logger.getLogger(JeloUpdateController.class);
	
	@EJB
	private JeloDaoLocal jeloDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("admin")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			System.out.println("************izmena jela zapoceta");
			Korisnik admin = (Korisnik)request.getSession().getAttribute("admin");
			System.out.println("admin: " + admin);
			
			//****brisanje jela**********
			Jelo novoJelo = jeloDao.findById(Integer.parseInt(request.getParameter("id")));
			novoJelo.setNazivJela(request.getParameter("naziv"));
			novoJelo.setOpisJela(request.getParameter("opis"));
			
			jeloDao.merge(novoJelo);
			
			System.out.println("izmena jela zavrsena");
			
			//*****************************
			
			getServletContext().getRequestDispatcher("/JelaAdminController").forward(request, response);
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
