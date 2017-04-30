package week4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class SnakeLadder3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(r);
		int t = Integer.parseInt(br.readLine());

		for (int i = 1; i <= t; i++) {

			String line = br.readLine();
			String[] inp1 = line.split(" ");
			int nd = Integer.parseInt(inp1[0]);
			int snakes = Integer.parseInt(inp1[1]);
			int ladders = Integer.parseInt(inp1[2]);
			int[] snake = new int[nd + 1];
			int[] ladder = new int[nd + 1];
			for (int index1 = 0; index1 < snakes; index1++) {
				inp1 = br.readLine().split(" ");
				snake[Integer.parseInt(inp1[0])] = Integer.parseInt(inp1[1]);
			}
			for (int index2 = 0; index2 < ladders; index2++) {
				line = br.readLine();
				inp1 = line.split(" ");
				ladder[Integer.parseInt(inp1[0])] = Integer.parseInt(inp1[1]);
			}
			int mVal = -1;
			int nxtVal = -1;
			boolean[] result = null;
			for (int dRoll = 1; dRoll <= 6; dRoll++) {
				nxtVal = newMove(dRoll, nd, snake, ladder);
				if (nxtVal == -1)
					continue;
				if (mVal == -1 || nxtVal < mVal) {
					mVal = nxtVal;
					result = new boolean[7];
					result[dRoll] = true;
				} else if (nxtVal == mVal)
					result[dRoll] = true;
			}
			if (result != null) {
				String res = "";
				for (int index = 1; index <= 6; index++){
					if (result[index]){
						res += index+ " ";
					}
				}
				System.out.println("Case #"+i+": "+res);			

				
			} else
				System.out.println("Case #"+i+": impossible ");					

			br.readLine();
		}

	}
	public static int newMove(int step, int n, int[] snake, int[] ladder) {
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
		dq.add(1);
		int[] cost = new int[n + 7];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[1] = 0;
		int node = 0;
		int value = Integer.MAX_VALUE;
		while (!dq.isEmpty()) {
			node = dq.remove();
			if (node >= n) {
				if (value > cost[node])
					value = cost[node];
				continue;
			}
			if (snake[node] > 0) {
				if (cost[snake[node]] > cost[node]) {
					dq.addFirst(snake[node]);
					cost[snake[node]] = cost[node];
				}
				continue;	
			} else if (ladder[node] > 0 && cost[ladder[node]] > cost[node]) {
				dq.addFirst(ladder[node]);
				cost[ladder[node]] = cost[node];
			}

			if (cost[node + step] > cost[node] + 1) {
				cost[node + step] = cost[node] + 1;
				dq.add(node + step);
			}
		}

		return value == Integer.MAX_VALUE ? -1 : value;
	}
}