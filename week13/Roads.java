package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
public class Roads {

	static class City{
		int no;
		City parent;
		int noOfConnections;
	City(int i)
	{
		this.no=i;
		this.parent=this;
		this.noOfConnections=1;
	}
	
}
	static class Edge implements Comparator<Edge>{
		public final City a;
		public final City b;
		public final int length;
		public final int lane;
		Edge parent;
		public Edge(City a,City b,int length,int lane)
		{
			this.a=a;
			this.b=b;
			this.length=length;
			this.lane=lane;
			this.parent=this;
		}
		@Override
		public int compare(Edge arg0, Edge arg1) {
			// TODO Auto-generated method stub
			return (arg1.length*arg1.lane)-(arg0.length*arg0.lane);
		}
		
		
	}
	public static City findParent(City tree)
	{
		City temp;
		if(tree == tree.parent)
			return tree;
		else
		{
			temp= findParent(tree.parent);
			tree.parent=temp;
			return temp;
			//return find(tree.parent);
		}
	}
	public static void union(City t1,City t2)
	{
		if(t1.no == t2.no)
		{}
		else
		{
			t2.parent=t1;
			t1.noOfConnections+=t2.noOfConnections;
		}
	}
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int noOfCases = Integer.parseInt(br.readLine());
		for(int i=1;i<=noOfCases;i++)
		{
			String output="";
			int k,l;
			int totalCapacity=0;
			String input[]=br.readLine().split(" ");
			int cities=Integer.valueOf(input[0]);
			ArrayList<City> cityList=new ArrayList<City>();
			ArrayList<Edge> edgeList=new ArrayList<Edge>();
			ArrayList<Edge> mst=new ArrayList<Edge>();
			int roads=Integer.valueOf(input[1]);
			for(k=1;k<=cities;k++)
			{
				cityList.add(new City(k));
			}
			for(l=1;l<=roads;l++)
			{
				String rd[]=br.readLine().split(" ");
				int city1=Integer.valueOf(rd[0]);
				int city2=Integer.valueOf(rd[1]);
				int length=Integer.valueOf(rd[2]);
				int lane=Integer.valueOf(rd[3]);
				edgeList.add(new Edge(cityList.get(city1-1),cityList.get(city2-1),length,lane));
			}
			edgeList.sort(new Edge(new City(-1),new City(-2),1,1));
			for(int m=0;m<edgeList.size();m++)
			{
				City c1=findParent(edgeList.get(m).a);
				City c2=findParent(edgeList.get(m).b);
				if(c1!=c2)
				{
					mst.add(edgeList.get(m));
					union(c1,c2);
				}
				totalCapacity=totalCapacity+(edgeList.get(m).lane*edgeList.get(m).length);
			}
			if(findParent(edgeList.get(0).a).noOfConnections<cityList.size())
					{
						output="impossible";
					}
			int mstCapacity=0;
			for(Edge e:mst)
			{
				mstCapacity=mstCapacity+(e.lane*e.length);
			}
			int out=totalCapacity-mstCapacity;
			output=!output.equals("impossible")?Integer.valueOf(out).toString():output;
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			sb.append(output);
			System.out.println(sb);
			br.readLine();
			
	}
}
}

