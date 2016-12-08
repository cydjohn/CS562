package dcq.stevens.cs562.sql;

import java.util.HashMap;

import dcq.stevens.cs562.utils.DCQSQLStringParse;

public class DCQSqlSentence {
	private DCQSelectElement selectElement; 
	
	private DCQFromElement fromElement;
	
	private DCQWhereElement whereElement;
	
	private DCQGroupByElement groupByElement;
	
	private DCQHavingElement havingElement;
	
	private DCQSuchThatElement suchThatElement;
	
	private HashMap<String, DCQGroupingVariable> groupingVariableDictionary;
	
	private HashMap<String, String> attributesType;
	
	public DCQSqlSentence(String sql) {
		// TODO Auto-generated constructor stub
		String[] temp = DCQSQLStringParse.parseString(sql);
	}
}
