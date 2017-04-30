package week5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class JetPack4 {
	static BigInteger sqrt(BigInteger n) {
		BigInteger x = BigInteger.ONE;
		BigInteger y = n.shiftRight(5).add(BigInteger.valueOf(8));
		while (y.compareTo(x) >= 0) {
			BigInteger mid = x.add(y).shiftRight(1);
			if (mid.multiply(mid).compareTo(n) > 0) {
				y = mid.subtract(BigInteger.ONE);
			} else {
				x = mid.add(BigInteger.ONE);
			}
		}
		return x.subtract(BigInteger.ONE);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System .in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 1; i <= t; i++) {
			BigInteger fuel = new BigInteger(br.readLine());	
			fuel = fuel.multiply(BigInteger.valueOf(6));
			BigInteger low = BigInteger.ONE;
			BigInteger high = new BigInteger("9999999999");
			BigInteger mid; 
			BigInteger prMid = new BigInteger("1");
			BigInteger value = new BigInteger("0"); 
			while(true){
				mid = (high.add(low)).divide(BigInteger.valueOf(2));	
				value = mid;
				BigInteger ans =  ((value.multiply(value.add(BigInteger.ONE))).multiply((value.multiply(BigInteger.valueOf(2))).add(BigInteger.ONE)));
				if(prMid.equals(mid)){
					break;
				}
				prMid = mid;
				if(ans.compareTo(fuel)<0){
					low = mid;
				}else if(ans.compareTo(fuel)>0){
					high = mid;
				}

			}

			System.out.println("Case #"+i+": "+value.add(BigInteger.valueOf(2)));
		}	
	}
}