package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Alarm3 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		int[][]  validLH = {{0},{0,1},{2}};
		int[][]  validMH = {{0},{0,1,3},{2},{3}};// when Lh value is 2 else it is same as matrix values for 0 and 1 values of validLH
		int[][]  validLM = {{0},{0,1,3,4},{2},{3},{4},{5}};
		Integer[][]  validMatrix = {{0},{0,1,3,4},{2},{3},{4},{5}};
		int[][]  matrix = {{0,8},{0,1,3,4,7,8,9},{2,8},{3,8,9},{4,8,9},{5,6,8,9},{6,8},{0,3,7,8,9},{8},{8,9}};
		Integer[][]  matrix1 = {{0,8},{0,1,3,4,7,8,9},{2,8},{3,8,9},{4,8,9},{5,6,8,9},{6,8},{0,3,7,8,9},{8},{8,9}};
		String[][] valid_next_elements = new String[10][10];
		valid_next_elements[0][8] = "0,1,7";
		
		valid_next_elements[1][0] = "1";
		valid_next_elements[1][3] = "1";
		valid_next_elements[1][4] = "1,7";
		valid_next_elements[1][7] = "1,4";
		valid_next_elements[1][8] = "1";
		valid_next_elements[1][9] = "1";
		
		valid_next_elements[2][8] = "2";
		
		valid_next_elements[3][8] = "1,3";
		valid_next_elements[3][9] = "1,2,3,7";
		
		valid_next_elements[4][8] = "1,4";
		valid_next_elements[4][9] = "1,4";
		
		valid_next_elements[5][6] = "1,3,4,5,7,9";
		valid_next_elements[5][8] = "5";
		valid_next_elements[5][9] = "2,5,6";
		
		valid_next_elements[6][8] = "5,6";
		
		valid_next_elements[7][0] = "1,7";
		valid_next_elements[7][3] = "1,7";
		valid_next_elements[7][8] = "1,7";
		valid_next_elements[7][9] = "1,7";
		
		valid_next_elements[9][8] = "1,3,4,5,7";
		List<Integer[]> list = Arrays.asList(matrix1);
		List validMM = new ArrayList();
		for(Integer[] array : list){
			validMM.add( Arrays.asList(array) );
		}
		List<Integer[]> list2 = Arrays.asList(validMatrix);
		List validLMList = new ArrayList();
		for(Integer[] array : list2){
			validLMList.add( Arrays.asList(array) );
		}
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++)
		{
			int n,j;
			boolean inConsistent = false;
			n=Integer.parseInt(br.readLine());//number of time entries
			int graph[][]= new int[n][4];//matrix to hold all time values
			for(j=0;j<n;j++)// getting input
			{
				String inp1 = br.readLine();
				if(!inConsistent){// if there is any inconsistant entry than the result is none hence no need to do any further processing
					int tlH = Integer.parseUnsignedInt(Character.toString(inp1.charAt(0)));
					if(tlH != 0 && tlH != 1 && tlH != 2 && tlH != 7){ // if tlh is any other value than the valid time, hence no need to check anything , result will be 'none'
						inConsistent = true;
					}
					else{
						graph[j][0] = tlH;
						int tmH = Integer.parseUnsignedInt(Character.toString(inp1.charAt(1)));
						if(tlH==2 && tmH != 0 && tmH != 1 && tmH != 2 && tmH != 7 && tmH != 3){
							inConsistent = true;
						}
						else {
							graph[j][1] = tmH;
							int tlM = Integer.parseUnsignedInt(Character.toString(inp1.charAt(3)));
							if(tlM == 6 || tlM == 8 || tlM == 9){
								inConsistent = true;
							}
							else{
								graph[j][2] = tlM;
								int tmM =Integer.parseUnsignedInt(Character.toString(inp1.charAt(4)));
								graph[j][3] = tmM;
							}
						}
					}
				}

			}

			System.out.println("Case #"+i+":");
			if(inConsistent){
				System.out.println("none");
			}
			else{
				boolean inValidOption = false;
				inConsistent = true;
				int lHr = graph[0][0];
				if(lHr == 7){//For mHour, value of 7 can only be 0 and not 7
					lHr = 0;
				}
				int[] lHrchoices = validLH[lHr];

				int[] mHrchoices = null;
				int mHr = graph[0][1];
				if(lHr == 2){
					if(mHr==7){
						int  arr[] = {0,3};
						mHrchoices = arr;
					}
					else{
						mHrchoices = validMH[mHr];
					}
				}
				else{
					mHrchoices = matrix[mHr];
				}
				int lMin = graph[0][2];
				int[] lMinchoices = null;//left value of minute
				if(lMin==7){
					int  arr[] = {0,3};
					lMinchoices=arr;
				}
				else{
					lMinchoices=validLM[lMin];
				}	
				int mMinValid[] = matrix[graph[0][3]];
				for(int lH:lHrchoices){//getting all LSB values of hour available for given set
					for(int mH: mHrchoices){
						for(int lM: lMinchoices){
							for(int mM: mMinValid){
								String validNxtElements=null;
								if(mM==graph[0][3]){//if element and not option are used than all elements are valid for this case
									validNxtElements="0,1,2,3,4,5,6,7,8,9";
								}
								else{
									validNxtElements = valid_next_elements[graph[0][3]][mM];
								}
								if(n==1){
									inValidOption = false;
								}
								else{
									int tmpMM = mM+1;
									int tempLM = lM;
									int tempMH=mH;
									int tempLH = lH;
									for(int p=0;p<n-1;p++){
										if( tmpMM == 10){
											tmpMM = 0;
											tempLM = lM+1;
											if(tempLM == 6){
												tempLM=0;
												tempMH = mH+1;
												if(lH==2){ 
													if(tempMH==4){ 
														tempMH=0;
														 tempLH = lH+1;
														 if(tempLH==3){
															 tempLH=0;
														 }
													}
												}
												else{//when Lhour can be 0 / 1 at that time Mhour can have all possible values as Minutes 
													if(tempMH==10){
														tempMH=0;
														tempLH = lH+1;
														if(tempLH==3){
															 tempLH=0;
														 }
													}
												}
											}
										}
										
										if(!((List<Integer>)validMM.get(graph[p+1][3])).contains(tmpMM)){// it can be improved by first check if it is equal to graph[p+1][3] otherwise go for checking from options
											inValidOption = true;
											break;
										}
										else if(graph[p+1][3]!=tmpMM){
											char elem = Integer.toString(graph[p+1][3]).charAt(0);//used to set next valid element
											if(validNxtElements.indexOf(elem)==-1){
												inValidOption = true;
												break;
											}
											validNxtElements = valid_next_elements[graph[p+1][3]][tmpMM];// setting the new valid elements
										}
										if(graph[p+1][2]==7){// if next LMinute is 7 than tempLM should be either 0 or 3
											if(tempLM != 0 && tempLM != 3){
												inValidOption = true;
												break;
											}
										}
										else if(!((List<Integer>)validLMList.get(graph[p+1][2])).contains(tempLM)){
											inValidOption = true;
											break;
										}
										
										if(lH==2){
											if(graph[p+1][1]==7){
												if(tempMH != 0 && tempMH != 3){
													inValidOption = true;
													break;
												}
											}
											else if(graph[p+1][1]==1){
												if(tempMH != 0 && tempMH  != 3 && tempMH  != 1){
													inValidOption = true;
													break;
												}
												
											}
											else if(graph[p+1][1]!=tempMH ){
												inValidOption = true;
												break;
											}
										}
										else{
											if(!((List<Integer>)validMM.get(graph[p+1][1])).contains(tempMH)){
												inValidOption = true;
												break;
											}
										}
										
										if(graph[p+1][0]==7){
											if( tempLH != 0){
												inValidOption = true;
												break;
											}
										}
										else if(graph[p+1][0]==1){
											if(tempLH != 0 && tempLH  != 1){
												inValidOption = true;
												break;
											}
										}
										else if(graph[p+1][0]!=tempLH){
											inValidOption = true;
											break;
										}
										tmpMM++;
									}
								}
								if(!inValidOption){
									inConsistent = false;
									System.out.println(""+lH+mH+":"+lM+mM);
								}
								inValidOption=false;
							}
						}
					}
				}
				if(inConsistent){
					System.out.println("none");
				}
			}
			br.readLine();
		}

	}
}
