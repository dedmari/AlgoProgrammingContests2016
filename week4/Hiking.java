package week4;
//inspired from http://www.sanfoundry.com/java-program-mplement-dijkstras-algorithm-using-priority_queue/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Hiking {
	public static void main(String[] args)  throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int x = 1; x <= t; x++) 
		{
			 String[] inp1 = br.readLine().split(" ");
			 int nd = Integer.parseInt(inp1[0]);
			 PriorityQueue<Node> pq=new PriorityQueue<Node>(nd,new Node());
			 int[][] gr=new int[nd][nd];
			 int ed = Integer.parseInt(inp1[1]);
			 String inp2[]=null;
			 int dist[]=new int[nd];
			 boolean visited[] = new boolean[nd];
			 for (int i = 1;i<nd;i++)
			 {
				 dist[i] = Integer.MAX_VALUE;
			 }
			 for(int i=0;i<ed;i++)
			 {
				 inp2=br.readLine().split(" ");
				 gr[Integer.parseInt(inp2[0])-1][Integer.parseInt(inp2[1])-1]=Integer.parseInt(inp2[2]);
				 gr[Integer.parseInt(inp2[1])-1][Integer.parseInt(inp2[0])-1]=Integer.parseInt(inp2[2]);
			 }
			 pq.add(new Node(0, 0));
			 while (!pq.isEmpty())
		        {
		            int d = pq.poll().node;
		            visited[d]=true;
		            if(visited[nd-1])
				 		break;
		            int edgeDistance = -1;
			        int newDistance = -1;
		            for (int destinationNode = 0; destinationNode < nd; destinationNode++)
			        {
		            	if (!visited[destinationNode] && gr[d][destinationNode] != 0)
		            	{
		            		edgeDistance = gr[d][destinationNode];
		            		newDistance = dist[d] + edgeDistance;
		            		if (newDistance < dist[destinationNode])
		            		{
		            			dist[destinationNode] = newDistance;
		            		}
		            		pq.add(new Node(destinationNode,dist[destinationNode]));
		            	}
			        }
		        }
			 StringBuilder sb = new StringBuilder();
				sb.append("Case #");
				sb.append(x);
				sb.append(": ");
				sb.append(dist[nd-1]);
				System.out.println(sb);
			 br.readLine();
		}
	}
}
class Node implements Comparator<Node>
{
    public int node;
    public int cost;
    public Node()
    {
    }
    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}
