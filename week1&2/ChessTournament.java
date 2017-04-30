import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ChessTournament {

	public static void main(String[] args) {
		int t;
		int n;
		int i= 0;
		int teams;
		int l=0;
		String input=null;
		StringBuilder cases = new StringBuilder();
		ArrayList<String> entries = new ArrayList<String>();
		String per[] = null;
		String ent =null;
		try {
			   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			   t= Integer.parseInt(br.readLine());
			   for(int j=1;(j<=t && j<=20);j++){
				   input  = br.readLine();
				   if(!(input.equals(""))){
					   n  = Integer.parseInt(input);
					   if(n >= 2 && n<=1000){
						  cases.append(n+"$");
						  for(int k=1;k<=n;k++){
							  input = br.readLine();//getting row
							  cases.append(input+" ");
						  } 
			   		   		entries.add(cases.toString());
			   		   		cases.delete(0, (cases.length()+1));
					   }
					   else {
						   for(int k=1;k<=n;k++){
								  br.readLine();// not valid number of schools
							} 
					   }
				   }
				   else {
					   j--;
				   }
				}
				   
			   for(String entrie : entries){
				   i++;
				   l = 0;
				   teams = Integer.parseInt(entrie.substring(0, entrie.indexOf('$')));
				   ent = entrie.substring((entrie.indexOf('$')+1));
				   per = ent.split("\\s+");
				   Integer[][] team = new Integer[teams][5];
				   System.out.println("Case #"+i+":");
				   for(int j =0;j<teams;j++){
					   for(int k = 0; k<5;k++){
						   team[j][k]= Integer.parseInt(per[l]);
						   l++;
					   }
				   }
				   for (Integer[] innerArray : team) {
					    Arrays.sort(innerArray, Collections.reverseOrder());
					}
				   Arrays.sort(team, new java.util.Comparator<Integer[]>() {
					    public int compare(Integer[] x, Integer[] y) {
					    	for(int index = 0;index<5; index ++){
					    		if (y[index]<x[index]){
					    			return -1;
					    		}else if(x[index]<y[index]){
					    			return 1;
					    		}			    		
					    	}
			    			return 0;
					    }
					});
				   for(int j =0;j<teams;j++){
					   for(int k = 0; k<5;k++){
						   System.out.print(team[j][k]+" ");
					   }
					   System.out.print("\n");
				   }
	   }
			 } catch (Exception e) {
			    System.err.println("Error: " + e);
			 }

	}
}


