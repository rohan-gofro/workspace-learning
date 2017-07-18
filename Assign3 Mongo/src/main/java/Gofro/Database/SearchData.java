package Gofro.Database;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

//import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class SearchData {
	
	public static void main(String[] args) {
		
		//Define parameters
		String host = "localhost";
		//int port = 27017;
		String database = "Rohan";
		String collection = "States";
		//Create connection
		
		MongoClient client = new MongoClient(host);
		System.out.println("Connection created");
		
		//Connect to a database
		
		MongoDatabase db = client.getDatabase(database);
		System.out.println("Connected to database");
		
		//Get collection
		MongoCollection<Document> table = db.getCollection(collection);
		
		//Search query specified
		Document myDoc = table.find(eq("state_name", "Andhra Pradesh")).first();
		
		//Getting the desired field
		int id = Integer.parseInt(myDoc.get("state_id").toString());
		System.out.println(id);
		
		
//Alternate methods as follows		
//		BasicDBObject filter = new BasicDBObject();
//		filter.put("state_name", "Bihar");
//		Document obj = table.find(filter).first();
//		//Created json string
//		String js = obj.toJson();
//		System.out.println(js);

		client.close();
	}
	

}
