package Email.JavaMailAPI;

import java.util.Properties;

import javax.mail.*;    
import javax.mail.internet.*;
import javax.mail.Session;

public class Mailer {
	
	final static String from = "aavishkar.abes@gmail.com";
	final static String password = "aavishkar@123";
	static String to = "rohan.gupta@gofro.com";
	static String sub = "Hello !";
	static String msg = "Java mail sending";
	 public static void send(){  
	 
		 //Get properties object
		 
		 Properties props = new Properties();
		 props.put("mail.smtp.host", "smtp.gmail.com");    
         props.put("mail.smtp.socketFactory.port", "465");    
         props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
         props.put("mail.smtp.auth", "true");    
         props.put("mail.smtp.port", "465");
         
         //Get session
         
         Session session = Session.getDefaultInstance(props,    
                 new javax.mail.Authenticator() {    
                 protected PasswordAuthentication getPasswordAuthentication() {    
                 return new PasswordAuthentication(from,password);  
                 }    
                });  
         
         //compose message    
         try {    
          MimeMessage message = new MimeMessage(session);    
          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
          message.setSubject(sub);    
          message.setText(msg);    
          //send message  
          Transport.send(message);    
          System.out.println("message sent successfully");    
         } catch (MessagingException e) {throw new RuntimeException(e);}    
            
   }  
}  