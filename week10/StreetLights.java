package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StreetLights {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 1; i <= t; i++) {
			int res = 0;
			int lgtDist = 0;
			String[] inp1 = br.readLine().split(" ");
			int l = Integer.parseInt(inp1[0]);
			int n = Integer.parseInt(inp1[1]);
			int d = Integer.parseInt(inp1[2]);
			int[] lgts = new int[n];
			inp1 = br.readLine().split(" ");
			if (n > 0) {
				for (int j = 0; j<inp1.length;j++)
						lgts[j] = Integer.parseInt(inp1[j]);
			}
			Arrays.sort(lgts);
			int tDist = 0;
			Boolean flag = false;

			for (int lk : lgts) {
				if (lk - d > tDist) {
					flag = false;
					break;
				} else {
					if (lk - d > lgtDist) {
						lgtDist = tDist;
						res++;
					}
					
					tDist = lk + d;
					if (tDist >= l) {
						res++;
						flag = true;
						break;
					}
				}
			}
			StringBuilder sb = new StringBuilder("Case #");
			sb.append(i);
			sb.append(": ");	
			if (flag) {
				sb.append(res);
			} else {
				sb.append("impossible");
			}
			System.out.println(sb.toString());
			br.readLine();
		}
	}
}
