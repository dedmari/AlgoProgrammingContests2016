package week5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Calendar {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) 
		{
			String s=br.readLine();
			Character c;
			int x0,num;
			if(s.length()==1)
			{
				num=Integer.valueOf(s);
				num=num%13;
			}
			else
			{
				Character z=s.charAt(0);
				num=Integer.valueOf(z.toString());
				for(int i=1;i<s.length();i++)
				{
					c=s.charAt(i);
					x0=Integer.valueOf(c.toString());
					num=((num*8)+x0)%13;
				}
			}
			num=(num+3)%13;
			num=num == 0?13:num;
			System.out.println("Case #"+tc+": "+num);
		}
	}
}

