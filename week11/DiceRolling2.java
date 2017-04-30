package week11;

import java.math.BigInteger;

public class DiceRolling2 {
	public static void main (String[] args) {
        findWays(20,2,15);
    }
	// The main function that returns number of ways to get sum 'x'
	// with 'n' dice and  with 'm' faces.
	public static BigInteger findWays(Integer m, int n, int sum)
	{
		int x = 6+m*n;//number of faces * number of dice = last sum possible
		System.out.println(x);
		BigInteger summation  = new BigInteger("0");
	    // Create a table to store results of subproblems.  One extra 
	    // row and column are used for simpilicity (Number of dice
	    // is directly used as row index and sum is directly used
	    // as column index).  The entries in 0th row and 0th column
	    // are never used.
		//BigInteger table[][] = new BigInteger[n+1][x+1];
		//BigInteger table[][] = new BigInteger[n+1][x+1];
		BigInteger table[][] = new BigInteger[3+1][x+1];
	    //BigInteger b = new BigInteger();
	    // Table entries for only one dice
		for(int i=0;i<=3;i++){
		//for(int i=0;i<=n;i++){
			//if(i)
			for(int j=0;j<=x;j++){
				table[i][j]=  new BigInteger("0");
			}
		}
		for (int j = 1; j <= 6 && j <= x; j++)
	  //  for (int j = 1; j <= m && j <= x; j++)
	        table[1][j] = new BigInteger("1");
	 
	    // Fill rest of the entries in table using recursive relation
	    // i: number of dice, j: sum
	    int numFaces =0;
	    //for (int i = 2; i <= n; i++)
	    for (int i = 2; i <= 3; i++)
	        for (int j = 1; j <= x; j++){
	        	if(j==1)
	        		 numFaces = 6;
	        	else 
	        		numFaces = m;
	            //for (int k = 1; k <= m && k < j; k++)
	        	for (int k = 1; k <= numFaces && k < j; k++)
	            	table[i][j]=  table[i][j].add(table[i-1][j-k]);// += table[i-1][j-k];
	        }
	    for (int i = 1; i <= n+1; i++)
	    {
	      for (int j = 1; j <= x; j++)
	        System.out.print(table[i][j]+" ");
	      System.out.println();
	    } 
	    for(int i=x;i>=sum;i--){
	    	if(table[n+1][i].equals("0")){
	    		//System.out.println("inside if");
	    		break;
	    		
	    	}
	    	summation = summation.add(table[n+1][i]); // sum of all possibilities till sum
	    }
	    BigInteger totalCombinations = new BigInteger(m.toString());// total combinations =m to the power n
	    totalCombinations = totalCombinations.pow(n).multiply(new BigInteger("6"));
	    System.out.println(totalCombinations);
	    System.out.println(summation);
	    BigInteger gcd = totalCombinations.gcd(summation);
	    BigInteger numerator = summation.divide(gcd);
	    BigInteger denominator = totalCombinations.divide(gcd);
	    System.out.println(numerator+"/"+denominator);
	    return table[n][x];
	}
}
