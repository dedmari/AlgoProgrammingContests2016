package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
public class PortableDevice {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for (int i = 1; i <= t; i++) {
			String[] input1 = br.readLine().split(" ");
			int w = Integer.parseInt(input1[0]);
			int h = Integer.parseInt(input1[1]);
			boolean[][] matrix = new boolean[h][w];
			int[][][] dMatrix = new int[h][w][4];
			PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> Integer.compare(a.cost, b.cost));
			Node strt= null;
			Node end = null;
			for (int index1 = 0; index1 < h; index1++) {
				String line = br.readLine();

				for (int j = 0; j < w; j++) {
					if (line.charAt(j) == '*') {
						strt= new Node(index1, j, 1, 0);
						matrix[index1][j] = true;
						dMatrix[index1][j][0] = 1;
						continue;
					}
					matrix[index1][j] = line.charAt(j) == '_';

					if ((index1 == 0 || index1 == h - 1 || j == 0 || j == w - 1) && matrix[index1][j])
						end = new Node(index1, j, Integer.MAX_VALUE, 0);
				}
			}
			// initialize the queue
			pq.add(strt);

			Node nd = null;
			Node nNode = null;
			int jCount = -1;
			int cost = 0;
			int src = 0;
			int dest = 0;
			boolean ntIn = false;
			while (!pq.isEmpty()) {
				nd = pq.remove();
				if (nd.src == end.src && nd.dest == end.dest) {
					ntIn = true;
					break;
				}

				cost = nd.cost + 1;
				{
					src = nd.src;
					dest = nd.dest - 1;
					if (dest >= 0 && matrix[src][dest]) {
						if ((dMatrix[src][dest][nd.val] == 0
								|| cost < dMatrix[src][dest][nd.val])
								&& (nd.val == 0 || ((dMatrix[src][dest][nd.val - 1] == 0
										|| cost < dMatrix[src][dest][nd.val - 1])
										&& (nd.val == 1
												|| ((dMatrix[src][dest][nd.val - 2] == 0
														|| cost < dMatrix[src][dest][nd.val - 2])
														&& (nd.val == 2
																|| dMatrix[src][dest][nd.val
																		- 3] == 0
																|| cost < dMatrix[src][dest][nd.val
																		- 3])))))) {
							dMatrix[src][dest][nd.val] = cost;

							nNode = new Node(src, dest, cost, nd.val);
							nNode.jPos = nd.jPos;
							pq.add(nNode);
						}

					} else if (nd.val < 3) {
						jCount = -2;
						dest = -1;
						if (nd.dest + jCount >= 0
								&& matrix[nd.src][nd.dest + jCount]) {
							dest = nd.dest + jCount;
						}
						if (dest != -1
								&& (dMatrix[src][dest][nd.val] == 0
										|| cost < dMatrix[src][dest][nd.val])
								&& (nd.val == 0 || ((dMatrix[src][dest][nd.val - 1] == 0
										|| cost < dMatrix[src][dest][nd.val - 1])
										&& (nd.val == 1
												|| ((dMatrix[src][dest][nd.val - 2] == 0
														|| cost < dMatrix[src][dest][nd.val - 2])
														&& (nd.val == 2
																|| dMatrix[src][dest][nd.val
																		- 3] == 0
																|| cost < dMatrix[src][dest][nd.val
																		- 3])))))) {
							nNode = new Node(src, dest, cost, nd.val + 1);
							nNode.jPos = new Node(nd.src, nd.dest - 1, nd.cost, nd.val);
							nNode.jPos.jPos = nd.jPos;
							pq.add(nNode);

							dMatrix[nNode.src][nNode.dest][nNode.val] = nNode.cost;
						}
					}
				}
				{
					src = nd.src;
					dest = nd.dest + 1;
					if (dest < w && matrix[src][dest]) {
						if ((dMatrix[src][dest][nd.val] == 0
								|| cost < dMatrix[src][dest][nd.val])
								&& (nd.val == 0 || ((dMatrix[src][dest][nd.val - 1] == 0
										|| cost < dMatrix[src][dest][nd.val - 1])
										&& (nd.val == 1
												|| ((dMatrix[src][dest][nd.val - 2] == 0
														|| cost < dMatrix[src][dest][nd.val - 2])
														&& (nd.val == 2
																|| dMatrix[src][dest][nd.val
																		- 3] == 0
																|| cost < dMatrix[src][dest][nd.val
																		- 3])))))) {
							dMatrix[src][dest][nd.val] = cost;

							nNode = new Node(src, dest, cost, nd.val);
							nNode.jPos = nd.jPos;
							pq.add(nNode);
						}

					} else if (nd.val < 3) {
						jCount = 2;
						dest = -1;
						if (nd.dest + jCount < w && matrix[nd.src][nd.dest + jCount]) {
							dest = nd.dest + jCount;
						}
						if (dest != -1
								&& (dMatrix[src][dest][nd.val] == 0
										|| cost < dMatrix[src][dest][nd.val])
								&& (nd.val == 0 || ((dMatrix[src][dest][nd.val - 1] == 0
										|| cost < dMatrix[src][dest][nd.val - 1])
										&& (nd.val == 1
												|| ((dMatrix[src][dest][nd.val - 2] == 0
														|| cost < dMatrix[src][dest][nd.val - 2])
														&& (nd.val == 2
																|| dMatrix[src][dest][nd.val
																		- 3] == 0
																|| cost < dMatrix[src][dest][nd.val
																		- 3])))))) {
							nNode = new Node(src, dest, cost, nd.val + 1);
							nNode.jPos = new Node(nd.src, nd.dest + 1, nd.cost, nd.val);
							nNode.jPos.jPos = nd.jPos;
							pq.add(nNode);

							dMatrix[nNode.src][nNode.dest][nNode.val] = nNode.cost;
						}
					}
				}

				{
					src = nd.src - 1;
					dest = nd.dest;
					if (src >= 0 && matrix[src][dest]) {
						if ((dMatrix[src][dest][nd.val] == 0
								|| cost < dMatrix[src][dest][nd.val])
								&& (nd.val == 0 || ((dMatrix[src][dest][nd.val - 1] == 0
										|| cost < dMatrix[src][dest][nd.val - 1])
										&& (nd.val == 1
												|| ((dMatrix[src][dest][nd.val - 2] == 0
														|| cost < dMatrix[src][dest][nd.val - 2])
														&& (nd.val == 2
																|| dMatrix[src][dest][nd.val
																		- 3] == 0
																|| cost < dMatrix[src][dest][nd.val
																		- 3])))))) {
							dMatrix[src][dest][nd.val] = cost;

							nNode = new Node(src, dest, cost, nd.val);
							nNode.jPos = nd.jPos;
							pq.add(nNode);
						}

					} else if (nd.val < 3) {
						jCount = -2;
						src = -1;
						if (nd.src + jCount >= 0 && matrix[nd.src + jCount][nd.dest]) {
							src = nd.src + jCount;
						}
						if (src != -1
								&& (dMatrix[src][dest][nd.val] == 0
										|| cost < dMatrix[src][dest][nd.val])
								&& (nd.val == 0 || ((dMatrix[src][dest][nd.val - 1] == 0
										|| cost < dMatrix[src][dest][nd.val - 1])
										&& (nd.val == 1
												|| ((dMatrix[src][dest][nd.val - 2] == 0
														|| cost < dMatrix[src][dest][nd.val - 2])
														&& (nd.val == 2
																|| dMatrix[src][dest][nd.val
																		- 3] == 0
																|| cost < dMatrix[src][dest][nd.val
																		- 3])))))) {
							nNode = new Node(src, dest, cost, nd.val + 1);
							nNode.jPos = new Node(nd.src - 1, nd.dest, nd.cost, nd.val);
							nNode.jPos.jPos = nd.jPos;
							pq.add(nNode);

							dMatrix[nNode.src][nNode.dest][nNode.val] = nNode.cost;
						}
					}
				}
				{
					src = nd.src + 1;
					dest = nd.dest;
					if (src < h && matrix[src][dest]) {
						if ((dMatrix[src][dest][nd.val] == 0
								|| cost < dMatrix[src][dest][nd.val])
								&& (nd.val == 0 || ((dMatrix[src][dest][nd.val - 1] == 0
										|| cost < dMatrix[src][dest][nd.val - 1])
										&& (nd.val == 1
												|| ((dMatrix[src][dest][nd.val - 2] == 0
														|| cost < dMatrix[src][dest][nd.val - 2])
														&& (nd.val == 2
																|| dMatrix[src][dest][nd.val
																		- 3] == 0
																|| cost < dMatrix[src][dest][nd.val
																		- 3])))))) {
							dMatrix[src][dest][nd.val] = cost;

							nNode = new Node(src, dest, cost, nd.val);
							nNode.jPos = nd.jPos;
							pq.add(nNode);
						}

					} else if (nd.val < 3) {
						jCount = 2;
						src = -1;
						if (nd.src + jCount < h && matrix[nd.src + jCount][nd.dest]) {
							src = nd.src + jCount;
						}
						if (src != -1
								&& (dMatrix[src][dest][nd.val] == 0
										|| cost < dMatrix[src][dest][nd.val])
								&& (nd.val == 0 || ((dMatrix[src][dest][nd.val - 1] == 0
										|| cost < dMatrix[src][dest][nd.val - 1])
										&& (nd.val == 1
												|| ((dMatrix[src][dest][nd.val - 2] == 0
														|| cost < dMatrix[src][dest][nd.val - 2])
														&& (nd.val == 2
																|| dMatrix[src][dest][nd.val
																		- 3] == 0
																|| cost < dMatrix[src][dest][nd.val
																		- 3])))))) {
							nNode = new Node(src, dest, cost, nd.val + 1);
							nNode.jPos = new Node(nd.src + 1, nd.dest, nd.cost, nd.val);
							nNode.jPos.jPos = nd.jPos;
							pq.add(nNode);

							dMatrix[nNode.src][nNode.dest][nNode.val] = nNode.cost;
						}
					}
				}
			}

			String[] op = { "unused", "unused", "unused" };
			src = 0;

			while (ntIn && nd != null && nd.val > 0) {
				op[src++] = (nd.jPos.dest + 1) + " " + (nd.jPos.src + 1);
				nd = nd.jPos;
			}

			System.out.println("Case #" + i + ": ");
			for (String s : op) {
				System.out.println(s);
			}

				br.readLine();
		}

	}

	static class Node {
		int src, dest, cost, val;
		Node jPos;

		Node(int i, int j, int cost, int val) {
			this.src = i;
			this.dest = j;
			this.cost = cost;
			this.val = val;
		}
	}
}
