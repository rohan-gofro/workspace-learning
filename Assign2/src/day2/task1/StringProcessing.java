package day2.task1;
public class StringProcessing {
	
	public static void main(String[] args) {
		
		String html = "<html>this is html<p>this is heading</p><p>this is para</p>this is html</html>";
		if(html.contains("<h>")){
		int start = html.indexOf("<h>") + "<h>".length();
		int end =  html.indexOf("</h>" , start);
		System.out.println(html.substring(start,end));
		}
		else
			System.out.println("No heading found");
	}

}
