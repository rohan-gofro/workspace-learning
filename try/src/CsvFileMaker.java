import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;

public class CsvFileMaker {
	
	public static void main(String[] args) throws Exception {
		
		String filepath = args[0];
		File file = new File(filepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while((line = br.readLine())!=null)
		{
			System.out.println(line.replaceAll(" ", ","));
		}
		br.close();
	}

}
