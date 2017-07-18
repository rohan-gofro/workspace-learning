package cityData;


import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CityCollection {
	
	String host = "localhost";
	String database = "Rohan";
	String collection1 = "TryCity1";
	String collection2 = "States";
	
	MongoClient client = new MongoClient(host);
	MongoDatabase db = client.getDatabase(database);
	MongoCollection<Document> citycollection = db.getCollection(collection1);
	MongoCollection<Document> statecollection = db.getCollection(collection2);
	
	List<Document> doc = new ArrayList<Document>(); 
	
	
	int searchStateId(String state)
	{
		int stateId;
		Document myDoc = this.statecollection.find(eq("state_name" , state)).first();
		stateId = Integer.parseInt(myDoc.get("state_id").toString());
		return stateId;
	}
	
	void writeToDb(City obj)
	{
		String state = "";
		int cityId ;
		String cityName;
		String stateName;
		int stateId = 0;
		
		for(City i:obj.ts)
		{
			cityId = i.cityId;
			cityName = i.cityName;
			stateName = i.stateName;
			if(!state.equals(stateName)){
				System.out.println("Mismatch");
				stateId = searchStateId(stateName);
				state = stateName;
			}
			
//			System.out.println("cityid: " + cityId);
//			System.out.println("cityname " + cityName);
//			System.out.println("stateid " + stateId );
//			System.out.println("statename " + stateName + "\n--------");
//			
			this.doc.add(new Document("city_id" , cityId).append("city_name", cityName).append("state_id", stateId).append("state_name", stateName).append("country_id", "IN").append("country_name", "India"));
		}
		
		//Writing to database
		this.citycollection.insertMany(doc);
		System.out.println("Success");
		
		
	}
	
	public static void main(String[] args) throws Exception {

		//Object of class city to access arraylist of cities
		City obj = new City();
		CityCollection obj1 = new CityCollection();
		obj.pushData();
		obj1.writeToDb(obj);
		obj1.client.close();
		
		
		
	}

}
