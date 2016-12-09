package dcq.cs562.sql;

import java.util.ArrayList;

public class DCQMFVariables {
	//NUMBER OF GROUPING VARIABLES(n)
	private Integer N;
	
	//GROUPING ATTRIBUTES(V)
	private ArrayList<String> V;
	
	//F-VECT([F])
	private ArrayList<String> F;
	
	
	//SELECT CONDITION-VECT([Sigma])
	private ArrayList<ArrayList> Sigma;
	
	//HAVING_CONDITION(G)
	private ArrayList<String> G;
	
	//WHERE CLAUSE
	private String where;
	
	//SELECT ATTRIBUTE(S)
	private ArrayList<String>  S;
	
	
	public ArrayList<String> getS() {
		return S;
	}
	public void setS(ArrayList<String> s) {
		S = s;
	}
	public Integer getN() {
		return N;
	}
	public void setN(Integer n) {
		N = n;
	}
	public ArrayList<String> getV() {
		return V;
	}
	public void setV(ArrayList<String> v) {
		V = v;
	}
	public ArrayList<String> getF() {
		return F;
	}
	public void setF(ArrayList<String> f) {
		F = f;
	}
	public ArrayList<ArrayList> getSigma() {
		return Sigma;
	}
	public void setSigma(ArrayList<ArrayList> sigma) {
		Sigma = sigma;
	}
	public ArrayList<String> getG() {
		return G;
	}
	public void setG(ArrayList<String> g) {
		G = g;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	
	
	public String toString() {
		return "DCQMFVariables [S=" + S + ", N=" + N + ", V=" + V + ", F=" + F
				+ ", Sigma=" + Sigma + ", G=" + G + ", where=" + where + "]";
	}
	
	public DCQMFVariables(ArrayList<String> s, Integer n, ArrayList<String> v,
			ArrayList<String> f, ArrayList<ArrayList> sigma,
			ArrayList<String> g, String where) {
		super();
		S = s;
		N = n;
		V = v;
		F = f;
		Sigma = sigma;
		G = g;
		this.where = where;
	}
	
	public DCQMFVariables() {
		super();
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DCQMFVariables other = (DCQMFVariables) obj;
		if (F == null) {
			if (other.F != null)
				return false;
		} else if (!F.equals(other.F))
			return false;
		if (G == null) {
			if (other.G != null)
				return false;
		} else if (!G.equals(other.G))
			return false;
		if (N == null) {
			if (other.N != null)
				return false;
		} else if (!N.equals(other.N))
			return false;
		if (S == null) {
			if (other.S != null)
				return false;
		} else if (!S.equals(other.S))
			return false;
		if (Sigma == null) {
			if (other.Sigma != null)
				return false;
		} else if (!Sigma.equals(other.Sigma))
			return false;
		if (V == null) {
			if (other.V != null)
				return false;
		} else if (!V.equals(other.V))
			return false;
		if (where == null) {
			if (other.where != null)
				return false;
		} else if (!where.equals(other.where))
			return false;
		return true;
	}

}
