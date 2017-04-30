package week8;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
// Code inspired from from http://introcs.cs.princeton.edu/java/92symbolic/Rational.java.html
class Rational implements Comparable<Rational> {
    private static Rational zero = new Rational(0, 1);
    private long num;
    private long den;
    public Rational(long numerator, long denominator) {
        long g = gcd(numerator, denominator);
        num = numerator   / g;
        den = denominator / g;
        if (den < 0) { den = -den; num = -num; }
    }
    public long numerator()   { return num; }
    public long denominator() { return den; }
    public double toDouble() {
        return (double) num / den;
    }
    public String toString() { 
        if (den == 1) return num + "";
        else          return num + "/" + den;
    }
    public int compareTo(Rational b) {
        Rational a = this;
        long lhs = a.num * b.den;
        long rhs = a.den * b.num;
        if (lhs < rhs) return -1;
        if (lhs > rhs) return +1;
        return 0;
    }
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Rational b = (Rational) y;
        return compareTo(b) == 0;
    }
    public int hashCode() {
        return this.toString().hashCode();
    }
    public static Rational mediant(Rational r, Rational s) {
        return new Rational(r.num + s.num, r.den + s.den);
    }
    private static long gcd(long numerator, long denominator) {
        if (numerator < 0) numerator = -numerator;
        if (denominator < 0) denominator = -denominator;
        if (0 == denominator) return numerator;
        else return gcd(denominator, numerator % denominator);
    }

    private static long lcm(long den2, long den3) {
        if (den2 < 0) den2 = -den2;
        if (den3 < 0) den3 = -den3;
        return den2 * (den3 / gcd(den2, den3));
    }
    public Rational times(Rational b) {
        Rational a = this;
        Rational c = new Rational(a.num, b.den);
        Rational d = new Rational(b.num, a.den);
        return new Rational(c.num * d.num, c.den * d.den);
    }

    public Rational plus(Rational b) {
        Rational a = this;
        if (a.compareTo(zero) == 0) return b;
        if (b.compareTo(zero) == 0) return a;
        long f = gcd(a.num, b.num);
        long g = gcd(a.den, b.den);
        Rational s = new Rational((a.num / f) * (b.den / g) + (b.num / f) * (a.den / g),
                                  lcm(a.den, b.den));
        s.num *= f;
        return s;
    }
    public Rational negate() {
        return new Rational(-num, den);
    }
    public Rational minus(Rational b) {
        Rational a = this;
        return a.plus(b.negate());
    }


    public Rational reciprocal() { return new Rational(den, num);  }
    public Rational divides(Rational b) {
        Rational a = this;
        return a.times(b.reciprocal());
    }
}

public class GingerBread {

	private static Double areaPoly(List<Point2D> cords, int len) {
		Double area=0.0D;
		for(int i=0;i<len;i++){
		    Point2D curr = cords.get(i);
		    Point2D next = cords.get((i+1)%len);
		    area+= curr.getX() * next.getY() - curr.getY() * next.getX();
		}
		return area;
	}
	
	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int cas = 1; cas <= t; cas++) 
        {
        	String inp[]=br.readLine().split(" ");
        	int n=Integer.parseInt(inp[0]);
        	int m=Integer.parseInt(inp[1]);
        	List<Point2D> cords = new ArrayList<>();
            for(int i=0;i<n;i++)
            {
                String[] cord = br.readLine().split(" ");
                cords.add(new Point2D.Double(Integer.parseInt(cord[0]),Integer.parseInt(cord[1])));
            }
            int len = cords.size();

            Double area = areaPoly(cords, len);
            Long areaIn=area.longValue();
            if(areaIn<0)
    		{
    			areaIn=(-1)*areaIn;
    		}
           Double sNum=0.0;
           Double areaTriangle;
            for(int i=0;i<n;i++)
    		{
    			Point2D p,q,r;
    			if(i==0)
    			{
    				p=new Point2D.Double(cords.get(i).getX(),cords.get(i).getY());
    				q=new Point2D.Double(cords.get(i+1).getX(),cords.get(i+1).getY());
    				r=new Point2D.Double(cords.get(n-1).getX(),cords.get(n-1).getY());
    			}
    			else if(i==n-1)
    			{
    				p=new Point2D.Double(cords.get(i).getX(),cords.get(i).getY());
    				q=new Point2D.Double(cords.get(0).getX(),cords.get(0).getY());
    				r=new Point2D.Double(cords.get(i-1).getX(),cords.get(i-1).getY());
    			}
    			else
    			{
    				p=new Point2D.Double(cords.get(i).getX(),cords.get(i).getY());
    				q=new Point2D.Double(cords.get(i+1).getX(),cords.get(i+1).getY());
    				r=new Point2D.Double(cords.get(i-1).getX(),cords.get(i-1).getY());
    			}
    			List<Point2D> crds = new ArrayList<>();
    			crds.add(p);
    			crds.add(q);
    			crds.add(r);
    			areaTriangle=areaPoly(crds,3);
    			if(areaTriangle<0)
    			{
    				areaTriangle=(-1)*areaTriangle;
    			}
    			sNum=sNum+areaTriangle;
    		}
            Rational ratio=new Rational(sNum.intValue(),m*m*areaIn);
            System.out.println("Case #"+cas+": "+ratio.numerator()+"/"+ratio.denominator());
       	 	br.readLine();
        	
        }
	}
      
}

