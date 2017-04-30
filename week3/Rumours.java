package week3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Rumours {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int x = 1; x <= t; x++) 
		{

			int n=Integer.parseInt(br.readLine());
			PriorityQueue<Integer> pq=new PriorityQueue<Integer>(new Comparator<Integer>(){
				public int compare(Integer arg0, Integer arg1) {
					return arg1-arg0;
				}

			});
			int inp[][]=new int[n][n];
			String input[]=null;
			int i,j;
			for(i=0;i<n;i++)
			{
				input=br.readLine().split(" ");
				for(j=0;j<n;j++)
				{
					inp[j][i]=Integer.valueOf(input[j]);
				}
			}
			int trust[]=new int[n];
			pq.add(0);
			trust[0] = 0;
			while (!pq.isEmpty()) {
				int next = pq.poll();
				for (i=0; i<n; i++) {
					if (trust[i] < inp[next][i]) {
						trust[i] = inp[next][i];
						pq.add(i);
					}
				}
			}
			int sum=0;
			for(i=1;i<n;i++)
			{
				sum=sum+trust[i];
			}
			System.out.println("Case #"+x+": "+sum);
			br.readLine();
		}
	}
}

