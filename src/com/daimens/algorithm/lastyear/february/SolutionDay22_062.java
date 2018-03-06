package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 62.Unique Paths
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the
 * bottom-right corner of the grid(marked 'Finish' in the diagram below)
 * 
 * How many possible unique paths are there?
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * Note: m and n will be at most 100.
 *
 */
public class SolutionDay22_062 {
	//dp 遍历的顺序是需要强调一下的
	public int uniquePaths(int m, int n) {
		int[][] dp = new int[m][n];
		dp[0][0] = 1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0)
					continue;
				dp[i][j] = ((i - 1 != -1) ? dp[i - 1][j] : 0) + ((j - 1 != -1) ? dp[i][j - 1] : 0);
			}
		}
		return dp[m - 1][n - 1];
	}
}
