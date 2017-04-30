package week4;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
public class RescueMission {
public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= t; i++) {
			String[] input1Array = br.readLine().split(" ");
			int nodes = Integer.parseInt(input1Array[0]);
			int edges = Integer.parseInt(input1Array[1]);
			int dr = Integer.parseInt(input1Array[2]);
			int[][] matrix = new int[nodes][nodes];
			List<Edge>[] nodeList = new ArrayList[nodes];
			 boolean[] dungeon = new boolean[nodes];
			 PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> Integer.compare(a.cost, b.cost));
			Edge edge;
			
			for (int j = 0; j < dr; j++){
				dungeon[Integer.parseInt(br.readLine()) - 1] = true;
			}
			for (int k = 0; k < edges; k++) {
				input1Array = br.readLine().split(" ");
				edge = new Edge(
						Integer.parseInt(input1Array[0]) - 1,
						Integer.parseInt(input1Array[1]) - 1, 
						Integer.parseInt(input1Array[2]));
				if (nodeList[edge.s] == null)
					nodeList[edge.s] = new ArrayList<Edge>();
				if (nodeList[edge.d] == null)
					nodeList[edge.d] = new ArrayList<Edge>();
				nodeList[edge.s].add(edge);
				nodeList[edge.d].add(edge);
			}
			for (int index = 0; index < nodes; index++) {
				if (!dungeon[index] && index != 0)
					continue;
				
				boolean[] visited = new boolean[nodes];
				boolean[] ninjaVisited = new boolean[nodes];
				pq.clear();
				if (nodeList[index] != null)
					pq.addAll(nodeList[index]);
				visited[index] = true;
				int sNode, dNode;
				int totalFreedNinjas = dungeon[index] ? 1 : 0;
				while (!pq.isEmpty()) {
					edge = pq.remove();
					sNode = edge.s;
					dNode = edge.d;
					if (visited[sNode] && visited[dNode])
						continue;
					if (visited[dNode]) {
						sNode = edge.d;
						dNode = edge.s;
					}
					if (matrix[index][dNode] == 0 || edge.cost < matrix[index][dNode]) {
						matrix[index][dNode] = edge.cost;
						matrix[dNode][index] = edge.cost;
					}
					visited[dNode] = true;
					if (dungeon[dNode] && !ninjaVisited[dNode]) {
						ninjaVisited[dNode] = true;
						if (++totalFreedNinjas == dr)
							break;
					}
					
					for (Edge e : nodeList[dNode]) {
						if (!visited[e.s] || !visited[e.d])
							pq.add(new Edge(e.s, e.d, e.cost + matrix[index][dNode]));
					}
				}
			}
			pq.clear();
			boolean[] visited = new boolean[nodes];
			int ms = Integer.MAX_VALUE;
			int index = 0;
			for (int index3 = 0; index3 < nodes; index3++)
				if (dungeon[index3] && ms > matrix[0][index3]) {
					index = index3;
					ms = matrix[0][index3];
				}
			visited[index] = true;
			visited[0] = true;
			
			for (int j = 0; j < nodes; j++) {
				if (dungeon[j] && !visited[j])
					pq.add(new Edge(index, j, matrix[index][j]));
			}
			dr--;
			while  (dr > 0) {
				edge = pq.poll();
				if (visited[edge.d]) {
					continue;
				}
				
				ms += edge.cost;
				visited[edge.d] = true;
				
				for (int j = 0; j < nodes; j++) {
					if (dungeon[j] && !visited[j])
						pq.add(new Edge(edge.d, j, matrix[edge.d][j]));
				}
				dr--;
			}	
		System.out.println("Case #"+i+": "+ms);
		br.readLine();
		}	
	}
static class Edge {
	int s;
	int d;
	int cost;
	Edge(int i, int j, int cost) {
		this.s = i;
		this.d = j;
		this.cost = cost;
	}
}

}