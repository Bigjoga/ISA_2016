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

public class StoCreateController extends HttpServlet {

	private static final long serialVersionUID = 3159719705495444762L;

	private static Logger log = Logger.getLogger(StoCreateController.class);
	
	@EJB
	private StoDaoLocal stoDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("menadzer")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			if ((request.getParameter("pozX")) == null || request.getParameter("pozX").equals("")
					|| (request.getParameter("pozY")) == null || request.getParameter("pozY").equals("") ) {
				getServletContext().getRequestDispatcher("/StoloviMenadzerContoller").forward(request, response);
				return;
			}
			System.out.println("************dodavanje novog stola zapoceto");
			Korisnik menadzer = (Korisnik)request.getSession().getAttribute("menadzer");
			System.out.println("menadzer: " + menadzer);
			Restoran restoran = menadzer.getRestoran();
			System.out.println("restoran: " + restoran);
			
			//****dodavanje stola**********
			Sto noviSto = new Sto();
			String naziv = request.getParameter("naziv").toString();
			System.out.println("naziv: " + naziv);
			noviSto.setNazivStola(naziv);
			Integer brojMesta = Integer.parseInt(request.getParameter("brojMesta").toString());
			System.out.println("broj mesta: " + brojMesta);
			noviSto.setBrojMesta(brojMesta);
			noviSto.setPozX(Integer.parseInt(request.getParameter("pozX").toString()));
			noviSto.setPozY(Integer.parseInt(request.getParameter("pozY").toString()));
			noviSto.setRestoran(restoran);
			stoDao.persist(noviSto);
			System.out.println("novi sto dodat: " + noviSto );
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
