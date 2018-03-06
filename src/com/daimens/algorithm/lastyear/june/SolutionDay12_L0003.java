package com.daimens.algorithm.june;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 
 *         3. Longest Substring Without Repeating Characters
 * 
 *         Given a string, find the length of the longest substring without
 *         repeating characters.
 * 
 *         Examples:
 * 
 *         Given "abcabcbb", the answer is "abc", which the length is 3.
 * 
 *         Given "bbbbb", the answer is "b", with the length of 1.
 * 
 *         Given "pwwkew", the answer is "wke", with the length of 3. Note that
 *         the answer must be a substring, "pwke" is a subsequence and not a
 *         substring.
 *
 */
public class SolutionDay12_L0003 {
	
	public int lengthOfLongestSubstring(String s) {
		if (s.isEmpty()) return 0;
		Map<Character, Integer> map = new HashMap<>();
		int max = 0;
		char[] ss = s.toCharArray();
		int n = ss.length;
		int slow = 0;
		for (int i = 0; i < n; ++i){
			if(map.containsKey(ss[i])){
				int lst = map.get(ss[i]);
				slow = Math.max(slow, lst + 1);
			}
			max = Math.max(max, i - slow + 1);
			map.put(ss[i],i);
		}
		return max;
    }
	
	public static void main(String[] args) {
		SolutionDay12_L0003 day = new SolutionDay12_L0003();
		System.out.println(day.lengthOfLongestSubstring("tmmzuxt"));
	}
}
