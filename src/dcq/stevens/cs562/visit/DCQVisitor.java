package dcq.stevens.cs562.visit;

import dcq.stevens.cs562.sql.DCQVariable;

public interface DCQVisitor {
	
	public void visit(DCQVariable variable);
	
	public void visit(DCQExpression expression);
}
