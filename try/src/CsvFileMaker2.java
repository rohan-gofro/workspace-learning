import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;

public class CsvFileMaker2 {
	
	public static void main(String[] args) throws Exception {
		
		String filepath = args[0];
		File file = new File(filepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		int i;
		String []ar;
		String line;
		while((line = br.readLine())!=null)
		{
			String str = "";
			ar = line.split("\\|");
			for(i=0;i<ar.length-1;i++)
			{
				str = str + ar[i].trim() + ",";
			}
			str = str + ar[i].trim();
			System.out.println(str);
		}
		br.close();
	}

}
