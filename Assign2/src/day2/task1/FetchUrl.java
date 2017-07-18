package day2.task1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
public class FetchUrl {
	
	public static void main(String[] args) throws Exception {
		
		int start , end;
		Scanner sc = new Scanner(System.in);
		System.out.println("Input URL");
		String link = sc.next();
		URL url = new URL(link);
		BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
		String input;
		while((input = bf.readLine())!= null){
			
			if(input.contains("<h2>")){
				
				start = input.indexOf("<h2") + "<h2>".length();
				end = input.indexOf("</h2>" , start);
				System.out.println(input.substring(start, end));		
			}	
		}
		sc.close();
	}

}
