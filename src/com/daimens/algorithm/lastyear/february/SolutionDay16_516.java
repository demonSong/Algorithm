package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 516.Longest Palindromic Subsequence
 * Given a string s,find the longest palindromic subsequence's length in s. You may
 * assume that the maximum length of s is 1000.
 * Example 1:
 * Input:
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb"
 * 
 * Example 2:
 * Input:
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb"
 *
 */
public class SolutionDay16_516 {
	
//	public int longestPalindromeSubseq(String s){
//		String reverse = new StringBuilder(s).reverse().toString();
//		int longest = 0;
//		
//		int beginIndex2s = 0;
//		int beginIndex2r = 0;
//		
//		while(beginIndex2r < s.length()){
//			while (beginIndex2r < s.length() && s.charAt(beginIndex2s) != reverse.charAt(beginIndex2r) ){
//				beginIndex2r ++;
//			}
//			
//			int count = 0;
//			while (beginIndex2r < s.length() && s.charAt(beginIndex2s) == reverse.charAt(beginIndex2r)){
//				beginIndex2s ++;
//				beginIndex2r ++;
//				count ++;
//			}
//			
//			beginIndex2s -= count;
//			
//			if (longest < count) {
//				longest = count;
//			}
//			
//		}
//		return longest;
//	}
	
	//dp[][] 记录了历史信息
//	public int longestPalindromeSubseq(String s) {
//		int[][] dp = new int[s.length()][s.length()];
//		
//		for (int i = s.length() - 1; i >= 0; i--) {
//			dp[i][i] = 1; // 至少是1个元素
//			for(int j = i + 1; j < s.length(); j++){
//				if(s.charAt(i) == s.charAt(j)){
//					dp[i][j] = dp[i+1][j-1] + 2;
//				}else{
//					dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
//				}
//			}
//		}
//		return dp[0][s.length()-1];
//	}
	
	
//	public int longestPalindromeSubseq(String s) {
//		int n = s.length();
//		int[][] dp = new int[n][n];
//		
//		char[] c = s.toCharArray();
//		
//		int max = 0;
//		for (int i = 0; i < n; i++){
//			dp[i][i] = 1;
//			for (int j = 0; j < i; j++){
//				if (c[j] == c[i]){
//					dp[j][i] = dp[j+1][i-1] + 2;
//				}
//				max = Math.max(max, dp[j][i]);
//			}
//		}
//		
//		return max;
//	}
	
	
	public int longestPalindromeSubseq(String s) {
		int n = s.length();
		int[][] dp = new int[n][n];
		char[] c = s.toCharArray();
		for (int i = 0; i < n; i++) {
			dp[i][i] = 1;
			for (int j = i-1; j >= 0; j--) {
				if (c[j] == c[i]) {
					dp[j][i] = dp[j + 1][i - 1] + 2;
				} else {
					dp[j][i] = Math.max(dp[j+1][i], dp[j][i-1]);
				}
			}
		}
		return dp[0][n-1];
	}
	
	public static void main(String[] args) {
		SolutionDay16_516 day = new SolutionDay16_516();
		day.longestPalindromeSubseq("bbbab");
	}
}
