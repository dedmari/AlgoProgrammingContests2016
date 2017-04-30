package week6;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Trapped {
	static int cLen, rLen;
	static int[][] vis;
	static Point start;
	static int tc;
	public static void main(String[] args) throws Exception {
		InputStreamReader r = new InputStreamReader ( System.in );
		BufferedReader br = new BufferedReader ( r );
		int t = Integer.parseInt(br.readLine());
		String input;
		String[] inp;
		char field;
		for (int i = 1; i <= t; i++) {
			inp = br.readLine().split(" ");
			cLen = Integer.parseInt(inp[0]);
			rLen = Integer.parseInt(inp[1]);
			tc = 0;
			vis = new int[rLen][cLen];
			for (int j = 0; j < rLen; j++) {
				input = br.readLine();
				for (int k = 0; k < cLen; k++) {
					field = input.charAt(k);
					if (field == '#')
						vis[j][k] = 2;
					else if (field == 'L') {
						start = new Point(k, j);
					} else if (field == 'T') {
						vis[j][k] = 1;
						tc++;
					}
				}
			}
			StringBuilder sb = new StringBuilder(12);
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			
			if (isPath(start.x, start.y, vis, 0))
				sb.append("yes");
			else
				sb.append("no");
			System.out.println(sb);
			br.readLine();
		}
	}
	
	public static boolean isPath(int x, int y, int[][] cv, int gt) {
		boolean isTool = false;
		if (cv[y][x] == 1) {
			gt++;
			isTool = true;
		}
		if (tc == gt)
			return true;
		cv[y][x] = 3;
		if (x - 1 >= 0 && (cv[y][x - 1] <= 1) && isPath(x - 1, y, cv, gt))
			return true;
		if (x + 1 < cLen && (cv[y][x + 1] <= 1) && isPath(x + 1, y, cv, gt))
			return true;
		if (y - 1 >= 0 && (cv[y - 1][x] <= 1) && isPath(x, y - 1, cv, gt))
			return true;
		if (y + 1 < rLen && (cv[y + 1][x] <= 1) && isPath(x, y + 1, cv, gt))
			return true;
		if (isTool)
			cv[y][x] = 1;
		else
			cv[y][x] = 0;
		
		return false;
	}
	static class CoOrdinate {
		int x;
		int y;

		public CoOrdinate(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
