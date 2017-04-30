package week8;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.lang.StringBuilder;

public class Fractals {

	public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
       
        for (int tc = 1; tc <= t; tc++) {
        	
        	StringBuilder sb = new StringBuilder("");
        	Map<String,String> sm=new HashMap<String,String>();
        	String inp[]=br.readLine().split(" ");
        	int n=Integer.parseInt(inp[0]);
        	int d=Integer.parseInt(inp[1]);
        	int a=Integer.parseInt(inp[2]);
        	String s=inp[3];
        	int j,k=0,l=0;
        	StringBuilder result = new StringBuilder("");
        	while(l<n)
        	{
        		inp=br.readLine().split("=>");
        		sm.put(inp[0], inp[1]);
        		l++;
        	}
        	sm.put("+","+");
        	sm.put("-","-");
        	for(j=1;j<=d;j++)
        	{
        		while(k<s.length())
        		{
        			result=result.append(sm.get(Character.valueOf(s.charAt(k)).toString()));
        			k++;
        		}
        		s=result.toString();
        		result = new StringBuilder("");
        		k=0;
        	}
        	double cX=0.0;
        	double cY=0.0;
        	char cur;
        	double pi=Math.PI/180;
        	int toR=0;
        	j=0;
            sb.append("Case #");
            sb.append(tc);
            sb.append(":\n");
            sb.append(cX);
            sb.append(" ");
            sb.append(cY);
            sb.append("\n");
        	while(j<s.length())
        	{
        		cur=s.charAt(j);
        		if(cur!='+' && cur!='-')
        		{
        			cY=cY+Math.sin(toR*pi);
        			cX=cX+Math.cos(toR*pi);
                    sb.append(cX);
                    sb.append(" ");
                    sb.append(cY);
                    sb.append("\n");
        		}
        		else
        		{
        			toR=cur == '+'?toR+a:toR-a;
        		}
        		j++;
        	}
        	System.out.print(sb.toString());
         	br.readLine();
        }
       
	}
}
