package CompAppProject2;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;



public class KmeansClustering{
	private static final ArrayList<Clusters> NewClusters = null;
	int k;
	int dim;//number of dimensions per point
	ArrayList<FinalSets> learningList; //what will be broken into clusters
	
	public KmeansClustering(int kk, int dims, ArrayList<FinalSets> LList){
		k=kk;
		dim=dims;
		learningList=LList;
	}
	
	
	//Declare variable to store the centroids, clusters and flower type in each cluster

	public ArrayList<FinalSets> kMeans(double[] mm) {
		//k is the number of centroids
		//learningSet is the set input that will be broken into clusters
		
		//boolean variable that says when to stop
		boolean finish=false;
		
		//create k clusters
		//clusters will hold the indices of the points
		ArrayList<Clusters> clusters = createEmptyClusters();
		
		//randomly divide the point up into k partitions
		clusters = RandomPartition(clusters);
		//calculate the mean value of each random partition
		double[][] centroids= CalculateCentroids(clusters);
		
		ArrayList<Clusters> NewClusters = new ArrayList<Clusters>();

		int counter=0;
		
		//calculate new clusters until the means don't change
		while(!finish){
			// calculate distance of each value from the centroids
            NewClusters.clear();
			NewClusters = AssignCluster(centroids);
			double[][] NewCentroids= CalculateCentroids(NewClusters);
		
			//calculate how much the centroids moved
			double centDist= CentroidDistances(centroids, NewCentroids);
		
			//if the centroids haven't moved, the clusters are final
			//System.out.println(centDist);
			if(centDist==0){
				finish=true; //you're done!
			}
			
			if(counter==1000){//don't let it go too long
				finish=true;
				System.out.println("******************Too many iterations***********************");
			}
			counter++;
		
			centroids = NewCentroids;
		}
		
		ArrayList<FinalSets> learningListnew= decideLabels(centroids, mm, NewClusters );
		return learningListnew;
	}
	
	
	
	public ArrayList<Clusters> createEmptyClusters(){
		//create k empty clusters
		ArrayList<Clusters> clusters = new ArrayList<Clusters>();
		for(int i=0; i<k; i++){
			clusters.add(new Clusters(new ArrayList<Integer>() ));
		}
		return clusters;
	}
	
	public ArrayList<Clusters>  RandomPartition(ArrayList<Clusters> clusters ){
//		Random Partition method randomly assigns each data point
//		to one of k partitions, then computes the initial location of the
//		k centers as the mean of the assigned datapoints
		
		//random numbers
		Random rn = new Random();
		
		//sort each point into random clusters (0 to k-1)
		for(int i=0; i<learningList.size(); i++){
			int c = rn.nextInt(k); //random integer 0,1,2,3
			
			clusters.get(c).idx.add(i);//add the index i to cluster c
		}
		return clusters;
	}
	
	//calculate the centroids of a the clusters
	public double[][] CalculateCentroids(ArrayList<Clusters> clusters){
		
		//calculate centroid of each cluster
		double[][] meanPt=new double[k][dim];//calculate sum of all points
		
		//add values of all points together
		for(int c=0; c<k; c++){//go through each cluster
			for(int i=0; i<clusters.get(c).idx.size(); i++){//go through all points in each cluster
				for(int t=0; t<dim; t++){//go through all dimensions
					meanPt[c][t]=meanPt[c][t]+learningList.get(clusters.get(c).idx.get(i)).item(t);//add the points together
				}
			}
		}
		//divide by number of points
		for(int c=0; c<k; c++){//go through each cluster
			for(int t=0; t<dim; t++){
				meanPt[c][t]=meanPt[c][t]/clusters.get(c).idx.size();//calculate the mean
			}
		}
		return meanPt;
	}
	
	public ArrayList<Clusters> AssignCluster(double[][] centroids){
		//go through each point in learning set and calculate the Euclidean distance to each mean, assign it to a cluster
		ArrayList<Clusters> NewClusters = createEmptyClusters();
		double[] dist=new double[k];//a distance for each centroid
		for(int i=0; i<learningList.size(); i++){//go through each data point
			for(int c=0; c<k; c++){//go through each centroid
				dist[c]=Euclidean(learningList.get(i), centroids, c);//calculate distance from centroid
			}
			int closestIdx = calcMin(dist);//choose closets centroid
			NewClusters.get(closestIdx).idx.add(i);//add the index i to cluster c

		}
		return NewClusters;
		
	}
	
	public double Euclidean(FinalSets point, double[][] centroids, int c ){
		//calculate Euclidean distance between two points
		double dist=0;
		for(int i=0; i<dim; i++){
			dist=dist+(point.item(i)-centroids[c][i])*(point.item(i)-centroids[c][i]);
		}
		dist=Math.sqrt(dist);
		return dist;
	}
	
	public int calcMin(double[] dist){
		//calculate in distance in order to assign clusters
		double min=dist[0];
		for(int i=1; i<dist.length; i++){
			min=Math.min(min, dist[i]);
		}
		int minId = 0;
		for(int i=0; i<dist.length; i++){
			if(min==dist[i]) minId=i;
		}
		return minId;
	}
	
	public double CentroidDistances(double[][] OldCentroids, double[][]NewCentroids){
		//check to see if the centroids have moved
		double totDist=0;
		double sqrDist=0;

		//calculate distance between new and old centroids
		for(int c=0; c<k; c++){//each centroid
			for(int t=0; t<dim; t++){//each dimension
				//square distance btw each dimension
				sqrDist = sqrDist + (OldCentroids[c][t]-NewCentroids[c][t])*(OldCentroids[c][t]-NewCentroids[c][t]);
			}
			//add the distances btw points together
			totDist=totDist + Math.sqrt(sqrDist);
			sqrDist=0;
		}
		return totDist;
				
	}
	
	public ArrayList<FinalSets>  decideLabels(double[][] centroids, double[] mm, ArrayList<Clusters> clusters){
		//centroids are the final centroids of the set
		//mm are the max and min voltage and angle values, used to de-normalize the points
		int[] vPoints={1,3,5,7,9,11,13,15,17};//voltages
		int[] aPoints={2,4,6,8,10,12,14,16,18};//angles
		double[] loadPowers={Math.sqrt(GUIassign2.Getactive5()*GUIassign2.Getactive5()+GUIassign2.GetReactive5()*GUIassign2.GetReactive5()), Math.sqrt(GUIassign2.Getactive7()*GUIassign2.Getactive7()+GUIassign2.GetReactive7()*GUIassign2.GetReactive7()), Math.sqrt(GUIassign2.Getactive9()*GUIassign2.Getactive9()+GUIassign2.GetReactive9()*GUIassign2.GetReactive9())};
		String[] labels = {"High load, peak hours", "Generator Disconnected", "Low load, off peak hours", "Line Disconnected"};
		
		//initialize variables
		int[] cLab=new int[k];
		double[] genAng = new double[k];
		double[] lineAng = new double[k];
		double[] loadCurrent = new double[k];
		System.out.println("\n\n");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("====================================================");
		System.out.println("                        K Means : Formation of Clusters");
		System.out.println("====================================================");
		//denormalize the  values
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\n");
		System.out.println("The clusters obtained through K means are as follows");
		System.out.println("\n");
		for(int c=0;c<k;c++){
			//denormalize
			System.out.println("Cluster "+c);
			System.out.println("\n");
			System.out.println("Voltages for Cluster: "+c);
			for(int v:vPoints){
				centroids[c][v]=centroids[c][v]*(mm[0]-mm[1])+mm[1];
				System.out.println(centroids[c][v]);
			}
			System.out.println("\n");
			System.out.println("Angles for Cluster: "+c);
			for(int a:aPoints){
				centroids[c][a]=centroids[c][a]*(mm[2]-mm[3])+mm[3];
				System.out.println(centroids[c][a]);
			}
			System.out.println("\n");
		System.out.println("====================================================");
		System.out.println("\n");
			
			//calculate important values for clusters
		    genAng[c] = Math.min(centroids[c][aPoints[1]], centroids[c][aPoints[2]]);//angle at gen buses
			lineAng[c] = Math.max(centroids[c][aPoints[3]], Math.max(centroids[c][aPoints[4]], //angle at other buses
					Math.max(centroids[c][aPoints[5]], Math.max(centroids[c][aPoints[6]], Math.max(centroids[c][aPoints[7]], centroids[c][8])))));
			//current at load buses
			loadCurrent[c] = loadPowers[0]/centroids[c][vPoints[4]]+loadPowers[1]/centroids[c][vPoints[6]]+loadPowers[2]/centroids[c][vPoints[8]];
		}
		
		//angles for when a gen or line is disconnected
		double minGen=Math.min(genAng[0], Math.min(genAng[1], Math.min(genAng[2], genAng[3])));
		double maxLine = Math.max(lineAng[0], Math.max(lineAng[1], Math.max(lineAng[2], lineAng[3])));

		//keep track of which labels have been used
		ArrayList<Integer> availableLabs=new ArrayList<Integer>();
		availableLabs.add(1); availableLabs.add(2); availableLabs.add(3); availableLabs.add(4);
		ArrayList<Integer> availClust=new ArrayList<Integer>();
		availClust.add(0); availClust.add(1); availClust.add(2); availClust.add(3); 
		
		
		//first label the gen and line disconnect
		for(int c=0; c<k; c++){
			if(genAng[c]==minGen){
				cLab[c]=2;
				availableLabs.remove(availableLabs.indexOf(2));
				availClust.remove(availClust.indexOf(c));
			}
			else if(lineAng[c]==maxLine){
				cLab[c]=4;
				availableLabs.remove(availableLabs.indexOf(4));
				availClust.remove(availClust.indexOf(c));
			}
		}
		
		//remaining are low and high load
		if(availableLabs.size()==2 && availableLabs.get(0)==1 && availableLabs.get(1)==3){
			if(loadCurrent[availClust.get(0)]<loadCurrent[availClust.get(1)]){
				cLab[availClust.get(0)]=3;
				cLab[availClust.get(1)]=1;
			}
			else{
				cLab[availClust.get(0)]=1;
				cLab[availClust.get(1)]=3;
					
			}
		}
		
		//add labels to data points
		for(int c=0;c<k;c++){
			int len=clusters.get(c).size();
			for(int l=0; l<len;l++){
				learningList.get(clusters.get(c).idx.get(l)).type=labels[cLab[c]-1];
			}
		}
		
		
		return learningList;
		
		
	}
	


}

