package Spring.MongoSpring;
public class State
{
	
	private String code;
	private String name;
	private Country country;
	
	public State(String code , String name , Country country)
	{
		this.code = code;
		this.name = name;
		this.country = country;
	}
	
	public State(String name)
	{
		this.name = name;
	}
	
	public void diplayState()
	{
		System.out.print("Code: " + code + " Name: " + name + " , ");
		country.displayCountry();
	}
	
	//getter methods

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public Country getCountry() {
		return country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
