package job.mail;

public class EmailRecipients {
	
	private String to;
	private String cc;
	private String bcc;
	
	public EmailRecipients(String to , String cc , String bcc)
	{
		this.to= to;
		this.cc = cc;
		this.bcc = bcc;
	}
	
	public String getTo() {
		return to;
	}
	
	public String getCc() {
		return cc;
	}
	
	public String getBcc() {
		return bcc;
	}
}
