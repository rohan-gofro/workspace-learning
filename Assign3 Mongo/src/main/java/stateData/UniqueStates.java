package stateData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class UniqueStates {
	
	HashSet<States> stateSet = new HashSet<States>();
	
	public static void main(String[] args) throws IOException {
		
		 String host = "localhost";
		 int port = 27017;
		 String database = "Rohan";
		 String collection = "States";
		 
		int id = 0;
		UniqueStates obj = new UniqueStates();
		
		//Read States list
		BufferedReader br = new BufferedReader(new FileReader("states.csv"));
		String s;
		String ar[];
		while((s=br.readLine()) != null){
			ar = s.split(",");
			obj.stateSet.add(new States(ar[1]));
//			System.out.println(ar[0] + " AND " + ar[1]);
		}
		System.out.println(obj.stateSet.size());
		br.close();

		//Writing unique data to mongodb as stateid , statename , countrycode as IN and country name as India
		
		//Connection
		 MongoClient client = new MongoClient(host , port);
		 System.out.println("Connected");
		 
		 //Connect to database
		 MongoDatabase db = client.getDatabase(database);
		 System.out.println("Connected to database");
		 
		 //create/access a collection
		 MongoCollection<Document> table = db.getCollection(collection);
		 
		 //Creating a list for multiple write
		 List<Document> documents = new ArrayList<Document>();
		 for(States i:obj.stateSet){
			 id++;
			 documents.add(new Document("state_id" , id).append("state_name", i.name).append("country_name", "India").append("country_id", "IN"));
			 //System.out.println(i.name);
			}
		 table.insertMany(documents);
		 client.close();	
	}
}