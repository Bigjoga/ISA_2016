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
import session.RestoranDaoLocal;

public class PreUpRestoranController extends HttpServlet {

	private static final long serialVersionUID = 76135701967909106L;
	
	private static Logger log = Logger.getLogger(PreUpRestoranController.class);
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("admin")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			System.out.println("************priprema izmene restorana zapoceta");
			Korisnik admin = (Korisnik)request.getSession().getAttribute("admin");
			System.out.println("admin: " + admin);
			
			String restoranId = request.getParameter("id");
			System.out.println("restoranId: " + restoranId);
			if (restoranId == null) {
				getServletContext().getRequestDispatcher("/PocetnaAdminController").forward(request, response);	
				//response.sendRedirect(response.encodeURL("./jelovniciMenadzer.jsp"));
				return;
			}
			
			//****priprema za izmenu restorana**********
			request.setAttribute("restoran", restoranDao.findById(Integer.parseInt(restoranId)));
			Restoran res = restoranDao.findById(Integer.parseInt(restoranId));
			ArrayList<Korisnik> menadzeri = new ArrayList<Korisnik>();
			for (Korisnik men : res.getMenadzeri()) {
				menadzeri.add(men);
			}
			request.setAttribute("menadzeri", menadzeri);
			System.out.println("setovani podaci za update restorana");
			//*****************************
			
			getServletContext().getRequestDispatcher("/restoranUpdate.jsp").forward(request, response);
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
