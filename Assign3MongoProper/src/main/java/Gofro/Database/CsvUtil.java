package Gofro.Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class CsvUtil {
	
	
	public ArrayList<Country> createCountryArrayList(String file) throws IOException
	{
		String line;
		String []args;
		ArrayList<Country> country = new ArrayList<Country>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		while((line = br.readLine())!=null){
			args = line.split(",");
			country.add(new Country(args[1], args[0]));
		}
		br.close();
		return country;	
	}
	
	public ArrayList<City> createCityArrayList(String file) throws IOException
	{	
		String line;
		String []args;
		ArrayList<City> city = new ArrayList<City>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		while((line = br.readLine())!=null)
		{
			args = line.split(",");
			city.add(new City(args[0] , args[1]));
		}
		br.close();
		return city;
		
	}
	public HashSet<String> createStatesHashSet(String file) throws IOException{
		
		String line;
		String []args;
		HashSet<String> states = new HashSet<String>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		while((line = br.readLine())!=null)
		{
			args = line.split(",");
			states.add(args[1]);
		}
		br.close();
		return states;
	}
}