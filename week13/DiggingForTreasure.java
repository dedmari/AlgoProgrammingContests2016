package week13;
//code inspired from http://algs4.cs.princeton.edu/99hull/GrahamScan.java.html and http://algs4.cs.princeton.edu/25applications/Point2D.java.html
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

class Point implements Comparable<Point>{
	int x, y, number;
	public Point(int x, int y, int number) {
		this.x = x;
		this.y = y;
		this.number = number;
	}

	public static int ccw(Point a, Point b, Point c) {
		double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
		if      (area2 < 0) return -1;
		else if (area2 > 0) return +1;
		else                return  0;
	}

	public Comparator<Point> polarOrder() {
		return new PolarOrder();
	}

	public int compareTo(Point that) {
		if (this.y < that.y) return -1;
		if (this.y > that.y) return +1;
		if (this.x < that.x) return -1;
		if (this.x > that.x) return +1;
		return 0;
	}
	private class PolarOrder implements Comparator<Point> {
		public int compare(Point q1, Point q2) {
			double dx1 = q1.x - x;
			double dy1 = q1.y - y;
			double dx2 = q2.x - x;
			double dy2 = q2.y - y;

			if      (dy1 >= 0 && dy2 < 0) return -1;   
			else if (dy2 >= 0 && dy1 < 0) return +1;    
			else if (dy1 == 0 && dy2 == 0) {           
				if      (dx1 >= 0 && dx2 < 0) return -1;
				else if (dx2 >= 0 && dx1 < 0) return +1;
				else                          return  0;
			}
			else return -ccw(Point.this, q1, q2);
		}
	}
}


public class DiggingForTreasure {

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++)
		{
			StringBuilder sb = new StringBuilder();
			int posts = Integer.parseInt(br.readLine());
			Point[] points = new Point[posts];
			Stack<Point> hull = new Stack<Point>();
			for(int j = 0; j < posts; j++) {
				String[] inp = br.readLine().split(" "); 
				int x = Integer.parseInt(inp[0]);
				int y = Integer.parseInt(inp[1]);
				points[j] = new Point(x, y, i);
			}
			Arrays.sort(points);
			Arrays.sort(points, 1, posts, points[0].polarOrder());
			hull.push(points[0]);
			int p1;
			for (p1 = 1; p1 < posts; p1++){
				if (!points[0].equals(points[p1])) 
					break;
			}
			
			int p2;
			for (p2 = p1+1; p2 < posts; p2++){
				if (Point.ccw(points[0], points[p1], points[p2]) != 0) 	
					break;
			}
			hull.push(points[p2-1]);

			for (int j = p2; j < posts; j++) {
				Point top = hull.pop();
				while (Point.ccw(hull.peek(), top, points[j]) <= 0) {
					top = hull.pop();
				}
				hull.push(top);
				hull.push(points[j]);
			}
			double volume = hull.get(hull.size()-1).x * hull.get(0).y - hull.get(hull.size()-1).y * hull.get(0).x;
			for(int j = 0; j < hull.size()-1; j++) {
				volume += hull.get(j).x * hull.get(j+1).y - hull.get(j).y * hull.get(j+1).x;
			}
			sb.append("Case #");
			sb.append(i+1);
			sb.append(": ");
			volume = Math.abs(volume);
			sb.append(volume);
			System.out.println(sb);
			br.readLine();
		}
	}
}
