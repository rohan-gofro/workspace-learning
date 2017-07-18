package Gofro.Database;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.*;

public class ConnectDB {
	
	public static void main(String[] args) {
		
		String host = "localhost";
		 int port = 27017;
		 String database = "Rohan";
		 String collection = "Test";
		 
		//Connection to Mongo
		MongoClient client = new MongoClient(host , port);
		System.out.println("Connected to mongo");
		//Connect to db or create db
		MongoDatabase mydb = client.getDatabase(database);
		System.out.println("Connected to database");
		//Create a collection
		MongoCollection<Document> table = mydb.getCollection(collection);
		Document doc = new Document("name", "Vipul");
		//doc.append("id", 130);
		table.insertOne(doc);
		//Close connection
		client.close();
		
		
	}
}