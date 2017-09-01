package data;

import java.io.FileWriter;
import java.util.Date;

public class FileWrite {  
    public static void main(String[] args) {  
        try {
        	
        	String d = new Date().toString() + ".txt";
        	FileWriter fw=new FileWriter(d);    
            fw.write("Welcome to javaTpoint.");    
            fw.close();      
            System.out.println("Done");  
            System.out.println(d);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  