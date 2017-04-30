package week11;
//code inspired at http://www.geeksforgeeks.org/dynamic-programming-set-7-cTemp-change/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MakingChange {
	public static int[] minChange(int[] denom, int totalAmount)
    {
        int[] at = new int[totalAmount + 1];
        int n = denom.length;
        int[] table = new int[totalAmount + 1];
        table[0] = 1;
        for (int i = 0 ; i < totalAmount; i++)
            if (table[i] > 0)
                for (int j = 0; j < n; j++)
                {
                    int sub_sol = i + denom[j];
                    if (sub_sol <= totalAmount)
                    {
                        if (table[sub_sol] == 0 || table[sub_sol] > table[i] + 1)
                        {
                            table[sub_sol] = table[i] + 1;
                            at[sub_sol] = j;
                        }
                    }
                }
//        if (table[totalAmount] == 0)
//            return null;
        int[] result = new int[table[totalAmount] - 1];
        int k = totalAmount;
        while (k > 0)
        {
            result[table[k] - 2] = denom[at[k]];
            k = k - denom[at[k]];
        }
        return result;
    }
	public static void main(String args[]) throws Exception
	{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	int t=Integer.parseInt(br.readLine());
	for(int i=1;i<=t;i++)
	{	
		String inp1[]=br.readLine().split(" ");
		int n=Integer.parseInt(inp1[0]);
		int c=Integer.parseInt(inp1[1]);
		String cTemp[]=br.readLine().split(" ");
		List<Integer>denomList=new ArrayList<Integer>();
		int denomArray[]=new int[n];
		
		int j;
		for(j=0;j<cTemp.length;j++)
		{
			denomArray[j]=(Integer.parseInt(cTemp[j]));
			denomList.add(Integer.parseInt(cTemp[j]));
		}
		int[] freq=minChange(denomArray,c);
		int table[]=new int[n];
		for(j=0;j<freq.length;j++)
		{
			int index=denomList.indexOf(freq[j]);
			table[index]++;
		}
		String output="";
		for(j=0;j<cTemp.length;j++)
		{
			output=output+ " "+table[j];
		}
		System.out.println("Case #"+i+": "+output);
		br.readLine();
	}
}
}
