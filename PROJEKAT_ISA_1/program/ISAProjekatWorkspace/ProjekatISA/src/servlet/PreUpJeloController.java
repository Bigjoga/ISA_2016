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

public class PreUpJeloController extends HttpServlet {

	private static final long serialVersionUID = 1166501027729778225L;

	private static Logger log = Logger.getLogger(PreUpJeloController.class);
	
	@EJB
	private JeloDaoLocal jeloDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("admin")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			System.out.println("************priprema izmene jela zapoceta");
			Korisnik admin = (Korisnik)request.getSession().getAttribute("admin");
			System.out.println("admin: " + admin);
			
			//****priprema izmene jela**********
			Jelo jeloZaIzmenu = jeloDao.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("jelo", jeloZaIzmenu);
			
			System.out.println("priprema izmene jela zavrsena");
			
			//*****************************
			
			getServletContext().getRequestDispatcher("/jeloUpdate.jsp").forward(request, response);
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
