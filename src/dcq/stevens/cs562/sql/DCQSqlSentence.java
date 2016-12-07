package dcq.stevens.cs562.sql;

import java.util.HashMap;

public class DCQSqlSentence {
	private DCQSelectElement selectElement; 
	
	private DCQFromElement fromElement;
	
	private DCQWhereElement whereElement;
	
	private DCQGroupByElement groupByElement;
	
	private DCQHavingElement havingElement;
	
	private DCQSuchThatElement suchThatElement;
	
	private HashMap<String, DCQGroupingVariable> groupingVariableDictionary;
	
	
}
