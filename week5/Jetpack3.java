package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
public class Jetpack3 {
	final static BigInteger ONE = new BigInteger("1");
	public static void main(String args[]) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 1; i <= t; i++) {
			BigInteger fuel = new BigInteger(br.readLine());
			BigInteger platform = new BigInteger("3");
			BigInteger fuel_level_needed_to_jump = new BigInteger("4");
			fuel = fuel.subtract(ONE);
			while (fuel.compareTo(fuel_level_needed_to_jump) >= 0) {
				fuel = fuel.subtract(fuel_level_needed_to_jump);
				fuel_level_needed_to_jump =  platform.pow(2);
				platform = platform.add(ONE);
			}
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			sb.append(platform.toString());
			System.out.println(sb);
		}
	}
}
