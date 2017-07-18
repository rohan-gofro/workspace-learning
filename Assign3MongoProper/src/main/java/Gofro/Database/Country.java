package Gofro.Database;

class Country {

	private String name;
	private String code;

	public Country(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

}