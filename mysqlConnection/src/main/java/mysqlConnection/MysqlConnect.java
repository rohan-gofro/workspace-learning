package mysqlConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MysqlConnect {
	
	String mysqlUrl;
	String mysqlUsername;
	String mysqlPassword;
	
	public void setProperties() throws IOException
	{
		File file = new File("mysql.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties props = new Properties();
		props.load(fis);
		this.mysqlUrl = "jdbc:mysql://localhost/Rohan?allowMultiQueries=true";
		this.mysqlUsername = "root";
		this.mysqlPassword = "root";
	}

	public static void main(String[] args) throws IOException, Exception {
		
		MysqlConnect obj = new MysqlConnect();
		obj.setProperties();
		System.out.println(obj.mysqlUrl);
		Connection con1 = DriverManager.getConnection(obj.mysqlUrl , obj.mysqlUsername , obj.mysqlPassword);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = con1;
			String query = "select * from lms.temp_dla_1";
			Statement st = con.createStatement();
			ResultSet set =  st.executeQuery(query);
			while(set.next())
			{
				System.out.print(set.getInt(1) + ",");
			}
			con.close();
		} catch (Exception E) {
			E.printStackTrace();
		}

	}

}
