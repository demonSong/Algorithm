package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 221.Maximal Square
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * For example, given the following matrix:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 4.
 *
 */
public class SolutionDay18_221 {
	
	
	
	//1. 全为1的情况下，该如何更新该dp
	//2. 出现不符合情况的几个状态时，该如何排除
	public int maximalSquare(char[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return 0;
		
		int n = matrix.length, m = matrix[0].length;
		
		int[][] dp = new int[n+1][m+1];
		
		int max = 0;
		for(int i = 0; i < n; i++){
			for (int j = 0; j < m; j++){
				if (matrix[i][j] == '1'){
					dp[i+1][j+1] = Math.min(dp[i][j+1], Math.min(dp[i+1][j], dp[i][j])) + 1;
					//状态记录，该dp[i][j]的状态表示，在端点 i 和 j 中能够取到的最大square?
					max = Math.max(max, dp[i+1][j+1]);
				}
			}
		}
		
		
		return max * max;
    }
	
	
}
