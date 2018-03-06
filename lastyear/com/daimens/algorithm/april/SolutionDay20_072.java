package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         72.Edit Distance
 * 
 *         Given two words word1 and word2, find the minimum number of steps
 *         required to convert word1 to word2. (each operation is counted as 1
 *         step.)
 * 
 *         You have the following 3 operations permitted on a word:
 * 
 *         a) Insert a character b) Delete a character c) Replace a character
 *
 */
public class SolutionDay20_072 {
	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();

		int[][] cost = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++)
			cost[i][0] = i;
		for (int i = 1; i <= n; i++)
			cost[0][i] = i;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (word1.charAt(i) == word2.charAt(j))
					cost[i + 1][j + 1] = cost[i][j];
				else {
					int a = cost[i][j];
					int b = cost[i][j + 1];
					int c = cost[i + 1][j];
					cost[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
					cost[i + 1][j + 1]++;
				}
			}
		}
		return cost[m][n];
	}
	
	public static void main(String[] args) {
		SolutionDay20_072 day = new SolutionDay20_072();
		day.minDistance("acjkasdhnasdoq", "dlksajdsaknwqplk");
	}
}
