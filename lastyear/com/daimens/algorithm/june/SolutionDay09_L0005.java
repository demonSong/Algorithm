package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         5. Longest Palindromic Substring
 * 
 *         Given a string s, find the longest palindromic substring in s. You
 *         may assume that the maximum length of s is 1000.
 * 
 *         Example:
 * 
 *         Input: "babad"
 * 
 *         Output: "bab"
 * 
 *         Note: "aba" is also a valid answer. Example:
 * 
 *         Input: "cbbd"
 * 
 *         Output: "bb"
 *
 */
public class SolutionDay09_L0005 {
//	class Range{
//		int i;
//		int j;
//		public Range(int i, int j){
//			this.i = i;
//			this.j = j;
//		}
//	}
//	public String longestPalindrome(String s) {
//		char[] cs = s.toCharArray();
//		Map<Range, String> map = new HashMap<>();
//		for (int i = 0; i < s.length(); ++i) map.put(new Range(i,i), cs[i]+"");
//		
//		for (int i = 0; i < s.length(); ++i){
//			for (int j = i - 1; j >= 0; --j){
//				if (cs[i] == cs[j]){
//					if (!map.containsKey(new Range(i-1,j+1))){
//						map.put(new Range(i, j), cs[i] + "" + cs[j]);
//					}
//					else{
//						map.put(new Range(i,j),cs[i] + map.get(new Range(i-1,j+1)) + cs[j]);
//					}
//				}
//				else{
//					String a1 = map.get(new Range(i-1,j));
//					String a2 = map.get(new Range(i,j+1));
//					if (a1.length() > a2.length()){
//						map.put(new Range(i,j), a1);
//					}else{
//						map.put(new Range(i,j), a2);
//					}
//				}
//			}
//		}
//		return map.get(new Range(s.length()-1,0));
//    }
	
	public String longestPalindrome(String s) {
		int n = s.length();
		if (n == 0) return "";
		char[] cs = s.toCharArray();
		boolean[][] dp = new boolean[n][n];
		for (int i = 0; i < n; ++i) dp[i][i] = true;
		int lo = 0, hi = 0, max = 0;
		for (int i = 0; i < n; ++i){
			for (int j = i - 1; j >= 0; --j){
				if (cs[j] == cs[i]){
					dp[j][i] = dp[j+1][i-1] && i - j + 1 >= 3 || i - j + 1 == 2;
				}
				
				if (dp[j][i] && (i - j + 1) > max){
					max = i - j + 1;
					lo = j;
					hi = i;
				}
			}
		}
		return s.substring(lo,hi+1);
	}

//	int lo, hi, max;
//	public String longestPalindrome(String s) {
//		int n = s.length();
//		if (n == 0) return "";
//		for (int i = 0; i < n - 1; ++i){
//			extendPalindrome(s.toCharArray(), i, i);
//			extendPalindrome(s.toCharArray(), i, i+1);
//		}
//		return s.substring(lo, hi + 1);
//	}
//	
//	private void extendPalindrome(char[] c, int i, int j){
//		while (i >= 0 && j < c.length && c[i] == c[j]){
//				i--;
//				j++;
//		}
//		if (j - i - 1 > max){
//			max = j - i - 1;
//			lo = i + 1;
//			hi = j - 1;
//		}
//	}
	
	public static void main(String[] args) {
		SolutionDay09_L0005 day = new SolutionDay09_L0005();
		System.out.println(day.longestPalindrome("acb"));
	}
}
