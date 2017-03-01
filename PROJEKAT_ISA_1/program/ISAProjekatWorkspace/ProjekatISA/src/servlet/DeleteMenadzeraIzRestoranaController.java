package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.Korisnik;
import entity.Restoran;
import session.KorisnikDaoLocal;
import session.RestoranDaoLocal;
import session.UlogaKorisnikaDaoLocal;

public class DeleteMenadzeraIzRestoranaController extends HttpServlet {

	private static final long serialVersionUID = -6077687710598128914L;
	
	private static Logger log = Logger.getLogger(DeleteMenadzeraIzRestoranaController.class);
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private UlogaKorisnikaDaoLocal ulogaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("admin")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			System.out.println("************brisanje menadzera iz restorana zapoceta");
			Korisnik admin = (Korisnik)request.getSession().getAttribute("admin");
			System.out.println("admin: " + admin);
			
			String menadzerId = request.getParameter("id");
			if (menadzerId == null) {
				getServletContext().getRequestDispatcher("/PocetnaAdminController").forward(request, response);	
				//response.sendRedirect(response.encodeURL("./jelovniciMenadzer.jsp"));
				return;
			}
			
			//****priprema za izmenu restorana**********
			Korisnik menadzer = korisnikDao.findById(Integer.parseInt(request.getParameter("id")));
			Restoran restoran = menadzer.getRestoran();
			restoran.remove(menadzer);
			restoranDao.merge(restoran);
			menadzer.setUloga(ulogaDao.findUlogaPoImenu(2));
			korisnikDao.merge(menadzer);
			System.out.println("menader izbacen iz restorana: " + menadzer);
			//*****************************
			
			response.sendRedirect(response.encodeURL("./PreUpRestoranController?id="+restoran.getId()));
			//getServletContext().getRequestDispatcher("/PreUpRestoranController?id="+restoran.getId()).forward(request, response);
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
