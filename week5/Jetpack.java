package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
public class Jetpack {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		HashMap<Integer,BigInteger> val = new HashMap<Integer,BigInteger>();
		for(int i=0;i<t;i++)
		{
			val.put(i, new BigInteger(br.readLine()));
		}

		Map<Integer,BigInteger> val2 =   val.entrySet().stream()
				.sorted(Entry.comparingByValue())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue,
						(e1, e2) -> e1, LinkedHashMap::new));
		BigInteger steps =new BigInteger("1");//step less by one, so that -1 operation in while loop is avoided 
		BigInteger sum = new BigInteger("0");
		Iterator entries = val2.entrySet().iterator();
		while (entries.hasNext()) {
			Entry thisEntry = (Entry) entries.next();
			int key = (Integer)thisEntry.getKey();
			BigInteger fuel = (BigInteger)thisEntry.getValue();
			if(fuel.compareTo(sum)==-1){//before doing calculation, if already fuel less than sum
				val2.put(key,steps);
				continue;
			}
			else if(fuel.compareTo(sum)==0){
				val2.put(key,steps.add(new BigInteger("1")));
				continue;
			}
			while(true){
				BigInteger exp = steps.pow(2);
				sum = sum.add(exp);
				steps = steps.add(new BigInteger("1"));
				if(fuel.compareTo(sum)==-1 ){//before doing calculation, if already fuel less than sum
					val2.put(key,steps);
					break;
				}
			if(fuel.compareTo(sum)==0){
					val2.put(key,steps.add(new BigInteger("1")));
					break;
				}
			}
		}
		for(int i=0;i<t;i++){
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i+1);
			sb.append(": ");
			sb.append(val2.get(i));
			System.out.println(sb);
		}
	}
}
