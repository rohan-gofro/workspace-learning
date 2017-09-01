package datafromTableII;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCConnectionConfig {
	private String url;
	private String username;
	private String password;
	private String type;
	private Connection sqlConnection;
	private String includedSchema;
	private String includedTables;
	private String dbName;

	public JDBCConnectionConfig(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		Properties props = new Properties();
		props.load(fis);
		this.type = props.getProperty("type");
		this.username = props.getProperty("username");
		this.password = props.getProperty("password");
		this.includedSchema = props.getProperty("included_schema");
		this.includedTables = props.getProperty("included_tables");
		this.url = "jdbc:" + type + "://" + props.getProperty("host");
		this.dbName = file.getName().split("-")[1];
		this.SetSqlConnection();
	}

	private void SetSqlConnection() throws Exception {
		sqlConnection = DriverManager.getConnection(url, username, password);
	}

	public Connection getSqlConnection() {
		return sqlConnection;
	}

	public String getType() {
		return type;
	}

	public String getIncludedSchema() {
		return includedSchema;
	}

	public String getIncludedTables() {
		return includedTables;
	}

	public String getDbName() {
		return dbName;
	}
}
