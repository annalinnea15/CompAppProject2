package CompAppProject2;

public class SQLMeasurements {
	//analog values read from SQL database
	String rdfID;
	String name;
	double time;
	double value;
	String sub_rdfID;
	String type;
	
	public SQLMeasurements() {
		
	}
	
	public SQLMeasurements(String id, String N, double t, double v, String sub) {
		// add entry to analog values
		rdfID=id;
		name=N;
		time=t;
		value=v;
		sub_rdfID=sub;

	}
	

}
