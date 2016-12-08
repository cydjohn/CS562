package dcq.stevens.cs562.sql;

import dcq.stevens.cs562.visit.DCQVisitor;

public interface DCQVisit {
	
	public void accept(DCQVisitor visitor);
}
