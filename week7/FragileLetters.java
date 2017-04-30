package week7;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// code inspired by  geeksfor geeks and sanfoundry

public class FragileLetters {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cas = Integer.parseInt(br.readLine());
		for (int i = 1; i <= cas; i++) {
			int noPnts = Integer.parseInt(br.readLine());
			Point2D.Double	cent = new Point2D.Double(0, 0);
			Point2D.Double[] coords = new Point2D.Double[noPnts];
			double x, y;
			for (int j = 0; j < noPnts; j++) {
				String[]	inp1 = br.readLine().split(" ");
				x = Double.parseDouble(inp1[0]);
				y = Double.parseDouble(inp1[1]);

				cent.x = cent.x + x;
				cent.y = cent.y + y;

				coords[j] = new Point2D.Double(x, y);
			}

			cent.x = cent.x / noPnts;
			cent.y = cent.y / noPnts;
			
			Point2D.Double point1 = coords[noPnts - 1];

			Point2D.Double p1 = null;

			int nos = 0; 

			for (int k = 0; k < noPnts; k++) {
				p1 = point1;
				point1 = coords[k];

				if (!chkCOG(p1, point1,cent))
					continue;

				Point2D.Double tstPnt = null;
				boolean isVal = true;
				boolean isClockwise = false;
				boolean isCounterClockwise = false;

				for (int j = 0; j < noPnts; j++) {
					tstPnt = coords[j];
					if (p1 == tstPnt || point1 == tstPnt)
						continue;
					double ccw = rotateCCW(p1, point1, tstPnt);

					if (ccw == 0) {
						isVal = false;
						break;
					}
					if (ccw < 0) {
						if (isCounterClockwise) {
							isVal = false;
							break;
						} else
							isClockwise = true;
					} else if (ccw > 0) {
						if (isClockwise) {
							isVal = false;
							break;
						} else
							isCounterClockwise = true;
					}
				}

				if (isVal)
					nos++;
			}

			System.out.println("Case #" + i + ": " + nos);
				br.readLine();
		}
	}

	public static double rotateCCW(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
		return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
	}


	public static boolean chkCOG(Point2D.Double p1, Point2D.Double p2, Point2D.Double cent) {
		Point2D.Double perp = keepStraight(p1, p2, cent);

		double xmin = p1.x < p2.x ? p1.x : p2.x;
		double ymin = p1.y < p2.y ? p1.y : p2.y;
		
		double xmax = p1.x > p2.x ? p1.x : p2.x;
		double ymax = p1.y > p2.y ? p1.y : p2.y;

		if (perp.x >= xmin && perp.x <= xmax && perp.y >= ymin && perp.y <= ymax)
			return true;

		return false;
	}
	
	public static Point2D.Double keepStraight(Point2D.Double p1, Point2D.Double p2, Point2D.Double q) {
		double px = p2.x - p1.x;
		double py = p2.y - p1.y;

		double dist = px * px + py * py;
		double u = ((q.x - p1.x) * px + (q.y - p1.y) * py) / dist;

		return new Point2D.Double(p1.x + u * px, p1.y + u * py);
	}


}
