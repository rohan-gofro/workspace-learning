package Gofro.Database;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MongoDBService 
{

	private static int StateID = 1;
	private static int CityID = 1;
	private MongoClient client;
	private MongoDatabase database;
	//Collections names
	private String stateCollection = "states";
	private String cityCollection = "cities";
	private String countryCollection = "countries";
	
	public MongoDBService(String host, int port, String dbName) 
	{
		this.client = new MongoClient(host , port);
		this.database = client.getDatabase(dbName);
	}
	
	public void stop()
	{
		client.close();
	}

	//----------------Country Related Operations--------------------------------

	public Boolean insertCountry(Country c) {

		MongoCollection<Document> countryTable = database.getCollection(countryCollection);
		Document countryDocument = new Document("_id", c.getCode()).append("name", c.getName());
		try {
			countryTable.insertOne(countryDocument);
			return true;
		} catch (Exception E) {
			return false;
		}
	}

	public Boolean insertCountryMany(ArrayList<Country> countriesList) {

		List<Document> countryArrayList = new ArrayList<Document>();

		for (Country i : countriesList) {
			countryArrayList.add(new Document("_id", i.getCode()).append("name", i.getName()));
		}
		MongoCollection<Document> countryTable = database.getCollection(countryCollection);
		try {
			countryTable.insertMany(countryArrayList);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private Document getCountryInternal(String field, Object value) {
		MongoCollection<Document> counrtyTable = database.getCollection(countryCollection);
		return counrtyTable.find(eq(field, value)).first();
	}

	public Country getCountryByName(String name) {
		Document countryDocument = getCountryInternal("name", name);
		String sid = countryDocument.get("_id").toString();
		String sname = countryDocument.get("name").toString();
		return (new Country(sid, sname));
	}

	public Country getCountryById(String id) {

		Document countryDocument = getCountryInternal("_id", id);
		String cid = countryDocument.get("_id").toString();
		String cname = countryDocument.get("name").toString();
		return (new Country(cid, cname));
	}

	//------------------------ State Methods --------------------------------------

	private Document getStateInternal(String field, Object value) 
	{
		MongoCollection<Document> stateTable = database.getCollection(stateCollection);
		return stateTable.find(eq(field, value)).first();
	}

	public State getStatebyId(String id) {
		Document stateDocument = getStateInternal("_id", id);
		String sid = stateDocument.get("_id").toString();
		String sname = stateDocument.get("name").toString();
		String cid = stateDocument.get("countryCode").toString();
		String cname = stateDocument.get("countryName").toString();
		return (new State(sid, sname, cname, cid));
	}

	public State getStatebyName(String name) {
		Document stateDocument = getStateInternal("name", name);
		String sid = stateDocument.get("_id").toString();
		String sname = stateDocument.get("name").toString();
		String cid = stateDocument.get("countryCode").toString();
		String cname = stateDocument.get("countryName").toString();
		return (new State(sid, sname, cname, cid));
	}
	
	
	public Boolean insertStateCollection(HashSet<String> stateSet)
	{
		List<Document> stateDocument = new ArrayList<Document>();
		for(String i:stateSet){
			String sid = "S" + StateID++;
			System.out.println("ID --> " + sid + "State --> " + i);
			stateDocument.add(new Document("_id",sid).append("name", i).append("countryCode", "IN").append("countryName", "India"));	
		}
		try{
			database.getCollection(stateCollection).insertMany(stateDocument);
			return true;
		}		
		catch(Exception E){
			return false;
		}
	}
	
	//-------------City Operations------------------------------------
	public Boolean insertCityCollection(ArrayList<City> cityList){
		
		List<Document> cityDocument = new ArrayList<Document>();
		String lastState = "";
		String stateId = "";
		for(City i: cityList)
		{
			
			if(!i.getStateName().equals(lastState)){
				
				lastState = i.getStateName();
				stateId = getStatebyName(i.getStateName()).getId();
			}
			String cityId = "C" + CityID++;
			cityDocument.add(new Document("_id" , cityId ).append("name",i.getCityName()).append("stateName", i.getStateName()).append("stateId", stateId).append("countryCode", "IN").append("countryName", "India"));
		}
		try
		{
		database.getCollection(cityCollection).insertMany(cityDocument);
		return true;
		}
		catch(Exception E)
		{
			return false;
		}
	}
}
