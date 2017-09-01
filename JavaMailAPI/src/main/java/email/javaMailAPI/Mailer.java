package email.javaMailAPI;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class Mailer {

	private final static String from = "aavishkar.abes@gmail.com";
	private final static String password = "aavishkar@123";

	private BodyPart messageBodyPart1;
	private BodyPart messageBodyPart2;
	private Multipart multipart;

	private final static Properties props = new Properties();
	private static Session session;
	MimeMessage message = new MimeMessage(session);

	// -----------------Static block to initialize properties-----------------

	static {
		props.put(Property.SMTP_HOST.getKey(), Property.SMTP_HOST.getValue());
		props.put(Property.SMTP_SOCKETPORT.getKey(), Property.SMTP_PORT.getValue());
		props.put(Property.SMTP_SOCKET.getKey(), Property.SMTP_SOCKET.getValue());
		props.put(Property.SMTP_AUTH.getKey(), Property.SMTP_AUTH.getValue());
		props.put(Property.SMTP_PORT.getKey(), Property.SMTP_PORT.getValue());
		System.out.println("Properties loaded");
		session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
	}

	/*-----------------------PRIVATE METHODS FOR INTERNAL USE----------------------------*/
	private void setSubject(String subject) {
		try {
			message.setSubject(subject);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setMsg(String msg) {

		try {
			messageBodyPart1.setText(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * Methods to attach files in mail you need to pass filepath in string as
	 * parameter
	 */

	private void attachFile(String filename) {
		messageBodyPart2 = new MimeBodyPart();
		DataSource source = new FileDataSource(filename);
		try {
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);
			multipart.addBodyPart(messageBodyPart2);
		} catch (MessagingException e) {
			System.out.println("Error attaching file");
			e.printStackTrace();
		}
	}

	/*------------------------------Methods to set recipient------------------------------------
	 * prefix of method will remain fixed i.e. setRecipient__
	 * to add recipients to TO  write setRecipientTo
	 * to add recipients to CC  write setRecipientsCc
	 * to add recipients to BCC write setRecipientsBcc
	 * Recipients email address will be given as String
	 * Input to be given comma separated in case of multiple email ids
	 * Ex- for single email id, write as emailaddress@host.com
	 * However in case of multiple email addresses write as emailaddress@host.com,emailaddress1@host1.com and so on
	*/

	// To add recipient(s) in TO

	private void setRecipientTo(String email) {
		try {
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		} catch (AddressException e) {
			System.out.println("Error adding recipients");
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// To add recipient(s) in CC

	private void setRecipientCc(String email) {
		try {
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(email));
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// To add recipient(s) in BCC

	private void setRecipientBcc(String email) {
		try {
			message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(email));
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean sendMail() {
		try {
			multipart.addBodyPart(messageBodyPart1);
			message.setContent(multipart);
			Transport.send(message);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/*-------------------------------Public Methods-------------------------------*/
	
	public boolean sendMail(EmailContent content , EmailRecipients recipients)
	{
		//setting email content
		messageBodyPart1 = new MimeBodyPart();
		multipart = new MimeMultipart();
		setSubject(content.getSubject());
		setMsg(content.getMessage());
		if(content.getFile() != null)
		{
			attachFile(content.getFile());
		}
		
		//setting recipients
		
		setRecipientTo(recipients.getTo());
		
		if(recipients.getCc()!=null)
		{
			setRecipientCc(recipients.getCc());
		}
		
		if(recipients.getBcc()!=null)
		{
			setRecipientBcc(recipients.getBcc());
		}
		return sendMail();
		
	}
	
}