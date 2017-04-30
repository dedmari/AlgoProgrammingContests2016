package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Interstellar {
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
			MaxFlowPreflowN3[] flowArray = new  MaxFlowPreflowN3[N];
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
				flowArray[i] = new MaxFlowPreflowN3();
				flowArray[i].init(n+1);
				for (int j=0;j<m;j++){
					String[] inp4 = br.readLine().split(" ");
					int index1 = Integer.parseInt(inp4[0]) % n;
					int index2 = Integer.parseInt(inp4[1]) % n;
					index1 = index1==0?n:index1;
					index2 = index2==0?n:index2;
					flowArray[i].addEdge(index1, index2, Integer.parseInt(inp4[2]));
					flowArray[i].addEdge(index2, index1, Integer.parseInt(inp4[2]));
				}
			}
			flowArray[0].addEdge(0, 1, Integer.MAX_VALUE);
			ShortestPath st = new ShortestPath(N,n,flowArray);
			StringBuilder sb = new StringBuilder("Case #");
			sb.append(tc);
			sb.append(": ");
			System.out.print(sb.toString());
			st.dijkstra(dijkstraGraph, 0);
			br.readLine();
		}
	}

}

class ShortestPath
{
	// A utility function to find the vertex with minimum distance value,
	// from the set of vertices not yet included in shortest path tree
	int V;
	MaxFlowPreflowN3 flowArray[];
	int allMax=0;
	int maxNodes = 0; //colonies in a star system
	public ShortestPath(int v,int nodes,MaxFlowPreflowN3[] flowArray){
		this.V = v;
		this.flowArray = flowArray;
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
			//		System.out.print(flowArray[j].maxFlow(0, 5)+" ");
			allMax = flowArray[j].maxFlow(0, maxNodes);
			if(V>1)
				flowArray[nxt].addEdge(0, 1, allMax);
			return;
		}

		printPath(parent, parent[j], j);
		allMax = flowArray[j].maxFlow(0, maxNodes);
		if(j != (V-1)){//last star 
			flowArray[nxt].addEdge(0, 1, allMax);
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
			if(allMax==0){
				System.out.println("impossible");
			}
			else
				System.out.println(allMax);
		}
	}
}

// Max flow Push-Relabel

class MaxFlowPreflowN3 {

	int[][] cap;

	public void init(int nodes) {
		cap = new int[nodes][nodes];
	}

	public void addEdge(int s, int t, int capacity) {
		cap[s][t] = capacity;
	}

	public int maxFlow(int s, int t) {
		int n = cap.length;

		int[] h = new int[n];
		h[s] = n - 1;

		int[] maxh = new int[n];

		int[][] f = new int[n][n];
		int[] e = new int[n];

		for (int i = 0; i < n; ++i) {
			f[s][i] = cap[s][i];
			f[i][s] = -f[s][i];
			e[i] = cap[s][i];
		}

		for (int sz = 0;;) {
			if (sz == 0) {
				for (int i = 0; i < n; ++i)
					if (i != s && i != t && e[i] > 0) {
						if (sz != 0 && h[i] > h[maxh[0]])
							sz = 0;
						maxh[sz++] = i;
					}
			}
			if (sz == 0)
				break;
			while (sz != 0) {
				int i = maxh[sz - 1];
				boolean pushed = false;
				for (int j = 0; j < n && e[i] != 0; ++j) {
					if (h[i] == h[j] + 1 && cap[i][j] - f[i][j] > 0) {
						int df = Math.min(cap[i][j] - f[i][j], e[i]);
						f[i][j] += df;
						f[j][i] -= df;
						e[i] -= df;
						e[j] += df;
						if (e[i] == 0)
							--sz;
						pushed = true;
					}
				}
				if (!pushed) {
					h[i] = Integer.MAX_VALUE;
					for (int j = 0; j < n; ++j)
						if (h[i] > h[j] + 1 && cap[i][j] - f[i][j] > 0)
							h[i] = h[j] + 1;
					if (h[i] > h[maxh[0]]) {
						sz = 0;
						break;
					}
				}
			}
		}

		int flow = 0;
		for (int i = 0; i < n; i++)
			flow += f[s][i];

		return flow;
	}
}
