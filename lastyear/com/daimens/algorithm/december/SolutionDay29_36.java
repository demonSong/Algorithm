package com.daimens.algorithm.december;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Demon Song
 * 36.Valid Sudoku
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * See http://sudoku.com.au/TheRules.aspx
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * A partially filled sudoku which is valid.
 *
 */
public class SolutionDay29_36 {
	
	//依次检查对应的行，列，块是否有重复的元素
	public boolean isValidSudoku(char[][] board){
		List<Set<Character>> r1 = new ArrayList<Set<Character>>();
		List<Set<Character>> c1 = new ArrayList<Set<Character>>();
		List<Set<Character>> s1 = new ArrayList<Set<Character>>();
		
		for(int i =0; i<9; i++){
			r1.add(new HashSet<Character>());
			c1.add(new HashSet<Character>());
			s1.add(new HashSet<Character>());
		}
		int n = board.length;
		for(int i =0; i<n;i++){
			for(int j =0; j<n;j++){
				char c = board[i][j];
				if(c=='.') continue;
				else if(r1.get(i).contains(c) || c1.get(j).contains(c) || s1.get(i/3*3 + j/3).contains(c)) return false;
				else{
					r1.get(i).add(c);
					c1.get(j).add(c);
					s1.get(i/3*3+j/3).add(c);
				}
			}
		}
		return true;
	}

}
