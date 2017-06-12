package CompAppProject2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KnnAlgo {
	int dimsKnn=0;
	int testRef=0;
	int learnRef=0;
	String state[]= {"Generator Disconnected", "Line Disconnected","Low load, off peak hours","High load, peak hours"};
	int learnSize=0;

	public KnnAlgo(int p, int dimsKnn1, ArrayList<FinalSets> learningSetKnn,ArrayList<FinalSets> testSetKnn){
		int knnVal= (int) Math.sqrt(learningSetKnn.size()); //The value of K for Knn is set as the root of 
															// total number of learning set values
		ArrayList<DistComp> CompTable = new ArrayList<DistComp>(); //Final sorted table with assigned labels
		ArrayList<DistComp> CompTableDummy = new ArrayList<DistComp>(); //Copy of the learning set in DistComp format
		ArrayList<String> LabelList = new ArrayList<String>(); //contains only labels of learning set
		dimsKnn = dimsKnn1; //dimensions to be considered for computing distances
		learnSize = learningSetKnn.size(); 
		
		for(int i=0; i<learningSetKnn.size(); i++)
			CompTableDummy.add(i, new DistComp(0,i,0.0, learningSetKnn.get(i).type)); //making a copy of learning set
		
		//formation of final table with test set distances from every learning set value
		for(int j=0; j<testSetKnn.size();j++){ //loop element for test set values
			testRef=j;
			for(int i=0; i<learningSetKnn.size(); i++){ //loop element for learning set values
				CompTable.add(new DistComp(j,i,EuclideanDistKnn(testSetKnn.get(j), learningSetKnn.get(i)), "")); 
				learnRef=i;
			}
			CompTable = SortBatch(testSetKnn.size(),learningSetKnn.size(), CompTable); //sorts the distances for every test set
																					// element into ascending order
		}
		
		for(int i=0; i<learningSetKnn.size(); i++)
			LabelList.add(learningSetKnn.get(i).type); //copy of labels of learning set values
			
		
		for (int i = 0; i < CompTable.size(); i++) {
			CompTable.get(i).type=LabelList.get(CompTable.get(i).li); //assigning the final test set table with labels
																	// as per the distances computed
		}
				
		System.out.println("\n");
		System.out.println("Using Kmeans the Learning Set is Labelled as follows:");
		System.out.println("\n");
		
		for (int i = 0; i < learningSetKnn.size(); i++) 
			System.out.println("Learning Set  "+i+" ..  "+learningSetKnn.get(i).type); //displays learning set with labels

		for (int i = 0; i <testSetKnn.size(); i++)
			testSetKnn.get(i).type = freqTest(i,knnVal,CompTable); //checks the majority occurence of the label in final test
																	// set table using K inital values
		
		System.out.println("\n");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("====================================================");
		System.out.println("                        K nn : Test Set Classification");
		System.out.println("====================================================");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\n");
		System.out.println("Using Knn the given Test Set is classified as follows:");
		System.out.println("\n");
		
		for (int i = 0; i < testSetKnn.size(); i++) {
			System.out.println("Test Set  "+i+" ..  "+testSetKnn.get(i).type); //displays test set values with labels
		}
				
		System.out.println("===============================================");
	
	
	}
	
	//function to check majority label occurences
	private String freqTest(int i, int knnVal, ArrayList<DistComp> compTable) { 
		List TempTable1 = new ArrayList();
		for (int j =i*learnSize ; j < (i*learnSize)+knnVal ; j++)
			TempTable1.add(compTable.get(j).type);
		int f0 = Collections.frequency(TempTable1, state[0]);
		int f1 = Collections.frequency(TempTable1, state[1]);
		int f2 = Collections.frequency(TempTable1, state[2]);
		int f3 = Collections.frequency(TempTable1, state[3]);
		String maxstr= state[0];
		int max =f0;
	    if (f1 > max){
	        max = f1;
	        maxstr = state[1];
	        }
	    if (f2 > max){
	        max = f2;
	        maxstr = state[2];
	        }
	    if (f3 > max){
	        max = f3;
	        maxstr = state[3];
	        }
	     return maxstr;
	}

	//function to sort the test set values as per the distances
	private ArrayList<DistComp> SortBatch(int TestSize, int LearningSize, ArrayList<DistComp> compTable) {
		
		int count=0;
			learnRef++;
		ArrayList<DistComp> TempTable = new ArrayList<DistComp>();
		for (int i =(testRef*learnRef) ; i < (testRef*learnRef)+learnRef ; i++) {
			TempTable.add(compTable.get(i));
		}
		Collections.sort(TempTable, new DistanceComparator());
		for (int i = (testRef*learnRef); i < (testRef*learnRef)+learnRef; i++) {
			compTable.set(i, TempTable.get(count));
			count++;
		}
		
		return(compTable);
	}
	
	
	public double EuclideanDistKnn(FinalSets tSet, FinalSets lSet){
		//calculates Euclidean distance between two points
		double distKnn=0;
		for(int i=0; i<dimsKnn; i++){
			distKnn=distKnn+((tSet.item(i)-lSet.item(i))*(tSet.item(i)-lSet.item(i)));
		}
		distKnn=Math.sqrt(distKnn);
		return distKnn;
	}
	

}
