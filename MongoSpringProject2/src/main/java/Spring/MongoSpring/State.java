package Spring.MongoSpring;
public class State extends Base
{
	
	private String code;
	private String name;
	private String countryName = "India";
	private String countryCode = "IN";
	
	public State(String code , String name)
	{
		this.code = code;
		this.name = name;
	}
	
	
	public void diplayState()
	{
		System.out.println("Code: " + code + " Name: " + name);
	}
	
	
	//getter methods

	public String getCode() {
		return code;
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


	@Override
	public void fillInstance(String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}
	
}
