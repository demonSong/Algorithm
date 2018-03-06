package com.daimens.algorithm.may;

import java.util.Arrays;

public class SolutionDay14_L0583 {
	
//	public int minDistance(String word1, String word2) {
//		int n = word1.length();
//		int m = word2.length();
//		
//		int[][] dp = new int[n+1][m+1];
//		for (int i = 0; i < n; i++){
//			Arrays.fill(dp[i], 1<<30);
//		}
//		
//		dp[0][0] = 0;
//		for (int i = 0; i < n; i++){
//			dp[i+1][0] = i+1;
//		}
//		for (int j = 0; j < m; j++){
//			dp[0][j+1] = j+1;
//		}
//		
//		for (int i = 0; i < n; i++){
//			for (int j = 0; j < m; j++){
//				if (word1.charAt(i) == word2.charAt(j)){
//					dp[i+1][j+1] = dp[i][j];
//				}else{
//					dp[i+1][j+1] = Math.min(dp[i][j+1]+1, Math.min(dp[i+1][j]+1, dp[i][j]+2));
//				}
//			}
//		}
//		
//		return dp[n][m];
//    }
	
	public int minDistance(String word1, String word2) {
		int n = word1.length();
		int m = word2.length();
		
		int[][] dp = new int[n+1][m+1];
		for (int i = 0; i <= n; i++){
			for (int j = 0; j <= m; j++){
				if (i == 0 || j == 0){
					dp[i][j] = i + j;
					continue;
				}
				if (word1.charAt(i-1) == word2.charAt(j-1)){
					dp[i][j] = dp[i-1][j-1];
				}else{
					dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		return dp[n][m];
    }
	
	public static void main(String[] args) {
		SolutionDay14_L0583 day = new SolutionDay14_L0583();
		day.minDistance("a", "ab");
	}

}
