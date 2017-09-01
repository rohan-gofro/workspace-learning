package datafromTableII;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class ComparisonReportGenerator {

	public static void main(String[] args) throws Exception {

		String file = args[0];
		File mysqlConfig = new File(file);
		
		File vsqlConfig = new File("db-vertica-config.properties");
		Map<String, Integer> sqlMap = new LinkedHashMap<String, Integer>();
		
		
		// Reading Data
		
		JDBCConnectionConfig mysqlConnection = new JDBCConnectionConfig(mysqlConfig);
		JDBCDataReader mysqlReader = new JDBCMySqlDataReader(mysqlConnection);
		
		JDBCConnectionConfig vsqlConnection = new JDBCConnectionConfig(vsqlConfig);
		JDBCDataReader vsqlReader = new JDBCVSqlDataReader(vsqlConnection);
		
		
		sqlMap.putAll(mysqlReader.getData());

		for(Map.Entry<String, Integer> m : sqlMap.entrySet())
		{
			System.out.println(m.getKey() + " " + mysqlReader.countQuery(m.getKey()) + " " +vsqlReader.countQuery(m.getKey()));	
		}
	}
}
