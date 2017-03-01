package session;

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

import org.omg.CORBA.Environment;

import entity.PozivPrijatelja;

@MessageDriven(
		activationConfig = {
			@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
			@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
			@ActivationConfigProperty(propertyName = "destination", propertyValue = "PaymentQueue") 
		}
	)
	public class PaymentBean implements MessageListener {
		
		@Resource(name="Mail")
		Session session;

		public PaymentBean() {

		}

		public void onMessage(Message message) {
			
		    try {
		        if (message instanceof ObjectMessage) {
		            ObjectMessage obj = (ObjectMessage) message;
		            //PaymentInfo info = (PaymentInfo)obj.getObject();
		            PozivPrijatelja info = (PozivPrijatelja)obj.getObject();
		            
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

		private void sendMessage(/*PaymentInfo*/PozivPrijatelja info) throws AddressException, MessagingException {

			// Constructs the message 
			javax.mail.Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("ftnmutic@gmail.com"));
			//msg.setRecipients(RecipientType.TO, InternetAddress.parse("<username>@gmail.com"));
			msg.setRecipients(RecipientType.TO, InternetAddress.parse(info.getKorisnik().getEmail()));
			msg.setSubject("Pozivnica u restoran " + info.getRezervacija().getRestoran().getNazivRestorana());
			//msg.setText("This is a text message sent using JMS!");
			msg.setText("Postovani " + info.getKorisnik().getIme() + "," + System.lineSeparator() +"Na pozivnicu mozete odgovoriti putem sledeceg linka:" + System.lineSeparator() + "http://localhost:8081/Vezbe09/PozivnicaController?id="+info.getId());
			msg.setSentDate(new Date());
			
			// Sends the message
			Transport.send(msg);

			System.out.println("MESSAGE BEAN: Mail was sent successfully.");
		}
	}
