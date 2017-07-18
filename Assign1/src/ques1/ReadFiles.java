package ques1;
import java.io.*;
public class ReadFiles {

	public static void main(String[] args) {
		
		if(args.length == 0)
		{
			System.out.println("No arguments found");
			System.exit(0);
		}
		System.out.println("Path given: " + args[0]);
		File dir = new File(args[0]);
		File[] files = dir.listFiles();
		System.out.println("\nListing of files:");
		for(File obj:files){
			
			System.out.print(obj.getName());
			if(obj.isFile()){
				System.out.println(" --> File");
			}
			else
				System.out.println(" --> Directory");
			
		}
		
	}
}
