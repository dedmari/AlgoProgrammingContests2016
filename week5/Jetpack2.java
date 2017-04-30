package week5;

import java.math.BigInteger;
import java.util.Scanner;


public class Jetpack2 {

	final static BigInteger ONE = new BigInteger("1");
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int i = 1; i <= t; i++) {
			BigInteger fuel = sc.nextBigInteger();
			BigInteger platform = new BigInteger("3");
			BigInteger fuel_level_needed_to_jump = new BigInteger("4");
			
			fuel = fuel.subtract(ONE);
			
			while (fuel.compareTo(fuel_level_needed_to_jump) >= 0) {
				fuel = fuel.subtract(fuel_level_needed_to_jump);
				fuel_level_needed_to_jump =  platform.pow(2);
				platform = platform.add(ONE);
			}
			System.out.println("Case #" + i + ": " + platform.toString());
		}
		sc.close();
	}
}
