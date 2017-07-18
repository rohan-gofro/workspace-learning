package Gofro.Database;

import java.io.*;
public class AccessFile{
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("countries.csv"));
		String s;
		String ar[];
		while((s=br.readLine()) != null){
			ar = s.split(",");
			System.out.println(ar[0] + " AND " + ar[1]);
		}
		br.close();
	}
}