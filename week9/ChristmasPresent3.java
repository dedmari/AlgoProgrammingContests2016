package week9;
//code inspired from http://www.geeksforgeeks.org/maximum-bipartite-matching/
import java.io.*;

class ChristmasPresent3
{
	// M is number of friends and N is number of presents
	int  M = 2;
	int  N = 2;
	public ChristmasPresent3(int m, int n){
		this.M=m;
		this.N=n;
	}
		public static void main (String[] args) throws java.lang.Exception
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int t = Integer.parseInt(br.readLine());
			for (int tc = 1; tc <= t; tc++) {
				String[] inp1 = br.readLine().split(" ");
				int n = Integer.parseInt(inp1[0]);//friends
				int m = Integer.parseInt(inp1[1]);//presents
				boolean[][] bpGraph = new boolean[n][m] ;
				boolean emptyPreference = false;
				int max=0;
				for(int i=0;i<n;i++){
					String[] inp2 = br.readLine().split(",");
					if(!(inp2[0].equals(""))){
						for(int j=0; j<inp2.length; j++){
							String[] option = inp2[j].split("-");
							if(option.length == 1 ){
								bpGraph[i][(Integer.parseInt(inp2[j])-1)]=true;
							}
							else{
								for(int k=Integer.parseInt(option[0]); k<=Integer.parseInt(option[1]); k++)	
									bpGraph[i][k-1]=true;
							}

						}
					}
					else{ // one of the friend has no preference, hence criteria not satisfied
						emptyPreference = true;
					}
				}
				if(!emptyPreference && n<=m){
					ChristmasPresent3 res = new ChristmasPresent3(n,m);
					max=res.maxBPM(bpGraph);
				}
				StringBuilder sb = new StringBuilder("Case #");
				sb.append(tc);
				sb.append(": ");
				if(emptyPreference || n > m || max < n){
					sb.append("no");
				}
				else{
					sb.append("yes");
				}
				System.out.println(sb.toString());
				br.readLine();
			}
		}
	boolean bpm(boolean bpGraph[][], int u, boolean seen[],
				int matchR[])
	{
		// Try every job one by one
		for (int v = 0; v < N; v++)
		{
			if (bpGraph[u][v] && !seen[v])
			{
				seen[v] = true;
				if (matchR[v] < 0 || bpm(bpGraph, matchR[v],
										seen, matchR))
				{
					matchR[v] = u;
					return true;
				}
			}
		}
		return false;
	}

	int maxBPM(boolean bpGraph[][])
	{
		int matchR[] = new int[N];
		for(int i=0; i<N; ++i)
			matchR[i] = -1;

		int result = 0; 
		for (int u = 0; u < M; u++)
		{
			boolean seen[] =new boolean[N] ;
			for(int i=0; i<N; ++i)
				seen[i] = false;
			if (bpm(bpGraph, u, seen, matchR))
				result++;
		}
		return result;
	}
}

