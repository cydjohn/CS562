package dcq.stevens.cs562.utils;

import java.beans.Expression;
import java.util.HashMap;

import dcq.stevens.cs562.sql.DCQSelectElement;

public class DCQSQLStringParsers {
	public static String currentSql = "";
	
	public static String[] parseString(String sql) {
		return DCQStringBuilder.splitSqlIntoStringArray(sql);
	}
	
	public static void parseStringToSelectElement(String sql,DCQSelectElement element) {
		if(DCQStringBuilder.isEmpty(sql)) {
			element.setExist(false);
			return;
		}
		
		String[] strings = sql.split(",");
		for(int i = 0;i<strings.length; i++) {
			strings[i] = strings[i].trim();
			element.getProjectItems().add(processExpression(strings[i], element.getSelfSentce().getGrouping_variable_dic()))
		}
	}
	
	
	
	
	private static Expression processExpression(String express, HashMap<String, DCQGroupingVaribale> dic) {
		Expression final_expression = null;
		if(DCQStringBuilder.isReplacement(express)) {
			String replaceSql = StringBuilder.getBackReplacementSql(current_sql, express);
			if(DCQStringBuilder.isAndOrOr(replaceSql)) {
				return (DCQComparisonAndComputeExpression)generateComputeOrComparisonExpression(replaceSql, dic);
			}
			if(DCQStringBuilder.isCompare(replaceSql)) {
				return gernerateComparisonAndComputeExpression(replaceSql, dic);
			}
			if(DCQStringBuilder.isCompute(replaceSql)) {
				return generateSingleComputeExpression(replaceSql, dic);
			}
		}
		if(DCQStringBuilder.isCompute(express.trim())) {
			return generateSingleComputeExpression(express, dic);
		}
		if(express.trim().matches("(?i:avg\\(.+?\\)|count\\(.+?\\)|min\\(.+?\\)|max\\(.+?\\)|sum\\(.+?\\))")) {
			final_expression = processAggregateExpression(express.trim(), dic);
		} else if(DCQStringBuilder.isNumber(express)) {
			final_expression = new IntegerExpression(Integer.valueOf(express.trim()));
		} else if(DCQStringBuilder.isString(express)){ 
			final_expression = new StringExpression(express.trim());
		} else {
			final_expression = processSimpleExpression(express.trim(), dic);
		}
		return final_expression;
	}

}
