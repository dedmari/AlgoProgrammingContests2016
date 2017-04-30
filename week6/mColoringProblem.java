package week6;
//inspired from http://code.geeksforgeeks.org/index.php
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class mColoringProblem {
	int V = 6;//number of countries
	int color[];
	mColoringProblem(int V){
		this.V=V;
	}
	boolean isSafe(int v, int graph[][], int color[],
				int c)
	{
		for (int i = 0; i < V; i++)
			if (graph[v][i] == 1 && c == color[i])
				return false;
		return true;
	}
	boolean graphColoringUtil(int graph[][], int m,
							int color[], int v)
	{
		if (v == V)
			return true;
		for (int c = 1; c <= m; c++)
		{
			if (isSafe(v, graph, color, c))
			{
				color[v] = c;
				if (graphColoringUtil(graph, m,
									color, v + 1))
					return true;
				color[v] = 0;
			}
		}
		return false;
	}

	boolean graphColoring(int graph[][], int m)
	{
		color = new int[V];
		for (int i = 0; i < V; i++)
			color[i] = 0;

		if (!graphColoringUtil(graph, m, color, 0))
		{
			System.out.println("impossible");
			return false;
		}
		printSolution(color);
		return true;
	}
	void printSolution(int color[])
	{
		for (int i = 0; i < V; i++)
			System.out.print(color[i] + " ");
		System.out.println();
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++)
		{
			String inp1[] = br.readLine().split(" ");
			mColoringProblem Coloring = new mColoringProblem(Integer.parseInt(inp1[0]));//number of vertices
			int graph[][]= new int[Integer.parseInt(inp1[0])][Integer.parseInt(inp1[0])];
			int m = Integer.parseInt(inp1[2]); // Number of colors
			for(int j=0;j<Integer.parseInt(inp1[1]);j++){// number of edges/connections
				String inp2[] = br.readLine().split(" ");
				graph[Integer.parseInt(inp2[0])-1][Integer.parseInt(inp2[1])-1] = 1;
				graph[Integer.parseInt(inp2[1])-1][Integer.parseInt(inp2[0])-1] = 1;
			}
			System.out.print("Case #"+i+": ");
			Coloring.graphColoring(graph, m);
			br.readLine();
		}

	}
}

