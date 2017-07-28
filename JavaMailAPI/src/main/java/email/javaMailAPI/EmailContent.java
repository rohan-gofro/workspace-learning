package email.javaMailAPI;

public class EmailContent {

	private String subject;
	private String message;
	private String file;
	
	
	public EmailContent(String subject , String message)
	{
		this.subject = subject;
		this.message = message;
	}
	
	public EmailContent(String subject , String message , String file)
	{
		this.subject = subject;
		this.message = message;
		this.file = file;
				
	}
	public String getSubject() {
		return subject;
	}
	public String getMessage() {
		return message;
	}
	public String getFile() {
		return file;
	}
	
	

}
