package ques4;
import java.util.*;
public class AccessFile {
	
	public static void main(String[] args) {
		
		String directory;
		String file;
		Scanner sc = new Scanner(System.in);
		System.out.println("Give the Directory Path: ");
		directory = sc.next();//Input the directory path from user
		FilesOnServer obj = new FilesOnServer(directory);//Initializing parameter via parameterized constructor
		if(obj.listFiles())//List files only if directory exists
		{
			System.out.println("Enter the file you want to access");
			file = sc.next();
			obj.accessFileContent(file);//Accessing file content in directory
		}
		else{
			System.out.println("Not found");
		}
		sc.close();	//Closing scanner class object
		System.out.println("\n___________\nEnd");
	}
}
