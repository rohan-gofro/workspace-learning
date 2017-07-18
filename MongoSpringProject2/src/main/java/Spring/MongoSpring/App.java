package Spring.MongoSpring;

import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args) {
		CsvToCollection obj = new GofroDataCsvToList();
		
		List<Country> list1 = new ArrayList<Country>();

		list1 = (List<Country>) (List<?>) obj.getList("countries.csv", Country.class);
		
		for(Country i:list1)
		{
			i.displayCountry();
		}

	}
}
