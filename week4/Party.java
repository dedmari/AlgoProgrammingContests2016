package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Queue;

public class Party{
		
public static void main(String[] args) throws NumberFormatException, IOException{	
	BufferedReader br = new BufferedReader (new InputStreamReader (System .in));
	int t = Integer.parseInt(br.readLine());
		for (int i = 1; i <= t; i++) {
			int nd = Integer.parseInt(br.readLine());
			int[][] gr = new int[nd][];
			int[] dist = new int[nd];
			int [] previous = new int[nd];
			int[] wait = new int[nd];
			Arrays.fill(dist, Integer.MAX_VALUE);
			Arrays.fill(previous, -1);
			int succ;
			for (int index1 = 0; index1 < nd; index1++) {
				String[] input1 = br.readLine().split(" ");
				wait[index1] = -1 * Integer.parseInt(input1[0]);
				succ = Integer.parseInt(input1[1]);
				gr[index1] = new int [succ];
				
				for (int j = 0; j < succ; j++)
					gr[index1][j] = Integer.parseInt(input1[j + 2]) - 1;
			}
			dist[0] = wait[0];
		    Queue<Integer>	q = new ArrayDeque<Integer>();
		    q.add(0);
			while (!q.isEmpty()) {				
				int quan = q.remove();						
				for (int cn : gr[quan]) {
					if (dist[cn] > wait[cn] + dist[quan]) {
						dist[cn] = wait[cn] + dist[quan];
						previous[cn] = quan;
						q.add(cn);
					}
				}
			}
			System.out.println("Case #"+i+": "+dist[nd - 1] * -1);					
		    br.readLine();
		}	
	}
}