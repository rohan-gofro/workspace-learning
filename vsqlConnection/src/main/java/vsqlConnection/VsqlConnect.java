package vsqlConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class VsqlConnect {

	String vsqlUrl;
	String vsqlUsername;
	String vsqlPassword;

	public void setProperties() throws IOException {
		File file = new File("vsql.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties props = new Properties();
		props.load(fis);
		this.vsqlUrl = "jdbc:vertica://vertica/DWH1";
		System.out.println(vsqlUrl);
		this.vsqlUsername = props.getProperty("vsql_username");
		this.vsqlPassword = props.getProperty("vsql_password");
	}

	public static void main(String[] args) {

		VsqlConnect obj = new VsqlConnect();

		try {

			obj.setProperties();
			Class.forName("com.vertica.jdbc.Driver");
			Connection con = DriverManager.getConnection(obj.vsqlUrl, "dbadmin", "dbadmin");
			String query = "SELECT anchor_table_name, SUM(row_count) FROM v_monitor.projection_storage where anchor_table_schema='las' and anchor_table_name not like 'stage_xxx%' and anchor_table_name not like '%_aud' GROUP BY anchor_table_schema, anchor_table_name order by 1;";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				System.out.format("%40s | %10d\n" , rs.getString(1) , rs.getInt(2));
			}
			con.close();
			System.out.println("Bye");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
