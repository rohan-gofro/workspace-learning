package job.mail;

public class EmailContent {

	private String subject;
	private String message;
	private String fileName;
	private String url;
	
	public EmailContent(String subject , String message)
	{
		this.subject = subject;
		this.message = message;
	}
	
	public EmailContent(String subject , String message , String file , String url)
	{
		this.subject = subject;
		this.message = message;
		this.fileName = file;
		this.url = url;
				
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
	
}
