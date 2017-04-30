package week7;

import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// code inspired from convex hull implementation of sanfoundry
public class MeteoriteRevisited {

	static Point2D.Double min, max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader br = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(br);
		int cas = Integer.parseInt(in.readLine());
		String[] inps;

		for (int i = 1; i <= cas; i++) {
		 int	n = Integer.parseInt(in.readLine());
		 Point2D.Double[] area = new Point2D.Double[n];

			for (int j = 0; j < n; j++) {
				inps = in.readLine().split(" ");
				area[j] = new Point2D.Double(Double.parseDouble(inps[0]), Double.parseDouble(inps[1]));

				if (min == null) {
					min = new Point2D.Double(area[j].x, area[j].y);
					max = new Point2D.Double(area[j].x, area[j].y);
				} else {
					if (min.x > area[j].x)
						min.x = area[j].x;
					else if (max.x < area[j].x)
						max.x = area[j].x;
					if (min.y > area[j].y)
						min.y = area[j].y;
					else if (max.y < area[j].y)
						max.y = area[j].y;
				}
			}

			Path2D.Double poly = new Path2D.Double();
			poly.moveTo(area[0].x, area[0].y);

			for (int k = 1; k < area.length; k++) {
				poly.lineTo(area[k].x, area[k].y);
			}
			poly.closePath();

			ArrayList<Point> convexHullPoints = new ArrayList<Point>();

			for (int k = (int) min.x; k <= max.x; k++) {
				for (int j = (int) min.y; j <= max.y; j++) {
					if (poly.contains(k, j) || checkBoundary(poly, new Point2D.Double(k, j))) {
						convexHullPoints.add(new Point(k, j));
					}
				}
			}

			String result = convexHull(convexHullPoints);

			System.out.println("Case #" + i + ":" + result);

			in.readLine();
		}
	}

	public static boolean contains(Point2D.Double a, Point2D.Double b, Point2D.Double p) {
		double minx = a.x < b.x ? a.x : b.x;
		double miny = a.y < b.y ? a.y : b.y;
		double maxx = a.x > b.x ? a.x : b.x;
		double maxy = a.y > b.y ? a.y : b.y;

		if (p.x >= minx && p.x <= maxx && p.y >= miny && p.y <= maxy)
			return true;

		return false;
	}

	public static boolean checkBoundary(Path2D poly, Point2D.Double p) {
		PathIterator pi = poly.getPathIterator(null);
		double[] values = new double[6];
		Point2D.Double start = null;
		Point2D.Double prev = null;
		Point2D.Double curr = null;

		while (!pi.isDone()) {
			int type = pi.currentSegment(values);
			if (type == PathIterator.SEG_LINETO) {
				prev = curr;
				curr = new Point2D.Double(values[0], values[1]);
			} else if (type == PathIterator.SEG_CLOSE) {
				prev = start;
			} else if (type == PathIterator.SEG_MOVETO) {
				start = new Point2D.Double(values[0], values[1]);
				curr = start;
			}

			if (curr.x == p.x && curr.y == p.y)
				return true;

			if (prev != null && contains(prev, curr, p)) {
				double aSlope = curr.x == p.x ? 1001 : (double) (curr.y - p.y) / (double) (curr.x - p.x);
				double bSlope = prev.x == p.x ? 1001 : (double) (prev.y - p.y) / (double) (prev.x - p.x);

				if (aSlope == bSlope)
					return true;
			}

			pi.next();
		}

		return false;
	}


	public static int compare(Point a, Point b, Point min) {
		if (a == min)
			return -1;
		if (b == min)
			return 1;

		double aSlope = a.x == min.x ? 1001 : (double) (a.y - min.y) / (double) (a.x - min.x);
		double bSlope = b.x == min.x ? 1001 : (double) (b.y - min.y) / (double) (b.x - min.x);

		if (aSlope < 0)
			aSlope = -(1 / aSlope) + 1001;
		if (bSlope < 0)
			bSlope = -(1 / bSlope) + 1001;

		if (aSlope == bSlope) {
			double aDist = Math.sqrt((double) ((a.x - min.x) * (a.x - min.x) + (a.y - min.y) * (a.y - min.y)));
			double bDist = Math.sqrt((double) ((b.x - min.x) * (b.x - min.x) + (b.y - min.y) * (b.y - min.y)));

			if (aDist > bDist)
				aSlope++;
			else
				bSlope++;

		}

		return Double.compare(aSlope, bSlope);
	}

	public static int ccw(Point p1, Point p2, Point p3) {
		return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
	}

	public static String convexHull(ArrayList<Point> input) {

		Point min = null;

		for (Point p : input)
			if (min == null || p.y < min.y || (p.y == min.y && p.x < min.x)) {
				min = p;
			}

		Point fMin = min;

		input.sort((a, b) -> compare(a, b, fMin));

		int k = 1;
		int n = input.size();
		for (int i = 1; i < n; i++) {
			while (i < n - 1 && ccw(input.get(0), input.get(i), input.get(i + 1)) == 0)
				i++;

			input.set(k, input.get(i));
			k++;
		}

		int m = 1;
		for (int i = 2; i < k; i++) {
			while (ccw(input.get(m - 1), input.get(m), input.get(i)) <= 0) {
				if (m > 1)
					m--;
				else if (i == input.size())
					break;
				else
					i++;
			}
			m++;

			Point temp = input.get(m);
			input.set(m, input.get(i));
			input.set(i, temp);
		}

		StringBuilder sb = new StringBuilder(12);
		Point p = null;
		n = input.size();

		sb.append(System.lineSeparator());
		sb.append(n <= 2 ? n : m + 1);

		for (int i = 0; i <= m && i < n; i++) {
			p = input.get(i);
			sb.append(System.lineSeparator());
			sb.append(p.x + " " + p.y);
		}

		return sb.toString();
	}
}