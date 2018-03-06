package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         132.Palindrome Partitioning II
 * 
 *         Given a string s, partition s such that every substring of the
 *         partition is a palindrome.
 * 
 *         Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 *         For example, given s = "aab", Return 1 since the palindrome
 *         partitioning ["aa","b"] could be produced using 1 cut.
 *
 */
public class SolutionDay01_132 {

	// public int minCut(String s) {
	// int[] mem = new int[s.length() + 1];
	// return minCut(s.toCharArray(), 0, mem);
	// }
	//
	// private int minCut(char[] ss,int cut,int[] mem){
	//
	// if (mem[cut] != 0) return mem[cut];
	// if (isPalindrome(ss, cut, ss.length-1)) return 0;
	//
	// int min = ss.length - cut - 1;
	// for (int i = cut; i < ss.length; i++){
	// if (isPalindrome(ss, cut, i)){
	// min = Math.min(min, 1+minCut(ss, i+1,mem));
	// }
	// }
	// return mem[cut] = min;
	// }

	private boolean isPalindrome(char[] ss, int start, int end) {
		for (int i = start, j = end; i < j; i++, j--) {
			if (ss[i] != ss[j])
				return false;
		}
		return true;
	}

//	public int minCut(String s) {
//		int n = s.length();
//		boolean[][] isPalindr = new boolean[n + 1][n + 1];
//		int[] dp = new int[n + 1];
//		for (int i = 0; i <= n; i++) dp[i] = i - 1;
//
//		for (int i = 2; i <= n; i++) {
//			for (int j = i - 1; j >= 0; j--) {
//				if (s.charAt(i - 1) == s.charAt(j) && (i - 1 - j < 2 || isPalindr[j + 1][i - 1])) {
//					isPalindr[j][i] = true;
//					dp[i] = Math.min(dp[i], dp[j] + 1);
//				}
//			}
//		}
//		return dp[n];
//	}

	public int minCut(String s) {
	    char[] c = s.toCharArray();
	    int n = c.length;
	    int[] cut = new int[n];
	    boolean[][] pal = new boolean[n][n];
	    
	    for(int i = 0; i < n; i++) {
	        int min = i;
	        for(int j = 0; j <= i; j++) {
	            if(c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
	                pal[j][i] = true;  
	                min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
	            }
	        }
	        cut[i] = min;
	    }
	    return cut[n - 1];
	}
	public static void main(String[] args) {
		SolutionDay01_132 day = new SolutionDay01_132();
		System.out.println(day.isPalindrome("a".toCharArray(), 0, 0));
		day.minCut("aab");
	}

}
