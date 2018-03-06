package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         44.WildCard Matching
 * 
 *         Implement wildcard pattern matching with support for '?' and '*'.
 * 
 *         '?' Matches any single character. '*' Matches any sequence of
 *         characters (including the empty sequence).
 * 
 *         The matching should cover the entire input string (not partial).
 * 
 *         The function prototype should be: bool isMatch(const char *s, const
 *         char *p)
 * 
 *         Some examples: isMatch("aa","a") → false isMatch("aa","aa") → true
 *         isMatch("aaa","aa") → false isMatch("aa", "*") → true isMatch("aa",
 *         "a*") → true isMatch("ab", "?*") → true isMatch("aab", "c*a*b") →
 *         false
 *
 */
public class SolutionDay26_044 {
	
	public boolean isMatch(String s, String p) {
		int n = s.length();
		int m = p.length();
		
		boolean[][] dp = new boolean[n+1][m+1];
		dp[0][0] = true;
		
		char[] ss = s.toCharArray();
		char[] pp = p.toCharArray();
		
		// s为空串 的所以匹配情况
		for (int j = 0; j < m; j++){
			if (pp[j] == '*' && dp[0][j]){
				dp[0][j+1] = true;
			}
		}
		
		// 遍历角度是元素，而dp的更新都是从1开始的
		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++){
				if (pp[j] == ss[i] || pp[j] == '?'){
					dp[i+1][j+1] = dp[i][j];
				}
				if (pp[j] == '*'){
					for (int k = 0; k <= i; k++)
						dp[i+1][j+1] = dp[i+1][j+1] ||  dp[i+1-k][j]; 
				}
			}
		}
		return dp[n][m];
	}
	
	public static void main(String[] args) {
		SolutionDay26_044 day = new SolutionDay26_044();
		day.isMatch("a", "a*");
	}
}
