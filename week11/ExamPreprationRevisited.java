package week11;
//code inspired from http://introcs.cs.princeton.edu/java/23recursion/Knapsack.java.html
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExamPreprationRevisited {

	public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
        ItemsDynamic[] items= new ItemsDynamic[10010];
		for (int i = 1; i <= t; i++) {
			String[] inp1 = br.readLine().split(" ");
			int W = Integer.parseInt(inp1[0]);
			int entries = Integer.parseInt(inp1[1]);
			int prevIndex=0;
			for (int j = 1; j <= entries; j++) {
				String[] inp2 = br.readLine().split(" ");
				int maxNum = Integer.parseInt(inp2[0]);
				int wt = Integer.parseInt(inp2[1]);
				int pf = Integer.parseInt(inp2[2]);
				for(int k =1;k<=maxNum;k++){
					items[prevIndex+k] = new ItemsDynamic(j, pf,wt);
				}
				prevIndex=prevIndex+maxNum;
			}
			int N = prevIndex;
			int[][] opt = new int[N+1][W+1];
	        boolean[][] sol = new boolean[N+1][W+1];
	        for (int n = 1; n <= N; n++) {
	            for (int w = 1; w <= W; w++) {
	                int option1 = opt[n-1][w];
	                int option2 = Integer.MIN_VALUE;
	                if (items[n].weight <= w) option2 = items[n].profit + opt[n-1][w-items[n].weight];
	                opt[n][w] = Math.max(option1, option2);
	                sol[n][w] = (option2 > option1);
	            }
	        }
	        boolean[] take = new boolean[N+1];
	        for (int n = N, w = W; n > 0; n--) {
	            if (sol[n][w]) {
	                take[n] = true;
	                w = w - items[n].weight;
	            }
	            else {
	                take[n] = false;
	            }
	        }
	        StringBuilder sb = new StringBuilder("Case #");
			sb.append(i);
			sb.append(": ");	
	        for (int n = 1; n <= N; n++) {
	        	if(take[n]){
	        		sb.append(items[n].id);
	        		sb.append(" ");
	        	}
	        }
    		System.out.println(sb.toString());
    		if(i!=t)
    			br.readLine();
		}
        
    }
}
class ItemsDynamic{
	int id;
	int weight;
	int profit;
	ItemsDynamic(int id, int pf,int wt) {
		super();
		this.id =id;
		this.weight = wt;
		this.profit = pf;
	}	
}