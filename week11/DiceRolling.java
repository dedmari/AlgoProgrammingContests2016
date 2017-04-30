package week11;

import java.io.*;

class DiceRolling {
    public static int noOfWays(int dices, int sumX)
    {
    int table[][] = new int[dices+1][sumX+1];
 
    table[1][1] = 1;
    // Fill rest of the table using equation we built
    for (int i = 2; i <= dices; i++)
        for (int j = 1; j <= sumX; j++)
            for (int k = 1; k <= i && k<=j ; k++)
                table[i][j] += table[i-1][j-k];
 
        //Uncomment these lines to see content of table
    /* Uncomment these lines to see content of table
        for (int[] arr : table) {
            System.out.println(Arrays.toString(arr));
        }
        */
    return table[dices][sumX];
}
     
     
    public static void main (String[] args) {
        System.out.println(noOfWays(4,7));
    }
}