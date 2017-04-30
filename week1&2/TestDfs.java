// Java program to print DFS traversal from a given given graph
import java.io.*;
import java.util.*;

// This class represents a directed graph using adjacency list
// representation
class TestDfs
{
	private int V; // No. of vertices

	// Array of lists for Adjacency List Representation
	private LinkedList<Integer> adj[];
	static int currentNode;
	static boolean invalid =true;
	// Constructor
	TestDfs(int v)
	{
		V = v;
		adj = new LinkedList[v];
		for (int i=0; i<v; ++i)
			adj[i] = new LinkedList();
	}

	//Function to add an edge into the graph
	void addEdge(int v, int w)
	{
		adj[v].add(w); // Add w to v's list.
		adj[w].add(v);// Add v to w's list to make it undirected graph
	}

	// A function used by DFS
	boolean DFSUtil(int v,boolean visited[],int previousNode)
	{
		// Mark the current node as visited and print it
		visited[v] = true;
		//System.out.println(v+" ");
		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = adj[v].listIterator();
		while (i.hasNext())
		{
			int n = i.next();
			if(n == currentNode &&  n != previousNode){
//				System.out.println("inside if ");
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

	public static void main(String args[])
	{
		TestDfs g = new TestDfs(7);
		g.addEdge(0, 3);
		g.addEdge(0, 6);
		g.addEdge(1, 6);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(3, 6);
		g.addEdge(4, 5);
		g.addEdge(5, 6);
// checking case 1 2
		currentNode = 1;
		if (g.DFS(1)){
			System.out.println("1 - 2 invalid");
		}
		else {
			invalid =true;
			currentNode = 6;
			if(g.DFS(6)){
				System.out.println("1 - 2 invalid");
			}
		}
		
		System.out.print(invalid);
		System.out.println("-----------End------------");
	}
}
