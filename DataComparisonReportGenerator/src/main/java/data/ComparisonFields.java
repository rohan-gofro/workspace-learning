package data;

public class ComparisonFields {

	private String schemaName;
	private String tableName;
	private String mysqlRowCount;
	private String vsqlRowCount;

	public ComparisonFields(String schemaName, String tableName, String mysqlRowCount, String vsqlRowCount) {
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.mysqlRowCount = mysqlRowCount;
		this.vsqlRowCount = vsqlRowCount;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getTableName() {
		return tableName;
	}

	public String getMysqlRowCount() {
		return mysqlRowCount;
	}

	public String getVsqlRowCount() {
		return vsqlRowCount;
	}

}
