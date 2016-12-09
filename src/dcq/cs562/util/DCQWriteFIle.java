package dcq.cs562.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dcq.cs562.sql.DCQMFVariables;
import dcq.cs562.sql.DCQPrintFile;

public class DCQWriteFIle {
	public String writeFile(DCQConnector connector, DCQMFVariables varibles) throws IOException{
		FileWriter fw = null;
		// initial the output file name and package name
		String className = "mfQuery.java";
		String packageName = "dcq.cs562.mfQuery";
		DCQPrintFile printFile = new DCQPrintFile();
		// set attributes of output file 
		printFile.setFileName(className);
		printFile.setPackageName(packageName);
		printFile.setTableName(connector.getTableStructure());
		printFile.setVaribles(varibles);
		// initial the importList
		ArrayList<String> importList = new ArrayList<String>();
		importList.add("java.sql.Statement");
		importList.add("java.sql.DriverManager");
		importList.add("java.sql.Connection");
		importList.add("java.util.List");
		importList.add("java.util.ArrayList");
		importList.add("java.util.HashMap");
		importList.add("java.util.Iterator");
		importList.add("java.sql.ResultSet");
		// set importList attribute of output file 
		printFile.setImportList(importList);
		try{
            
            fw = new FileWriter("dcq.cs562.mfQuery//"+className);
            // write it to the disk
            fw.write(printFile.write(connector));

            
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(fw != null){
                fw.close();
            }
        }
		return className;
    }
}
