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
import entity.Sto;
import session.StoDaoLocal;

public class StoUpdateController extends HttpServlet {

	private static final long serialVersionUID = 2916048299736246410L;

	private static Logger log = Logger.getLogger(StoUpdateController.class);
	
	@EJB
	private StoDaoLocal stoDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			System.out.println();
			if ((request.getSession().getAttribute("menadzer")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			if ((request.getParameter("id")) == null || request.getParameter("id").equals("")) {
				getServletContext().getRequestDispatcher("/StoloviMenadzerContoller").forward(request, response);
				return;
			}
			System.out.println("************izmena stola zapoceta");
			Korisnik menadzer = (Korisnik)request.getSession().getAttribute("menadzer");
			System.out.println("menadzer: " + menadzer);
			Restoran restoran = menadzer.getRestoran();
			System.out.println("restoran: " + restoran);
			
			//****update stola**********
			Sto stariSto = stoDao.findById(Integer.parseInt(request.getParameter("id")));
			Sto noviSto = new Sto();
			noviSto.setId(new Integer(stariSto.getId()));
			noviSto.setNazivStola(request.getParameter("naziv"));
			noviSto.setBrojMesta(Integer.parseInt(request.getParameter("brojMesta").toString()));
			noviSto.setPozX(Integer.parseInt(request.getParameter("pozX").toString()));
			noviSto.setPozY(Integer.parseInt(request.getParameter("pozY").toString()));
			
			stoDao.merge(noviSto);
			//*****************************
			
			getServletContext().getRequestDispatcher("/StoloviMenadzerContoller").forward(request, response);
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
