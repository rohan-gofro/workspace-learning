package Spring.MongoSpring;

public class City {
	
	private String code;
	private String name;
	private State state;
	public City(String code , String name , State state)
	{
		this.code = code;
		this.name = name;
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public State getState() {
		return state;
	}
	
	public void displayCity()
	{
		System.out.print("Code: " + code + "Name: " + name);
		state.diplayState();
	}
	
}
