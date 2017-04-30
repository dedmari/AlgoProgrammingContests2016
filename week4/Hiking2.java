package week4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
public class Hiking2 {
	
	public static void main(String args[]) throws NumberFormatException, IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++)
		{
			int k;
			String input[]=br.readLine().split(" ");
			int nd=Integer.valueOf(input[0]);
			PriorityQueue<Node> pq=new PriorityQueue<Node>(nd,new Node(-1));
			ArrayList<Node> nodes=new ArrayList<Node>();
			for(k=0;k<nd;k++)
			{
				nodes.add(new Node(k));
			}
			int gr[][]=new int[nd][nd];
			int ed=Integer.valueOf(input[1]);
			for(k=1;k<=ed;k++)
			{
				String inp[]=br.readLine().split(" ");
				int n1=Integer.valueOf(inp[0]);
				int n2=Integer.valueOf(inp[1]);
				int e=Integer.valueOf(inp[2]);
				gr[n1-1][n2-1]=e;gr[n2-1][n1-1]=e;
			}
			nodes.get(0).dist=0;
			nodes.get(0).visited=true;
			for(int m=0;m<nd;m++)
			{
				if(gr[0][m]!=0)
				{
					nodes.get(m).dist=nodes.get(0).dist+gr[0][m];
					pq.add(nodes.get(m));
				}

			}
			while(!pq.isEmpty())
			{
				Node w=pq.poll();
				w.visited=true;
				for(int l=0;l<nd;l++)
				{
					if(gr[w.no][l]!=0) 
					{
						if(nodes.get(l).visited==false)
						{
							if(!pq.contains(nodes.get(l)))
							{

								nodes.get(l).dist=nodes.get(l).dist<nodes.get(w.no).dist+gr[w.no][l]?nodes.get(l).dist:nodes.get(w.no).dist+gr[w.no][l];
								nodes.get(l).visited=true;
								pq.add(nodes.get(l));
							}
							else
							{
								pq.remove(nodes.get(l));
								nodes.get(l).dist=nodes.get(l).dist<nodes.get(w.no).dist+gr[w.no][l]?nodes.get(l).dist:nodes.get(w.no).dist+gr[w.no][l];
								nodes.get(l).visited=true;
								pq.add(nodes.get(l));
							}

						}
						else
						{ 
							if(pq.contains(nodes.get(l)))
							{
								if(nodes.get(l).dist>=nodes.get(w.no).dist+gr[w.no][l])
								{
									pq.remove(nodes.get(l));
									nodes.get(l).dist=nodes.get(w.no).dist+gr[w.no][l];
									nodes.get(l).visited=true;
									pq.add(nodes.get(l));
								}
							}
						}
					}

				}

			}
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			sb.append(nodes.get(nd-1).dist);
			System.out.println(sb);
			br.readLine();			
		}
	}
	static class Node implements Comparator<Node>{
		int dist;
		boolean visited;
		Node pred;
		int no;
		Node(int no)
		{
			this.no=no;
			visited=false;
			dist=Integer.MAX_VALUE;
			pred=null;		
		}
		public int compare(Node arg0, Node arg1) {
			return (arg0.dist-arg1.dist); 
		}

	}
	public static boolean notVisited(ArrayList<Node> nodes)
	{
		for(int i=0;i<nodes.size();i++)
		{
			if(nodes.get(i).visited==false)
			{
				return true;
			}

		}
		return false;
	}

}
