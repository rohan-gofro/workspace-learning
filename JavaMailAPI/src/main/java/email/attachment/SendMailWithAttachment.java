package email.attachment;

public class SendMailWithAttachment {
	
	
	public static void main(String[] args) {
		
		SendAttachment obj = new SendAttachment();
		obj.getSession();
		obj.sendMessage();
		
	}

}
