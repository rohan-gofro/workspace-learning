package Gofro.Database;

import java.io.*;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class StateData 
{
	
	public int getStateId(String state) throws IOException {
		
		//Database parameters
		String host = "localhost";
		String database = "Rohan";
		String collection = "States";
		//Connecting to mongodb
		MongoClient client = new MongoClient(host);
		
		//Connectiong to database
		MongoDatabase db = client.getDatabase(database);
		
		//Creating or accessing collection
		MongoCollection<Document> table = db.getCollection(collection);
	
		//Search Query
		Document myDoc = table.find(eq("state_name", state)).first();
		
		int id = Integer.parseInt(myDoc.get("state_id").toString());
		
		//table.insertMany(doc);
		client.close();
		return id;
	}	
}
