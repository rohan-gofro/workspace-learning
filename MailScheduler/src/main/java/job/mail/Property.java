package job.mail;

public enum Property {

	SMTP_HOST("mail.smtp.host", "smtp.gmail.com") ,
	SMTP_SOCKETPORT("mail.smtp.socketFactory.port" , "465"),
	SMTP_SOCKET("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"),
	SMTP_AUTH("mail.smtp.auth", "true"),
	SMTP_PORT("mail.smtp.port", "465");
	
	
	String key;
	String value;

	Property(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

}
