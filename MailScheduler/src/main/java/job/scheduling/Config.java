package job.scheduling;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import job.mail.EmailContent;
import job.mail.EmailRecipients;

public class Config {

	private static final Logger LOG = LoggerFactory.getLogger(Config.class);

	// ----------------Cron---------------------------
	private String cron;
	// Mail Configuration
	private EmailRecipients emailRecipients;
	// Email Content
	private EmailContent emailContent;

	public Config(File f) throws Exception {
		HashMap<String, String> map = ParseConfigFile.parseFile(f);
		emailRecipients = new EmailRecipients(map.get("to"), map.get("cc"), map.get("bcc"));
		emailContent = new EmailContent(map.get("subject"), map.get("message"), map.get("filename"), map.get("api-url"),
				map.get("from"));
		this.cron = map.get("cron");
		if (!checkSenderConfig(emailContent.getFrom())) {
			LOG.error("Senders email -> " + emailContent.getFrom() + " Not configured");
			System.exit(1);
		}

	}

	public String getCron() {
		return cron;
	}

	public EmailRecipients getEmailRecipients() {
		return emailRecipients;
	}

	public EmailContent getEmailContent() {
		return emailContent;
	}

	private boolean checkSenderConfig(String email) throws Exception {
		File file = new File("/home/rohan/mail-scheduler/setup/configuration.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties propsMail = new Properties();
		propsMail.load(fis);
		if (propsMail.getProperty(email) == null)
			return false;
		else
			return true;
	}
}
