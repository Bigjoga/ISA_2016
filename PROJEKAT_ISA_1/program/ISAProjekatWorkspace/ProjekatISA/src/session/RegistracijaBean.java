package session;

import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import entity.Korisnik;
import entity.PozivPrijatelja;

@MessageDriven(
		activationConfig = {
			@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
			@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
			@ActivationConfigProperty(propertyName = "destination", propertyValue = "RegistracijaQueue") 
		}
	)
public class RegistracijaBean implements MessageListener{

	@Resource(name="Mail")
	Session session;

	public RegistracijaBean() {

	}

	public void onMessage(Message message) {
		
	    try {
	        if (message instanceof ObjectMessage) {
	            ObjectMessage obj = (ObjectMessage) message;
	            //PaymentInfo info = (PaymentInfo)obj.getObject();
	            Korisnik info = (Korisnik)obj.getObject();
	            
	            // Validate the credit card using web services...
	            
	            sendMessage(info);
	        } else {
	            System.out.println("MESSAGE BEAN: Message of wrong type: " + message.getClass().getName());
	        }
	    } catch (JMSException e) {
	        e.printStackTrace();
	    } catch (Throwable te) {
	        te.printStackTrace();
	    }
	}

	private void sendMessage(/*PaymentInfo*/Korisnik info) throws AddressException, MessagingException {

		// Constructs the message 
		javax.mail.Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("ftnmutic@gmail.com"));
		//msg.setRecipients(RecipientType.TO, InternetAddress.parse("<username>@gmail.com"));
		msg.setRecipients(RecipientType.TO, InternetAddress.parse(info.getEmail()));
		msg.setSubject("Registracija na sajt ISA");
		//msg.setText("This is a text message sent using JMS!");
		String linkStr = "http://localhost:8081/Vezbe09/PotvrdaRegistracijeController?" + 
				"email=" + info.getEmail() + "&lozinka=" + info.getSifra() + "&ime=" + info.getIme() + "&adresa=" + info.getAdresa();
		linkStr = linkStr.replaceAll(" ", "%20");
		msg.setText("Postovani " + info.getIme() + "," + System.lineSeparator() +"Registraciju mozete potvrditi putem sledeceg linka:" + System.lineSeparator() + linkStr);
		msg.setSentDate(new Date());
		
		// Sends the message
		Transport.send(msg);

		System.out.println("MESSAGE BEAN: Mail was sent successfully.");
	}


}
