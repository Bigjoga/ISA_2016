package servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import entity.Korisnik;
import entity.PozivPrijatelja;
import session.KorisnikDaoLocal;
import session.UlogaKorisnikaDaoLocal;

public class PosetilacCreateController extends HttpServlet {

	private static final long serialVersionUID = -5580469975218486475L;

	private static Logger log = Logger.getLogger(PosetilacCreateController.class);

	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private UlogaKorisnikaDaoLocal ulogaDao;
	
	@Resource(name="JmsConnectionFactory")
	private ConnectionFactory qcf;

	@Resource(name="RegistracijaQueue")
	private Queue registracijaQueue;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String email = request.getParameter("email");
			String lozinka = request.getParameter("lozinka");
			
			if ((email == null) || (email.equals("")) || (lozinka == null) || (lozinka.equals(""))) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
				return;
			}
			
			System.out.println("***************pocetak registracije zapocet");
			//Korisnik korisnik = new Korisnik();
			Korisnik korisnik = korisnikDao.findKorisnikRegistracija(email);
			
			System.out.println("korisnik dobijen upitom: " + korisnik);
			
			if (korisnik!=null) {
				//korisnik sa datim emailom vec postoji!!!
				response.sendRedirect(response.encodeRedirectURL("./PreLoginController?upoz=1"));
				//response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
				return;
			}
			
			System.out.println("prelazi se na pripremu za registraciju");
			
			String ime = request.getParameter("ime");
			String adresa = request.getParameter("adresa");
			
			Korisnik noviKorisnik = new Korisnik(email, lozinka, ime, adresa, ulogaDao.findUlogaPoImenu(2), null);
			
			
			//@@@@@@@@@@@ POSLATI MAIL KORISNIKU NA DATU ADRESU ZA REGISTRACIJU @@@@@@@@@@@@@
			Connection connection = null;
			Session session = null;
			MessageProducer producer = null;
			
			try {
				// Creates a connection
				connection = qcf.createConnection();

				
	            // Creates a session
	            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

	            // Creates a message producer from the Session to the Topic or Queue
	            producer = session.createProducer(registracijaQueue);

	            // Creates an object message
	            ObjectMessage object = session.createObjectMessage();
	            System.out.println("@@@@@@poslat mail za registraciju korisnika: " + noviKorisnik);
	            object.setObject(noviKorisnik);
	            
	            // Tells the producer to send the object message
	            producer.send(object);
	            
	            // Closes the producer
	            producer.close();
	            
	            
	            // Closes the session
	            session.close();
	            
	            // Closes the connection
	            connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			
			
			response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
			//getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			
		} catch (EJBException e) {
			if (e.getCause().getClass().equals(NoResultException.class)) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
			} else {
				throw e;
			}
		/*} catch (ServletException e) {
			log.error(e);
			throw e;
		*/} catch (IOException e) {
			log.error(e);
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
