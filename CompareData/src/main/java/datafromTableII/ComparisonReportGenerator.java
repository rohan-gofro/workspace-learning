package datafromTableII;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class ComparisonReportGenerator {

	public static void main(String[] args) throws Exception {

		String rootPath = "";
		//File mysqlopsConfig = new File(rootPath + "db-ops-config.properties");
		File mysqlipsConfig = new File(rootPath + "db-ips-config.properties");
		File mysqlcmsConfig = new File(rootPath + "db-cms-config.properties");
		File verticaConfig = new File(rootPath + "db-vertica-config.properties");

		//Map<String, Integer> opsSqlMap = new LinkedHashMap<String, Integer>();
		Map<String, Integer> ipsSqlMap = new LinkedHashMap<String, Integer>();
		Map<String, Integer> cmsSqlMap = new LinkedHashMap<String, Integer>();
		
		
		// Reading Data
		//JDBCConnectionConfig opsconnection = new JDBCConnectionConfig(mysqlopsConfig);
		//JDBCDataReader mysqlops = new JDBCMySqlDataReader(opsconnection);
//		JDBCConnectionConfig ipsconnection = new JDBCConnectionConfig(mysqlipsConfig);
//		JDBCDataReader mysqlips = new JDBCMySqlDataReader(ipsconnection);
		JDBCConnectionConfig cmsconnection = new JDBCConnectionConfig(mysqlcmsConfig);
		JDBCDataReader mysqlcms = new JDBCMySqlDataReader(cmsconnection);
		
		JDBCConnectionConfig verticaconnection = new JDBCConnectionConfig(verticaConfig);
		JDBCDataReader vertica = new JDBCVSqlDataReader(verticaconnection);

		// Sql Data in one map
		//opsSqlMap.putAll(mysqlops.getData());
		//ipsSqlMap.putAll(mysqlips.getData());
		cmsSqlMap.putAll(mysqlcms.getData());

		// Vertica Data in one map
		//Map for SQL
		
		/*
		for(Map.Entry<String, Integer> m : opsSqlMap.entrySet())
		{
			
			System.out.println("ops " + m.getKey() + " " + mysqlops.countQuery(m.getKey()) + " " +vertica.countQuery(m.getKey()));
			
		}
		
		for(Map.Entry<String, Integer> m : ipsSqlMap.entrySet())
		{
			
			System.out.println("ips " + m.getKey() + " " + mysqlips.countQuery(m.getKey()) + " " +vertica.countQuery(m.getKey()));
			
		}*/
		for(Map.Entry<String, Integer> m : cmsSqlMap.entrySet())
		{
			
			System.out.println("cms " + m.getKey() + " " + mysqlcms.countQuery(m.getKey()) + " " +vertica.countQuery(m.getKey()));
			
		}
		System.out.println("done");
		
		/*
		for (Map.Entry<String, ComparisonFields> map : comparisonMap.entrySet()) {
			ComparisonFields cf = map.getValue();
			
			System.out.format(" %5d | %30s | %50s | %10s | %10s \n", ++serialno , cf.getSchemaName(), cf.getTableName(),
					cf.getMysqlRowCount(), cf.getVsqlRowCount());
		}*/
	}
}
