package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 63.Unique Paths II
 * Follow up for "Unique Paths"
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * [
 *  [0,0,0],
 *  [0,1,0],
 *  [0,0,0]
 * ]
 * 
 * The total number of unique paths is 2.
 * Note: m and n will be at most 100.
 *
 */
public class SolutionDay22_063 {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if(obstacleGrid.length == 0) return 0;
		if(obstacleGrid[0].length == 0) return 0;
		
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		
		int[][] dp = new int[m][n];
		dp[0][0] = 1;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1)
				    return 0;
				if(obstacleGrid[i][j] == 1 || i == 0 && j== 0) continue;
				dp[i][j] = ((i-1 != -1) ? dp[i-1][j] : 0) +((j-1 != -1) ? dp[i][j-1] : 0);
			}
		}
		
		return dp[m-1][n-1];
    }
	
	public static void main(String[] args) {
		SolutionDay22_063 day = new SolutionDay22_063();
		int[][] obstacleGrid = {{0}};
		day.uniquePathsWithObstacles(obstacleGrid);
	}
}
