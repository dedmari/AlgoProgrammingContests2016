
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CableCar {

	private static boolean polePlacing(double canBegin, double canFinish, double mid, int poles, double distance) {
			
			double ind = 0;
			int alonePoles = 0;
			
			while(ind <= distance){
	
			if ( (ind+mid) > canBegin && (ind+mid) < canFinish) {
	
				ind = canFinish;
				alonePoles++;
				}else{
					
					alonePoles++;
					ind = ind+mid;
				}
			}
			
			return alonePoles>=poles;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		  InputStreamReader inputReader = new InputStreamReader(System.in);
	        BufferedReader br = new BufferedReader(inputReader); 
	        int cases = Integer.parseInt(br.readLine());  
	        for(int i=1; i<=cases; i++){	
	            String input = br.readLine();
	            String[] entries = input.split(" "); // get only 2 inputs
	            double distance = Integer.parseInt(entries[0]);
	            int poles = Integer.parseInt(entries[1]);
	            double canBegin = Integer.parseInt(entries[2]);	
	            double canFinish = Integer.parseInt(entries[3]); 
	            double high = distance;
	            double low = 0; 
	            while(high-low >  0.0000001){
	            	double mid  = (high + low)/2;
	            	if(polePlacing(canBegin,canFinish, mid,poles,distance)){
	            		low = mid;	
	            	}else{
	            		high = mid;	
	            	}
	        }
			System.out.println("Case #"+ i +": "+ low);	
}
	}

}