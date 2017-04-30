package week11;
//code inspired from http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class PotatoFarmers  {
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++)
		{	
			int n = Integer.parseInt(br.readLine());
			Farmer[] farmers = new Farmer[n];
			for(int j=0;j<n;j++){
				String[] inp = br.readLine().split(" ");
				farmers[j] = new Farmer(j,Integer.parseInt(inp[0]),Integer.parseInt(inp[1]));
			}
			Arrays.sort(farmers, new Comparator<Farmer>() {
				public int compare(Farmer o1, Farmer o2) {
					if(o1.iq < o2.iq)
						return -1;
					if(o1.iq > o2.iq)
						return 1;
					if(o1.iq == o2.iq){
						if(o1.wt > o2.wt){
							return -1;
						}
						if(o1.wt < o2.wt){
							return 1;
						}
						return 0 ;
					}						
					return 0;
				}

			});
		    sb = sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			sb.append(lisFarm( farmers, n ));
			sb.append("\n");
			br.readLine();
		}
		System.out.println(sb.toString() );
	}
	static int lisFarm(Farmer[]arr,int n)
	{
		int lis[] = new int[n];
		int i,j,max=1;//max = 0;
		//for ( i = 0; i < n; i++ )
		//	lis[i] = 1;
		for ( i = 1; i < n; i++ )
			for ( j = 0; j < i; j++ ) 
				if ( ((arr[i].iq > arr[j].iq && arr[i].wt < arr[j].wt) || (arr[i].iq == arr[j].iq && (arr[i].wt != arr[j].wt ) ))) {
					if(lis[i] < lis[j] + 1){
						//System.out.println(arr[i].iq+" "+arr[i].wt+"   "+arr[j].iq+" "+arr[j].wt);
						if(lis[j]==0)
							lis[j]=1;
						lis[i] = lis[j] + 1;
						if(max < lis[i]){
							//System.out.println(lis[i]);
							max = lis[i];
						}
					}
				}
		
//		for ( i = 0; i < n; i++ )
//			if ( max < lis[i] )
//				max = lis[i];
		return max;
	}
}
class Farmer{
	int id;
	int wt;
	int iq;
	Farmer(int id, int pf,int wt) {
		super();
		this.id =id;
		this.wt = wt;
		this.iq = pf;
	}	
	Farmer(){
		super();
	}
}