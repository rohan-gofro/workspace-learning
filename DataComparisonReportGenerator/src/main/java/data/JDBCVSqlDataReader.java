package data;

public class JDBCVSqlDataReader extends JDBCDataReader{
	
	public JDBCVSqlDataReader(JDBCConnectionConfig config) throws Exception {
		
		this.config = config;
		buildQuery();
		executeQuery();
		
	}
	
	
	public void buildQuery() {
		String[] schema;
		String[] tables;
		int i;
		this.query = "select abs.Table_name , abs.Row_count from ( select anchor_table_schema || '.' || anchor_table_name as Table_name , SUM(row_count) as Row_count FROM v_monitor.projection_storage";
		if (config.getExemptedSchema() != null) {
			this.query = this.query + " WHERE anchor_table_schema NOT IN ('tungsten_applier_cms'";
			schema = config.getExemptedSchema().split(",");
			for (i = 0; i < schema.length; i++) {
				this.query = this.query + ",'" + schema[i] + "'";
			}
			this.query = this.query + ")";
		} else {
			this.query = this.query + " WHERE anchor_table_schema NOT IN ('tungsten_applier_cms') ";
		}
		this.query = this.query
				+ " and anchor_table_name not like 'stage_xxx%' and anchor_table_name not like '%_aud' and anchor_table_name not like 'QRTZ%' GROUP BY anchor_table_schema, anchor_table_name order by 1) as abs ";

		if (config.getExemptedTables() != null) {
			tables = config.getExemptedTables().split(",");
			this.query = this.query + " WHERE Table_name NOT IN (";
			for (i = 0; i < tables.length - 1; i++) {
				this.query = this.query + "'" + tables[i] + "',";
			}
			this.query = this.query + "'" + tables[i] + "') ";
		}
	}


}
