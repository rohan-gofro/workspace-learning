package Spring.MongoSpring;

import java.util.HashSet;
import java.util.List;

public interface DBService {
	
	public  boolean writeCountry(List<Country> list);
	public  boolean writeState(HashSet<State> list);
	public  boolean writeCity(List<City> list);
	void stopService();

}