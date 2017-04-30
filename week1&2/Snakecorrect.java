import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Snakecorrect {
private static  String calculateOrientation(String currentDirection, String currentCoordinates, char moveStep, int numGrids) {
		
		String[] coordinateArray = currentCoordinates.split(",");
		int curColumn = Integer.parseInt(coordinateArray[0]);
		int curRow = Integer.parseInt(coordinateArray[1]);
        
		int nCol = 0;
		int nRow = 0;
		String newDirection = " ";		
		if(currentDirection.equals("Right")){
			
			 if(moveStep == 'F'){
				 
				 nRow = curRow;
				 nCol = Math.floorMod(((curColumn + 1)) ,numGrids);;

				 newDirection = "Right";
				 
			    	
			    }else if (moveStep == 'L') {
			    	
			    	nRow =	Math.floorMod((curRow -1) ,numGrids);
					 nCol = curColumn;	
					 newDirection = "Up";
					
				}else if(moveStep == 'R'){

			    	nRow =	Math.floorMod((curRow +1) ,numGrids);
					 nCol = curColumn ;
					 newDirection = "Down";

				}
			
		}else if (currentDirection.equals("Left")) {
			
			 if(moveStep == 'F'){
				 
				 nRow = curRow;
				 nCol = Math.floorMod(((curColumn - 1)) ,numGrids);;

				 newDirection = "Left";
			    	
			    }else if (moveStep == 'L') {

			    	 nRow =	Math.floorMod((curRow +1) ,numGrids);
					 nCol = curColumn ;
					 newDirection = "Down";

					
				}else if(moveStep == 'R'){
					
				    	nRow =	Math.floorMod((curRow -1) ,numGrids);

					 nCol = curColumn;	
					 newDirection = "Up";
					
				}
			
		}else if (currentDirection.equals("Up")){
			
			 if(moveStep == 'F'){
			     nRow =	Math.floorMod((curRow -1) ,numGrids);
				 nCol = curColumn /*% numGrids*/;	
				 newDirection = "Up";
			    	
			    }else if (moveStep == 'L') {
					 nRow = curRow /*% numGrids*/;
				     nCol =	Math.floorMod((curColumn -1) ,numGrids);
					 newDirection = "Left";					
				}else if(moveStep == 'R'){
					
					 nRow = curRow /*% numGrids*/;
				     nCol =	Math.floorMod((curColumn +1) ,numGrids);
					 newDirection = "Right";
					
				}
			
		}else if (currentDirection.equals("Down")){
			
			 if(moveStep == 'F'){
				 
			     nRow =	Math.floorMod((curRow +1) ,numGrids);
				 nCol = curColumn ;
				 newDirection = "Down";
				 
			    	
			    }else if (moveStep == 'L') {
			    	
			    	 nRow = curRow;
				    	nCol =	Math.floorMod((curColumn +1) ,numGrids);

					 newDirection = "Right";
					
				}else if(moveStep == 'R'){
					
					 nRow = curRow;
				     nCol =	Math.floorMod((curColumn -1) ,numGrids);
					 newDirection = "Left";
				}
		}
		
		return newDirection+"@"+nCol+","+nRow;
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException, Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int cases = Integer.parseInt(br.readLine());
		for(int i=1; i<=cases; i++){
			String input1 = br.readLine();
			String[] input1Array = input1.split(" ");
			int numGrids = Integer.parseInt(input1Array[0]);
			int numberOfFoods = Integer.parseInt(input1Array[1]);
			int gameScheme[][] = new int[numGrids][numGrids];
			String input2 = br.readLine();
			String[] input2Array = input2.split(" ");
			int columStart = Integer.parseInt(input2Array[0])-1;
			int rowStart = Integer.parseInt(input2Array[1])-1;
			for(int index=0; index< numberOfFoods; index++){
				String foodLine = br.readLine();
				String[] foodArray = foodLine.split(" ");
				int ci = Integer.parseInt(foodArray[0])-1;
				int ri = Integer.parseInt(foodArray[1])-1;
				int wi = Integer.parseInt(foodArray[2]);
				int hi = Integer.parseInt(foodArray[3]);
				int tempCi = ci;
				int tempRi = ri;
				for(int index2 = 0; index2<hi; index2++){
					for(int index3 = 0; index3<wi; index3++){
						tempCi = tempCi % numGrids; 
						tempRi = tempRi % numGrids;
						gameScheme[tempRi][tempCi] = 1;
						tempCi++;
					}
					tempRi++;
					tempCi = ci;
				}				
			}
			gameScheme[rowStart][columStart] = 5;
			String input3 = br.readLine();
			String[] input3Array = input3.split(" ");
			int len = Integer.parseInt(input3Array[0]);
			String gameString = input3Array[1];		
			List<String> snakePos = new ArrayList<String>();
			int numMoves = 0;
			int totalFood = 0;
			String currentDirection = "Right"; 
			String currentCoordinates = columStart+","+rowStart;
			snakePos.add(currentCoordinates); 
			for (int charIndex = 0; charIndex < len; charIndex++){
			    char moveStep = gameString.charAt(charIndex);   
			   String newOrientation =  calculateOrientation(currentDirection,currentCoordinates,moveStep,numGrids);
			   String[] orienTemp = newOrientation.split("@");
			   currentDirection = orienTemp[0];
			   currentCoordinates = orienTemp[1];
			   int curColumn = Integer.parseInt(currentCoordinates.split(",")[0]);
			   int curRow = Integer.parseInt(currentCoordinates.split(",")[1]);
			   if(gameScheme[curRow][curColumn] != 1){
					  String backSnake = snakePos.get(0);
				   snakePos.remove(0);
					  gameScheme[Integer.parseInt(backSnake.split(",")[1])][Integer.parseInt(backSnake.split(",")[0])] = 0;
			   }
			  if( snakePos.contains(currentCoordinates)){
				  break;
			  }else{
				  snakePos.add(currentCoordinates);
				  numMoves ++;
				  if(gameScheme[curRow][curColumn] == 1){
					  totalFood++;
				  }
				  gameScheme[curRow][curColumn] = 5;
	  
			  }
			    
			}
			
			System.out.println("Case #"+i+": " + numMoves + " "+ totalFood);
			if(i!=cases){
				br.readLine();
			}
		}
	}
			
}