package CompAppProject2;

import java.util.ArrayList;

public class MainCalcs {


		public static void main(String[] args) {
			Read_Data dat = new Read_Data();
			ArrayList<SQLMeasurements> measurementList = dat.Read_Learning_Set();
			ArrayList<SQLMeasurements> analogList = dat.Read_Test_Set();
			ArrayList<Substations> substationList = dat.Read_Substations();
			double[] mm=dat.findMaxMin(measurementList);
			
			
			//combine the voltage and angle measurements
			ArrayList<FinalSets> learningSet = dat.Combine_Voltage_Angle(measurementList);
			ArrayList<FinalSets> testSet = dat.Combine_Voltage_Angle(analogList);
			
			
			//combine into clusters and add label
			KmeansClustering kclust = new KmeansClustering(4,19,learningSet);
			
			ArrayList<FinalSets> learningListnew = kclust.kMeans(mm);
			ArrayList<FinalSets> testListnew = new ArrayList<FinalSets>();
			for (int i = 0; i < 9; i++) {
				 testListnew.add(learningListnew.get(i));
			}
//			System.out.println("+++++++++++++++++++++++++++++++");
//			for (int i = 0; i <testSet.size(); i++) 
//				System.out.println(i+","+testSet.get(i).AmheAng+","+testSet.get(i).AmheVolt+","+testSet.get(i).BowmAng+","+testSet.get(i).BowmVolt+","+testSet.get(i).ClarAng+","+testSet.get(i).ClarVolt+","+testSet.get(i).CrossAng+","+testSet.get(i).CrossVolt+","+testSet.get(i).GranAng+","+testSet.get(i).GranVolt+","+testSet.get(i).MaplAng+","+testSet.get(i).MaplVolt+","+testSet.get(i).TroyAng+","+testSet.get(i).TroyVolt+","+testSet.get(i).WautAng+","+testSet.get(i).WautVolt+","+testSet.get(i).WinlAng+","+testSet.get(i).WinlVolt+","+testSet.get(i).time);
//			System.out.println("+++++++++++++++++++++++++++++++");
//			for (int i = 0; i < learningListnew.size(); i++) 
//				System.out.println(i+".."+learningListnew.get(i).type);
//			System.out.println("+++++++++++++++++++++++++++++++");
			
			KnnAlgo kclas = new KnnAlgo(4,19,learningSet,testSet);
			
			
		}

	}

