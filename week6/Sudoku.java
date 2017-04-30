package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//code inspired from geeks for geeks, peter norvig's explanation on sudoku
public class Sudoku {
	 static int N = 9;
	 static int grid[][] ;
	 static class Cell {
	  int row, col;
	  public Cell(int row, int col) {
	   super();
	   this.row = row;
	   this.col = col;
	  }
	  public String toString() {
	   return "Cell [row=" + row + ", col=" + col + "]";
	  }
	 };
	 static boolean isValid(Cell cell, int value) {

	  if (grid[cell.row][cell.col] != 0) {
	   throw new RuntimeException(
	     "Cannot call for cell which already has a value");
	  }
	  for (int c = 0; c < 9; c++) {
	   if (grid[cell.row][c] == value)
	    return false;
	  }
	  for (int r = 0; r < 9; r++) {
	   if (grid[r][cell.col] == value)
	    return false;
	  }

	  int x1 = 3 * (cell.row / 3);
	  int y1 = 3 * (cell.col / 3);
	  int x2 = x1 + 2;
	  int y2 = y1 + 2;

	  for (int x = x1; x <= x2; x++)
	   for (int y = y1; y <= y2; y++)
	    if (grid[x][y] == value)
	     return false;
	  return true;
	 }
	 static Cell getNextCell(Cell cur) {

	  int row = cur.row;
	  int col = cur.col;
	  col++;
	  if (col > 8) {
	   col = 0;
	   row++;
	  }

	  if (row > 8)
	   return null; // reached end

	  Cell next = new Cell(row, col);
	  return next;
	 }

	 static boolean drive(Cell cur) {

	  if (cur == null)
	   return true;

	  if (grid[cur.row][cur.col] != 0) {

	   return drive(getNextCell(cur));
	  }

	  for (int i = 1; i <= 9; i++) {
	   boolean valid = isValid(cur, i);

	   if (!valid) 
	    continue;
	   grid[cur.row][cur.col] = i;

	   boolean solved = drive(getNextCell(cur));
	   if (solved)
	    return true;
	   else
	    grid[cur.row][cur.col] = 0; // reset
	  }

	  return false;
	 }

	 public static void main(String[] args) throws NumberFormatException, IOException {
		 
		 BufferedReader br = new BufferedReader (new InputStreamReader (System .in));
			int cases = Integer.parseInt(br.readLine());

			for (int i = 1; i <= cases; i++) {
				
				grid = new int[N][N];
				
				for(int row=0;row<9;row++){
					
					String line = br.readLine();
					
					for(int col=0;col<9;col++){
						
						if(line.charAt(col) == '?'){
							grid[row][col] = 0;

						}else{
						
						grid[row][col] = Character.getNumericValue(line.charAt(col));
						
						}
					}
					
				}
				
				drive(new Cell(0, 0));
				System.out.println("Case #"+i+":");
				  pGrid(grid);
				  System.out.println();
				  	
				br.readLine();
			} 
	 }

	 static void pGrid(int grid[][]) {
	  for (int row = 0; row < N; row++) {
	   for (int col = 0; col < N; col++)
	    System.out.print(grid[row][col]);
	   System.out.println();
	  }
	 }
	}
