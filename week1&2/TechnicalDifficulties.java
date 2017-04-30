import java.io.*;
import java.util.*;

class TechnicalDifficulties
{
	private int V; // No. of vertices
	// Array of lists for Adjacency List Representation
	private LinkedList<Integer> adj[];
	static int currentNode;
	static boolean invalid =true;
	TechnicalDifficulties(int v)
	{
		V = v;
		adj = new LinkedList[v];
		for (int i=0; i<v; ++i)
			adj[i] = new LinkedList();
	}
	void addEdge(int v, int w)
	{
		adj[v].add(w); // Add w to v's list.
		adj[w].add(v);// Add v to w's list to make it undirected graph
	}
	// A function used by DFS
	boolean DFSUtil(int v,boolean visited[],int previousNode)
	{
		visited[v] = true;
		Iterator<Integer> i = adj[v].listIterator();
		while (i.hasNext())
		{
			int n = i.next();
			if(n == currentNode &&  n != previousNode){
				invalid = false;
				break;
			}
			else if (!visited[n])// checking if node is visited and if previous node is equal to next node
				invalid = DFSUtil(n, visited,v);
			else if(!invalid){
				break;
			}
		}
		return invalid;
	}

	// The function to do DFS traversal. It uses recursive DFSUtil()
	boolean DFS(int v)
	{

		boolean visited[] = new boolean[V];
		return DFSUtil(v, visited,v);
	}

	public static void main(String args[]) throws NumberFormatException, IOException, Exception 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t= Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++){
			List<Integer> leftStations  = new ArrayList<Integer>();
			List<Integer> rightStations = new ArrayList<Integer>();
			String input1[]  = br.readLine().split(" ");// input1[0]-->n, input1[1]-->m
			TechnicalDifficulties g = new TechnicalDifficulties(Integer.parseInt(input1[0]));
			StringBuilder invalidList = new StringBuilder();
			for(int j=1;j<=Integer.parseInt(input1[1]);j++){// get values of stations connections
				String input2[]  = br.readLine().split(" ");//input2[0]--> station1, input2[1]--> station2
				g.addEdge(Integer.parseInt(input2[0])-1, Integer.parseInt(input2[1])-1);
				leftStations.add(Integer.parseInt(input2[0])-1);
				rightStations.add(Integer.parseInt(input2[1])-1);	
			}
			for(int j=0;j<=(Integer.parseInt(input1[1])-1);j++){// checking for the impossible connections
				currentNode = leftStations.get(j);
				if (g.DFS(currentNode)){
					invalidList.append(j+1);
					invalidList.append(" ");
				}
				else {
					invalid =true;
					currentNode = rightStations.get(j);;
					if(g.DFS(currentNode)){
						invalidList.append(j+1);
						invalidList.append(" ");
					}
					invalid =true;
				}
				invalid =true;
			}
			System.out.println("Case #"+i+": "+invalidList);
			br.readLine();// reading blank space
			invalid =true;
		}

	}
}

