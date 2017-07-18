package Spring.MongoSpring;

public class Country {

	private String name;
	private String code;

	public Country() {
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
}
