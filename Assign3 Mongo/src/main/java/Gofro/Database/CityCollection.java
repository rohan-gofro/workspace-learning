package Gofro.Database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CityCollection {
	
	public static void main(String[] args) throws IOException {
		
		//Object of other class to access its methods
		StateData obj = new StateData();
		
		//Defining parameters
		String statename;
		String cityname;
		int id=1;
		int stateId;
		
		//Database parameters
		String host = "localhost";
		String database = "Rohan";
		String collection = "TryCity";
		
		//Connecting to mongodb
		MongoClient client = new MongoClient(host);
		
		//Connectiong to database
		MongoDatabase db = client.getDatabase(database);
		
		//Creating or accessing collection to push data
		MongoCollection<Document> table = db.getCollection(collection);
		
		
		//Reading data from file to push into database
		FileReader fr = new FileReader("states.csv");
		BufferedReader br = new BufferedReader(fr);
		String fileread;
		String fields[];
		//Creating a document to push multiple data at a time
		List<Document> doc = new ArrayList<Document>();
		
		while((fileread = br.readLine())!=null)
		{
			fields = fileread.split(",");
			cityname = fields[0];
			statename = fields[1];
			stateId = obj.getStateId(statename);
			doc.add(new Document("city" , cityname).append("state_id", stateId).append("state", statename).append("city_id", id++).append("country_code", "IN").append("country_name", "India"));			
			
				}
		table.insertMany(doc);

		br.close();
		client.close();
	}	
	

}
