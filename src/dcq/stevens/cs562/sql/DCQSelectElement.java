package dcq.stevens.cs562.sql;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.List;

import dcq.stevens.cs562.utils.DCQSQLStringParsers;

public class DCQSelectElement extends DCQAbstractSqlElement {
	
	private List<Expression> projectItems;

	public DCQSelectElement(String elementSql, DCQSqlSentence selfSentence) {
		super(elementSql, selfSentence);
		// TODO Auto-generated constructor stub
	}
	
	public List<Expression> getProjectItems() {
		if (projectItems == null) {
			projectItems = new ArrayList<Expression>();
		}
		return projectItems;
	}
	
	private void convert(String elementSql) {
		DCQSQLStringParsers.parseStringToSelectElement(elementSql, this);
	}
	
}
