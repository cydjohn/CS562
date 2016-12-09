// This is an auto generate file 
 package dcq.cs562.mfQuery;

import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.sql.ResultSet;


 class Table{
	private String prod;
	public String getProd(){
		return prod;
	}
	public void setProd(String prod){
		this.prod = prod;
	}
	private int month;
	public int getMonth(){
		return month;
	}
	public void setMonth(int month){
		this.month = month;
	}
	private int year;
	public int getYear(){
		return year;
	}
	public void setYear(int year){
		this.year = year;
	}
	private String state;
	public String getState(){
		return state;
	}
	public void setState(String state){
		this.state = state;
	}
	private int quant;
	public int getQuant(){
		return quant;
	}
	public void setQuant(int quant){
		this.quant = quant;
	}
	private String cust;
	public String getCust(){
		return cust;
	}
	public void setCust(String cust){
		this.cust = cust;
	}
	private int day;
	public int getDay(){
		return day;
	}
	public void setDay(int day){
		this.day = day;
	}
}

 class MFStruture{
	private String cust;
	public String getCust(){
		return cust;
	}
	public void setCust(String cust){
		this.cust = cust;
	}
	private double mf1_count_quant;
	public double getMf1_count_quant(){
		return mf1_count_quant;
	}
	public void setMf1_count_quant(double mf1_count_quant){
		this.mf1_count_quant = mf1_count_quant;
	}
	private double mf2_count_quant;
	public double getMf2_count_quant(){
		return mf2_count_quant;
	}
	public void setMf2_count_quant(double mf2_count_quant){
		this.mf2_count_quant = mf2_count_quant;
	}
	private double mf3_count_quant;
	public double getMf3_count_quant(){
		return mf3_count_quant;
	}
	public void setMf3_count_quant(double mf3_count_quant){
		this.mf3_count_quant = mf3_count_quant;
	}
}
public class  mfQuery {
	public static void main(String[] args) throws Exception {
		Class.forName("org.postgresql.Driver");
	Connection con = DriverManager.getConnection(
	"jdbc:postgresql://localhost:5432/test", "serf", "HailCS548!");
	Statement st = con.createStatement();
	ResultSet rs;
	System.out.println("Connect to databse success!!!");

	
	ArrayList<MFStruture> mfs = new ArrayList<MFStruture>(); 
	rs = st.executeQuery("select * from sales" ); 
	while (rs.next()){ 
		boolean flag = false; 
	Table t = new Table();
	t.setProd(rs.getString("prod"));
	t.setMonth(rs.getInt("month"));
	t.setYear(rs.getInt("year"));
	t.setState(rs.getString("state"));
	t.setQuant(rs.getInt("quant"));
	t.setCust(rs.getString("cust"));
	t.setDay(rs.getInt("day"));
	//handle the mfs part
	for (int i = 0; i < mfs.size()&&!flag; i++) {
	// if the tuples have not appear in MFstructure, we need add it to the arraylist 
	if (mfs.get(i).getCust().equals(t.getCust())&&!flag){
		flag = true;
	}
	}
		if (!flag){
	MFStruture mfstructue = new MFStruture();
	mfstructue.setCust(t.getCust());
	mfstructue.setMf1_count_quant(0);
	mfstructue.setMf2_count_quant(0);
	mfstructue.setMf3_count_quant(0);
	mfs.add(mfstructue);
	}
	}
	
// ==========================================================	
	
	
	rs = st.executeQuery("select * from sales "); 
	while (rs.next()){ 
	Table t = new Table();
	t.setProd(rs.getString("prod"));
	t.setMonth(rs.getInt("month"));
	t.setYear(rs.getInt("year"));
	t.setState(rs.getString("state"));
	t.setQuant(rs.getInt("quant"));
	t.setCust(rs.getString("cust"));
	t.setDay(rs.getInt("day"));
	for(int i = 0;i < mfs.size(); i ++){ 
	 
	MFStruture mf = mfs.get(i); 
	if ((t.getCust().equals(mfs.get(i).getCust()))&&(t.getState().equals("NY"))){
	mf.setMf1_sum_quant(mf.getMf1_sum_quant()+ t.getQuant());
	mf.setMf1_count_quant(mf.getMf1_count_quant()+1);
	mfs.set(i, mf);
	}
	}
	for(int i = 0;i < mfs.size(); i ++){ 
	 
	MFStruture mf = mfs.get(i); 
	if ((t.getCust().equals(mfs.get(i).getCust()))&&(t.getState().equals("CT"))){
	mf.setMf2_sum_quant(mf.getMf2_sum_quant()+ t.getQuant());
	mf.setMf2_count_quant(mf.getMf2_count_quant()+1);
	mfs.set(i, mf);
	}
	}
	for(int i = 0;i < mfs.size(); i ++){ 
	 
	MFStruture mf = mfs.get(i); 
	if ((t.getCust().equals(mfs.get(i).getCust()))&&(t.getState().equals("NJ"))){
	mf.setMf3_sum_quant(mf.getMf3_sum_quant()+ t.getQuant());
	mf.setMf3_count_quant(mf.getMf3_count_quant()+1);
	mfs.set(i, mf);
	}
	}
	}
for(int i = 0;i < mfs.size(); i ++){ 
	MFStruture mf = mfs.get(i);
if(!(((mf.getMf1_sum_quant() / mf.getMf1_count_quant())>(mf.getMf2_sum_quant() / mf.getMf2_count_quant()))&&((mf.getMf1_sum_quant() / mf.getMf1_count_quant())>(mf.getMf3_sum_quant() / mf.getMf3_count_quant()))))){
	mfs.remove(i);
	i--;
}
}
	System.out.printf("%-8s","cust");
	System.out.print("    ");
	System.out.printf("%-11s","1_avg_quant");
	System.out.print("    ");
	System.out.printf("%-11s","2_avg_quant");
	System.out.print("    ");
	System.out.printf("%-11s","3_avg_quant");
	System.out.print("    ");
	System.out.println("");
	System.out.print("========");
	System.out.print("    ");
	System.out.print("===========");
	System.out.print("    ");
	System.out.print("===========");
	System.out.print("    ");
	System.out.print("===========");
	System.out.print("    ");
	System.out.println("");
	for (int i = 0; i < mfs.size(); i++){
	MFStruture mf = mfs.get(i);

	System.out.printf("%-8s",mf.getCust());
	System.out.print("    ");
	System.out.printf("%11f",	Double.parseDouble(mf.getMf1_sum_quant()+"") / Double.parseDouble(mf.getMf1_count_quant()+""));
	System.out.print("    ");
	System.out.printf("%11f",	Double.parseDouble(mf.getMf2_sum_quant()+"") / Double.parseDouble(mf.getMf2_count_quant()+""));
	System.out.print("    ");
	System.out.printf("%11f",	Double.parseDouble(mf.getMf3_sum_quant()+"") / Double.parseDouble(mf.getMf3_count_quant()+""));
	System.out.print("    ");
	System.out.println("");
	}
}
}
