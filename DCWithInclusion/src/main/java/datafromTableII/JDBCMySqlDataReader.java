package datafromTableII;

public class JDBCMySqlDataReader extends JDBCDataReader {

	public JDBCMySqlDataReader(JDBCConnectionConfig config) throws Exception {

		this.config = config;
		buildQuery();
		executeQuery(query);
	}

	public void buildQuery() {
		String[] schema;
		String[] tables;
		int i;
		this.query = "select abs.Table_name , Table_rows from(SELECT CONCAT(table_schema , '.' ,TABLE_NAME) AS Table_name , TABLE_ROWS  FROM information_schema.tables ";
		if (config.getIncludedSchema() != null) {
			this.query = this.query + " WHERE table_schema IN ('in'";
			schema = config.getIncludedSchema().split(",");
			for (i = 0; i < schema.length; i++) {
				this.query = this.query + ",'" + schema[i] + "'";
			}
			this.query = this.query + ")";
		} else {
			this.query = this.query + " WHERE table_schema NOT IN ('information_schema','mysql') ";
		}

		this.query = this.query + "and Table_name not like '%_aud' and Table_name not like 'QRTZ%'  order by 1) as abs";

		if (config.getIncludedTables() != null) {
			tables = config.getIncludedTables().split(",");
			this.query = this.query + " WHERE Table_name IN (";
			for (i = 0; i < tables.length - 1; i++) {
				this.query = this.query + "'" + tables[i] + "',";
			}
			this.query = this.query + "'" + tables[i] + "') ";
		}
		
	}
}
