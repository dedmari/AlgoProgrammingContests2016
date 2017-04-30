package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AlarmClock {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		Integer[][]  matrix = {{0,8},{0,1,3,7,8,9},{2,8},{3,8,9},{4,8,9},{5,6,8,9},{6,8},{0,3,7,8,9},{8},{8,9}};
		List<Integer[]> list = Arrays.asList(matrix);
	    List validMM = new ArrayList();
	    for(Integer[] array : list){
	    	validMM.add( Arrays.asList(array) );
	    }
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++)
		{
			int n,j;
			boolean inConsistent = false;
			n=Integer.parseInt(br.readLine());
			int inpmMin = -1;
			int nomMins = 0;
			//String[] time = new String[n];  
//			int[][]  validLH = {{0},{0,1},{2}};
//			int[][]  validMH = {{0},{0,1,3},{2},{3}};
//			int[][]  validLM = {{0},{0,1,3},{2},{3},{4},{5}};
			
		
		   
		    	//System.out.println(validMM.get(0));
		    
//			int lH=-1;
//			int mH=-1;
//			int lM=-1;
//			int mM=-1;
			Set<Integer> lHour = new HashSet<Integer>();
			//Integer[] lHour = new Integer[4];// valid values are only 0,1,2,7--> 7 can be 0 also that is y it is valid
			Set<Integer> mHour = new HashSet<Integer>();
			//Integer[] mHour = new Integer[20];
			Set<Integer> lMinute = new HashSet<Integer>();
			//Integer[] lMinute = new Integer[20];
			Set<Integer> mMinute = new HashSet<Integer>();
			Integer[] mMin = new Integer[20];
			//int mMin[]=new int[20];
			//int x[];
			for(j=0;j<n;j++)
			{
				String inp1 = br.readLine();
				if(!inConsistent){// if there is any inconsistant entry than the result is none hence no need to do any calculation
					int tlH = Integer.parseUnsignedInt(Character.toString(inp1.charAt(0)));
					if(tlH != 0 && tlH != 1 && tlH != 2 && tlH != 7){ // if tlh is any other value than the time is invalid hence no need to check anything , result will be 'none'
						inConsistent = true;
					}
					else{
						if(tlH == 7)
							lHour.add(0);
						else if(tlH == 1){
							lHour.add(tlH);
							lHour.add(0);//coz zero can be the option when value inserted in 1
						}
						
						else
							lHour.add(tlH);
						int tmH = Integer.parseUnsignedInt(Character.toString(inp1.charAt(1)));
						if(tlH==2 && tmH != 0 && tmH != 1 && tmH != 2 && tmH != 7 && tmH != 3){
							inConsistent = true;
						}
						else {
							if(tmH == 7){
								mHour.add(0);
								mHour.add(3);
							}
							else if(tlH == 1){
								lHour.add(tlH);
								lHour.add(0);//coz zero can be the option when value inserted in 1
								lHour.add(3);//coz zero can be the option when value inserted in 3
							}
							else
								mHour.add(tmH);
							int tlM = Integer.parseUnsignedInt(Character.toString(inp1.charAt(3)));
							if(tlM == 6 || tlM == 8 && tlM == 9){
								inConsistent = true;
							}
							else{
								if(tlM == 7){
									lMinute.add(0);
									lMinute.add(3);
								}
								else if(tlH == 1){
									lHour.add(tlH);
									lHour.add(0);//coz zero can be the option when value inserted in 1
									lHour.add(3);//coz zero can be the option when value inserted in 3
								}
								else
									lMinute.add(tlM);
								
								int tmM =Integer.parseUnsignedInt(Character.toString(inp1.charAt(4)));
								nomMins++;
								//System.out.println();
								mMin[j]= tmM;
								if(j==0){
									inpmMin = tmM;
								}
								//mMin[i]= tmM;
								if(tmM==0){
									mMinute.add(0);
									mMinute.add(8);
								}
								else if(tmM==1){
									mMinute.add(0);
									mMinute.add(1);
									mMinute.add(3);
									mMinute.add(7);
									mMinute.add(8);
									mMinute.add(9);
								}
								else if(tmM==2){
									mMinute.add(2);
									mMinute.add(8);
								}
								else if(tmM==3){
									mMinute.add(3);
									mMinute.add(9);
									mMinute.add(8);
								}
								else if(tmM==4){
									mMinute.add(4);
									mMinute.add(9);
									mMinute.add(8);
								}
								else if(tmM==5){
									mMinute.add(6);
									mMinute.add(9);
									mMinute.add(8);
								}
								else if(tmM==6){
									mMinute.add(6);
									mMinute.add(8);
								}
								else if(tmM==7){
									mMinute.add(0);
									mMinute.add(3);
									mMinute.add(7);
									mMinute.add(8);
									mMinute.add(9);
								}
								else if(tmM==9){
									mMinute.add(8);
									mMinute.add(9);
								}
								else
									mMinute.add(8);
							}
						}
					}
				}
				
			}
			System.out.println();
			System.out.println("Case #"+i+":");
			if(inConsistent){
				System.out.println("none");
			}
			else{
				//int c=0;;
				//inConsistent = true;
//				for(int lH:lHour){//getting all LSB values of hour available for given set
//					for(int mH: mHour){
//						for(int lM: lMinute){
//							for(int mM: mMinute){
//								c++;
//								if(mMLen>c && mMinute.contains((mM+1))){
//									System.out.println(""+lH+mH+":"+lM+mM);
//									inConsistent = false;
//								}
//								else if(!inConsistent && mMLen==c){
//									System.out.println(""+lH+mH+":"+lM+mM);
//								}
//							}
//						}
//					}
//				}
				boolean inValidOption = false;
				for(int lH:lHour){//getting all LSB values of hour available for given set
					System.out.println("inside for lH");
					for(int mH: mHour){
						System.out.println("inside for mH");
						for(int lM: lMinute){
							System.out.println("inside for lM");
							List<Integer> mMinValid = (List<Integer>)validMM.get(mMin[0]);
							for(int mM: mMinValid){
								System.out.println("inside for mM");
								//c++;
								for(int p=1;p<nomMins-1;p++){
//									System.out.println(p);
//									System.out.println(mMin[p]);
									if(mMin[p]==null){
										break;
									}
									int temp = 0;
									if(mMin[p] == 9){
										temp = 0;
									}
									else{
										temp = mMin[p]+1;
									}
									System.out.println("tmp="+temp);
									if(!((List<Integer>)validMM.get(mMin[p+1])).contains(temp)){
										System.out.println("inside if");
										 inValidOption = true;
										 break;
									}
								}
								if(!inValidOption){
									System.out.println(""+lH+mH+":"+lM+mM);
								}
								else{
									inConsistent = true;
								}
								inValidOption=false;
//								if(mMLen>c && mMinute.contains((mM+1))){
//									System.out.println(""+lH+mH+":"+lM+mM);
//									inConsistent = false;
//								}
//								else if(!inConsistent && mMLen==c){
//									System.out.println(""+lH+mH+":"+lM+mM);
//								}
							}
						}
					}
				}
				if(inConsistent){
					System.out.println("none");
				}
				//System.out.println("valid");
//					System.out.print(lHour);
//					System.out.print(mHour);
//					System.out.print(lMinute);
//					System.out.print(mMinute);
				
				
			}
			br.readLine();
		}

	}

}
