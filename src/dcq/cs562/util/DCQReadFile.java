package dcq.cs562.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.sound.midi.Soundbank;

import dcq.cs562.sql.DCQMFVariables;

public class DCQReadFile {
	public DCQReadFile() {
		super();
	}
	public BufferedReader readFile(String fileName) {
		String path = System.getProperty("user.dir");

		String path1 = path+"/sql/" + fileName;  

		System.out.println(path1);
		File file = new File(path1);
		BufferedReader bufferedReader = null ;
		try {

			String encoding = "GBK";
			if (file.isFile() && file.exists()) { // the file is exit
				// Reader reader = null;
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
				bufferedReader = new BufferedReader(read);
			} else {
				System.out.println("system cannot find the file");

			}
			
		} catch (

		Exception e)

		{
			e.printStackTrace();
		}
		return bufferedReader;
	}
	
	public DCQMFVariables load(String filename) throws IOException {
		boolean flag = false;
		DCQMFVariables varibles = new DCQMFVariables();
		BufferedReader bf = readFile(filename);
		System.out.println(bf);
		String selectAttribute = bf.readLine();
		//#SELECT ATTRIBUTE(S)
		//System.out.println(selectAttribute);
		String attribute = bf.readLine();
		String[] attributes = attribute.split(",");
		ArrayList<String> varibles_s = new ArrayList<String>();
		for (int i = 0; i < attributes.length; i++) {
			String att = attributes[i].trim();
			varibles_s.add(att);
		}
		//System.out.println(varibles_s);
		
		
		//#NUMBER OF GROUPING VARIABLES(n)
		String numberCluse = bf.readLine();
		//System.out.println(numberCluse);
		String number = bf.readLine();
		//System.out.println(number);
		
		
		//#GROUPING ATTRIBUTES(V)
		String group = bf.readLine();
		//System.out.println(group);
		String groupAttr = bf.readLine();
		String[] groupAttrs = groupAttr.split(",");
		ArrayList<String> varibles_v = new ArrayList<String>();
		for (int i = 0; i < groupAttrs.length; i++) {
			String att = groupAttrs[i].trim();
			varibles_v.add(att);
		}
		//System.out.println(varibles_v);
		//#F-VECT([F])
		String f = bf.readLine();
		//System.out.println(f);
		String fv = bf.readLine();
		String[] fvs = fv.split(",");
		ArrayList<String> varibles_f = new ArrayList<String>();
		for (int i = 0; i < fvs.length; i++) {
			String vaf = fvs[i].trim();
			varibles_f.add(vaf);
		}
		//System.out.println(varibles_f);
		//#SELECT CONDITION-VECT([sigma])  number
		String sigmaClause = bf.readLine();
		//System.out.println(sigmaClause);
		
		ArrayList<ArrayList> varibles_sigmas = new ArrayList<ArrayList>();
		
		for (int i = 0; i <  Integer.parseInt(number); i++) {
			String sigma = bf.readLine();
			String[] sigmas = sigma.split("and");
			ArrayList<String> varibles_sigma = new ArrayList<String>();
			for (int j = 0; j < sigmas.length; j++) {
				String sigmaValue = sigmas[j].trim();
				varibles_sigma.add(sigmaValue);
			}
			varibles_sigmas.add(varibles_sigma);
		}
		
		//System.out.println(varibles_sigmas);
		//#HAVING CONDITION(G)
		String HavingCluse = bf.readLine();
		//System.out.println(HavingCluse);
		String HavingCondition = bf.readLine();
		ArrayList<String> varibles_g = new ArrayList<String>();
		if(!HavingCondition.startsWith("#")){
			String[] havingConditions = HavingCondition.split("and");
			
			for (int i = 0; i < havingConditions.length; i++) {
				String att = havingConditions[i].trim();
				varibles_g.add(att);
			}
			//System.out.println(varibles_g);
		}else{
			flag = true;
		}
	
		//#WHERE CONDITION
		String where ;
		if(!flag){
			String WHEREClause = bf.readLine();
			//System.out.println(WHEREClause);
			 where = bf.readLine();
			//System.out.println(where);
		}else{
			String WHEREClause = HavingCondition;
			//System.out.println(WHEREClause);
			 where = bf.readLine();
			//System.out.println(where);
		}
		varibles.setN(Integer.parseInt(number));
		varibles.setS(varibles_s);
		varibles.setV(varibles_v);
		varibles.setF(varibles_f);
		varibles.setSigma(varibles_sigmas);
		varibles.setG(varibles_g);
		varibles.setWhere(where);
		
		return varibles;
		
	}
}
