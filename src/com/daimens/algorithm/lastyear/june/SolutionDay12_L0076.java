package com.daimens.algorithm.june;

import com.sun.java.swing.plaf.windows.resources.windows;

/**
 * 
 * @author DemonSong
 * 
 *         76.Minimum Window Substring
 * 
 *         Given a string S and a string T, find the minimum window in S which
 *         will contain all the characters in T in complexity O(n).
 * 
 *         For example, S = "ADOBECODEBANC" T = "ABC" Minimum window is "BANC".
 * 
 *         Note: If there is no such window in S that covers all characters in
 *         T, return the empty string "".
 * 
 *         If there are multiple such windows, you are guaranteed that there
 *         will always be only one unique minimum window in S.
 *
 */
public class SolutionDay12_L0076 {	
	
	public String minWindow(String s, String t) {
		if (t.length() > s.length()) return "";
		
		int[] map = new int[128];
		int[] window = new int[128];
		
		char[] ts = t.toCharArray();
		for (int i = 0; i < t.length(); ++i){
			map[ts[i] - 'A']++;
		}
		
		char[] ss = s.toCharArray();
		int letterCnt = 0;
		int slow = 0;
		int min = Integer.MAX_VALUE;
		int lo = -1, hi = -1;
		for (int i = 0; i < s.length(); ++i){
			if (map[ss[i] - 'A'] != 0){
				if (letterCnt < t.length() && window[ss[i] - 'A'] < map[ss[i] - 'A']){
					letterCnt++;
				}
				window[ss[i] - 'A']++;
			}
			if(letterCnt >= t.length()){
				while(slow < s.length() && (window[ss[slow] - 'A'] == 0 || window[ss[slow] - 'A'] > map[ss[slow] - 'A'])){
					window[ss[slow] - 'A']--;
					slow++;
				}
				
				if (i - slow + 1 < min){
					min = i - slow + 1;
					lo = slow;
					hi = i;
				}
			}
		}
		if (lo == -1 && hi == -1) return "";
		return s.substring(lo, hi + 1);
	}
	
	public static void main(String[] args) {
		SolutionDay12_L0076 day = new SolutionDay12_L0076();
		System.out.println(day.minWindow("ADOBECODEBANC", "BANC"));
	}
}
