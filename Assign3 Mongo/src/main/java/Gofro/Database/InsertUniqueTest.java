package Gofro.Database;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertUniqueTest {
	
	//Define Host , port(optional) and Database
	String host = "192.168.10.101";
	int port = 27017;
	String database = "Rohan";
	String table = "tryCountry";
	
	//Creating a client
	MongoClient client = new MongoClient(host,port);
	
	//Access a database
	MongoDatabase db = client.getDatabase(database);
	
	//Access a collection
	MongoCollection<Document> collection = db.getCollection(table);
	
	
	

}
