package dcq.stevens.cs562.sql;

public class DCQAbstractSqlElement {
	private DCQSqlSentence selfSentence;
	
	private boolean exist = true;
	
	public DCQAbstractSqlElement(String elementSql, DCQSqlSentence selfSentence) {
		this.selfSentence = selfSentence;
	}
	
	public DCQSqlSentence getSelfSentce() {
		return selfSentence;
	}
	
	public void setSelfSentence(DCQSqlSentence selfSentence) {
		this.selfSentence = selfSentence;
	}
	
	public boolean isExist() {
		return exist;
	}
	
	public void setExist(boolean exist) {
		this.exist = exist;
	}
}
