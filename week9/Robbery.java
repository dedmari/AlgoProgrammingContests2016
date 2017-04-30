package week9;

//code inspired from http://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/

import java.io.*;
import java.util.LinkedList;

class Robbery
{
	static int V;
	Robbery(int v)
	{
		V=v;
	}
	
	public static void main (String[] args) throws java.lang.Exception
	{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for(int i=1;i<=t;i++)
		{
			int j;
			String inp1[]=br.readLine().split(" ");
			int policemen=Integer.parseInt(inp1[0]);
			int nodes=Integer.parseInt(inp1[1]);
			int edges=Integer.parseInt(inp1[2]);
			int graph[][]=new int[nodes][nodes];
			for(j=0;j<edges;j++)
			{
				String inp2[]=br.readLine().split(" ");
				int src=Integer.parseInt(inp2[0]);
				int dst=Integer.parseInt(inp2[1]);
				int wt=Integer.parseInt(inp2[2]);
				graph[src-1][dst-1]+=wt;
				graph[dst-1][src-1]+=wt;
			}
			Robbery m = new Robbery(nodes);
			int output=m.fordFulkerson(graph, 0, nodes-1);
			StringBuilder sb = new StringBuilder("Case #");
        	sb.append(i);
            sb.append(": ");
			if(output>policemen)
			{
				sb.append("no");
			}
			else
				sb.append("yes");
			System.out.println(sb.toString());
			br.readLine();

		}
	}
	
	boolean bfs(int rGraph[][], int s, int t, int parent[])
	{
		boolean visited[] = new boolean[V];
		for(int i=0; i<V; ++i)
			visited[i]=false;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		visited[s] = true;
		parent[s]=-1;
		while (queue.size()!=0)
		{
			int u = queue.poll();

			for (int v=0; v<V; v++)
			{
				if (visited[v]==false && rGraph[u][v] > 0)
				{
					queue.add(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}
		return (visited[t] == true);
	}
	int fordFulkerson(int graph[][], int s, int t)
	{
		int u, v;
		int rGraph[][] = new int[V][V];
		for (u = 0; u < V; u++)
			for (v = 0; v < V; v++)
				rGraph[u][v] = graph[u][v];
		int parent[] = new int[V];
		int max_flow = 0; 
		while (bfs(rGraph, s, t, parent))
		{
			int path_flow = Integer.MAX_VALUE;
			for (v=t; v!=s; v=parent[v])
			{
				u = parent[v];
				path_flow = Math.min(path_flow, rGraph[u][v]);
			}
			for (v=t; v != s; v=parent[v])
			{
				u = parent[v];
				rGraph[u][v] -= path_flow;
				rGraph[v][u] += path_flow;
			}
			max_flow += path_flow;
		}

		return max_flow;
	}
}

