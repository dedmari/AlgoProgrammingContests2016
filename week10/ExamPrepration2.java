package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
public class ExamPrepration2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 1; i <= t; i++) {
			String[] inp1 = br.readLine().split(" ");
			int W = Integer.parseInt(inp1[0]);
			int items = Integer.parseInt(inp1[1]);
			Item[] itms = new Item[items];
			for (int j = 1; j <= items; j++) {
				String[] inp2 = br.readLine().split(" ");
				int wt = Integer.parseInt(inp2[0]);
				int pf = Integer.parseInt(inp2[1]);
				itms[j-1] = new Item(j,wt, pf);
			}
			Arrays.sort(itms, new Comparator<Item>() {
				public int compare(Item o1, Item o2) {
					if(o1.ratio < o2.ratio)
						return 1;
					if(o1.ratio > o2.ratio)
						return -1;
					return 0;
				}
			});
			int tempW = 0;
			StringBuilder sb = new StringBuilder("Case #");
			sb.append(i);
			sb.append(":");	
			for(int j = 0; j < items; j++) {
				int wt = itms[j].wt;
				while(tempW+wt <= W) {
					tempW += wt;
					sb.append(" "+itms[j].id);				
				}
			}
			System.out.println(sb.toString());
			br.readLine();
		}
	}
}
class Item{
	int id;
	int wt;
	int pf;
	double ratio;
	Item(int id,int wt, int pf) {
		super();
		this.id =id;
		this.wt = wt;
		this.pf = pf;
		this.ratio = (double) pf/wt;
	}	
}