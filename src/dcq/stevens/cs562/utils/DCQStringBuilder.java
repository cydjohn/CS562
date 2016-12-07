package dcq.stevens.cs562.utils;

import java.util.regex.Pattern;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;;

public class DCQStringBuilder {
	public static boolean isEqual(String s1,String s2) {
		if (s1 == null && s2 == null) {
			return true;
		}
		else if (s1 == null || s2 == null) {
			return false;
		}
		else {
			return s1.equals(s2);
		}
	}
	
	public static boolean isAndOrOr(String s1) {
		Pattern pattern = Pattern.compile("\\band\\b|\\bor\\b", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(s1);
		if(s1 == null) {
			return false;
		}
		else if(matcher.find()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isCompare(String s1) {
		Pattern pattern = Pattern.compile(">=|<=|<>|>|<|=", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(s1);
		if(s1 == null) {
			return false;
		}
		else if(matcher.find()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isAlgorith(String s1) {
		Pattern pattern = Pattern.compile("\\+|-|\\*|/", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(s1);
		if(s1 == null) {
			return false;
		}
		else if(matcher.find()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isCompareOrSingle(String s1) {
		Pattern pattern = Pattern.compile("\\band\\b|\\bor\\b", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(s1);
		if(s1 == null) {
			return false;
		}
		else if(matcher.find()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isEmpty(String s1) {
		if(s1 == null) {
			return true;
		}
		else if(s1.trim().equals("")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isNumber(String s1) {
		if(s1 == null) {
			return false;
		}
		// || s1.trim().matches("^\\d*\\.\\d*$") do not support float
		else if(s1.trim().matches("^\\d+$") ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isString(String s1) {
		if(s1 == null) {
			return false;
		}
		else if(s1.trim().matches("^'.+?'$") || s1.trim().matches("\".+?\"$")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isReplacement(String s1) {
		if(s1 == null) {
			return false;
		}
		else if(s1.trim().matches("^" + DCQConstants.CHARACTER_REPLACEMENT + "\\d+" +"$")) {
			return true;
		}
		return false;
	}
	
	public static boolean isCompute(String s1) {
		if(s1 == null) {
			return false;
		}
		String compute_regex = "\\+|-|\\*|/";
		String[] strings = s1.split(compute_regex);
		if(strings.length > 1) {
			return true;
		}
		return false;
	}
	
	public static int toInt(String s1) {
		if(s1 == null) {
			return 0;
		} else {
			return Integer.valueOf(s1.trim());
		}
	}
	
	public static List<String> splitStringFromParenthesis(String sql) {
		Stack<Integer> indexLeftStack = new Stack<Integer>();
		List<String> results = new ArrayList<String>();
		for(int i = 0; i < sql.length(); i++) {
			if(sql.charAt(i) == '(') {
				indexLeftStack.push(i);
			}
			if(sql.charAt(i) == ')') {
				int lastPosition = indexLeftStack.pop();
				String str_back = sql.substring(0,lastPosition);
				if(!str_back.matches("(?i:.*?(avg|count|max|min|sum)\\s*$)")) {
					results.add(sql.substring(lastPosition+1, i));
				}
				
			}
		}
		return results;
	}
	
	public static String replaceSqlWithReplacement(String sql, List<String> replacements) {
		for(int i = replacements.size() -1 ; i >= 0 ; i--) {
			sql = sql.replaceAll("\\Q(" + replacements.get(i) + ")\\E", DCQConstants.CHARACTER_REPLACEMENT + i);
		}
		return sql;
	}
	
	public static String getBackReplacementSql(String sql, String replacement) {
		List<String> replacements = splitStringFromParenthesis(sql);
		int index = Integer.valueOf(replacement.trim().replaceAll(DCQConstants.CHARACTER_REPLACEMENT, ""));
		String result = "";
		for(int i = replacements.size() -1 ; i >= 0 ; i--) {
			if(index == i) {
				result = replacements.get(i);
				break;
			}
		}
		List<String> replacementsResult = splitStringFromParenthesis(result.trim());
		if(replacementsResult.size() != 0) {
			DCQSQLStringParsers.currentSql = result.trim();
		}
		result = replaceSqlWithReplacement(result.trim(), replacementsResult);
		return result.trim();
	}
	
	
	public static String[] splitSqlIntoStringArray(String a) {
		String[] results = new String[6];
		int fromBegin = getStartMatch(a, "from");
		int whereBegin =getStartMatch(a, "where");
		int groupBegin = getStartMatch(a, "group by");
		int suchthatBegin = getStartMatch(a, "such that");
		int havingBegin = getStartMatch(a, "having");
		
		int selectEnd = getEndMatch(a, "select");
		int fromEnd = getEndMatch(a, "from");
		int whereEnd =getEndMatch(a, "where");
		int groupEnd = getEndMatch(a, "group by");
		int suchthatEnd = getEndMatch(a, "such that");
		int havingEnd = getEndMatch(a, "having");
		
		String selectPart = a.substring(selectEnd, fromBegin);
		String fromPart = "";
		if(whereBegin != -1) {
			fromPart = a.substring(fromEnd, whereBegin);
		} else if(groupBegin != -1) {
			fromPart = a.substring(fromEnd, groupBegin);
		} else {
			fromPart = a.substring(fromEnd, a.length());
		}
		
		String where_part = "";
		if(whereBegin != -1) {
			if(groupBegin != -1) {
				where_part = a.substring(whereEnd, groupBegin);
			} else {
				where_part = a.substring(whereEnd, a.length());
			}
		} 
		
		String group_part = "";
		if(groupBegin != -1) {
			if(suchthatBegin != -1) {
				group_part = a.substring(groupEnd, suchthatBegin);
			} else if(havingBegin != -1){
				group_part = a.substring(groupEnd, havingBegin);
			} else {
				group_part = a.substring(groupEnd,  a.length());
			}
		} 
		
		String suchthat_part = "";
		if(suchthatBegin != -1) {
			if(havingBegin != -1) {
				suchthat_part = a.substring(suchthatEnd, havingBegin);
			} else {
				suchthat_part = a.substring(suchthatEnd, a.length());
			}
		} 
		
		String having_part = "";
		if(havingBegin != -1) {
			having_part = a.substring(havingEnd, a.length());
		} 
		
		results[0] = selectPart.trim();
		results[1] = fromPart.trim();
		results[2] = where_part.trim();
		results[3] = group_part.trim();
		results[4] = suchthat_part.trim();
		results[5] = having_part.trim();
		
		return results;
	}
	
	private static int getEndMatch(String a, String regex) {
		 Pattern pattern3 = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		 Matcher matcher = pattern3.matcher(a);
		while(matcher.find()) {
			return matcher.end();
		}
		return -1;
	}
	
	private static int getStartMatch(String a, String regex) {
		 Pattern pattern3 = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		 Matcher matcher = pattern3.matcher(a);
		while(matcher.find()) {
			return matcher.start();
		}
		return -1;
	}
	
	public static String getProperPath(String path) {
		if(!(path.charAt(path.length()-1) == '/')) {
			path = path + "/";
		} 
		return path;
	}
	
	public static String getFinalPath(String path) {
		String path1 =  getProperPath(path) + "src/";
		File src = new File(path1);
		if(!src.exists()) {
			src.mkdirs();
		}
		return path1;
	}
	
}
