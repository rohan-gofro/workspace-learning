package Spring.MongoSpring;

public class MongoDb {
	
	private String host;
	private int port;
	private String database;
	
	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getHost() {
		return host;
	}
	public int getPort() {
		return port;
	}
	public String getDatabase() {
		return database;
	}
	
	public void showDataBase()
	{
		System.out.println("Host : " + host);
		System.out.println("port : " + port);
		System.out.println("db : " + database);	
		
	}
	
}
