package CompAppProject2;

import java.util.ArrayList;

public class Clusters {
	ArrayList<Integer> idx; //indices in set
	
	public Clusters(){
	}
	
	public Clusters(ArrayList<Integer> id){
		idx=id;
	}
	
	public void clear(){
		idx.clear();
	}
	
	public int size(){
		int s=idx.size();
		return s;
	}

}
