package email.javaMailAPI;

public class SendMailSSL {
	public static void main(String[] args) {
		String subject = "Hello Attachment";
		String message = "How are you ?\n\n-Rohan Gupta";
		String to = "rohan.gupta@gofro.com";
		String cc = "rguptarrr@gmail.com";
		String file = "testout.txt";
		Mailer mailer = new Mailer();
		EmailContent content = new EmailContent(subject, message , file);
		EmailRecipients recipients = new EmailRecipients(to, cc);
		if (mailer.sendMail(content, recipients)) {
			System.out.println("Success");
		} else {
			System.out.println("Failure");
		}
	}
}
