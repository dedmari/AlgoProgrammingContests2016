package week8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//code inspire from http://paulbourke.net/geometry/pointlineplane/ and stack-overflow
class Dimensions {
	double x, y, z;
	public Dimensions(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

public class SwordFighting2 {

	public static Dimensions croosProd(Dimensions p1, Dimensions p2) {
		double x = p1.y * p2.z - p1.z * p2.y;
		double y = p1.z * p2.x - p1.x * p2.z;
		double z = p1.x * p2.y - p1.y * p2.x;
		return new Dimensions(x, y, z);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int t = Integer.parseInt(line);
		for (int i = 0; i < t; i++) {
			line = br.readLine();
			String inp1[] = line.split(" ");
			Dimensions pt1 = new Dimensions(Double.parseDouble(inp1[0]), Double.parseDouble(inp1[1]), 1);
			Dimensions pt2 = new Dimensions(Double.parseDouble(inp1[2]), Double.parseDouble(inp1[3]), 1);
			Dimensions pt3 = new Dimensions(Double.parseDouble(inp1[4]), Double.parseDouble(inp1[5]), 1);
			Dimensions pt4 = new Dimensions(Double.parseDouble(inp1[6]), Double.parseDouble(inp1[7]), 1);
			Dimensions pt5 = new Dimensions(Double.parseDouble(inp1[8]), Double.parseDouble(inp1[9]), 1);
			Dimensions pt6 = new Dimensions(Double.parseDouble(inp1[10]), Double.parseDouble(inp1[11]), 1);
			Dimensions l1 = croosProd(pt1, pt2);
			Dimensions l2 = croosProd(pt4, pt5);
			Dimensions vectorTemp = new Dimensions(0, 0, 1);
			Dimensions line1 = croosProd(l1, vectorTemp);
			double x = line1.x;
			line1.x = line1.y;
			line1.y = -x;
			line1 = croosProd(pt3, line1);
			Dimensions line2 = croosProd(l2, vectorTemp);
			x = line2.x;
			line2.x = line2.y;
			line2.y = -x;
			line2 = croosProd(pt6, line2);
			Dimensions p14 = croosProd(line1, l1);
			p14.x = p14.x / p14.z;
			p14.y = p14.y / p14.z;
			Dimensions p24 = croosProd(line2, l2);
			p24.x = p24.x / p24.z;
			p24.y = p24.y / p24.z;
			Dimensions s = croosProd(line1, line2);
			if (s.z != 0) {
				s.x = s.x / s.z;
				s.y = s.y / s.z;
			}
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i + 1);
			sb.append(": ");
			if (s.y < p14.y 
					|| s.y < p24.y 
					|| s.z == 0)
				sb.append("strange");
			else {
				sb.append(s.x);
				sb.append(" ");
				sb.append(s.y);
			}
			System.out.println(sb.toString());
		}

	}
}


