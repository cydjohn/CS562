package dcq.stevens.cs562.visit;

import dcq.stevens.cs562.sql.DCQVariable;

public interface DCQExpression {
	void calculate();
	
	DCQVariable getVariable();
	
	String getConvertionName();
}
