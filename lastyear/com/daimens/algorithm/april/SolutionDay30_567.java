package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         567. Permutation in String
 * 
 *         Given two strings s1 and s2, write a function to return true if s2
 *         contains the permutation of s1. In other words, one of the first
 *         string's permutations is the substring of the second string.
 * 
 *         Example 1: Input:s1 = "ab" s2 = "eidbaooo" Output:True Explanation:
 *         s2 contains one permutation of s1 ("ba"). Example 2: Input:s1= "ab"
 *         s2 = "eidboaoo" Output: False Note: The input strings only contain
 *         lower case letters. The length of both given strings is in range [1,
 *         10,000].
 *
 */
public class SolutionDay30_567 {

	// public boolean checkInclusion(String s1, String s2) {
	//
	// int[] map1 = new int[26];
	// for (int i = 0; i < s1.length(); i++){
	// map1[s1.charAt(i)-'a']++;
	// }
	//
	// int len = s1.length();
	//
	// for (int i = 0; i + len -1 < s2.length(); i++) {
	// String tmp = s2.substring(i, i+len);
	// int[] map2 = new int[26];
	// for (int j = 0; j < tmp.length();j++){
	// map2[tmp.charAt(j)-'a']++;
	// }
	// if (isArraySame(map1, map2)) return true;
	// }
	//
	// return false;
	// }

	public boolean checkInclusion(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();

		if (n > m)
			return false;

		int[] f = new int[26];
		for (int i = 0; i < n; i++) {
			f[s1.charAt(i) - 'a']++;
		}

		int[] g = new int[26];
		for (int i = 0; i < n; i++) {
			g[s2.charAt(i) - 'a']++;
		}
		if (isArraySame(f, g))
			return true;

		for (int i = 1; i + n - 1 < s2.length(); i++) {
			g[s2.charAt(i - 1) - 'a']--;
			g[s2.charAt(i + n - 1) - 'a']++;
			if (isArraySame(f, g))
				return true;
		}
		return false;
	}

	private boolean isArraySame(int[] map1, int[] map2) {
		for (int i = 0; i < map1.length; i++) {
			if (map1[i] != map2[i])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		SolutionDay30_567 day = new SolutionDay30_567();
		System.out.println(day.checkInclusion("ab", "eidboaoo"));
	}

}
