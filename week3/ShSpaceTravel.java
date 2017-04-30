package week3;
import java.io.*;
class ShSpaceTravel
{
	private int V;
	public ShSpaceTravel(int v){
		this.V=v;
	}
	int minKey(int key[], Boolean mstSet[])
	{
		int min = Integer.MAX_VALUE, min_index=-1;
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
    	for (int i = 1; i < V; i++)
    		res = res + graph[i][parent[i]];	
    	return res;
	}

	public static void main (String[] args) throws NumberFormatException, IOException, Exception
	{
		// inspired from http://www.geeksforgeeks.org/greedy-algorithms-set-5-prims-minimum-spanning-tree-mst-2/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t= Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++){
			int in1= Integer.parseInt(br.readLine());
			int graph[][] = new int[in1][in1];
			String[] in2 = new String[in1];
			for(int j=0;j<in1;j++){
				in2[j]  = br.readLine();
			}
			for(int j=0;j<in1;j++){
				String[] temp1  = in2[j].split(" ");
				for(int k=1;k<in1;k++){
					String[] temp2  = in2[k].split(" ");
					int weight=0;
					weight = Math.abs((Integer.parseInt(temp1[0])-Integer.parseInt(temp2[0]))) + Math.abs((Integer.parseInt(temp1[1])-Integer.parseInt(temp2[1]))) + Math.abs((Integer.parseInt(temp1[2])-Integer.parseInt(temp2[2])));
					graph[j][k] = weight;
					graph[k][j] = weight;
				}
			}
			ShSpaceTravel tr = new ShSpaceTravel(in1);
			System.out.println("Case #"+i+": "+tr.primMST(graph));
			br.readLine();
		}
	}
}
