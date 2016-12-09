package dcq.cs562.main;

import java.util.HashMap;

import dcq.cs562.sql.DCQMFVariables;
import dcq.cs562.util.DCQConnector;
import dcq.cs562.util.DCQReadFile;
import dcq.cs562.util.DCQWriteFIle;

public class main {
	public static void main(String[] args) throws Exception {
	DCQConnector connector;
	try{
		connector = new DCQConnector();

	}catch (Exception e) {
		e.printStackTrace();
		System.out.println("Connection fail!!!");
		return;
	}

	 HashMap<String, String> tableStructure = connector.getTableStructure();
	DCQReadFile f = new DCQReadFile();

	DCQMFVariables variables = null;
//	variables = f.load("test02");
	variables = f.load("testSql1.txt");
	
	DCQWriteFIle fw = new DCQWriteFIle();
	String fileName = fw.writeFile(connector,variables);

	}
}

