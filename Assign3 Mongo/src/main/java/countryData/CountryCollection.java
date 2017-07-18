package countryData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

class CountryCollection{
	 
	 public static void main(String[] args) throws IOException {
		
		 String host = "localhost";
		 int port = 27017;
		 String database = "Rohan";
		 String collection = "Country";
		 
		 //Connection
		 MongoClient client = new MongoClient(host , port);
		 System.out.println("Connected");
		 
		 //Connect to database
		 MongoDatabase db = client.getDatabase(database);
		 System.out.println("Connected to database");
		 
		 //create/access a collection
		 MongoCollection<Document> table = db.getCollection(collection);
		 
		 //Create list of documents
		 List<Document> documents = new ArrayList<Document>();
		 
		 // Reading Data from file
		 BufferedReader br = new BufferedReader(new FileReader("countries.csv"));
		 String s;
		 String ar[];
		 while((s=br.readLine()) != null)
		    {
				ar = s.split(",");
				documents.add(new Document("country_name" , ar[0]).append("country_code", ar[1]));
				//System.out.println(ar[0] + " AND " + ar[1]);
			}
		table.insertMany(documents);
		br.close();
		client.close();	 
	}
 }