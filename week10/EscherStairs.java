package week10;
// code inspired from http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class EscherStairs {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 1; i <= t; i++) {
			PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(new Comparator<Vertex>() {
				public int compare(Vertex o1, Vertex o2) {
					return o1.dist 
							- o2.dist;
				}
			});
			String[] inp1 = br.readLine().split(" ");
			int n = Integer.parseInt(inp1[0]);
			int m = Integer.parseInt(inp1[1]);
			int s = Integer.parseInt(inp1[2]);
			Vertex[] graph = new Vertex[n + 1];		
			for (int index1 = 1; index1 <= n; index1++){
				graph[index1] = new Vertex(index1, true);
			}		

			for (int j = 0; j < m; j++) {
				inp1 = br.readLine().split(" ");
				int ai = Integer.parseInt(inp1[0]);
				int bi = Integer.parseInt(inp1[1]);
				int ci = Integer.parseInt(inp1[2]);
				Vertex vertexA = graph[ai];
				vertexA.flag = true;
				vertexA.adjList.add(bi);
				vertexA.adjDist.add(ci);
				Vertex vertexB = graph[bi];
				vertexB.flag = true;
				vertexB.adjList.add(ai);
				vertexB.adjDist.add(-ci);
			}
			Vertex startVertex = graph[s];
			pq.add(startVertex);
			startVertex.dist = 0;
			whileLoop: while (!pq.isEmpty()) {
				Vertex v1 = pq.remove();
				for (int index3 = 0; index3 < 
						v1.adjList.size(); index3++) {
					Vertex adjVertex = 
							graph[v1.adjList
							      .get(index3)];
					int distance = v1.adjDist.get(index3);
					int nxtDist = v1.steps 
							+ distance;
					int totalDist = v1.dist + 1;
					if (adjVertex.id == s) {
						if (nxtDist == 0)
							continue;
						else {
							startVertex.dist = totalDist;
							break whileLoop; 
						}
					}
					if (!adjVertex.distMap.containsKey(nxtDist)
							|| 
							totalDist < 
							adjVertex.distMap
							.get(nxtDist)) {
						Vertex connectingVertex = 
								new Vertex(adjVertex, nxtDist, totalDist, distance);
						adjVertex.distMap.put(nxtDist, totalDist);
						pq.add(connectingVertex);
					}
				}
			}
			StringBuilder sb = new StringBuilder("Case #");
			sb.append(i);
			sb.append(": ");	
			if(startVertex.dist == 0 ){
				sb.append("possible");
			}else{
				sb.append(startVertex.dist);
			}
			System.out.println(sb.toString());
			br.readLine();
		}
	}

}

class Vertex {
	int id;
	int steps = 0;
	ArrayList<Integer> adjDist = new ArrayList<Integer>();
	int dist = Integer.MAX_VALUE;
	int nxtDist;
	Boolean flag;
	int concs;
	ArrayList<Integer> adjList = new ArrayList<Integer>();
	HashMap<Integer, Integer> distMap;
	public Vertex(Vertex nodes, int steps, int dist, int nxtDist) {
		this.id = nodes.id;
		this.adjList = nodes.adjList;
		this.nxtDist = nxtDist;
		this.adjDist = nodes.adjDist;
		this.dist = dist;
		this.steps = steps;
		this.flag = false;
	}
	public Vertex(int id, Boolean x) {
		if(x)
			distMap = new HashMap<Integer, Integer>();
		this.id = id;
	}

}