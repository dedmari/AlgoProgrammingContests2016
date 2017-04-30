package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

import week9.Interesteller3.Graph;

public class Interesteller3 {

	class Edge
	{
		// To store current flow and capacity of edge
		int flow, capacity;

		// An edge u--->v has start vertex as u and end
		// vertex as v.
		int u, v;

		Edge(int flow, int capacity, int u, int v)
		{
			this.flow = flow;
			this.capacity = capacity;
			this.u = u;
			this.v = v;
		}
	};
	class Vertex
	{
		int h, e_flow;

		Vertex(int h, int e_flow)
		{
			this.h = h;
			this.e_flow = e_flow;
		}
	}

	class Graph
	{
		int V;    // No. of vertices
		Vector<Vertex> ver;
		Vector<Edge> edge;

		// Function to push excess flow from 

		Graph(int V)
		{
			this.V = V;
			ver=new Vector<Vertex>();
			edge=new Vector<Edge>();
			// all vertices are initialized with 0 height
			// and 0 excess flow
			for (int i = 0; i < V; i++)
			{
				Vertex v=new Vertex(0,0);
				ver.add(v);
			}
		}

		void addEdge(int u, int v, int capacity)
		{
			// flow is initialized with 0 for all edge
			Edge e=new Edge(0,capacity,u,v);
			edge.add(e);
		}

		void preflow(int s)
		{
			// Making h of source Vertex equal to no. of vertices
			// Height of other vertices is 0.
			ver.get(s).h = ver.size();

			//
			for (int i = 0; i < edge.size(); i++)
			{
				// If current edge goes from source
				if (edge.get(i).u == s)
				{
					// Flow is equal to capacity
					edge.get(i).flow = edge.get(i).capacity;

					// Initialize excess flow for adjacent v
					ver.get(edge.get(i).v).e_flow += edge.get(i).flow;

					// Add an edge from v to s in residual graph with
					// capacity equal to 0
					//edge.push_back(Edge(-edge[i].flow, 0, edge[i].v, s));
					Edge e=new Edge(-edge.get(i).flow,0,edge.get(i).v,s);
					edge.add(e);
				}
			}
		}

		// returns index of overflowing Vertex
		int overFlowVertex(Vector<Vertex> ver)
		{
			for (int i = 1; i < ver.size() - 1; i++)
				if (ver.get(i).e_flow > 0)
					return i;

			// -1 if no overflowing Vertex
			return -1;
		}

		// Update reverse flow for flow added on ith Edge
		void updateReverseEdgeFlow(int i, int flow)
		{
			int u = edge.get(i).v, v = edge.get(i).u;

			for (int j = 0; j < edge.size(); j++)
			{
				if (edge.get(j).v == v && edge.get(j).u == u)
				{
					edge.get(j).flow -= flow;
					return;
				}
			}

			// adding reverse Edge in residual graph
			Edge e = new Edge(0, flow, u, v);
			edge.add(e);
		}

		// To push flow from overflowing vertex u
		Boolean push(int u)
		{
			// Traverse through all edges to find an adjacent (of u)
			// to which flow can be pushed
			for (int i = 0; i < edge.size(); i++)
			{
				// Checks u of current edge is same as given
				// overflowing vertex
				if (edge.get(i).u == u)
				{
					// if flow is equal to capacity then no push
					// is possible
					if (edge.get(i).flow == edge.get(i).capacity)
						continue;

					// Push is only possible if height of adjacent
					// is smaller than height of overflowing vertex
					if (ver.get(u).h > ver.get(edge.get(i).v).h)
					{
						// Flow to be pushed is equal to minimum of
						// remaining flow on edge and excess flow.
						int flow = Math.min(edge.get(i).capacity - edge.get(i).flow,
								ver.get(u).e_flow);

						// Reduce excess flow for overflowing vertex
						ver.get(u).e_flow -= flow;

						// Increase excess flow for adjacent
						ver.get(edge.get(i).v).e_flow += flow;

						// Add residual flow (With capacity 0 and negative
						// flow)
						edge.get(i).flow += flow;

						updateReverseEdgeFlow(i, flow);

						return true;
					}
				}
			}
			return false;
		}

		// function to relabel vertex u
		void relabel(int u)
		{
			// Initialize minimum height of an adjacent
			int mh = Integer.MAX_VALUE;

			// Find the adjacent with minimum height
			for (int i = 0; i < edge.size(); i++)
			{
				if (edge.get(i).u == u)
				{
					// if flow is equal to capacity then no
					// relabeling
					if (edge.get(i).flow == edge.get(i).capacity)
						continue;

					// Update minimum height
					if (ver.get(edge.get(i).v).h < mh)
					{
						mh = ver.get(edge.get(i).v).h;

						// updating height of u
						ver.get(u).h = mh + 1;
					}
				}
			}
		}
		// main function for printing maximum flow of graph
		int getMaxFlow(int s, int t)
		{
			preflow(s);

			// loop untill none of the Vertex is in overflow
			while (overFlowVertex(ver) != -1)
			{
				int u = overFlowVertex(ver);
				if (!push(u))
					relabel(u);
			}

			// ver.back() returns last Vertex, whose
			// e_flow will be final maximum flow
			return ver.get(ver.size()-1).e_flow;
			//return ver.back().e_flow;
		}
	}


	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			String[] inp1 = br.readLine().split(" ");
			int N = Integer.parseInt(inp1[0]);
			int M = Integer.parseInt(inp1[1]);
			int n = Integer.parseInt(inp1[2]);
			int m = Integer.parseInt(inp1[3]);
			String[] CoordinateStarSystem = new String[N]; 
			double[][] dijkstraGraph =  new double[N][N];
			//int[][] maxGraph = new int[n*N][n*N];
			//PushRelabel flow = new  PushRelabel();
			Interesteller3 x=new Interesteller3();
		 	Graph flow=x.new Graph((n*N)+1); 
			//flow.init((n*N)+1);
			for(int i=0;i<N;i++){
				CoordinateStarSystem[i] = br.readLine();
			}
			for(int i=0;i<M;i++){
				String[] inp3 = br.readLine().split(" ");
				int a = Integer.parseInt(inp3[0]);
				int b = Integer.parseInt(inp3[1]);
				String[] sys1 = CoordinateStarSystem[a-1].split(" ");
				String[] sys2 = CoordinateStarSystem[b-1].split(" ");
				int x1=Integer.parseInt(sys1[0]),y1=Integer.parseInt(sys1[1]),z1=Integer.parseInt(sys1[2]);
				int x2=Integer.parseInt(sys2[0]),y2=Integer.parseInt(sys2[1]),z2=Integer.parseInt(sys2[2]);
				double dist = Math.sqrt(((x1-x2)*(x1-x2)) + ((y1-y2)*(y1-y2)) + ((z1-z2)*(z1-z2)));
				dijkstraGraph[a-1][b-1] = dist;
			}			
			for (int i=0;i<N;i++){
				//flowArray[i] = new MaxFlowPreflowN3();
				//flowArray[i].init(n+1);
				for (int j=0;j<m;j++){
					String[] inp4 = br.readLine().split(" ");
					int index1 = Integer.parseInt(inp4[0]);
					int index2 = Integer.parseInt(inp4[1]);
					//index1 = index1==0?n:index1;
					//index2 = index2==0?n:index2;
					flow.addEdge(index1, index2, Integer.parseInt(inp4[2]));
					flow.addEdge(index2, index1, Integer.parseInt(inp4[2]));
				}
			}
			flow.addEdge(0, 1, Integer.MAX_VALUE);
			ShortPth st = new ShortPth(N,n,flow);
			StringBuilder sb = new StringBuilder("Case #");
			sb.append(tc);
			sb.append(": ");
			System.out.print(sb.toString());
			st.dijkstra(dijkstraGraph, 0);
			br.readLine();
		}
	}
}

class ShortPth
{
	// A utility function to find the vertex with minimum distance value,
	// from the set of vertices not yet included in shortest path tree
	int V;
	Graph flow;
	int maxNodes = 0; //colonies in a star system
	public ShortPth(int v,int nodes,Graph flow2){
		this.V = v;
		this.flow = flow2;
		this.maxNodes = nodes;
	}
	int minDistance(double dist[], boolean sptSet[])
	{
		// Initialize min value
		double min = Double.MAX_VALUE;
		int min_index=-1;

		for (int v = 0; v < V; v++)
			if (sptSet[v] == false && dist[v] <= min)
			{
				min = dist[v];
				min_index = v;
			}

		return min_index;
	}

	void printPath(int parent[], int j, int nxt)
	{
		// Base Case : If j is source
		if (parent[j]==-1) {
			if(V>1)
				flow.addEdge((j+1)*maxNodes, ((nxt)*maxNodes)+1, Integer.MAX_VALUE);
			return;
		}

		printPath(parent, parent[j], j);
		if(j != (V-1)){//last star 
			flow.addEdge((j+1)*maxNodes, ((nxt)*maxNodes)+1, Integer.MAX_VALUE);
		}
	}

	void dijkstra(double graph[][], int src)
	{
		double dist[] = new double[V]; // The output array. dist[i] will hold
		// the shortest distance from src to i

		// sptSet[i] will true if vertex i is included in shortest
		// path tree or shortest distance from src to i is finalized
		boolean sptSet[] = new boolean[V];
		// Parent array to store shortest path tree
		int parent[] = new int[V];
		parent[0] = -1;
		// Initialize all distances as INFINITE and stpSet[] as false
		for (int i = 0; i < V; i++)
		{
			dist[i] = Double.MAX_VALUE;
		}

		// Distance of source vertex from itself is always 0
		dist[src] = 0;

		// Find shortest path for all vertices
		for (int count = 0; count < V-1; count++)
		{
			// Pick the minimum distance vertex from the set of vertices
			// not yet processed. u is always equal to src in first
			// iteration.
			int u = minDistance(dist, sptSet);

			// Mark the picked vertex as processed
			sptSet[u] = true;

			// Update dist value of the adjacent vertices of the
			// picked vertex.
			for (int v = 0; v < V; v++)

				// Update dist[v] only if is not in sptSet, there is an
				// edge from u to v, and total weight of path from src to
				// v through u is smaller than current value of dist[v]
				if (!sptSet[v] && graph[u][v]!=0 &&
				dist[u] != Double.MAX_VALUE &&
				dist[u]+graph[u][v] < dist[v]){
					parent[v] = u;
					dist[v] = dist[u] + graph[u][v];
				}

		}
		if(dist[V-1]==Double.MAX_VALUE){//no path
			System.out.println("impossible");
		}
		else{
			printPath(parent,V-1,-1);
			int allMax=flow.getMaxFlow(0, V*maxNodes);
			if(allMax==0){
				System.out.println("impossible");
			}
			else
				System.out.println(allMax);
		}
	}
}