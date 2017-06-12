package CompAppProject2;

public class FinalSets {
	//analog values read from SQL database

	double time;
	double ClarVolt;
	double ClarAng;
	double AmheVolt;
	double AmheAng;
	double WinlVolt;
	double WinlAng;
	double BowmVolt;
	double BowmAng;
	double TroyVolt;
	double TroyAng;
	double MaplVolt;
	double MaplAng;
	double GranVolt;
	double GranAng;
	double WautVolt;
	double WautAng;
	double CrossVolt;
	double CrossAng;
	String type;
	
	public FinalSets(){
		
	}
	
	public FinalSets(double t, double Cv, double Ca, double Av, double Aa, double Wv, double Wa, 
			double Bv, double Ba, double Tv, double Ta, double Mv, double Ma, double Gv, double Ga, double Wav, double Waa, double Crv, double Cra){
		time=t;
		ClarVolt=Cv;
		ClarAng=Ca;
		AmheVolt=Av;
		AmheAng=Aa;
		WinlVolt=Wv;
		WinlAng=Wa;
		BowmVolt=Bv;
		BowmAng=Ba;
		TroyVolt=Tv;
		TroyAng=Ta;
		MaplVolt=Mv;
		MaplAng=Ma;
		GranVolt=Gv;
		GranAng=Ga;
		WautVolt=Wav;
		WautAng=Waa;
		CrossVolt=Crv;
		CrossAng=Cra;
	}
	

	public double item(int id){
		if(id==0) return time;
		else if(id==1) return ClarVolt;
		else if(id==2) return ClarAng;
		else if(id==3) return AmheVolt;
		else if(id==4) return AmheAng;
		else if(id==5) return WinlVolt;
		else if(id==6) return WinlAng;
		else if(id==7) return BowmVolt;
		else if(id==8) return BowmAng;
		else if(id==9) return TroyVolt;
		else if(id==10) return TroyAng;
		else if(id==11) return MaplVolt;
		else if(id==12) return MaplAng;
		else if(id==13) return GranVolt;
		else if(id==14) return GranAng;
		else if(id==15) return WautVolt;
		else if(id==16) return WautAng;
		else if(id==17) return CrossVolt;
		else if(id==18) return CrossAng;
		else return (Double) null;
		
	}

}
