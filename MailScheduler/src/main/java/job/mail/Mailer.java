package job.mail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Mailer {

	private static final Logger LOG = LoggerFactory.getLogger(Mailer.class);
	private String from;
	private String password;

	private BodyPart messageBodyPart1;
	private BodyPart messageBodyPart2;
	private Multipart multipart;

	private final static Properties props = new Properties();
	private Session session;
	MimeMessage message;

	// -----------------Static block to initialize properties-----------------

	static {
		props.put(Property.SMTP_HOST.getKey(), Property.SMTP_HOST.getValue());
		props.put(Property.SMTP_SOCKETPORT.getKey(), Property.SMTP_PORT.getValue());
		props.put(Property.SMTP_SOCKET.getKey(), Property.SMTP_SOCKET.getValue());
		props.put(Property.SMTP_AUTH.getKey(), Property.SMTP_AUTH.getValue());
		props.put(Property.SMTP_PORT.getKey(), Property.SMTP_PORT.getValue());
	}

	/*-----------------------PRIVATE METHODS FOR INTERNAL USE----------------------------*/

	private void getSession(String sender) throws Exception
	{
		File file = new File("/home/rohan/mail-scheduler/setup/configuration.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties propsMail = new Properties();
		propsMail.load(fis);
		this.from = sender;
		this.password = propsMail.getProperty(sender); 
		
		session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		message = new MimeMessage(session);
	}
	
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
			LOG.info("mail sent successfully");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Error sending mail");
			e.printStackTrace();
			return false;
		}
	}

	/*-------------------------------Public Methods-------------------------------*/

	public boolean sendMail(EmailContent content, EmailRecipients recipients) throws Exception {
		// setting email content
		
		getSession(content.getFrom());
		messageBodyPart1 = new MimeBodyPart();
		multipart = new MimeMultipart();
		setSubject(content.getSubject());
		setMsg(content.getMessage());
		
		// setting recipients

		if (recipients.getTo() != null) {
			setRecipientTo(recipients.getTo());
		}

		if (recipients.getCc() != null) {
			setRecipientCc(recipients.getCc());
		}

		if (recipients.getBcc() != null) {
			setRecipientBcc(recipients.getBcc());
		}
		
		//Attach File
		
		//1. Download File 2. Attach
		
		attachFile(downloadFile(content.getUrl(), content.getFileName()));
		
		return sendMail();
	}

	// Download file
	private String downloadFile(String apiurl, String filename) {
		URL url = null;
		URLConnection connection = null;
		int i;
		try {
			url = new URL(apiurl);
			connection = url.openConnection();
			File file = new File(filename);
			BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file.getName()));
			while ((i = bis.read()) != -1) {
				bos.write(i);
			}
			bos.flush();
			bis.close();
			bos.close();
			LOG.info("File downloaded successfully");
		} catch (MalformedInputException e) {
			LOG.error("Error downloading file");
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error("Error downloading file");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			LOG.error("Error downloading file");
			e.printStackTrace();
		}
		
		return filename;
	}

}