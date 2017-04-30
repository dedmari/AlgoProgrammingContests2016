package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author rayo
 *
 */
public class BrokenTetris2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int t = 1; t <= cases; t++) {
			String result = "";
			StringBuilder sb = new StringBuilder();

			String[] input1 = br.readLine().split(" ");

			/**
			 * The first line of the input contains an integer t. t test cases
			 * follow, each of them separated by a blank line. Each test case
			 * begins with two integers n and k. n is the width of the playing
			 * field, k is the total number of blocks to spawn. k lines follow
			 * describing the blocks. Each line contains three integers w, h and
			 * p. w and h give the width and height of the block, p the offset
			 * from the left side of the playing field, i.e. p = 0 means that
			 * the block spawns at the leftmost position of the playing field.
			 * 
			 */
			int n = Integer.parseInt(input1[0]);
			int[] playingField = new int[n + 1];
			int k = Integer.parseInt(input1[1]);
            int output = 0;
			for (int index1 = 0; index1 < k; index1++) {
				int tempValue = Integer.MIN_VALUE;

				String[] input2 = br.readLine()
						.split(" ");

				int w = Integer.parseInt(input2[0]);
				int h = Integer.parseInt(input2[1]);
				// offset
				int p = Integer.parseInt(input2[2]);

				for (int index2 = 0; index2 < w; index2++) {

					int currentPosition = p + index2;

					if (playingField[currentPosition] > tempValue) {
						// set new temp value
						tempValue = playingField[currentPosition];
					}
				}
				for (int index3 = 0; index3 < w; index3++) {

					int nextPosition = p + index3;
                    // adding h to the new position
					playingField[nextPosition] = tempValue + h;
				}
				if (tempValue + h > output)
					output = tempValue + h;

				sb.append(" ");
				sb.append(output);
			}

			/**
			 * For each test case output one line containing Case #i: r1 rk
			 * where i is its number, starting at 1, and rj is the maximum
			 * height of the stacked blocks on the playing field after the j-th
			 * block has landed.
			 * 
			 */
			System.out.println("Case #" + t + ":" + sb.toString());

			br.readLine();

		}
	}
}
