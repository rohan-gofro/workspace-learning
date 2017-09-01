package data;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ComparisonReportGenerator {

	public static void docomparison() throws Exception {

		int serialno = 0;
		String rootPath = "";
		//String rootPath = "/home/rohangupta/work/my-projects/Mysql-Vertica-Data-Compartison-Report/";
		File mysqlopsConfig = new File(rootPath + "db-ops-config.properties");
		//File mysqlipsConfig = new File(rootPath + "db-ips-config.properties");
		//File mysqlcmsConfig = new File(rootPath +"db-cms-config.properties");
		File verticaConfig = new File(rootPath + "db-vertica-config.properties");

		Map<String, Integer> sqlMap = new LinkedHashMap<String, Integer>();
		Map<String, Integer> verticaMap;
		Map<String, ComparisonFields> comparisonMap;
		
		// Reading Data
		JDBCDataReader mysqlops = new JDBCMySqlDataReader(new JDBCConnectionConfig(mysqlopsConfig));
		//JDBCDataReader mysqlips = new JDBCMySqlDataReader(new JDBCConnectionConfig(mysqlipsConfig));
		//JDBCDataReader mysqlcms = new JDBCMySqlDataReader(new JDBCConnectionConfig(mysqlcmsConfig));
		JDBCDataReader vertica = new JDBCVSqlDataReader(new JDBCConnectionConfig(verticaConfig));

		// Sql Data in one map
		sqlMap.putAll(mysqlops.getData());
		//sqlMap.putAll(mysqlips.getData());
		//sqlMap.putAll(mysqlcms.getData());

		// Vertica Data in one map
		verticaMap = vertica.getData();
		
		// Passing Master Map as first parameter
		comparisonMap = JDBCDataComprator.DataComprator(sqlMap, verticaMap);

		//File name as current timestamp
		
		String file = "Report/"+new Date().toString() + ".csv";
    	FileWriter fw=new FileWriter(file);    
		
		for (Map.Entry<String, ComparisonFields> map : comparisonMap.entrySet()) {
			ComparisonFields cf = map.getValue();
			String diff = "";
			if(cf.getVsqlRowCount() != null)
			diff = "" + (Integer.parseInt(cf.getVsqlRowCount()) - Integer.parseInt(cf.getMysqlRowCount())); 
			fw.write(++serialno + "," + cf.getSchemaName() + ","+ cf.getTableName()+ ","+cf.getMysqlRowCount()+ ","+cf.getVsqlRowCount()+ ","+diff+"\n");
		}
		fw.close();
		System.out.println("success");
	}
}