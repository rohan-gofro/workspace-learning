package Gofro.Database;

import java.io.IOException;

public class WriteData {
	
	public static void main(String[] args) throws IOException {
		
		
		if(args.length < 3)
		{
			System.out.println("Usage : java -jar db.jar <DB_HOST> <DB_PORT>");
			System.exit(-1);
		}
		
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		String db = args[2];
		
		MongoDBService service = new MongoDBService(host, port, db);
		
		CsvUtil fileService = new CsvUtil();
		
		//Countries
		
		service.insertCountryMany(fileService.createCountryArrayList("countries.csv"));
		System.out.println("Countries success");
		
		//States
		
		service.insertStateCollection(fileService.createStatesHashSet("states.csv"));
		System.out.println("States success");

		//cities
		
		service.insertCityCollection(fileService.createCityArrayList("states.csv"));
		System.out.println("cities success");
		
		service.stop();
	}

}
