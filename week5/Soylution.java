package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Soylution {
	public interface Function {
		public double f(double x);
	}

	private static int sign(double x) {
		return (x < 0.0) ? -1 : (x > 0.0) ? 1 : 0;
	}

	public static int printRoots(Function f, double lowerBound,
			double upperBound, double step) {
		double x = lowerBound, ox = x;
		double y = f.f(x), oy = y;
		int s = sign(y), os = s;
		for (; x <= upperBound ; x += step) {
			s = sign(y = f.f(x));
			if (s == 0) {
				int con = (int)x;
				return con;
			} else if (s != os) {
				double dx = x - ox;
				double dy = y - oy;
				double cx = x - dx * (y / dy);
				int con = (int)cx;
				return con;
			}
			else{
			}
			ox = x; oy = y; os = s;
		}
		return 0;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 1; i <= t; i++) {
			int res = 0;
			double fuel = Double.parseDouble(br.readLine());
			/*if(fuel >= 5 && fuel <14){
				res=4;
			}
			else{*/
				Function poly = new Function () {
					public double f(double x) {
						return x*x*x/3+x*x/2+x/6-fuel;
					}
				};
				res = 2+printRoots(poly, 1, 1000000000, 1);
			//}
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			sb.append(res);
			System.out.println(sb);
		}
	}
}