package week8;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.geom.*;
import java.io.BufferedReader;

public class EulerLine {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cas = Integer.parseInt(br.readLine());
        for (int t = 1; t <= cas; t++) {
            String[] inp = br.readLine().split(" ");
            Point2D a = new Point2D.Double(Double.parseDouble(inp[0]),Double.parseDouble(inp[1]));
            inp = br.readLine().split(" ");
            Point2D b = new Point2D.Double(Double.parseDouble(inp[0]),Double.parseDouble(inp[1]));
            inp = br.readLine().split(" ");
            Point2D c = new Point2D.Double(Double.parseDouble(inp[0]),Double.parseDouble(inp[1]));
            StringBuilder out = new StringBuilder();
            out.append("Case #").append(t).append(": \n");
            Point2D oCenter = getOrthoCenter(a,b,c);
            Point2D centroid = getCentroid(a,b,c);
            Point2D cCenter = getCCenter(a,b,c);
            out.append(centroid.getX()).append(" ").append(centroid.getY()).append("\n");
            out.append(oCenter.getX()).append(" ").append(oCenter.getY()).append("\n");
            out.append(cCenter.getX()).append(" ").append(cCenter.getY());
            System.out.println(out);
                br.readLine();
        }
    }

    static Point2D getCentroid(Point2D a, Point2D b, Point2D c){
        return new Point2D.Double((a.getX()+b.getX()+c.getX())/3,(a.getY()+b.getY()+c.getY())/3);
    }

    static Point2D getCCenter(Point2D a, Point2D b, Point2D c){
        double d = 2*(a.getX()*(b.getY()-c.getY())+b.getX()*(c.getY()-a.getY())+c.getX()*(a.getY()-b.getY()));
        double x = ((Math.pow(a.getX(),2)+Math.pow(a.getY(),2))*(b.getY()-c.getY()) + (Math.pow(b.getX(),2)+Math.pow(b.getY(),2)) * (c.getY()-a.getY()) + (Math.pow(c.getX(),2)+Math.pow(c.getY(),2))*(a.getY()-b.getY()))/d;
        double y = ((Math.pow(a.getX(),2)+Math.pow(a.getY(),2))*(c.getX()-b.getX()) + (Math.pow(b.getX(),2)+Math.pow(b.getY(),2)) * (a.getX()-c.getX()) + (Math.pow(c.getX(),2)+Math.pow(c.getY(),2))*(b.getX()-a.getX()))/d;
        return new Point2D.Double(x,y);
    }
    
    static Point2D getBisect(Point2D a, Point2D b, Point2D c){
        double x = a.getX()+(((c.getX()-a.getX())*(b.getX()-a.getX())+(c.getY()-a.getY())*(b.getY()-a.getY())) / (Math.pow((b.getX()-a.getX()),2)+Math.pow((b.getY()-a.getY()),2)) * (b.getX()-a.getX()));
        double y = a.getY()+(((c.getX()-a.getX())*(b.getX()-a.getX())+(c.getY()-a.getY())*(b.getY()-a.getY())) / (Math.pow((b.getX()-a.getX()),2)+Math.pow((b.getY()-a.getY()),2)) * (b.getY()-a.getY()));
        return new Point2D.Double(x,y);
    }

    static Point2D getOrthoCenter(Point2D a, Point2D b, Point2D c){
        Line2D.Double p1, p2;
        p2= new Line2D.Double(a, getBisect(b,c,a));
        p1 = new Line2D.Double(c,getBisect(a,b,c));
        double x1 = p1.getX1();
        double y1 = p1.getY1();
        double x2 = p1.getX2();
        double y2 = p1.getY2();
        double x3 = p2.getX1();
        double y3 = p2.getY1();
        double x4 = p2.getX2();
        double y4 = p2.getY2();
        double d = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);
        double x=0.0d,y=0.0d;
        if(d!= 0.0d)
        {
             x = ((x3-x4)*(x1*y2 - y1*x2)-(x1-x2)*(x3*y4 - y3*x4))/d;
             y = ((y3-y4)*(x1*y2 - y1*x2)-(y1-y2)*(x3*y4 - y3*x4))/d;
        }

        return new Point2D.Double(x,y);
    }   
}


