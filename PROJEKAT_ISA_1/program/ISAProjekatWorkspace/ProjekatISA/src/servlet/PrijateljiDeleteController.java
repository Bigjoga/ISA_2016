package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.Korisnik;
import entity.Prijatelji;
import session.KorisnikDaoLocal;
import session.PrijateljiDaoLocal;

public class PrijateljiDeleteController extends HttpServlet {

	private static final long serialVersionUID = -153567862515161304L;
	
	private static Logger log = Logger.getLogger(PrijateljiDeleteController.class);
	
	@EJB
	private PrijateljiDaoLocal prijateljiDao;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("posetilac")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			System.out.println("************brisanje prijatelja zapoceto");
			Korisnik posetilac = (Korisnik)request.getSession().getAttribute("posetilac");
			System.out.println("posetilac: " + posetilac);
			Korisnik prijateljZaBrisanje = korisnikDao.findById(Integer.parseInt(request.getParameter("korId")));
			
			//***zapoceto brisanje prijatelja
			List<Prijatelji> lista = prijateljiDao.findAll();
			for (Prijatelji prija : lista) {
				if (
						(prija.getIdKor1().getId().toString().equals(posetilac.getId().toString()) && 
						prija.getIdKor2().getId().toString().equals(prijateljZaBrisanje.getId().toString())
						) ||
						(prija.getIdKor2().getId().toString().equals(posetilac.getId().toString()) && 
						prija.getIdKor1().getId().toString().equals(prijateljZaBrisanje.getId().toString())
						) 
					) 
				{
					prijateljiDao.remove(prija);
					
					break;
				}
			}
			if (request.getParameter("adr")!=null && request.getParameter("adr").toString().equals("pr")) {
				response.sendRedirect(response.encodeRedirectURL("./PrijateljiReadController"));
				return;
			}
			
			response.sendRedirect(response.encodeRedirectURL("./PosetilacReadController"));
			return;
			
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
