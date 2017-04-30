package week3;
import java.io.*;
//inspired from http://www.geeksforgeeks.org/greedy-algorithms-set-5-prims-minimum-spanning-tree-mst-2/
class TravelTrouble
{
	private int V;
	private StringBuilder rt;
	public TravelTrouble(int v){
		this.V=v;
	}
	int minKey(int key[], Boolean mstSet[],int graph[][],int u,int[] parent)
	{
		
		int min = Integer.MAX_VALUE, min_index=0;
		int inList = 0;
		for (int v = 0; v < V; v++){
			if (mstSet[v] == false && key[v] < min){
				if(parent[v]!=-1 && graph[parent[v]][V-1]==key[v]+graph[v][V-1]){
					if(graph[v][V-1]==0){
						min_index = -1;
						inList =v+1;
						break;
					}
					min = key[v];
					min_index = v;
					inList =v+1;
				}
			}
		}
		if(inList!=0){
			rt.append(" "+inList);
		}
		return min_index;
	}
	StringBuilder primMST(int graph[][])
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
		int u = 0;
		rt = new StringBuilder();
		rt.append("1");
		for (int count = 0; count < V; count++)
		{
			u = minKey(key, mstSet,graph,u,parent);
			if(u==-1){
				break;
			}
			mstSet[u] = true;
			for (int v = 0; v < V; v++)
				if (graph[u][v]!=0 && mstSet[v] == false &&
					graph[u][v] < key[v])
				{
					parent[v] = u;
					key[v] = graph[u][v];
				}
		}
    	return rt;
	}

	public static void main (String[] args) throws NumberFormatException, IOException, Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t= Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++){
			int in1= Integer.parseInt(br.readLine());
			int graph[][] = new int[in1][in1];
			for(int j=0;j<in1;j++){
				String in2  = br.readLine();
				String[] temp2  = in2.split(" ");
				for(int k=j+1;k<in1;k++){
					graph[j][k] = Integer.parseInt(temp2[k]);
					graph[k][j] = Integer.parseInt(temp2[k]);
				}
			}
			TravelTrouble tr = new TravelTrouble(in1);
			System.out.println("Case #"+i+": "+tr.primMST(graph));
			br.readLine();
		}
	}
}
