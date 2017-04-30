package week9;
//code inspired from http://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Chocolate {
	int V;
	public Chocolate(int v){
		this.V = v;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			String[] inp1=br.readLine().split(" ");
			int n = Integer.parseInt(inp1[0]);
			int k = Integer.parseInt(inp1[1]);
			int m = Integer.parseInt(inp1[2]);
			int l = Integer.parseInt(inp1[3]);
			int graph[][] =new int[n+k+m+2][n+k+m+2];
			for(int i=1;i<=l;i++){
				String[] inp2=br.readLine().split(" ");
				graph[Integer.parseInt(inp2[0])][Integer.parseInt(inp2[1])] = Integer.parseInt(inp2[2]);
				graph[Integer.parseInt(inp2[1])][Integer.parseInt(inp2[0])] = Integer.parseInt(inp2[2]);
			}
			for(int i=1;i<=n;i++){
				graph[0][i]=Integer.MAX_VALUE; //multiple sources
			}
			for(int i=n+k+1;i<=n+k+m;i++){
				graph[i][n+k+m+1]=Integer.MAX_VALUE; //multiple destinations 
			}
			int vertices = n+k+m+2;
			Chocolate ch = new Chocolate(vertices);
			int maxFlow = ch.fordFulkerson(graph, 0, vertices-1);
			StringBuilder sb = new StringBuilder("Case #");
        	sb.append(tc);
            sb.append(": ");
            sb.append(maxFlow);
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
