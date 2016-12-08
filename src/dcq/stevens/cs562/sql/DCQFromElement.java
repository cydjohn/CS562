package dcq.stevens.cs562.sql;

public class DCQFromElement {
	private String tableName;
	
	public String getTableName(){
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String toString() {
		return tableName;
	}
}
