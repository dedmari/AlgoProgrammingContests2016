package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


//code inspire from http://paulbourke.net/geometry/pointlineplane/
public class SwordFighting {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
        	String[] inp=br.readLine().split(" ");
        	Point2D p1 = new Point2D.Double(Double.parseDouble(inp[0]),Double.parseDouble(inp[1]));
        	Point2D p2 = new Point2D.Double(Double.parseDouble(inp[2]),Double.parseDouble(inp[3]));
        	Point2D p3 = new Point2D.Double(Double.parseDouble(inp[4]),Double.parseDouble(inp[5]));
        	Point2D p4 = new Point2D.Double(Double.parseDouble(inp[6]),Double.parseDouble(inp[7]));
        	Point2D p5 = new Point2D.Double(Double.parseDouble(inp[8]),Double.parseDouble(inp[9]));
        	Point2D p6 = new Point2D.Double(Double.parseDouble(inp[10]),Double.parseDouble(inp[11]));
        	Point2D pointLineIntersection1 = projectionPointToLine(p1, p2, p3);
        	Point2D pointLineIntersection2 = projectionPointToLine(p4, p5, p6);
        	
        	double lenAB = Math.sqrt(((p3.getX() - pointLineIntersection1.getX()) * (p3.getX() - pointLineIntersection1.getX())) + ((p3.getY() - pointLineIntersection1.getY()) * (p3.getY() - pointLineIntersection1.getY()))); 
        	double extendedX1 = pointLineIntersection1.getX() + (pointLineIntersection1.getX() - p3.getX()) / lenAB * 100000.0;//PROBLEM TO SELECT LENGTH OF BLADE
        	double extendedY1 = pointLineIntersection1.getY() + (pointLineIntersection1.getY() - p3.getY()) / lenAB * 100000.0;
        	Point2D extendedLine1EndPoint = new Point2D.Double(extendedX1,extendedY1);
        	Line2D Line1 = new Line2D.Double(pointLineIntersection1,extendedLine1EndPoint);
        	
        	lenAB = Math.sqrt(((p6.getX() - pointLineIntersection2.getX()) * (p6.getX() - pointLineIntersection2.getX())) + ((p6.getY() - pointLineIntersection2.getY()) * (p6.getY() - pointLineIntersection2.getY()))); 
        	double extendedX2 = pointLineIntersection2.getX() + (pointLineIntersection2.getX() - p6.getX()) / lenAB * 1000000.0;
        	double extendedY2 = pointLineIntersection1.getY() + (pointLineIntersection2.getY() - p6.getY()) / lenAB * 1000000.0;
        	//System.out.println("EXTENDED DIMENSIONS:"+extendedX2+"   "+extendedY2);
        	Point2D extendedLine2EndPoint = new Point2D.Double(extendedX2,extendedY2);
        	Line2D Line2 = new Line2D.Double(pointLineIntersection2,extendedLine2EndPoint);
        	Point2D ptIntersection = new Point2D.Double();
        	boolean isIntersects = DoLinesIntersect(Line1, Line2, ptIntersection);
        	StringBuilder sb = new StringBuilder("Case #");
        	sb.append(tc);
            sb.append(": ");
        	if(isIntersects){
        		sb.append(ptIntersection.getX());
                sb.append(" ");
                sb.append(ptIntersection.getY());
        	}
        	else{
        		sb.append("strange");
        	}
        	System.out.println(sb.toString());
        }

	}
	
	public static Point2D projectionPointToLine(Point2D p1, Point2D p2, Point2D p3) {

		final double xDelta = p2.getX() - p1.getX();
		final double yDelta = p2.getY() - p1.getY();
		final double u = ((p3.getX() - p1.getX()) * xDelta + (p3.getY() - p1.getY()) * yDelta) / (xDelta * xDelta + yDelta * yDelta);
        //System.out.println("value of u in point line intersection "+u);
		final Point2D closestPoint;
		if (u < 0) {
			closestPoint = p1;
		} else if (u > 1) {
			closestPoint = p2;
		} else {
			closestPoint = new Point2D.Double(p1.getX() + u * xDelta, p1.getY() + u * yDelta);
		}
		//System.out.println(closestPoint.getX()+" "+closestPoint.getY());
		return closestPoint;
	}
	
	public static boolean DoLinesIntersect(Line2D L1, Line2D L2, Point2D ptIntersection)
    {
       // Denominator for ua and ub are the same, so store this calculation
       double d =
          (L2.getY2() - L2.getY1()) * (L1.getX2() - L1.getX1())
          -
          (L2.getX2() - L2.getX1()) * (L1.getY2() - L1.getY1());

       //n_a and n_b are calculated as seperate values for readability
       double n_a =
          (L2.getX2() - L2.getX1()) * (L1.getY1() - L2.getY1())
          -
          (L2.getY2() - L2.getY1()) * (L1.getX1() - L2.getX1());

       double n_b =
          (L1.getX2() - L1.getX1()) * (L1.getY1() - L2.getY1())
          -
          (L1.getY2() - L1.getY1()) * (L1.getX1() - L2.getX1());
       if (d == 0)
          return false;
       double ua = n_a / d;
       double ub = n_b / d;
       //System.out.println("value of ua in  ray intersection "+ua);
       // The fractional point will be between 0 and 1 inclusive if the lines
       // intersect.  If the fractional calculation is larger than 1 or smaller
       // than 0 the lines would need to be longer to intersect.
       if (ua >= 0.0d && ua<=1.0d && ub >= 0.0d && ub<=1.0d)
       {
          double x = L1.getX1() + (ua * (L1.getX2() - L1.getX1()));
          double y = L1.getY1() + (ua * (L1.getY2() - L1.getY1()));
          ptIntersection.setLocation(x, y);
          return true;
       }
       return false;
    }


}
