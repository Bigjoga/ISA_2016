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

public class PozivnicaController extends HttpServlet {

	private static final long serialVersionUID = 7900445879419946213L;

	private static Logger log = Logger.getLogger(PozivnicaController.class);
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private PozivPrijateljaDaoLocal pozivPrijateljaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("posetilac")) == null) {
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				//response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			System.out.println("************prikaz pozivnice zapocet");
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
				//response.sendRedirect(response.encodeURL("./PosetePosetilacController"));
				//return;
				//zbog toga se prebacuje na logovanje koje ce ga (ako je uspesno) redirektovati na pozivnicu
				response.sendRedirect(response.encodeURL("./PreLoginController?id="+pozivnicaId));
				return;
			}
			
			ArrayList<Korisnik> ostaliGosti = new ArrayList<Korisnik>();
			for (Korisnik kor : pozivPrijateljaDao.findDrugiPosetiociRezervacije(pp.getRezervacija(), posetilac)) {
				//kreatora rezervacije izdvajam iz liste gostiju (on ce se posebno prikazati)
				if (!kor.getId().toString().equals(pp.getRezervacija().getKorisnik().getId().toString())) {
					ostaliGosti.add(kor);
				}
			}
			System.out.println("broj ostalih gostiju: " + ostaliGosti.size());
			request.setAttribute("gosti", ostaliGosti);
			System.out.println("datum: " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(pp.getRezervacija().getDatumVreme()));
			request.setAttribute("datumStr", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(pp.getRezervacija().getDatumVreme()));
			System.out.println("naziv: " + pp.getRezervacija().getRestoran().getNazivRestorana());
			request.setAttribute("naziv", pp.getRezervacija().getRestoran().getNazivRestorana());
			request.setAttribute("trajanje", pp.getRezervacija().getTrajanje());
			request.setAttribute("autor", pp.getRezervacija().getKorisnik().getIme());
			
			
			System.out.println("parametri za prikaz pozivnice prosledjeni na jsp");
			getServletContext().getRequestDispatcher("/pozivnica.jsp").forward(request, response);
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
