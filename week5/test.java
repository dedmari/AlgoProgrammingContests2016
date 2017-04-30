package week5;

public class test {
    private int[] coef;  // coefficients
    private int deg;     // degree of polynomial (0 for the zero polynomial)

    // a * x^b
    public test(int a, int b) {
        coef = new int[b+1];
        coef[b] = a;
        deg = degree();
    }

    // return the degree of this polynomial (0 for the zero polynomial)
    public int degree() {
        int d = 0;
        for (int i = 0; i < coef.length; i++)
            if (coef[i] != 0) d = i;
        return d;
    }

    // return c = a + b
    public test plus(test b) {
        test a = this;
        test c = new test(0, Math.max(a.deg, b.deg));
        for (int i = 0; i <= a.deg; i++) c.coef[i] += a.coef[i];
        for (int i = 0; i <= b.deg; i++) c.coef[i] += b.coef[i];
        c.deg = c.degree();
        return c;
    }

    // return (a - b)
    public test minus(test b) {
        test a = this;
        test c = new test(0, Math.max(a.deg, b.deg));
        for (int i = 0; i <= a.deg; i++) c.coef[i] += a.coef[i];
        for (int i = 0; i <= b.deg; i++) c.coef[i] -= b.coef[i];
        c.deg = c.degree();
        return c;
    }

    // return (a * b)
    public test times(test b) {
        test a = this;
        test c = new test(0, a.deg + b.deg);
        for (int i = 0; i <= a.deg; i++)
            for (int j = 0; j <= b.deg; j++)
                c.coef[i+j] += (a.coef[i] * b.coef[j]);
        c.deg = c.degree();
        return c;
    }

    // return a(b(x))  - compute using Horner's method
    public test compose(test b) {
        test a = this;
        test c = new test(0, 0);
        for (int i = a.deg; i >= 0; i--) {
            test term = new test(a.coef[i], 0);
            c = term.plus(b.times(c));
        }
        return c;
    }


    // do a and b represent the same polynomial?
    public boolean eq(test b) {
        test a = this;
        if (a.deg != b.deg) return false;
        for (int i = a.deg; i >= 0; i--)
            if (a.coef[i] != b.coef[i]) return false;
        return true;
    }


    // use Horner's method to compute and return the polynomial evaluated at x
    public int evaluate(int x) {
        int p = 0;
        for (int i = deg; i >= 0; i--)
            p = coef[i] + (x * p);
        return p;
    }

    // differentiate this polynomial and return it
    public test differentiate() {
        if (deg == 0) return new test(0, 0);
        test deriv = new test(0, deg - 1);
        deriv.deg = deg - 1;
        for (int i = 0; i < deg; i++)
            deriv.coef[i] = (i + 1) * coef[i + 1];
        return deriv;
    }

    // convert to string representation
    public String toString() {
        if (deg ==  0) return "" + coef[0];
        if (deg ==  1) return coef[1] + "x + " + coef[0];
        String s = coef[deg] + "x^" + deg;
        for (int i = deg-1; i >= 0; i--) {
            if      (coef[i] == 0) continue;
            else if (coef[i]  > 0) s = s + " + " + ( coef[i]);
            else if (coef[i]  < 0) s = s + " - " + (-coef[i]);
            if      (i == 1) s = s + "x";
            else if (i >  1) s = s + "x^" + i;
        }
        return s;
    }

    // test client
    public static void main(String[] args) { 
        test zero = new test(0, 0);

        test p1   = new test(2, 3);
        test p2   = new test(3, 2);
        test p3   = new test(-30, 0);
        test p4   = new test(1, 1);
        test p    = p1.plus(p2).plus(p3).plus(p4);   // 4x^3 + 3x^2 + 1

        test q1   = new test(3, 2);
        test q2   = new test(5, 0);
        test q    = q1.plus(q2);                     // 3x^2 + 5


        test r    = p.plus(q);
        test s    = p.times(q);
        test t    = p.compose(q);
        System.out.println("zero(x) =     " + zero);
        System.out.println("p(x) =        " + p);
        System.out.println("q(x) =        " + q);
        System.out.println("p(x) + q(x) = " + r);
        System.out.println("p(x) * q(x) = " + s);
        System.out.println("p(q(x))     = " + t);
        System.out.println("0 - p(x)    = " + zero.minus(p));
        System.out.println("p(3)        = " + p.evaluate(3));
        System.out.println("p'(x)       = " + p.differentiate());
        System.out.println("p''(x)      = " + p.differentiate().differentiate());
   }

}