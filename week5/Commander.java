package week5;
//gcd using Euclidean Algo
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Commander {
	public static void main(String args[]) throws Exception
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++)
		{
			int n,j;
			n=Integer.parseInt(br.readLine());
			String inp[]=br.readLine().split(" ");
			int arr[]=new int[n];
			for(j=0;j<n;j++)
			{
				arr[j]=Integer.parseInt(inp[j]);
			}
			int res=cal(arr);
			System.out.println("Case #"+i+": "+res);
			br.readLine();
		}
	}
	private static int cal(int[] cas)
	{
		int res = cas[0];

		for(int i = 1; i < cas.length; i++) 
		{
			res = gcd(res, cas[i]);
		}
		return res;
	}
	private static int gcd(Integer a, Integer b)
	{
		while (b > 0)
		{
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}
}

