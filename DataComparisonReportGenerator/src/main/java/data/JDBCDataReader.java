package data;

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
	
	public void executeQuery() throws Exception {
		Statement st = config.getSqlConnection().createStatement();
		ResultSet result = st.executeQuery(query);
		while (result.next()) {
			data.put(result.getString(1), result.getInt(2));
		}
	}

	public Map<String, Integer> getData() {
		return data;
	}
}
