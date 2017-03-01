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
import entity.Sto;
import session.PrijateljiDaoLocal;
import session.StoDaoLocal;

public class RezervacijaKorak3Controller extends HttpServlet {

	private static final long serialVersionUID = -7771532972230530526L;
	
	private static Logger log = Logger.getLogger(RezervacijaKorak3Controller.class);
	
	@EJB
	private StoDaoLocal stoDao;
	
	@EJB
	private PrijateljiDaoLocal prijateljiDao;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("posetilac")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			System.out.println("************korak 3 rezervacije zapocet");
			Korisnik posetilac = (Korisnik)request.getSession().getAttribute("posetilac");
			System.out.println("posetilac: " + posetilac);
			
			String stoloviStringId = request.getParameter("id");
			
			if (stoloviStringId == null) {
				response.sendRedirect(response.encodeURL("./RestoraniPosetilacController"));
				return;
			}
			String[] tokens = stoloviStringId.split("_");
			System.out.println("broj rezervisanih stolova: " + tokens.length);
			if (tokens.length<1) {
				response.sendRedirect(response.encodeURL("./RestoraniPosetilacController"));
				return;
			}
			
			ArrayList<Sto> stolovi = new ArrayList<Sto>();
			for (int i=0; i<tokens.length; i++) {
				System.out.println("sto oznacen za rezervaciju: " + stoDao.findById(Integer.parseInt(tokens[i])));
				stolovi.add(stoDao.findById(Integer.parseInt(tokens[i])));
			}
			
			//**obrisati i stolove iz sesije nakon uspesnog kreiranja REZERVACIJE!!!
			request.getSession().setAttribute("stolovi", stolovi);		
			//**********************************************************************
			
			request.setAttribute("prijatelji", prijateljiDao.findPrijateljiKorisnika(posetilac));
			
			
			System.out.println("parametri za korak 3 rezervacije prosledjeni na jsp");
			getServletContext().getRequestDispatcher("/rezervacijaKorak3.jsp").forward(request, response);
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
