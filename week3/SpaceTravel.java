package week3;

import java.util.*;
import java.io.*;

class SpaceTravel
{
	// Number of vertices in the graph
	private int V;
	public SpaceTravel(int v){
		this.V=v;
	}

	// A utility function to find the vertex with minimum key
	// value, from the set of vertices not yet included in MST
	int minKey(int key[], Boolean mstSet[])
	{
		// Initialize min value
		int min = Integer.MAX_VALUE, min_index=-1;

		for (int v = 0; v < V; v++)
			if (mstSet[v] == false && key[v] < min)
			{
				min = key[v];
				min_index = v;
			}

		return min_index;
	}

	// A utility function to print the constructed MST stored in
	// parent[]
	void printMST(int parent[], int n, int graph[][])
	{
		System.out.println("Edge Weight");
		for (int i = 1; i < V; i++)
			System.out.println(parent[i]+" - "+ i+" "+
							graph[i][parent[i]]);
	}
    int minCost(int parent[], int n, int graph[][]){
    	int res = 0;
    	for (int i = 1; i < V; i++)
    		res = res + graph[i][parent[i]];	
    	return res;
    }
	// Function to construct and print MST for a graph represented
	// using adjacency matrix representation
	int primMST(int graph[][])
	{
		// Array to store constructed MST
		int parent[] = new int[V];

		// Key values used to pick minimum weight edge in cut
		int key[] = new int [V];

		// To represent set of vertices not yet included in MST
		Boolean mstSet[] = new Boolean[V];

		// Initialize all keys as INFINITE
		for (int i = 0; i < V; i++)
		{
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}

		// Always include first 1st vertex in MST.
		key[0] = 0;	 // Make key 0 so that this vertex is
						// picked as first vertex
		parent[0] = -1; // First node is always root of MST

		// The MST will have V vertices
		for (int count = 0; count < V-1; count++)
		{
			// Pick thd minimum key vertex from the set of vertices
			// not yet included in MST
			int u = minKey(key, mstSet);

			// Add the picked vertex to the MST Set
			mstSet[u] = true;

			// Update key value and parent index of the adjacent
			// vertices of the picked vertex. Consider only those
			// vertices which are not yet included in MST
			for (int v = 0; v < V; v++)

				// graph[u][v] is non zero only for adjacent vertices of m
				// mstSet[v] is false for vertices not yet included in MST
				// Update the key only if graph[u][v] is smaller than key[v]
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
			int in1= Integer.parseInt(br.readLine()); //number of planets (nodes)
			int graph[][] = new int[in1][in1];// 2d matrix to store weights of all nodes connected with every other node
			String[] in2 = new String[in1];
			for(int j=0;j<in1;j++){
				in2[j]  = br.readLine();// X,Y,Z
			}
			for(int j=0;j<in1;j++){ //calculate weights for all nodes. All nodes are connected with each other. on node there is n-1 edges
				String[] temp1  = in2[j].split(" ");
				for(int k=1;k<in1;k++){ // can be optimized by skipping already included values in matrix e.g weight for edge 1-3 and 3-1 are updated simultanously
					String[] temp2  = in2[k].split(" ");
					int weight=0;
					weight = Math.abs((Integer.parseInt(temp1[0])-Integer.parseInt(temp2[0]))) + Math.abs((Integer.parseInt(temp1[1])-Integer.parseInt(temp2[1]))) + Math.abs((Integer.parseInt(temp1[2])-Integer.parseInt(temp2[2])));
					graph[j][k] = weight;
					graph[k][j] = weight;
				}
			}
			SpaceTravel tr = new SpaceTravel(in1);
			System.out.println("Case #"+i+": "+tr.primMST(graph));
			br.readLine();// reading blank space
		}
	}
}
