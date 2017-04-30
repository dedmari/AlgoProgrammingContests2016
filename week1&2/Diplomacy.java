import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//insert  DisjointUnionSets class

public class Diplomacy {

	public static void main(String[] args) throws NumberFormatException, IOException, Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t= Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++){
			String[] input1;
			List<Integer> friendsListLeft = new ArrayList<Integer>();
			List<Integer> enemiesListLeft = new ArrayList<Integer>();
			List<Integer> friendsListRight = new ArrayList<Integer>();
			List<Integer> enemiesListRight = new ArrayList<Integer>();
			Set<Integer> hatesLeaSet = new HashSet<Integer>();
			Map<Integer, Set<Integer>> hatesSet = new HashMap();// hate set of each country
			input1  = br.readLine().split(" ");// input1[0]-->no. of countries, input1[1]-->no. of interactions between countries
			DisjointUnionSets dus = new DisjointUnionSets(Integer.parseInt(input1[0]));// initializing the set for union and find operations
			int numberOfFriends = 1;// counting leas country as friend
			for (int j=1;j<=Integer.parseInt(input1[1]);j++){
				String[] interactions = br.readLine().split(" ");// interactions[0]--> A/F
				if(interactions[0].equals("A")){
					enemiesListLeft.add(Integer.parseInt(interactions[1])-1);
					enemiesListRight.add(Integer.parseInt(interactions[2])-1);
					// inspired from http://stackoverflow.com/questions/20128160/can-i-create-an-array-of-sets
					if(hatesSet.get(Integer.parseInt(interactions[1])-1) == null)
			        {
						hatesSet.put(Integer.parseInt(interactions[1])-1, new HashSet()); 
			        }
					Set<Integer> wordContainer = hatesSet.get(Integer.parseInt(interactions[1])-1);
			        wordContainer.add(Integer.parseInt(interactions[2])-1);//adding interactions[2] in enemy set of interaction[1] 
			        
			        if(hatesSet.get(Integer.parseInt(interactions[2])-1) == null)
			        {
						hatesSet.put(Integer.parseInt(interactions[2])-1, new HashSet()); 
			        }
					wordContainer = hatesSet.get(Integer.parseInt(interactions[2])-1);
			        wordContainer.add(Integer.parseInt(interactions[1])-1);//adding interactions[1] in enemy set of interaction[2]
					if(Integer.parseInt(interactions[1]) == 1){// interatcion[2] directly hates lea
						hatesLeaSet.add(Integer.parseInt(interactions[2])-1); //-1 coz to make it 0 indexed 
					}
					else if(Integer.parseInt(interactions[2]) == 1){
						hatesLeaSet.add(Integer.parseInt(interactions[1])-1); //-1 coz to make it 0 indexed
					}
				}
				else if(interactions[0].equals("F")){
					friendsListLeft.add(Integer.parseInt(interactions[1])-1);
					friendsListRight.add(Integer.parseInt(interactions[2])-1);
					dus.union(Integer.parseInt(interactions[1])-1, Integer.parseInt(interactions[2])-1);
				}
			}
			for(int k = 0;k< Integer.parseInt(input1[0]) ; k++){// two times to 
				for(int j = 1;j< Integer.parseInt(input1[0]) ; j++){
					if(dus.find(0) != dus.find(j)){// if not friends than only possible that enemies of j can be friend of 0
						if(hatesSet.get(0) != null && hatesSet.get(0).contains(j)){// if 0 is enemy of j, than only j's enemies are friends of 0
							if(hatesSet.get(j) != null){
								for(Integer entrie:hatesSet.get(j)){
									dus.union(0,entrie);// adding enemies of j to friend list of 0 and vis-versa
									hatesSet.get(0).addAll(hatesSet.get(entrie));// enemies of friends of 0 are 0's enemies as well
									hatesSet.get(entrie).addAll(hatesSet.get(0));
								}
							}
						}
					}
					else{
						// enemies of friends of 0 ------ updating hateSet of 0 and j in other step
						if(hatesSet.get(0) == null)// hatesSet doesn't exists for 0
				        {
							hatesSet.put(0, new HashSet());
				        }
						if(hatesSet.get(j) != null){// only put value in 0 if j has enemies
			            	hatesSet.get(0).addAll(hatesSet.get(j));
			            }
						// updating hatesSet of friends of 0 
						if(hatesSet.get(j) == null)// hatesSet doesn't exists for 0
				        {
							hatesSet.put(j, new HashSet());
				        }
						if(hatesSet.get(0) != null){// only put value in 0 if j has enemies
			            	hatesSet.get(j).addAll(hatesSet.get(0));
			            }
					}
				}
			}
			
			for(int j=1;j<Integer.parseInt(input1[0]);j++){
				if(dus.find(j) == dus.find(0)){// counting all friends of 0	
					numberOfFriends++;
				}
			}
			if (((double)numberOfFriends) > (Double.parseDouble(input1[0]) / 2.0)){
				System.out.println("Case #"+i+": yes");
			}
			else {
				System.out.println("Case #"+i+": no");
			}
//			// checking hatesSet of all countries  // it is working
//			for(int j = 0; j< Integer.parseInt(input1[0]);j++){
//				Set<Integer> wordContainer =  hatesSet.get(j);
//				System.out.println("hate list of country: "+j);
//				for(Integer enemies:wordContainer){
//					System.out.print(" "+enemies);
//				}
//				System.out.println();
//			}
//			// have to implement union find ----pending
//			for(int j = 0; j < enemiesListRight.size();j++){
//				if(hatesLeaSet.contains(enemiesListRight.get(j))){//checking if other countries hate the country hated by lea than make lea and other country friends
//					dus.union(enemiesListLeft.get(j)-1, 0);
//				}
//				else if(hatesLeaSet.contains(enemiesListLeft.get(j))){
//					dus.union(enemiesListRight.get(j)-1, 0);
//				}
//			}
//			for(int j = 1;j<Integer.parseInt(input1[0])-1;j++){
//				
//			}
			//used to check which countries are direct or indirect friends of Lea (0) based on only F interactions
//			for(int j=1;j<Integer.parseInt(input1[0]);j++){
//				if(dus.find(j) == dus.find(0)){	
//					System.out.println("Case #"+i+" :"+(j+1)+ "is friend of Lea");
//				}	
//			}
			//end of check
//			
//			System.out.println("Direct Hates SET");
//			hatesLeaSet.forEach(System.out::println);// java8 notation1
//			System.out.println("Enemies List");
//			for(int j =0; j< enemiesListRight.size(); j++){
//				System.out.println(" "+ enemiesListLeft.get(j)+ " "+enemiesListRight.get(j));
//			}
			br.readLine();// reading blank space 
		}
	}
}
