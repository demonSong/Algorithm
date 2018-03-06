package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         132.Palindrome Partitioning II 
 *         Given a string s, partition s such
 *         that every substring of the partition is a palindrome.
 * 
 *         Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 *         For example, given s = "aab", Return 1 since the palindrome
 *         partitioning ["aa","b"] could be produced using 1 cut.
 * 
 */
public class SolutionDay27_132 {
	public int minCut(String s) {
		char[] ss = s.toCharArray();
		
		for (int i = 0;i < s.length(); i++){
			for (int j = 0; j <= i; j++){
				
			}
		}
		
        return 0;
    }
	
	private boolean isPalindrome(char[] ss,int start, int end){
		for (int i = start, j = end; i < j; i++, j--) {
			if (ss[i] != ss[j]) return false;
		}
		return true;
	}
}
