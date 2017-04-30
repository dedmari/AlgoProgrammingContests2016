package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collector;

import static java.util.stream.Collectors.*;

public class WaterTemple {
	static class Crooms implements Comparator<Crooms>
	{
		int num;
		boolean hasWay;
		Integer d;
		Crooms(int num,int d,boolean has)
		{
			this.num=num;
			this.d=d;
			this.hasWay=has;
		}
		public int compare(Crooms a, Crooms b) {
			return a.d-b.d;
		}
	}
	public static Crooms getRoom(int num,List<Crooms>cRooms)
	{
		for(Crooms n:cRooms)
		{
			if(n.num==num)
				return n;
		}
		return null;
	}
	static class Halls implements Comparator<Halls>,  Cloneable
	{
		boolean posRoute;
		int from;
		int to;
		int level;
		Halls(int from,int to,int level,int currentlevel)
		{
			this.from=from;
			this.to=to;
			this.level=level;
			if(level>=currentlevel)
			{
				posRoute=true;
			}
			else
			{
				posRoute=false;
			}
		}
		public int compare(Halls a, Halls b) {
			return b.level-a.level;
		}
	}
	public static void main(String args[]) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++)
		{
			String input[]=br.readLine().split(" ");
			String inp[]=null;
			int n=Integer.parseInt(input[0]);
			int m=Integer.parseInt(input[1]);
			int k=Integer.parseInt(input[2]);
			int l=Integer.parseInt(input[3]);
			int maxWLevel=l;int from,to,level;
			int a,b,d,ai;
			Halls h;
			Set<Integer> reachableRooms=new HashSet<Integer>();
			List<Halls> notRechableHalls=new ArrayList<Halls>();
			List<Crooms> cRooms=new ArrayList<Crooms>();
			for(int j=1;j<=m;j++)
			{
				inp=br.readLine().split(" ");
				from=Integer.parseInt(inp[0]);
				to=Integer.parseInt(inp[1]);
				level=Integer.parseInt(inp[2]);
				h=new Halls(from,to,level,maxWLevel);
				if(h.posRoute == true)
				{
					for(a=from;a<=to;a++)
					{
						reachableRooms.add(a);
					}
				}
				else
				{
					notRechableHalls.add(h);
				}
			}
			int minD=Integer.MAX_VALUE;
			for(b=1;b<=k;b++)
			{
				inp=br.readLine().split(" ");
				ai=Integer.parseInt(inp[0]);
				d=Integer.parseInt(inp[1]);
				if(reachableRooms.contains(ai))
				{

					minD=minD>d?d:minD;
				}
				else
				{
					cRooms.add(new Crooms(ai,d,false));
				}

			}
			Collections.sort(notRechableHalls, new Halls(1,2,3,4));
			Collections.sort(cRooms,new Crooms(0,0,false));

			List<Halls> tmp=notRechableHalls;
			boolean levelLess = false;
			Crooms crms;
			boolean flag=false;
			if(reachableRooms.size()==n)
			{
				flag=true;
			}
			else
			{
				while(maxWLevel>=minD)
				{
					for(Halls hall:tmp)
					{
						if(hall.level>=maxWLevel)
						{
							notRechableHalls.remove(hall);
							hall.posRoute=true;
							for(a=hall.from;a<=hall.to;a++)
							{
								reachableRooms.add(a);
								crms=getRoom(a,cRooms);
								if(crms!=null)
								{
									minD=minD>crms.d?crms.d:minD;
									cRooms.remove(crms);
								}
							}
						}
						else
						{
							tmp=notRechableHalls;
							maxWLevel=hall.level;
							break;
						}

					}
					if(reachableRooms.size()==n)
					{
						flag=true;
						break;
					}

				}
			}
			System.out.println("Case #"+i+": "+(flag ? maxWLevel:"impossible"));
			br.readLine();
		}
	}
	private static Collector toCollection(Halls halls) {
		// TODO Auto-generated method stub
		return null;
	}
}

