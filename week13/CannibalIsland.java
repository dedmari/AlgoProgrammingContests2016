package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CannibalIsland {
	public static void main(String args[]) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    	int t=Integer.parseInt(br.readLine());
    	for(int i=1;i<=t;i++){
    		String inp1[]=br.readLine().split(" ");
    		int n = Integer.parseInt(inp1[0]);
    		int k = Integer.parseInt(inp1[1]);
    		int currentPos = (k-1)%(n);
    		List<Integer> entries = new ArrayList<Integer>();
    		for(int j=1;j<=n;j++){
    			entries.add(j);
    		}
    		int count = 0;
    		while(entries.size() > 1){
    			entries.remove(currentPos);
    			count++;
    			currentPos = (currentPos + (k-1)) % (n-count);
    		}
    		System.out.println("Case #"+i+": "+entries.get(0));
    	}
	}
}
