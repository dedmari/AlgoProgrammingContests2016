package week10;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class ExamPrepration {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			String[] inp1 = br.readLine().split(" ");
			int W =  Integer.parseInt(inp1[0]);
			int n =  Integer.parseInt(inp1[1]);
			int wt[] = new int[n];
			int pr[] = new int[n];
			Map<Integer,Double> ratio = new HashMap<Integer,Double>(n);
			//double ratio[] = new double[n];
			for(int i=0;i<n;i++){
				String[] inp2 = br.readLine().split(" ");
				wt[i] = Integer.parseInt(inp2[0]);
				pr[i] = Integer.parseInt(inp2[1]);
				double rtio = (double)pr[i] / (double)wt[i];
				ratio.put(i, rtio);
			}
			//System.out.println(ratio);
			//Map sortedRatio = sortByValue(ratio);
			 ratio =
					ratio.entrySet().stream()
				       .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				       .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
		                        (e1, e2) -> e2, LinkedHashMap::new));
			//System.out.print(ratio);
			Iterator<Map.Entry<Integer, Double>> ratioIterator =  ratio.entrySet().iterator();
			while (ratioIterator.hasNext()) {
				Map.Entry<Integer, Double> entry = ratioIterator.next();
				if(wt[entry.getKey()]<=W){
					System.out.print(entry.getKey()+" ");
					W = W-wt[entry.getKey()];
				}
	            //System.out.printf("Key : %s and Value: %s %n", entry.getKey(), 
	            //                                               entry.getValue());
			}
		}

	}
}
