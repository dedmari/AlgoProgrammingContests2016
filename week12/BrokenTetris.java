package week12;
// simple approach used . No segment tree algo used
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class BrokenTetris {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 1; i <= t; i++) {
			int res = 0;
			StringBuilder sb2 = new StringBuilder();
			String[] inp1 = br.readLine().split(" ");
			int n = Integer.parseInt(inp1[0]);
			int blocks = Integer.parseInt(inp1[1]);
			int[] gameGrid = new int[n + 1];// +1 because index starts from 0 and can go to n
			for (int j = 0; j < blocks; j++) {
				int prevValue = Integer.MIN_VALUE;
				String[] inp2 = br.readLine().split(" ");
				int width = Integer.parseInt(inp2[0]);
				int height = Integer.parseInt(inp2[1]);
				int position = Integer.parseInt(inp2[2]);
				for (int k = 0; k < width; k++) {
					int newLocation = position + k;
					if (gameGrid[newLocation] > prevValue) {
						prevValue = gameGrid[newLocation];
					}
				}
				for (int k = 0; k < width; k++) {
					int nextPosition = position + k;
					gameGrid[nextPosition] = prevValue + height;
				}
				
				if (prevValue + height > res){// to check with the previous max value of result
					res = prevValue + height;
				}
				sb2.append(" ");
				sb2.append(res);
			}
			sb.append("Case #");
			sb.append(i);
			sb.append(":");
			sb.append(sb2);
			sb.append("\n");
			br.readLine();
		}
		System.out.println(sb.toString());
	}
}
