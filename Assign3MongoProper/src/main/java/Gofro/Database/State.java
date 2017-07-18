package Gofro.Database;

public class State {
	
	private String id;
	private String name;
	private String countryName = "India";
	private String countryCode = "IN";
	
	public State(String id , String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCountryName() {
		return countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}

}