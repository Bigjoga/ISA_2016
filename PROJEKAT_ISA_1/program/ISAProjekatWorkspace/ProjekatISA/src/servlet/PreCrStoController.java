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

public class PreCrStoController extends HttpServlet {

	private static final long serialVersionUID = 156601864840403550L;

	private static Logger log = Logger.getLogger(PreCrStoController.class);
	
	@EJB
	private StoDaoLocal stoDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			System.out.println();
			if ((request.getSession().getAttribute("menadzer")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			if ((request.getParameter("pozX")) == null || request.getParameter("pozX").equals("")
					|| (request.getParameter("pozY")) == null || request.getParameter("pozY").equals("") ) {
				getServletContext().getRequestDispatcher("/StoloviMenadzerContoller").forward(request, response);
				return;
			}
			System.out.println("************priprema dodavanja novog stola zapoceta");
			Korisnik menadzer = (Korisnik)request.getSession().getAttribute("menadzer");
			System.out.println("menadzer: " + menadzer);
			Restoran restoran = menadzer.getRestoran();
			System.out.println("restoran: " + restoran);
			
			//****priprema**********
			request.setAttribute("pozX", request.getParameter("pozX"));
			request.setAttribute("pozY", request.getParameter("pozY"));
			//*****************************
			
			getServletContext().getRequestDispatcher("/stoCreate.jsp").forward(request, response);
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