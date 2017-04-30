import java.io.*;
import java.util.*;

class DisjointUnionSets
{
	int[] rank, parent;
	int n;
	public DisjointUnionSets(int n)
	{
		rank = new int[n+1];
		parent = new int[n+1];
		this.n = n;
		makeSet();
	}
	void makeSet()
	{
		for (int i=0; i<=n; i++)
		{
			parent[i] = i;
		}
	}

	int find(int x)
	{
		if (parent[x]!=x)
		{
			parent[x] = find(parent[x]);
		}

		return parent[x];
	}

	void union(int x, int y)
	{
		int xRoot = find(x), yRoot = find(y);

		if (xRoot == yRoot)
			return;

		if (rank[xRoot] < rank[yRoot])

			parent[xRoot] = yRoot;

		else if (rank[yRoot] < rank[xRoot])

			parent[yRoot] = xRoot;

		else // if ranks are the same
		{
			parent[yRoot] = xRoot;
			rank[xRoot] = rank[xRoot] + 1;
		}
	}
}

public class Marry2
{
	public static void main(String[] args) throws NumberFormatException, IOException, Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t= Integer.parseInt(br.readLine());
		// inspired from http://www.geeksforgeeks.org/disjoint-set-data-structures-java-implementation/
		for(int i=1;i<=t;i++){
			String[] input2;// mi 
			String[] input1;
			List<Integer> marriages = new ArrayList<Integer>();
			List<String> money = new ArrayList<String>();
			boolean flag=false;
			int x=0;// result
			input1  = br.readLine().split(" ");//a=input1[0], b=input1[1] and c=input1[2]
			DisjointUnionSets dus = new DisjointUnionSets(Integer.parseInt(input1[0]));// initializing the set for union and find operations
			input2 = br.readLine().split(" ");// value of money associated with persons except Lea
			if(input2[0].matches("\\d+")){
				money = new ArrayList<String>(Arrays.asList(input2));
			}	
			for (int j=1;j<=Integer.parseInt(input1[1]);j++){// no of family relations i.e b 
				String[] inputrel = br.readLine().split(" ");	
				dus.union(Integer.parseInt(inputrel[0])-1, Integer.parseInt(inputrel[1])-1);
			}
			for(int j=1;j<=Integer.parseInt(input1[2]);j++){// and marriages i.e c
				String[] inputmar = br.readLine().split(" ");
				marriages.add(Integer.parseInt(inputmar[0])-1);
				marriages.add(Integer.parseInt(inputmar[1])-1);
				dus.union(Integer.parseInt(inputmar[0])-1, Integer.parseInt(inputmar[1])-1);
			}
			for(int j=0;j<Integer.parseInt(input1[0])-1 && !(money.isEmpty());j++){
				if(dus.find(j) != dus.find(Integer.parseInt(input1[0])-1)){	
					if(!(marriages.contains(j))){
						if(x <= Integer.parseInt(money.get(j))){
							flag = true;
							x=Integer.parseInt(money.get(j));
						}
					}
				}
			}
			if(flag){
				System.out.println("Case #"+i+": "+x);
			}
			else{
				System.out.println("Case #"+i+": impossible");
			}
		    br.readLine();// reading blank space
		}
	}
}
