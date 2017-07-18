package cityData;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class City implements Comparable<City> {
	
	int cityId;
	String cityName;
	String stateName;
	
	ArrayList<City> ts = new ArrayList<City>();
	
	public City()
	{
		
	}
	
	public City(int cityId , String cityName , String stateName){
		
		this.cityId = cityId;
		this.cityName = cityName;
		this.stateName = stateName;
		
	}
	
	public int compareTo(City o) {
		
		if(stateName.compareTo(o.stateName) < 0)
			return -1;
		else if(stateName.compareTo(o.stateName) > 0)
			return 1;
		else	
		return 0;
	}
	
	void pushData() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("states.csv"));
		String line;
		String args[];
		int id=1;
		while((line = br.readLine())!=null){
			
			args = line.split(",");
			this.ts.add(new City(id++, args[0], args[1]));			
		}
		Collections.sort(this.ts);
		br.close();		
	}
	
	void showData()
	{
		for(City c:this.ts){
			System.out.println(c.cityId + " " +c.stateName + " --> " + c.cityName);
			
		}
	}

	public static void main(String[] arg) throws IOException {
		
		//Writing to tree hash set
		City obj = new City();
		obj.pushData();
		obj.showData();
		
	}

}