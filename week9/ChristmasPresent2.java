package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ChristmasPresent2 {
	static  int numFriends;
	static boolean friendsSatisfied = false; // used to check if the constraints are satisfying
	static List<Integer>[] friendsPreferences;
	static boolean[] presentsTaken;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			String[] inp1 = br.readLine().split(" ");
			int n = Integer.parseInt(inp1[0]);//friends
			int m = Integer.parseInt(inp1[1]);//presents
			presentsTaken = new boolean[m+1]; // used to keeping track of presents sequence
			numFriends = n;
			boolean emptyPreference = false;
			friendsPreferences = new ArrayList[n];
			for(int i=0;i<n;i++){
				friendsPreferences[i] = new ArrayList<Integer>();
				String[] inp2 = br.readLine().split(",");
				if(!(inp2[0].equals(""))){
					for(int j=0; j<inp2.length; j++){
						String[] option = inp2[j].split("-");
						//System.out.println(option[0]+" "+option.length);
						if(option.length == 1 ){
							friendsPreferences[i].add(Integer.parseInt(inp2[j]));
						}
						else{
							for(int k=Integer.parseInt(option[0]); k<=Integer.parseInt(option[1]); k++)
								friendsPreferences[i].add(k);	
						}

					}
				}
				else{ // one of the friend has no preference, hence criteria not satisfied
					emptyPreference = true;
				}
			}
			if(!emptyPreference && n<=m){
				for(Integer preference:friendsPreferences[0]){
					if(friendsSatisfied)
						break;
					presentsTaken[preference] = true;
					checkCriteria(1);
					presentsTaken[preference] = false;
				}
			}
			StringBuilder sb = new StringBuilder("Case #");
			sb.append(tc);
			sb.append(": ");
			sb.append(friendsSatisfied?"yes":"no");
			System.out.println(sb.toString());
			friendsSatisfied = false;
			br.readLine();
		}

	}
	
	static void checkCriteria(int friend){
		if(numFriends == friend){ // no more Friend remaining (Program will only reach here if all the friends gets present)
			friendsSatisfied = true;
			return;
		}
		else if(friendsSatisfied){// some options sequence already valid
			return;
		}
		else{
			for(Integer preference:friendsPreferences[friend]){
				if(friendsSatisfied)
					break;
				if(!(presentsTaken[preference])){
					presentsTaken[preference] = true;// add gift to gifts taken list
					checkCriteria(friend+1);
					presentsTaken[preference] = false;// after evualvating this option, remove it from the giftsTaken list, so that new preference will replace this one.
				}
			}
		}
		return;
	}

}
