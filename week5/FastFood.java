package week5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
inspired from http://www.geeksforgeeks.org/compute-ncr-p-set-2-lucas-theorem/
http://fishi.devtail.com/weblog/2015/06/25/computing-large-binomial-coefficients-modulo-prime-non-prime/
*/
public class FastFood {
	public static List<Integer> primeFactors(long number) {
		
		        List<Integer> primefactors = new ArrayList<Integer>();
		        long copyOfInput = number;
		        for (int i= 2; i <= copyOfInput; i++) {
		            if (copyOfInput % i == 0) {
		                primefactors.add(i);
		                copyOfInput /= i;
		                i--;
		            }
		        }
		        if(number == 1)
		        {
		        	primefactors.add(1);
		        }
		        return primefactors;
		    }
	
	public static int gcd(int a, int b){
	    if (a<b) return gcd(b,a);
	    if (a%b==0) return b;
	    else return gcd(b, a%b);
	}
	
	public static int lcm(int a, int b){
	    return ((a*b)/gcd(a,b));

	}
	
	public static int lcmofarray(int[] arr, int start, int end){
	    if ((end-start)==1) return lcm(arr[start],arr[end-1]);
	    else return (lcm (arr[start], lcmofarray(arr, start+1, end)));
	}
	
	public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= cases; t++) 
        
        {
          int k=Integer.parseInt(br.readLine());	
          String[] inp = br.readLine().split(" ");
          int mod[]=new int[k];
          List<Integer> primefactors = new ArrayList<Integer>();
          List<Integer> mSet=new ArrayList<Integer>();
          int freq[]=new int[k];
          int add;
          for(int i=0;i<k;i++)
          {
        	  mod[i]=Integer.valueOf(inp[i]);
        	  primefactors=primeFactors(mod[i]);
        	  Iterator<Integer> it=primefactors.iterator();
        	  while(it.hasNext())
        	  {
        		  add=it.next();
        		  if(!mSet.contains(add))
        		  {
        			  mSet.add(add);
        			  freq[i]++;
        		  }
        		  
        	  }
          }
          int b=lcmofarray(mod,0,k);
          int size=mSet.size();
          int mods[]=new int[size];
          Iterator<Integer> it=mSet.iterator();
          int j=0;
    	  while(it.hasNext())
    	  {
    		  mods[j]=it.next();
    		  j++;
    	  }
          
          int[] constraints = new int[size];
          constraints[0]=0;
          for(int i=0,l=0;i<k;i++)
          {
        	  for(j=0;j<freq[i];j++)
        	  {
        	  constraints[l]=mods[l]-i;
        	  l++;
        	  }
          }
          for(j=0;j<freq[0];j++)
          {
        	  constraints[0]=0;
          }
          Long x=(long) 0;
          int M = 1;
          for(int i=0;i<size;i++)
          {
                M*=mods[i];
                System.out.println("M*=mods[i]: "+M);
          }

          int[] multInv = new int[constraints.length];

         
           
            for(int i=0;i<multInv.length;i++)
            {
                multInv[i]=euclidean(M/mods[i],mods[i])[0];
                System.out.println("multInv[i]: "+multInv[i]);
            }

            for(int i=0;i<mods.length;i++)
            {
                x+=M/mods[i]*constraints[i]*multInv[i];
                System.out.println("value of x: "+x);
            }
            System.out.println("value of x outside loop: "+x);
            System.out.println("value of M outside loop: "+M);
            x=leastPosEquiv(x,M);
            String l1Str = Long.toUnsignedString(x);
            
            System.out.println("Case #"+t+": "+l1Str+" "+b);
            br.readLine();

        }
    }

    public static int nCrModpDP(int n,int r, int p) {
        int[] c = new int[r + 1];

        c[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, r); j > 0; j--)
                c[j] = (c[j] + c[j - 1]) % p;

        }
        return c[r];
    }

    public static int nCrModPLucas(int n, int r, int p){
        if(r==0)
            return 1;

        int ni=n%p, ri = r%p;

        return (nCrModPLucas(n/p,r/p,p)*nCrModpDP(ni,ri,p))%p;
    }
    //https://github.com/GregOwen/Chinese-Remainder-Theorem/blob/master/CRT.java
    public static int[] euclidean(int a, int b)
    {
        if(b > a)
        {
            int[] coeffs = euclidean(b, a);
            int[] output = {coeffs[1], coeffs[0]};
            return output;
        }

        int q = a/b;
        int r = a -q*b;

        if(r == 0)
        {
            int[] output = {0, 1};
            return output;
        }

        int[] next = euclidean(b, r);

        int[] output = {next[1], next[0] - q*next[1]};
        return output;
    }

    public static long leastPosEquiv(long a, long m)
    {

        if(m < 0)
            return leastPosEquiv(a, -1*m);

        if(a >= 0 && a < m)
            return a;


        if(a < 0)
            return -1*leastPosEquiv(-1*a, m) + m;

        long q = a/m;

        return a - q*m;
    }

}

