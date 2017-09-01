package job.mail;

public class EmailContent {

	private String from;
	private String subject;
	private String message = "System Generated Message";
	private String fileName;
	private String url;

	public EmailContent(String subject, String message) {
		this.subject = subject;
		this.message = message;
	}

	public EmailContent(String subject, String message, String file, String url, String from) {
		this.subject = subject;
		this.fileName = file;
		this.url = url;
		this.from = from;
		if (message != null)
			this.message = message;

	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public String getFileName() {
		return fileName;
	}

	public String getUrl() {
		return url;
	}

	public String getFrom() {
		return from;
	}

}
