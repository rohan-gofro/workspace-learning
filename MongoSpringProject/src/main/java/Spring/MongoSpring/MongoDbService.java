package Spring.MongoSpring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbService implements DBService 
{

	
	private MongoDb dbCredentials;
	
	private MongoClient client;
	private MongoDatabase db;

	// Collections name
	private String countryCollection = "countries";
	private String stateCollection = "states";
	private String cityCollection = "cities";

	
	//Setter methods
	
	public void setDbCredentials(MongoDb dbCredentials) {
		this.dbCredentials = dbCredentials;
	}
	
	public void setCountryCollection(String countryCollection) {
		this.countryCollection = countryCollection;
	}

	
	public void setStateCollection(String stateCollection) {
		this.stateCollection = stateCollection;
	}

	public void setCityCollection(String cityCollection) {
		this.cityCollection = cityCollection;
	}

	// To use default values
	public MongoDbService() {
		
		
	}
	
	//--Db credentials
	
	public void showDb()
	{
		dbCredentials.showDataBase();
	}


	// ------------To start/stop Mongo DB services----------------------------

	// to initialize start service internally
	private void startService() {

		this.client = new MongoClient(dbCredentials.getHost(), dbCredentials.getPort());
		db = client.getDatabase(dbCredentials.getDatabase());
	}

	// to stop service
	public void stopService() {
		this.client.close();
	}

	// ------------------Country related operations----------------------

	public boolean writeCountry(List<Country> list) {
		
		
        startService();
		MongoCollection<Document> collection = db.getCollection(countryCollection);
		List<Document> country = new ArrayList<Document>();
		for (Country i : list) {
			country.add(new Document("_id", i.getCode()).append("name", i.getName()));
		}
		try {
			collection.insertMany(country);
			stopService();
			return true;
		} catch (Exception E) {
			stopService();
			return false;
		}
	}

	private Country getCountryInternal(String field, Object value) {

        startService();
		MongoCollection<Document> collection = db.getCollection(countryCollection);
		Document countryDoc = collection.find(eq(field, value)).first();
		stopService();
		return (new Country(countryDoc.get("_id").toString(), countryDoc.get("name").toString()));
	}

	public Country getCountryByName(String name) {

		return (getCountryInternal("name", name));

	}

	public Country getCountryByCode(String code) {

		return (getCountryInternal("_id", code));

	}

	// ------------------State related operations------------------------

	private State getStateInternal(String field, Object value) {
        startService();
		MongoCollection<Document> collection = db.getCollection(stateCollection);
		Document stateDocument = collection.find(eq(field, value)).first();
		stopService();
		return new State(stateDocument.get("_id").toString(), stateDocument.get("name").toString(),
				getCountryByName(stateDocument.get("countryName").toString()));
	}

	public State getStatebyName(String stateName) {
		return getStateInternal("name", stateName);
	}

	public boolean writeState(HashSet<State> StateSet) {

        startService();
		MongoCollection<Document> collection = db.getCollection(stateCollection);
		List<Document> stateDocument = new ArrayList<Document>();
		for (State i : StateSet) {
			stateDocument.add(new Document("_id", i.getCode()).append("name", i.getName())
					.append("countryName", i.getCountry().getName()).append("countryCode", i.getCountry().getCode()));
		}

		try {
			collection.insertMany(stateDocument);
	        startService();
			return true;
		} catch (Exception E) {
	        startService();
			return false;
		}
	}

	// ---------------City Related Operations----------------------------------

	public boolean writeCity(List<City> list) 
	{
		startService();
		MongoCollection<Document> collection = db.getCollection(cityCollection);
		List<Document> cities = new ArrayList<Document>();
		for (City i : list) {
			cities.add(new Document("_id", i.getCode()).append("name", i.getName())
					.append("stateCode", i.getState().getCode()).append("stateName", i.getState().getName())
					.append("countryCode", i.getState().getCountry().getCode())
					.append("countryName", i.getState().getCountry().getName()));
		}
		try 
		{
			collection.insertMany(cities);
			stopService();
			return true;
		} 
		catch (Exception e) 
		{
			stopService();
			return false;
		}
	}
}