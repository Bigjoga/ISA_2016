package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.Korisnik;
import entity.Prijatelji;
import entity.Restoran;
import session.KorisnikDaoLocal;
import session.PrijateljiDaoLocal;
import session.RestoranDaoLocal;
import session.UlogaKorisnikaDaoLocal;

public class MenadzerCreateController extends HttpServlet {

	private static final long serialVersionUID = -3650606222466810752L;
	
	private static Logger log = Logger.getLogger(MenadzerCreateController.class);
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private UlogaKorisnikaDaoLocal ulogaDao;
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private PrijateljiDaoLocal prijateljiDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("admin")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			System.out.println("************dodavanje menadzera restorana zapoceto");
			Korisnik admin = (Korisnik)request.getSession().getAttribute("admin");
			System.out.println("admin: " + admin);
			
			String restoranId = request.getParameter("resId");
			if (restoranId == null) {
				getServletContext().getRequestDispatcher("/PocetnaAdminController").forward(request, response);	
				//response.sendRedirect(response.encodeURL("./jelovniciMenadzer.jsp"));
				return;
			}
			String korisnikId = request.getParameter("korId");
			if (korisnikId == null) {
				getServletContext().getRequestDispatcher("/PocetnaAdminController").forward(request, response);	
				//response.sendRedirect(response.encodeURL("./jelovniciMenadzer.jsp"));
				return;
			}
			
			//****dodavanje menadzera restorana zapoceto**********
			Restoran res = restoranDao.findById(Integer.parseInt(restoranId));
			Korisnik men = korisnikDao.findById(Integer.parseInt(korisnikId));
			//@@@@@@@S OBZIROM DA POSETILAC POSTAJE MENADZER, OBRISATI SVA NJEGOVA PRIJATELJSTVA@@@@@
			List<Prijatelji> lista = prijateljiDao.findAll();
			for (Prijatelji prija : lista) {
				if (
						(prija.getIdKor1().getId().toString().equals(men.getId().toString())
						) ||
						(prija.getIdKor2().getId().toString().equals(men.getId().toString())
						) 
					) 
				{
					prijateljiDao.remove(prija);
					
					break;
				}
			}
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			men.setUloga(ulogaDao.findUlogaPoImenu(1));
			res.add(men);
			korisnikDao.merge(men);
			restoranDao.merge(res);
			System.out.println("postavljen menadzer restorana: " + men);
			//*****************************************************
			
			response.sendRedirect(response.encodeURL("./PreUpRestoranController?id="+res.getId()));
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
