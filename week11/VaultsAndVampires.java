package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class VaultsAndVampires {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 1; i <= t; i++) {
			List<Integer> diceRolls = new ArrayList<Integer>();
			int maxScore = 0;
			int finalScore = 0;
			StringBuilder sb = new StringBuilder();
			String input1[] = br.readLine().split(" ");
			int n = Integer.parseInt(input1[0]);
			String input[] = input1[1].split("\\+");
			for (int j = 0; j < input.length; j++) {
				String rolls[] = input[j].split("d");
				int rollId = Integer.parseInt(rolls[0]);
				int val = Integer.parseInt(rolls[1]);
				for (int index2 = 1; 
						index2 <= rollId; index2++) {
					diceRolls.add(val);
				}
				finalScore += rollId * val;
				maxScore += rollId;
			}

			if (n > finalScore)
				sb.append("0/1");
			else if (n <= maxScore)
				sb.append("1/1");
			else {

				BigInteger[][] table = new BigInteger[maxScore][n + 1];
				BigInteger[][] duelTable = new BigInteger[maxScore][n + 1];
				int istDie = 2;
				int initial = 0;
				int oneThrow = diceRolls.get(0);
				int diceValue = oneThrow + 1;
				int finalDiceValue = diceValue;
				BigInteger firstVal = BigInteger.valueOf(oneThrow);
				for (int j = istDie; j <= diceValue
						&& j <= n; j++) {
					duelTable [0][j] = BigInteger
							.valueOf(initial*j);
					table[0][j] = BigInteger
							.valueOf(j - 1);
				}
				for (int j = 1; j < maxScore; j++) {

					oneThrow = diceRolls.get(j);

					firstVal = firstVal.multiply(BigInteger
							.valueOf(oneThrow));
					diceValue += oneThrow;
					//initializing table
					table[j][istDie] = BigInteger.ZERO;
					istDie++;
					for (int k = istDie; 
							k <= diceValue
									&& k <= n; k++) {
						BigInteger num1 = table[j]
								[k - 1];
						if (k - oneThrow >= istDie) {
							if (k > finalDiceValue) {
								BigInteger num2 = table[j - 1]
										[k - 1 - oneThrow];
								BigInteger num3 = table[j - 1]
										[finalDiceValue];

								table[j][k] = num1
										.add(num3
												.subtract(num2));
							} else {

								BigInteger num4 = table[j - 1][k - 1 
								                                       - oneThrow];						
								BigInteger num5 = table[j - 1][k - 1];
								table[j][k] = num1
										.add(num5
												.subtract(num4));
							}

						} else {
							if (k > finalDiceValue) {
								BigInteger num5 = table[j - 1]
										[finalDiceValue];
								table[j][k] = num1
										.add(num5);
							} else {

								BigInteger num6 = table[j - 1]
										[k - 1];
								table[j][k] = num1
										.add(num6);
							}
						}
					}

					finalDiceValue = diceValue;
				}
				BigInteger prob = firstVal.subtract(table[maxScore - 1][n]);
				BigInteger gcd = prob.gcd(firstVal);
				sb.append(prob.divide(gcd));
				sb.append("/");
				sb.append(firstVal.divide(gcd));
			}
			System.out.println("Case #"+i+": "+sb.toString());
		}
	}
}
