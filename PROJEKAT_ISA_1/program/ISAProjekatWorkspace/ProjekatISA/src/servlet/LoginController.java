package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import entity.Korisnik;
import session.KorisnikDaoLocal;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = -7345471861052209628L;
	
	private static Logger log = Logger.getLogger(LoginController.class);

	@EJB
	private KorisnikDaoLocal korisnikDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String email = request.getParameter("email");
			String lozinka = request.getParameter("lozinka");
			
			if ((email == null) || (email.equals("")) || (lozinka == null) || (lozinka.equals(""))) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
				return;
			}
			
			Korisnik korisnik = korisnikDao.findKorisnikSaEmailomILozinkom(email, lozinka);
			
			if (korisnik != null) {	
				HttpSession session = request.getSession(true);
				//session.setAttribute("admin", korisnik);
				log.info("Korisnik " + korisnik.getIme() + " se prijavio.");
				if (korisnik.getUloga().getNazivUloge().equals("posetilac")) {
					//getServletContext().getRequestDispatcher("/ReadController").forward(request, response);	
					//response.sendRedirect(response.encodeRedirectURL("./PosetilacReadController"));
					session.setAttribute("posetilac", korisnik);
					//@@@@@@@@ PROVERA DA LI TREBA REDIREKTOVATI NA POZIVNICU @@@@@@
					System.out.println("pozId:" + request.getParameter("pozId") + "krajISPISA@@@@@@@@@");
					if (request.getParameter("pozId")!=null && !request.getParameter("pozId").equals("")) {
						System.out.println("@@@@@ preusmeravanje na pozivnicu @@@@@@@@@@@@");
						response.sendRedirect(response.encodeRedirectURL("./PozivnicaController?id=" + request.getParameter("pozId")));
						return;
					}
					//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					response.sendRedirect(response.encodeRedirectURL("./PosetilacReadController"));
					return;
				} else if (korisnik.getUloga().getNazivUloge().equals("menadzer")) {
					session.setAttribute("menadzer", korisnik);
					//***da bi se tokom cele sesije znalo za koji restoran je korisnik menadzer
					session.setAttribute("restoran", korisnik.getRestoran());
					//*************************************************************************
					response.sendRedirect(response.encodeRedirectURL("./JelovniciMenadzerController"));
					//getServletContext().getRequestDispatcher("/JelovniciMenadzerController").forward(request, response);	
					//response.sendRedirect(response.encodeRedirectURL("./jelovniciMenadzer.jsp"));
					return;
				}  else if (korisnik.getUloga().getNazivUloge().equals("administrator")) {
					session.setAttribute("admin", korisnik);
					response.sendRedirect(response.encodeRedirectURL("./PocetnaAdminController"));
					//getServletContext().getRequestDispatcher("/PocetnaAdminController").forward(request, response);	
					//response.sendRedirect(response.encodeRedirectURL("./pocetnaAdmin.jsp"));
					return;
				}
				if (false) {
					session.setAttribute("admin", korisnik);
					getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
				}
			}
			
		} catch (EJBException e) {
			if (e.getCause().getClass().equals(NoResultException.class)) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
			} else {
				throw e;
			}
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