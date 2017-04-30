package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class FineDining {
	public static void main(String args[]) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    	int t=Integer.parseInt(br.readLine());
    	for(int i=1;i<=t;i++){
    		Set<Integer> meal 		= new  HashSet<Integer>();
    		Set<Integer> beverage 	= new  HashSet<Integer>();
    		String inp1[]=br.readLine().split(" ");
    		int n = Integer.parseInt(inp1[0]);
    		int m = Integer.parseInt(inp1[1]);
    		int b = Integer.parseInt(inp1[2]);
    		for(int j=0;j<n;j++){
    			String inp2[]=br.readLine().split(" ");
    			meal.add( Integer.parseInt(inp2[0]));
    			beverage.add( Integer.parseInt(inp2[1]));
    		}
    		System.out.println("Case #"+i+": "+(meal.size()>beverage.size()?beverage.size():meal.size()));
    		br.readLine();
    	}
	}
}
