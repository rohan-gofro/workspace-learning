package email.attachment;

import java.io.FileWriter;

public class WriteAFile {
	
	public static void main(String[] args) {
		
		try{    
	           FileWriter fw=new FileWriter("testout.txt");    
	           fw.write("Welcome to javaTpoint.");    
	           fw.close();    
	          }catch(Exception e){System.out.println(e);}    
	          System.out.println("Success...");   
		
	}

}
