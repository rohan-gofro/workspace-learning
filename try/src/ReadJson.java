import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadJson {
	
	public static void main(String[] args) throws Exception {
		
		File file = new File("json1.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> al = new ArrayList();
		int count = 0;
		String line;
		String[] arg = new String[2]; 
		while((line = br.readLine())!=null)
		{
			if(line.contains("SchemaName"))
			{
				arg = line.split(":");
				al.add(arg[1].substring(2, arg[1].length()-2));
				//System.out.println(++count + " " +arg[1].substring(2, arg[1].length()-2));
			}
		}	
		br.close();
		File file1 = new File("json.txt");
		FileReader fr1 = new FileReader(file1);
		BufferedReader br1 = new BufferedReader(fr1);
		while((line = br1.readLine())!=null)
		{
			if(line.contains("SchemaName"))
			{
				arg = line.split(":");
				
				if(al.contains(arg[1].substring(2, arg[1].length()-2)))
				{
					
				}
				else
				{
					System.out.println(arg[1].substring(2, arg[1].length()-2));
				}
			}
		}	
		br.close();
	}

}
