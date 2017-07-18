package Gofro.Database;
import org.bson.Document;

//import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
 
public class ShowData {
 
	public static void main(String[] args) {
 
		String host = "localhost";
		 int port = 27017;
		 String database = "Rohan";
		 String collection = "States";
		 
		MongoClient client = new MongoClient(host, port);
		MongoDatabase db = client.getDatabase(database);
		MongoCollection<Document> table = db.getCollection(collection);
		MongoCursor<Document> cursor = table.find().iterator();
		try{
			
			while(cursor.hasNext()){
				//System.out.println(cursor.next().toJson());
				Document obj = cursor.next();
				System.out.println(obj.get("country_name"));
			}
			
		}
		finally{
			cursor.close();
		}
		client.close();
	}
}