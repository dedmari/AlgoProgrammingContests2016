package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;

public class Customs {
	static int n;
	static int[][] ways;
	static Integer[] sortedWays;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());	
		for (int i = 1; i <= t; i++) {
			String[] inp1 = br.readLine().split(" ");
			n = Integer.parseInt(inp1[0]);	
			ways = new int[n][];
			sortedWays = new Integer[n];
			for (int j = 0; j < n; j++) {
				sortedWays[j] = j;
				inp1 = br.readLine().split(" ");
				ways[j] = new int[Integer.parseInt(inp1[0])];	
				for (int k = 0; k < ways[j].length; k++)
					ways[j][k] = Integer.parseInt(inp1[k + 1]) - 1;
			}				
			Arrays.sort(sortedWays, (a, b) -> Integer.compare(ways[b].length, ways[a].length));		
			TreeSet<Integer> citySet = new TreeSet<Integer>();	
			int[] roads = new int[n];
			for (int j = 0; j < n; j++) {
				int city = sortedWays[j];
				int outgoing = 0;
				for (int toCity : ways[city]) {
					if (!citySet.contains(toCity))
						outgoing++;
				}
				if (outgoing > roads[city]) {
					roads[city] = 0;
					for (int toCity : ways[city]) {
						if (!citySet.contains(toCity))
							roads[toCity]++;
					}
					citySet.add(city);
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i);
			sb.append(": \n");
			for (Integer j : citySet)
				sb.append((j + 1) + " ");
			System.out.println(sb.toString());	
			br.readLine();
		}
	}
}
