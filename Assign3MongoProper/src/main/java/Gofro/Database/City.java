package Gofro.Database;
public class City implements Comparable<City> 
{
	
	private String cityName;
	private String stateName;
	private String countryName = "India";
	private String countryCode = "IN";
	
	public City(String cityName , String stateName){
		
		this.cityName = cityName;
		this.stateName = stateName;
		
	}
	
	public int compareTo(City arg0) 
	{
		
		if(stateName.compareTo(arg0.getStateName()) < 0)
			return -1;
		
		else if(stateName.compareTo(arg0.getStateName()) > 0)
			return 1;
		
		else
		return 0;
	}
	
	public String getCityName() {
		return cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}
	
}