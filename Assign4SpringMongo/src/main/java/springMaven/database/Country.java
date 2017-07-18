package springMaven.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="country")
public class Country {

	@Id
	public String code;
	public String name ;
	
	public Country()
	{
		
	}
	public Country(String name , String code){
		
		this.name = name;
		this.code = code;
		
	}
	
	@Override
	public String toString()
	{
		return String.format("Country[code='%s' , name='%s']",code, name);		
	}
	
}
