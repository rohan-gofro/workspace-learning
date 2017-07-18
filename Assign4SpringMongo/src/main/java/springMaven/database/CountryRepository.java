package springMaven.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends MongoRepository<Country, String> {

	public Country findByName(String name);

	public Country findByCode(String code);

}