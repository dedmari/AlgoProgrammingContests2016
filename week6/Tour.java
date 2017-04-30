package week6;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Tour {
	public static void main(String args[]) throws NumberFormatException, IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++)
		{
			int pthSum=100000;
			int pth=0;
			int j,k=1,l,r;
			int n=Integer.parseInt(br.readLine());
			int arr[]=new int[n-1];
			int resPath[]=new int[n-1];
			int mat[][]=new int[n][n];
			for(j=0;j<n;j++)
			{
				String input[]=br.readLine().split(" ");
				for(k=0;k<n;k++)
				{
					mat[j][k]=Integer.parseInt(input[k]);
				}
			}
			if(n>1)
			{
				for(l=0;l<n-1;l++)
					arr[l]=l+1;
			}
			do { 
				pth=mat[0][arr[0]];
				for(r=0;r<n-2;r++)
				{
					pth=pth+mat[arr[r]][arr[r+1]];
				}
				pth=pth+mat[arr[n-2]][0];
				if(pth<pthSum)
				{
					pthSum=pth;
					resPath=arr.clone();
				}
			} while (perm(arr));
			String res="1 ";
			for(k=0;k<n-1;k++)
			{
				Integer x=resPath[k]+1;
				res=res.concat(x.toString()+" ");
			}
			System.out.println("Case #"+i+": "+res);
			br.readLine();
		}
	}
	static boolean perm(int[] arr) {
		int i = arr.length - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			i--;
		if (i <= 0)
			return false;
		int j = arr.length - 1;
		while (arr[j] <= arr[i - 1])
			j--;
		int temp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = temp;
		j = arr.length - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
		return true;
	}
}

