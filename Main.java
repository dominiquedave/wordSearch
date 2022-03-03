/*Written by: Dave Dominique */
import java.io.*;
import java.util.*;

public class Main {
    
    //clockwise movements through the matrix
    public static int controls[][]=  {	{0, 1}, {1, 0},{0, -1}, {-1, 0},
									  	{1, 1}, {1, -1}, {-1, -1}, {-1, 1}
									  };
		
    static void output(int R, int C, char[][] CharArray, boolean[][] sol) {
    	for(int i=0; i <CharArray.length; i++ ) {
			System.out.print( "[");
			for(int a=0; a <	CharArray[0].length; a++ ) {
				if(sol[i][a] == true) {
					System.out.print( CharArray[i][a]);
				}
				else {
					System.out.print(" ");
				}
				
				if (a!= CharArray[0].length-1)
				{
					System.out.print(", ");
				}
			}
			System.out.println( "]");
		}
		
		for(int k = 0; k < R; k++) {
			for(int l=0; l < C; l++) {
				sol[k][l]= false;
			}
		}
		System.out.print("\n");
    }
    
	static boolean solve(int idx, int row, int col, char[][] CharArray, boolean[][] sol, String word) 
	{
		//base case
		if(idx == word.length()) {
			return true;
		}
		
		boolean flag = false;
		
		//ACTUAL BT SOLUTION
		for(int i = 0; i < 8 ; i++ ) {
			
			int newr = row + controls[i][0];
			int newc = col + controls[i][1];

			//out of bouds: skip
			if(newr < 0 || newr >= 	CharArray.length ) {
				continue;
			}
			//out of bouds: skip
			if(newc < 0 || newc >= 	CharArray[0].length) {
				continue;
			}
			
			//letter cant be already used
			if(sol[newr][newc] == true) {
				continue;
			}
			
			//letter has to be what were looking for
			if(CharArray[newr][newc] != word.charAt(idx)){
				continue;
			}
		
			sol[newr][newc] = true;
			
		    //System.out.println("found "+ CharArray[newr][newc]);
			if(solve(idx +1, newr, newc, CharArray, sol, word)) {
				return true;
			}
			
			sol[newr][newc] = false;
		}
		
		return false;
	}									
	
    static void find(int idx, int R, int C, String word, char[][] CharArray)
	{	
	    //solution matrix to track answers
		boolean found = true;
		boolean[][] sol = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			for(int j=0; j < C; j++) {
				sol[i][j]= false;
			}
		}
		
		System.out.println("Looking for " + word);
		for(int i = 0; i < R; i++) {
			for(int j=0; j < C; j++) {
				//if char match found
			    if(CharArray[i][j] == word.charAt(idx)) {
					sol[i][j] = true; 
					//System.out.println("found "+ CharArray[i][j]);
					found = solve((idx+1) , i, j, CharArray,sol, word);
					
					if(found == true) {
						output(R, C, CharArray, sol);
						return;
					}
					else {
						sol[i][j] = false; 
					}
						
					
				}
			    
					
			    
			}
		}
		System.out.println(word +" not found!" );
		return;
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{ 
	    File file = new File("in.txt"); 
		Scanner myScan = new Scanner(file);
		
		int k = myScan.nextInt();
		
		for(int i = 0; i<k; i++) {
		
			System.out.println("Test#" + (i+1));
			int R = myScan.nextInt();
			int C = myScan.nextInt();
			int N = myScan.nextInt();
			
			//fill char matrix
			char[][] CharArray = new char[R][C];
			for(int row = 0; row < R; row++ ) {
				for(int col = 0; col < C; col++) {
					
					CharArray[row][col] = myScan.next().charAt(0);
				}
			}
			
			//look in the matrix for a given word
			for(int f = 0; f< N; f++) {
				String word = myScan.next();
				find(0, R, C, word, CharArray);
				
			}	
			System.out.print("\n");
		}
	}
}
