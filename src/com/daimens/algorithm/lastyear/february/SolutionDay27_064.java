package com.daimens.algorithm.february;

/**
 * 
 * @author DemonSong
 * 64.Minimum Path Sum
 * Given a m x n grid field with non-negative numbers,find a path from top left to bottom right which 
 * minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 *
 */
public class SolutionDay27_064 {
	
	public int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		
		if( m == 0 && n == 0) return 0;
		
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        
        for (int i = 1; i < m; i++){
        	dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        
        for (int i = 1; i < n; i++){
        	dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + grid[i][j];
			}
		}
		
		return dp[m-1][n-1];
    }
	
	public static void main(String[] args) {
		SolutionDay27_064 day = new SolutionDay27_064();
		int[][] grid = new int[2][2];
		grid[0][0] = 1;
		grid[0][1] = 2;
		grid[1][0] = 1;
		grid[1][1] = 1;
		
		day.minPathSum(grid);
	}
}
