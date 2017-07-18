package springMaven.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CountryRepository repository;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

	public void run(String... args) throws Exception {
	
		//repository.deleteAll();
		
		//Save some countries
		repository.save(new Country("India", "IN"));
		repository.save(new Country("Pakistan", "PAK"));
		repository.save(new Country("Saudi Arabia", "RSA"));
		repository.save(new Country("South Africa", "SA"));
		
		//fetch all records
		System.out.println("All records are -->");
		for(Country c :repository.findAll()){
			System.out.println(c);
		}
		
		//specific records
		System.out.println(repository.findByName("India"));
		System.out.println(repository.findByCode("PAK"));
		
	}
	

}
