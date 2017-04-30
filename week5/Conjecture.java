package week5;
// inspired from http://introcs.cs.princeton.edu/java/14array/PrimeSieve.java.html
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Conjecture {
	public static void main(String[] args)  throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int[] nums = new int[t];
		int max=0;
		for(int i=0;i<t;i++)
		{
			nums[i] = Integer.parseInt(br.readLine());
			if(max<nums[i])
				max=nums[i];
		}
		boolean[] isPrime = new boolean[max+1];
		for (int i = 2; i <= max; i++) {
			isPrime[i] = true;
		}
		for (int factor = 2; factor*factor <= max; factor++) {
			if (isPrime[factor]) {
				for (int j = factor; factor*j <= max; j++) {
					isPrime[factor*j] = false;
				}
			}
		}
		int a=0,b=0,c=0;
		for(int i=0;i<t;i++){
			if(nums[i]%2==0){
				int j=2;
				int k=nums[i]-2;
				while(true){
					if(isPrime[j] && isPrime[k]){
						a=j;
						b=k;
						break;
					}
					else{
						j++;
						k--;
					}
				}
			}
			else{
				if(isPrime[nums[i]-4]){
					a=b=2;
					c=nums[i]-4;
				}
				else{
					a=3;
					int j=2;
					int k=nums[i]-5;
					while(true){
						if(isPrime[j] && isPrime[k]){
							b=j;
							c=k;
							break;
						}
						else{
							j++;
							k--;
						}
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i+1);
			sb.append(": ");
			sb.append(a);
			sb.append(" ");
			sb.append(b);
			if (c!=0){
				sb.append(" ");
				sb.append(c);
			}
			System.out.println(sb);
			a=b=c=0;
		}
	}
}