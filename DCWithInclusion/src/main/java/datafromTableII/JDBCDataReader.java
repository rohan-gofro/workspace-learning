package datafromTableII;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class JDBCDataReader {

	public Map<String, Integer> data = new LinkedHashMap<String, Integer>();
	public JDBCConnectionConfig config;
	public String query;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("com.vertica.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

	abstract public void buildQuery();	
	
	public void executeQuery(String query) throws Exception {
		Statement st = config.getSqlConnection().createStatement();
		ResultSet result = st.executeQuery(query);
		while (result.next()) {
			data.put(result.getString(1), result.getInt(2));
		}
		
	}
	
	public int countQuery(String tableName)
	{
		try{
		Statement st = config.getSqlConnection().createStatement();
		String query = "select count(*) as row_count from " + tableName;
		ResultSet result = st.executeQuery(query);
		int r=-1;
		while(result.next())
		{
			r = result.getInt(1);
		}
		return r;}
		catch(Exception e)
		{
			return -1;
		}
	}

	public Map<String, Integer> getData() {
		return data;
	}
}
