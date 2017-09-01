package data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JDBCDataComprator {

	public static Map<String, ComparisonFields> DataComprator(Map<String, Integer> mysqlData,
			Map<String, Integer> vsqlData) {
		Map<String, ComparisonFields> comparitiveData = new LinkedHashMap<String, ComparisonFields>();

		for (Entry<String, Integer> m : mysqlData.entrySet()) {
			String schemaName;
			String tableName;
			String mysqlRowCount;
			String vsqlRowCount;

			schemaName = m.getKey().split("\\.")[0];
			tableName = m.getKey().split("\\.")[1];
			mysqlRowCount = "" + m.getValue();
			if (vsqlData.get(m.getKey()) != null) {
				vsqlRowCount = "" + vsqlData.get(m.getKey());
			} else {
				vsqlRowCount = null;
			}
			comparitiveData.put(m.getKey(), new ComparisonFields(schemaName, tableName, mysqlRowCount, vsqlRowCount));
		}

		return comparitiveData;
	}
}
