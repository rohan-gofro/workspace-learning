package Spring.MongoSpring;

public class Country extends Base {

	private String name;
	private String code;
	
	public Country()
	{
		
	}

	public Country(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public void displayCountry() {
		System.out.println("Code: " + code + " Name: " + name);
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	@Override
	public void fillInstance(String arg1, String arg2) {

		name = arg1;
		code = arg2;

	}

}
