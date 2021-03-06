package Spring.MongoSpring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbService implements DBService {
	
	private String host = "localhost";
	private int port = 27017;
	private String dbname = "Rohan";
	private MongoClient client;
	private MongoDatabase db;
	
	//Collections name
	private String countryCollection = "countries";
	private String stateCollection = "states";
	private String cityCollection = "cities";
	
	
	
	//Object creation
	
	//To use default values
	public MongoDbService()
	{
		startService();
	}
	
	//to change db and use default collection names
	public MongoDbService(String host , int port , String db) 
	{
		this.host = host;
		this.port = port;
		this.dbname = db;
		startService();
	}
	
	//to use our own database and collections
	public MongoDbService(String host , int port , String db , String country , String state , String city) 
	{
		this.host = host;
		this.port = port;
		this.dbname = db;
		this.countryCollection = country;
		this.stateCollection = state;
		this.cityCollection = city;
		startService();
	}
	
	//------------To start/stop Mongo DB services----------------------------
	
	
	//to initialize start service internally
	private void startService() {
		
		this.client = new MongoClient(host , port);
		client.getDatabase(dbname);
	}
	
	//to stop service
	public void stopService() {
		this.client.close();
	}
	
	
	//------------------Country related operations----------------------

	public boolean writeCountry(List<Country> list) {
		
		MongoCollection<Document> collection = db.getCollection(countryCollection);
		List<Document> country = new ArrayList<Document>();
		for(Country i:list){
			country.add(new Document("_id" , i.getCode()).append("name", i.getName()));
		}
		try{
		collection.insertMany(country);
		return true;
		}
		catch(Exception E){
			return false;
		}
	}
	
	
	//------------------State related operations------------------------

	
	private Document getStateInternal(Object value , String field)
	{
		MongoCollection<Document> collection = db.getCollection(stateCollection);
		return collection.find(eq("field" , "value")).first();
	}
	
	
	private String getStateId(String stateName)
	{
		Document stateDocument = getStateInternal(stateName, "_id");
		return stateDocument.get("_id").toString();
	}
	
	public boolean writeState(HashSet<State> StateSet) {
		
		MongoCollection<Document> collection = db.getCollection(stateCollection);
		List<Document> stateDocument = new ArrayList<Document>();
		for(State i:StateSet){
			stateDocument.add(new Document("_id" , i.getCode()).append("name", i.getName()).append("countryName", i.getCountryName()).append("countryCode", i.getCountryCode()));
		}
		
		try
		{
		  collection.insertMany(stateDocument);
		  return true;
		}
		catch(Exception E){
			return false;
		}
	}

	
	//---------------City Related Operations----------------------------------
	
	public boolean writeCity( List<City> list) {
		
		MongoCollection<Document> collection = db.getCollection(cityCollection);
		List<Document> cities = new ArrayList<Document>();
		String lastState = null;
		String stateId = null;
		for(City i:list)
		{
			if(!(i.getStateName().equals(lastState)))
			{
				
				stateId = getStateId(i.getStateName());
			}
			cities.add(new Document("_id" , i.getCode()).append("name", i.getName()).append("stateCode", stateId).append("stateName", i.getStateName()).append("countryCode", i.getCountryCode()).append("countryName", i.getCountryName()));
		}
		try{
		collection.insertMany(cities);
		return true;
		}catch(Exception e){
			return false;
		}
	}
}
