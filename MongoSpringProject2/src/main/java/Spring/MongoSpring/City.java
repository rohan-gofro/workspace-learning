package Spring.MongoSpring;

public class City extends Base{
	
	private String code;
	private String name;
	private String stateName;
	private String countryName = "India";
	private String countryCode = "IN";
	
	public City(String code , String name)
	{
		this.code = code;
		this.name = name;
	}
	
	public void displayCity()
	{
		System.out.println("Code: " + code + " Name: " + name);
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
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

	@Override
	public void fillInstance(String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
