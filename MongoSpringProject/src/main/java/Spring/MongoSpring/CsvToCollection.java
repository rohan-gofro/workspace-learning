package Spring.MongoSpring;

import java.util.List;
import java.util.Set;


public interface CsvToCollection 
{
	
	public List<Country> createCountryList(String filepath);
	public Set<State> createStatesList(String filepath);
	public List<City> createCityList(String filepath);

}
