package week3;
import java.util.*;
import java.io.*;
class KruskalJungleNetwork
{
	class Edge implements Comparable<Edge>
	{
		int src, dest, weight;
		public int compareTo(Edge compareEdge)
		{
			return this.weight-compareEdge.weight;
		}
	};
	class subset
	{
		int parent, rank;
	};
	int V, E;
	Edge edge[];
	Boolean mstSet[] = new Boolean[V];
	KruskalJungleNetwork(int v, int e)
	{
		V = v;
		E = e;
		edge = new Edge[E];
		for (int i=0; i<e; ++i)
			edge[i] = new Edge();
	}
	int find(subset subsets[], int i)
	{
		if (subsets[i].parent != i)
			subsets[i].parent = find(subsets, subsets[i].parent);

		return subsets[i].parent;
	}
	void Union(subset subsets[], int x, int y)
	{
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);
		if (subsets[xroot].rank < subsets[yroot].rank)
			subsets[xroot].parent = yroot;
		else if (subsets[xroot].rank > subsets[yroot].rank)
			subsets[yroot].parent = xroot;
		else
		{
			subsets[yroot].parent = xroot;
			subsets[xroot].rank++;
		}
	}
	int KruskalMST()
	{
		Edge result[] = new Edge[V];
		int e = 0;
		int i = 0;
		for (i=0; i<V; ++i)
			result[i] = new Edge();
		Arrays.sort(edge);
		subset subsets[] = new subset[V];
		for(i=0; i<V; ++i)
			subsets[i]=new subset();
		for (int v = 0; v < V; ++v)
		{
			subsets[v].parent = v;
			subsets[v].rank = 0;
		}
		i = 0;
		while (e < V - 1 && i < edge.length)
		{
			Edge next_edge = new Edge();
			next_edge = edge[i++];
			int x = find(subsets, next_edge.src);
			int y = find(subsets, next_edge.dest);
			if (x != y)
			{
				result[e++] = next_edge;
				Union(subsets, x, y);
			}
		}
		int res = -1;
		if(e == V-1){
			res = 0;
			for (i = 0; i < e; ++i)
				res = res + result[i].weight;
		}
		return res;
	}
	public static void main (String[] args)throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t= Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++){
			String[] in1  = br.readLine().split(" ");
			KruskalJungleNetwork graph = new KruskalJungleNetwork(Integer.parseInt(in1[0]), Integer.parseInt(in1[1]));
			for(int j= 0; j<Integer.parseInt(in1[1]);j++){
				String[] in2  = br.readLine().split(" ");
				graph.edge[j].src = (Integer.parseInt(in2[0])-1);
				graph.edge[j].dest = (Integer.parseInt(in2[1])-1);
				graph.edge[j].weight = (Integer.parseInt(in2[2]));
			}	
			int res = graph.KruskalMST();
			if(res != -1)
				System.out.println("Case #"+i+": "+res);
			else
				System.out.println("Case #"+i+": impossible");
			br.readLine();
		}
	}
}
