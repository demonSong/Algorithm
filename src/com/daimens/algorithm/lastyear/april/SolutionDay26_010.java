package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         10.Regular Expression Matching
 * 
 *         Implement regular expression matching with support for '.' and '*'.
 * 
 *         '.' Matches any single character. '*' Matches zero or more of the
 *         preceding element.
 * 
 *         The matching should cover the entire input string (not partial).
 * 
 *         The function prototype should be: bool isMatch(const char *s, const
 *         char *p)
 * 
 *         Some examples: isMatch("aa","a") → false isMatch("aa","aa") → true
 *         isMatch("aaa","aa") → false isMatch("aa", "a*") → true isMatch("aa",
 *         ".*") → true isMatch("ab", ".*") → true isMatch("aab", "c*a*b") →
 *         true
 *
 */
public class SolutionDay26_010 {
	
	public boolean isMatch(String s, String p) {
		
		if (!containsRegexp(p)) return s.equals(p);
		int n = s.length();
		int m = p.length();
		boolean[][] valid = new boolean[n+1][m+1];
		valid[0][0] = true;
		
		for (int i = 0; i < m; i++){
			if (p.charAt(i) == '*' && valid[0][i-1]){
				valid[0][i+1] = true;
			}
		}
		
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < p.length(); j++) {
				if (p.charAt(j) == '.') {
					valid[i + 1][j + 1] = valid[i][j];
				}
				if (p.charAt(j) == s.charAt(i)) {
					valid[i + 1][j + 1] = valid[i][j];
				}
				if (p.charAt(j) == '*') {
					if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
						valid[i + 1][j + 1] = valid[i + 1][j - 1];
					} else {
						valid[i + 1][j + 1] = (valid[i + 1][j] || valid[i][j + 1] || valid[i + 1][j - 1]);
					}
				}
			}
		}
		return valid[s.length()][p.length()];
    }
	
	
	private boolean containsRegexp(String reg){
		char[] regs = reg.toCharArray();
		for (int i = 0; i < reg.length(); i++){
			if (regs[i] == '.' || regs[i] == '*') return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		SolutionDay26_010 day = new SolutionDay26_010();
		System.out.println(day.containsRegexp("ashdakj"));
	}

}
