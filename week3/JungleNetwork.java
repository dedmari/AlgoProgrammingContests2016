package week3;
import java.io.BufferedReader;
//inspired from http://www.geeksforgeeks.org/greedy-algorithms-set-5-prims-minimum-spanning-tree-mst-2/
import java.io.IOException;
import java.io.InputStreamReader;
public class JungleNetwork {
	private int V;
	public JungleNetwork(int v){
		this.V=v;
	}
	int minKey(int key[], Boolean mstSet[])
	{
		int min = Integer.MAX_VALUE, min_index=0;
		for (int v = 0; v < V; v++)
			if (mstSet[v] == false && key[v] < min)
			{
				min = key[v];
				min_index = v;
			}
		return min_index;
	}
	int primMST(int graph[][])
	{
		int parent[] = new int[V];
		int key[] = new int [V];
		Boolean mstSet[] = new Boolean[V];
		for (int i = 0; i < V; i++)
		{
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}
		key[0] = 0;
		parent[0] = -1;
		for (int count = 0; count < V-1; count++)
		{
			int u = minKey(key, mstSet);
			mstSet[u] = true;
			for (int v = 0; v < V; v++)
				if (graph[u][v]!=0 && mstSet[v] == false &&
					graph[u][v] < key[v])
				{
					parent[v] = u;
					key[v] = graph[u][v];
				}
		}
		int res = 0;
			for (int i = 1; i < V; i++){
				if(graph[i][parent[i]] == 0){
					res = -1;
					break;
				}
	    		res = res + graph[i][parent[i]];
			}	
    	return res;
	}
	public static void main (String[] args) throws NumberFormatException, IOException, Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t= Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++){
			int in1= Integer.parseInt(br.readLine());
			String[] in2 = new String[in1+1];
			int graph[][] = new int[in1+1][in1+1];
			in2[0]="0 0 "+Integer.MAX_VALUE;
			for(int j=1;j<=in1;j++){
				in2[j]  = br.readLine();
			}
			for(int j=0;j<=in1;j++){
				String[] temp1  = in2[j].split(" ");
				for(int k=j+1;k<=in1;k++){
					String[] temp2  = in2[k].split(" ");
					int x = Integer.parseInt(temp2[0])-Integer.parseInt(temp1[0]);
					int y = Integer.parseInt(temp2[1])-Integer.parseInt(temp1[1]);
					int power = (int)Math.pow(x,2)+(int)Math.pow(y,2);
					if(Integer.parseInt(temp2[2])>=power && Integer.parseInt(temp1[2])>=power){
						graph[j][k] = power;
						graph[k][j] = power;
					}
				}
			}
			JungleNetwork jn =new JungleNetwork(in1+1);
			int res = jn.primMST(graph);
			if(res == -1)
				System.out.println("Case #"+i+": impossible");
			else 
				System.out.println("Case #"+i+": "+(2 * res));
			br.readLine();
		}
	}

}
