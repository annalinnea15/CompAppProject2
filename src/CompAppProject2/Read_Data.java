package CompAppProject2;


import java.sql.*;
import java.util.Collections;
import java.util.ArrayList;

public class Read_Data {
	//public static ParsingTotal PTOT = new main();
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String DISABLE_SSL = "?useSSL=false";
	// Database credentials
	static final String USER = GUIassign2.GetUsername();
	static final String PASS = GUIassign2.GetPassword(); // insert the password to SQL server
	public static Connection conn = null;
	public static Statement stmt = null;
	public static String sql;
	static final String DB_name = "subtables";//name of database, eventually read this from the GUI
	static final String Table_analog = "analog_values";//name of table1, eventually read this from the GUI
	static final String Table_measurements = "measurements";//name of table2, eventually read this from the GUI
	static final String Table_substations = "substations";//name of table2, eventually read this from the GUI



	
	//read in the learning set
		public ArrayList<SQLMeasurements> Read_Learning_Set(){
			try{
				//connect to SQL database
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL + DB_name, USER, PASS);
				Statement stmt = conn.createStatement();
				
				//read in the measurements table
				sql = "Select * from "+Table_measurements; // drop the analog_values
				ResultSet measurements_results = stmt.executeQuery(sql) ; // execute query
				ArrayList<SQLMeasurements> measurements_Array = new ArrayList<SQLMeasurements>();

				//create array list of measurements
				while (measurements_results.next()) {
					measurements_Array.add(new SQLMeasurements(measurements_results.getString(1), measurements_results.getString(2), 
							Double.parseDouble(measurements_results.getString(3)),Double.parseDouble(measurements_results.getString(4)),
							measurements_results.getString(5)));//add to array
				}

				return measurements_Array;

			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();}
			System.out.println("Goodbye!");
			return null;
		}
	
		
		
//read in the test set from the SQL server
	public ArrayList<SQLMeasurements> Read_Test_Set(){
		try{
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL + DB_name, USER, PASS);
			Statement stmt = conn.createStatement();
			
			//read in the table
			sql = "Select * from "+Table_analog; // drop the analog_values
			ResultSet analog_results = stmt.executeQuery(sql) ; // execute query
			ArrayList<SQLMeasurements> analog_val_Array = new ArrayList<SQLMeasurements>();

			//create ArrayList
			while (analog_results.next()) {
				analog_val_Array.add(new SQLMeasurements(analog_results.getString(1), analog_results.getString(2), 
						Double.parseDouble(analog_results.getString(3)),Double.parseDouble(analog_results.getString(4)),analog_results.getString(5)));//add to array
			}

			return analog_val_Array;

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();}
		System.out.println("Goodbye!");
		return null;
	}



	public ArrayList<Substations> Read_Substations(){
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL + DB_name, USER, PASS);
			Statement stmt = conn.createStatement();
			
			//read in table
			sql = "Select * from "+Table_substations; // drop the analog_values
			ResultSet substations_results = stmt.executeQuery(sql) ; // execute query
			ArrayList<Substations> substations_Array = new ArrayList<Substations>();

			//create ArrayList
			while (substations_results.next()) {
				substations_Array.add(new Substations(substations_results.getString(1), substations_results.getString(2), 
						substations_results.getString(3)));//add to array
			}

			return substations_Array;

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();

		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();}
		System.out.println("Goodbye!");
		return null;
	}
	
	
	public ArrayList<FinalSets> Combine_Voltage_Angle(ArrayList<SQLMeasurements> SQLMeasurements){
		ArrayList<FinalSets> finalSets = new ArrayList<FinalSets>();
		
		double ClarVolt=1000;
		double ClarAng=1000;
		double AmheVolt=1000;
		double AmheAng=1000;
		double WinlVolt=1000;
		double WinlAng=1000;
		double BowmVolt=1000;
		double BowmAng=1000;
		double TroyVolt=1000;
		double TroyAng=1000;
		double MaplVolt=1000;
		double MaplAng=1000;
		double GranVolt=1000;
		double GranAng=1000;
		double WautVolt=1000;
		double WautAng=1000;
		double CrossVolt=1000;
		double CrossAng=1000;
		
		
		ArrayList<Double> times = new ArrayList<Double>();//unique times
		times.add(SQLMeasurements.get(0).time);
		for(int i=1; i<SQLMeasurements.size(); i++){
			//read all the times
			if(!times.contains(SQLMeasurements.get(i).time)){
				//if it is a new time, save it
				times.add(SQLMeasurements.get(i).time);//unique time
			}
		}
		double[] mm=findMaxMin(SQLMeasurements);
			
		String[] name1;
		for(double t:times){ //make new point for each time period
		for(int j=0; j<SQLMeasurements.size(); j++){
			if(SQLMeasurements.get(j).time==t){//same time value
				name1 = SQLMeasurements.get(j).name.split("_");
				
						if(name1[0].equals("CLAR")){
							if(name1[1].equals("VOLT")){
								ClarVolt=(SQLMeasurements.get(j).value-mm[1])/(mm[0]-mm[1]);
							}
							else if(name1[1].equals("ANG")){
								ClarAng=(SQLMeasurements.get(j).value-mm[3])/(mm[2]-mm[3]);
							}
							else System.out.println("something is wrong");
						}
						else if(name1[0].equals("AMHE")){
							if(name1[1].equals("VOLT")){
								AmheVolt=(SQLMeasurements.get(j).value-mm[1])/(mm[0]-mm[1]);
							}
							else if(name1[1].equals("ANG")) {
								AmheAng=(SQLMeasurements.get(j).value-mm[3])/(mm[2]-mm[3]);
							}
							else System.out.println("something is wrong");
						}
						else if(name1[0].equals("WINL")){
							if(name1[1].equals("VOLT")){ {
								WinlVolt=(SQLMeasurements.get(j).value-mm[1])/(mm[0]-mm[1]);
								}
							}
							else if(name1[1].equals("ANG")){ {
								WinlAng=(SQLMeasurements.get(j).value-mm[3])/(mm[2]-mm[3]);
								}
							}
							else System.out.println("something is wrong");
						}
						else if(name1[0].equals("BOWM")){
							if(name1[1].equals("VOLT")){ 
								BowmVolt=(SQLMeasurements.get(j).value-mm[1])/(mm[0]-mm[1]);
								}
								
							else if(name1[1].equals("ANG")){
								BowmAng=(SQLMeasurements.get(j).value-mm[3])/(mm[2]-mm[3]);
								}
							else System.out.println("something is wrong");
						}
						else if(name1[0].equals("TROY")){
							if(name1[1].equals("VOLT")){
								TroyVolt=(SQLMeasurements.get(j).value-mm[1])/(mm[0]-mm[1]);
								}
							else if(name1[1].equals("ANG")){
								TroyAng=(SQLMeasurements.get(j).value-mm[3])/(mm[2]-mm[3]);
								}
							else System.out.println("something is wrong");
						}
						else if(name1[0].equals("MAPL")){
							if(name1[1].equals("VOLT")){
								MaplVolt=(SQLMeasurements.get(j).value-mm[1])/(mm[0]-mm[1]);
								}
							else if(name1[1].equals("ANG")){
								MaplAng=(SQLMeasurements.get(j).value-mm[3])/(mm[2]-mm[3]);
								}
							else System.out.println("something is wrong");
						}
						else if(name1[0].equals("GRAN")){
							if(name1[1].equals("VOLT")){ 
								GranVolt=(SQLMeasurements.get(j).value-mm[1])/(mm[0]-mm[1]);
								}
							else if(name1[1].equals("ANG")){
								GranAng=(SQLMeasurements.get(j).value-mm[3])/(mm[2]-mm[3]);
								}
							else System.out.println("something is wrong");
						}
						else if(name1[0].equals("WAUT")){
							if(name1[1].equals("VOLT")){ 
								WautVolt=(SQLMeasurements.get(j).value-mm[1])/(mm[0]-mm[1]);
								}
							else if(name1[1].equals("ANG")){
								WautAng=(SQLMeasurements.get(j).value-mm[3])/(mm[2]-mm[3]);
								}
							else System.out.println("something is wrong");
						}
						else if(name1[0].equals("CROSS")){
							if(name1[1].equals("VOLT")){ 
								CrossVolt=(SQLMeasurements.get(j).value-mm[1])/(mm[0]-mm[1]);
								}
							else if(name1[1].equals("ANG")){
								CrossAng=(SQLMeasurements.get(j).value-mm[3])/(mm[2]-mm[3]);
								}
							else System.out.println("something is wrong");
						}
				}
			}
		
		
		finalSets.add(new FinalSets(t, ClarVolt, ClarAng, AmheVolt, AmheAng, WinlVolt, WinlAng, 
				BowmVolt, BowmAng, TroyVolt, TroyAng, MaplVolt, MaplAng, GranVolt, GranAng, WautVolt, WautAng, CrossVolt, CrossAng));
		
//		System.out.println("Point "+t+ ", "+ hour + ", "+ClarVolt + ", "+ ClarAng + ", "+  AmheVolt + ", "+  AmheAng + ", "+  WinlVolt + ", "+  WinlAng + ", "+  
//				BowmVolt + ", "+  BowmAng + ", "+  TroyVolt + ", "+  TroyAng + ", "+  MaplVolt + ", "+  MaplAng + ", "+  GranVolt + ", "+ 
//				GranAng + ", "+  WautVolt + ", "+  WautAng + ", "+  CrossVolt + ", "+  CrossAng);
		}
		
		return finalSets;
		
	}

	
	public static double[] findMaxMin(ArrayList<SQLMeasurements> SQLMeasurements){
		//find the max and min voltage and angles of the set
		ArrayList<Double> voltages=new ArrayList<Double>();
		ArrayList<Double> angles=new ArrayList<Double>();
		for(int i=0; i<SQLMeasurements.size(); i++){
			//read all the voltage and angle values
			String[] name = SQLMeasurements.get(i).name.split("_");
			if(name[1].equals("VOLT")) voltages.add(SQLMeasurements.get(i).value);
			else if(name[1].equals("ANG")) angles.add(SQLMeasurements.get(i).value);
			else System.out.println("Something wrong in min/max");
		}
		double[] mm = {Collections.max(voltages), Collections.min(voltages), Collections.max(angles), Collections.min(angles)};
		return mm;
	}
}


