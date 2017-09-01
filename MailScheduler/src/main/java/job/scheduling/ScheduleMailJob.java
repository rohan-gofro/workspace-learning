package job.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import job.mail.Mailer;

public class ScheduleMailJob extends ScheduleJob
{

	private static final Logger LOG = LoggerFactory.getLogger(ScheduleMailJob.class);
	private Mailer mailer;

	public ScheduleMailJob(Config obj) {
		super(obj);
		mailer = new Mailer();
	}

	public void run() {
		LOG.info("Job scheduled for sending email");
		try {
			mailer.sendMail(config.getEmailContent(), config.getEmailRecipients());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Error sending email");
			e.printStackTrace();
			System.exit(1);
		}
	}
}