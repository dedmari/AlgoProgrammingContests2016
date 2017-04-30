package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//inspired from code of  Darel Rex Finley.
class Vertex { 
	int x;
	int y;
	boolean vis = false;
	double dist = Double.MAX_VALUE;
	Vertex pVert = this;
	HashMap<Vertex, Double> adjList = new HashMap<Vertex, Double>();

	public Vertex(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Rectangle {
	int lt;
	int rt;
	int bot;
	int top;

	public Rectangle(int lt, int rt, int bot, int top) {
		this.lt = lt;
		this.rt = rt;
		this.bot = bot;
		this.top = top;
	}
}

public class Pathing {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		int cases = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= cases; t++) {
			// priority queue implementation, takes time, but works
			PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(new Comparator<Vertex>() {
				@Override
				public int compare(Vertex n1, Vertex n2) {
					if (n1.dist > n2.dist) {
						return 1;
					}
					if (n1.dist < n2.dist) {
						return -1;
					}
					return 0;
				}
			});
			String[] input1 = br.readLine().split(" ");
			int obs = Integer.parseInt(input1[2]);
			Vertex[] vertices = new Vertex[obs * 4 + 2];
			Rectangle[] rects = new Rectangle[obs];
			for (int k = 0; k < obs; k++) {
				input1 = br.readLine().split(" ");
				int v1 = Integer.parseInt(input1[0]);
				int v2 = Integer.parseInt(input1[1]);
				int v3 = Integer.parseInt(input1[2]);
				int v4 = Integer.parseInt(input1[3]);
				vertices[k * 4] = new Vertex(v1, v2);
				vertices[k * 4 + 1] = new Vertex(v1 + v3, v2);
				vertices[k * 4 + 2] = new Vertex(v1, v2 + v4);
				vertices[k * 4 + 3] = new Vertex(v1 + v3, v2 + v4);
				rects[k] = new Rectangle(v1, v1 + v3, v2, v2 + v4);
			}

			input1 = br.readLine().split(" ");
			int val1 = Integer.parseInt(input1[0]);
			int val2 = Integer.parseInt(input1[1]);
			Vertex start = new Vertex(val1, val2);
			vertices[obs * 4] = start;

			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			input1 = line.split(" ");
			val1 = Integer.parseInt(input1[0]);
			val2 = Integer.parseInt(input1[1]);
			Vertex last = new Vertex(val1, val2);
			vertices[obs * 4 + 1] = last;

			pq.add(start);
			start.dist = 0.0;
			ArrayList<Vertex> sol = new ArrayList<Vertex>();
			while (!pq.isEmpty()) {
				Vertex v = pq.remove();
				v.vis = true;
				if (v == last)
					break;
				toLoop1: for (int index1 = 0; index1 < vertices.length; index1++) {
					Vertex n = vertices[index1];
					if (n.vis)
						continue;
					for (int j = 0; j < obs; j++) {
						Rectangle rect = rects[j];
						if (intsect(rect, n, v))
							continue toLoop1;
					}
					v.adjList.put(n, Math.sqrt(Math.pow(n.x - v.x, 2) + Math.pow(n.y - v.y, 2)));
				}
				for (Map.Entry<Vertex, Double> ne : v.adjList.entrySet()) {
					double totalDist = v.dist + ne.getValue();
					Vertex nbr = ne.getKey();
					if (nbr.dist > totalDist && !nbr.vis) {
						nbr.dist = totalDist;
						nbr.pVert = v;
						pq.add(nbr);
					}
				}
			}
			Vertex tmp = last;
			while (tmp != start) {
				sol.add(tmp);
				tmp = tmp.pVert;
			}
			sol.add(start);
			sb.append("Case #");
			sb.append(t);
			sb.append(":");
			for (int i = sol.size() - 1; i >= 0; i--) {
				sb.append(" (");
				sb.append(sol.get(i).x);
				sb.append(",");
				sb.append(sol.get(i).y);
				sb.append(")");
			}
			sb.append("\n");
			line = br.readLine();
		}
		System.out.println(sb);
	}
	public static boolean intsect(Rectangle rect, Vertex v, Vertex vMin) {
		int xMin, yMin, xMax, yMax;
		Vertex lt, rt;
		if (v.x < vMin.x) {
			xMin = v.x;
			xMax = vMin.x;
			lt = v;
			rt = vMin;
		} else {
			xMin = vMin.x;
			xMax = v.x;
			lt = vMin;
			rt = v;
		}
		if (rect.lt >= xMax || rect.rt <= xMin)
			return false;
		if (v.y < vMin.y) {
			yMin = v.y;
			yMax = vMin.y;
		} else {
			yMin = vMin.y;
			yMax = v.y;
		}
		if (rect.top <= yMin || rect.bot >= yMax)
			return false;
		double m = ((double) rt.y - lt.y) / (rt.x - lt.x);
		double b = (double) v.y - m * v.x;
		double yL = m * rect.lt + b;
		double yR = m * rect.rt + b;
		if (rect.bot >= yL && rect.bot >= yR)
			return false;
		if (rect.top <= yL && rect.top <= yR)
			return false;
		return true;
	}

}
