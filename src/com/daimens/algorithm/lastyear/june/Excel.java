package com.daimens.algorithm.june;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 	
 * 			
 * 631. Design Excel Sum Formula
 * 
 *         Your task is to design the basic function of Excel and implement the
 *         function of sum formula. Specifically, you need to implement the
 *         following functions:
 * 
 *         Excel(int H, char W): This is the constructor. The inputs represents
 *         the height and width of the Excel form. H is a positive integer,
 *         range from 1 to 26. It represents the height. W is a character range
 *         from 'A' to 'Z'. It represents that the width is the number of
 *         characters from 'A' to W. The Excel form content is represented by a
 *         height * width 2D integer array C, it should be initialized to zero.
 *         You should assume that the first row of C starts from 1, and the
 *         first column of C starts from 'A'.
 * 
 * 
 *         void Set(int row, char column, int val): Change the value at C(row,
 *         column) to be val.
 * 
 * 
 *         int Get(int row, char column): Return the value at C(row, column).
 * 
 * 
 *         int Sum(int row, char column, List of Strings : numbers): This
 *         function calculate and set the value at C(row, column), where the
 *         value should be the sum of cells represented by numbers. This
 *         function return the sum result at C(row, column). This sum formula
 *         should exist until this cell is overlapped by another value or
 *         another sum formula.
 * 
 *         numbers is a list of strings that each string represent a cell or a
 *         range of cells. If the string represent a single cell, then it has
 *         the following format : ColRow. For example, "F7" represents the cell
 *         at (7, F).
 * 
 *         If the string represent a range of cells, then it has the following
 *         format : ColRow1:ColRow2. The range will always be a rectangle, and
 *         ColRow1 represent the position of the top-left cell, and ColRow2
 *         represents the position of the bottom-right cell.
 * 
 * 
 *         Example 1: Excel(3,"C"); // construct a 3*3 2D array with all zero.
 *         // A B C // 1 0 0 0 // 2 0 0 0 // 3 0 0 0
 * 
 *         Set(1, "A", 2); // set C(1,"A") to be 2. // A B C // 1 2 0 0 // 2 0 0
 *         0 // 3 0 0 0
 * 
 *         Sum(3, "C", ["A1", "A1:B2"]); // set C(3,"C") to be the sum of value
 *         at C(1,"A") and the values sum of the rectangle range whose top-left
 *         cell is C(1,"A") and bottom-right cell is C(2,"B"). Return 4. // A B
 *         C // 1 2 0 0 // 2 0 0 0 // 3 0 0 4
 * 
 *         Set(2, "B", 2); // set C(2,"B") to be 2. Note C(3, "C") should also
 *         be changed. // A B C // 1 2 0 0 // 2 0 2 0 // 3 0 0 6 Note: You could
 *         assume that there won't be any circular sum reference. For example,
 *         A1 = sum(B1) and B1 = sum(A1). The test cases are using double-quotes
 *         to represent a character. Please remember to RESET your class
 *         variables declared in class Excel, as static/class variables are
 *         persisted across multiple test cases. Please see here for more
 *         details.
 *
 */
public class Excel {
	
	int[][] table;
	Map<String, String[]> mem = new HashMap<>();
	
	public Excel(int H, char W) {
        table = new int[H][W - 'A' + 1];
	}
    
    public void set(int r, char c, int v) {
        table[r-1][c - 'A'] = v;
        if (mem.containsKey(""+c+r)) mem.remove(""+c+r);
    }
    
	public int get(int r, char c) {
		if(mem.containsKey(""+r+c)) sum(r,c,mem.get(""+r+c));
		return table[r - 1][c - 'A'];
    }
    
    public int sum(int r, char c, String[] strs) {
    	mem.put(""+c+r,strs);
    	int sum = 0;
    	for (int i = 0; i < strs.length; ++i){
    		String cell = strs[i];
    		if (cell.contains(":")){
    			String[] cells = cell.split(":");
    			char topC = cells[0].charAt(0);
    			char botC = cells[1].charAt(0);
    			int topR = 0;
    			for (int j = 1; j < cells[0].length(); ++j) topR = topR * 10 + cells[0].charAt(j) - '0';
    			int botR = 0;
    			for (int j = 1; j < cells[1].length(); ++j) botR = botR * 10 + cells[1].charAt(j) - '0';
    			for (int k = topR; k <= botR; ++k){
    				for (char l = topC; l <= botC; ++l){
    					sum += get(k, l);
    				}
    			}
    		}
    		else{
    			char col = cell.charAt(0);
    			int row = 0;
    			for (int j = 1; j < cell.length(); ++j) row = row * 10 + cell.charAt(j) - '0';
    			sum += get(row, col);
    		}
    	}
    	return table[r-1][c - 'A'] = sum;
    }
}
