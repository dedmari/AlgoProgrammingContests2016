import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PickUpSticks {

	public static void main(String[] args) throws NumberFormatException, IOException, Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t= Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++){
			String[] input1  = br.readLine().split(" ");//n=input1[0](no. of sticks), m=input1[1](no. of intersections)
			String[] input2 = br.readLine().split(" ");// value of each stick i.e pi
			String[] input3;
			List<Integer> a = new ArrayList<Integer>();
			List<Integer> b = new ArrayList<Integer>();
			Set<Integer> qualified = new HashSet<Integer>();// set of those sets whose value should be summed up to get p
			int sum=0;//P
			for(int j=1;j<=Integer.parseInt(input1[1]);j++){
				input3 = br.readLine().split(" ");// intersections
				a.add(Integer.parseInt(input3[0]));
				b.add(Integer.parseInt(input3[1]));
			}
			for (int j = 1; j<=(Integer.parseInt(input1[0]));j++){// adding to qualified list that are not present in intersection lists
				if(!(a.contains(j)) && !(b.contains(j))){
					sum = sum+Integer.parseInt(input2[j-1]);
					//qualified.add(j);
				}
			}
			for(int j=1;j<=Integer.parseInt(input1[1]);j++){// getting valid sticks
				for (int k = 0; k < a.size(); k++) {
					if(!(b.contains(a.get(k)))){// if a[i] is not present in list b than add it to qualified list
						if(!(qualified.contains(a.get(k)))){
							sum = sum+Integer.parseInt(input2[a.get(k)-1]);
						}
						qualified.add(a.get(k));
						int temp = b.get(k);
						b.remove(k);
						if(!(b.contains(temp))){// if b[i] is not present in list b than add it to qualified list
							if(!(qualified.contains(temp))){
								sum = sum+Integer.parseInt(input2[temp-1]);
							}
							qualified.add(temp);
						}	
						a.remove(k);
					}
				}
				if(a.isEmpty()){
					break;
				}
			}
			System.out.println("Case #"+i+": "+sum);
			br.readLine();// reading blank space
		}		
	}
}
