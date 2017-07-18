package ques4;
import java.io.*;

public class FilesOnServer {
	
	String directoryPath;//Directory path as parameter entered by user
	
	public FilesOnServer(String directoryPath){
		
		this.directoryPath = directoryPath;//initializing directorypath parameter at the time of object creation
		
	}
	
	//Method to list file of a directory. Returns false in case if a directory doesn't exists
	 boolean listFiles(){
		
		try{
		File fo = new File(this.directoryPath);
		if(!fo.exists()){
			return false;
		}
		File[] fileList = fo.listFiles();
		for(File displayList:fileList){
			System.out.println(displayList.getName());
		}
		}catch(NullPointerException e){
			System.out.println("No such directory exists");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
	
	//Method to access content of file if found in the directory
	void accessFileContent(String file){
		
		try{
		FileReader fr = new FileReader(this.directoryPath + "/" + file);
		BufferedReader br = new BufferedReader(fr);
		int i;
		while((i = br.read())!=-1){
			System.out.print((char)i);
		}
		}catch(FileNotFoundException e){
			System.out.println("No such file found");
		}
		catch(Exception E){
			E.printStackTrace();
		}
		
	}
}
