package week11;

//code inspired from http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class LIS  {
	public static void main(String[] args) throws Exception{
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
			System.out.println("Case #"+i+": " + LongestIncreasingSubsequenceLength( farmers, n ) );
			br.readLine();
		}
	}
	static int LongestIncreasingSubsequenceLength(Farmer[] A, int size)
    {
        // Add boundary case, when array size is one
 
		Farmer[] tailTable   = new Farmer[size];
		for(int i=0;i<size;i++){
			tailTable[i] = A[0];
		}
        int len; // always points empty slot
 
        tailTable[0] = A[0];
        len = 1;
        for (int i = 1; i < size; i++)
        {
            if (A[i].iq < tailTable[0].iq && A[i].wt >tailTable[i].wt)
                // new smallest value
                tailTable[0] = A[i];
 
            else if (A[i].iq >= tailTable[len-1].iq && A[i].wt <tailTable[i].wt)
                // A[i] wants to extend largest subsequence
                tailTable[len++] = A[i];
 
            else
                // A[i] wants to be current end candidate of an existing
                // subsequence. It will replace ceil value in tailTable
                tailTable[CeilIndex(tailTable, -1, len-1, A[i])] = A[i];
        }
 
        return len;
    }
	static int CeilIndex(Farmer A[], int l, int r, Farmer key)
    {
        while (r - l > 1)
        {
            int m = l + (r - l)/2;
            if (A[m].iq >= key.iq && A[m].wt <key.wt)
                r = m;
            else
                l = m;
        }
 
        return r;
    }
}
//class Farmer{
//	int id;
//	int wt;
//	int iq;
//	Farmer(int id, int pf,int wt) {
//		super();
//		this.id =id;
//		this.wt = wt;
//		this.iq = pf;
//	}	
//}