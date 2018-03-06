package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         51.N - Queens
 * 
 *         The n-queens puzzle is the problem of placing n queens on an n×n
 *         chessboard such that no two queens attack each other.
 * 
 * 
 * 
 *         Given an integer n, return all distinct solutions to the n-queens
 *         puzzle.
 * 
 *         Each solution contains a distinct board configuration of the
 *         n-queens' placement, where 'Q' and '.' both indicate a queen and an
 *         empty space respectively.
 * 
 *         For example, There exist two distinct solutions to the 4-queens
 *         puzzle:
 * 
 *         [ [".Q..", // Solution 1 "...Q", "Q...", "..Q."],
 * 
 *         ["..Q.", // Solution 2 "Q...", "...Q", ".Q.."] ]
 *
 */
public class SolutionDay22_L0051 {
	
	public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] cs = new char[n][n];
        for (int i = 0; i < n; i++){
        	Arrays.fill(cs[i], '.');
        }
        
        boolean[] cols = new boolean[n];
        boolean[] diag = new boolean[2*n];
        boolean[] riag = new boolean[2*n];
		backTrack(ans, cs, n, 0, cols, diag, riag);
        return ans;
    }
	
	private void backTrack(List<List<String>> ans, char[][] path, int n, int start, boolean[] cols, boolean[] diag, boolean[] riag){
		if (start == n){
			List<String> pp = new ArrayList<>();
			for (int i = 0; i < path.length; i++){
				pp.add(new String(path[i]));
			}
			ans.add(new ArrayList<>(pp));
			return;
		}else{
			for (int i = 0; i < n; i++){
				if (!cols[i] && !diag[start + i] && !riag[start-i+n]){
					path[start][i] = 'Q';
					cols[i] = true;
					diag[start + i] = true;
					riag[start - i + n] = true;
					backTrack(ans, path, n, start+1, cols, diag, riag);
					path[start][i] = '.';
					cols[i] = false;
					diag[start + i] = false;
					riag[start - i + n] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SolutionDay22_L0051 day = new SolutionDay22_L0051();
		day.solveNQueens(4);
	}

}
