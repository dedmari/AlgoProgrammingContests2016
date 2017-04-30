import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Relativity {

	public static void main(String[] args) {
		int t;
		int i= 0;
		long input=(8987551787368176400l);
		ArrayList<Long> entries = new ArrayList<Long>();
		long result=0;
		try {
			   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			   t= Integer.parseInt(br.readLine());
			   System.out.println(t);
			   for(int j=1;(j<=t && j<=1000);j++){
				   input  = Long.parseLong(br.readLine());
				   if(input >=1 && input <= 100)
					   entries.add(input);  
			   }
			   for(long entrie : entries){
				   i++;
				   result = entrie*299792458l*299792458l;
				   System.out.println("Case #"+i+": "+result);
			   }
			 } catch (Exception e) {
			    System.err.println("Error: " + e);
			 }

	}

}
