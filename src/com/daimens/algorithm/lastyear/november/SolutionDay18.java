package com.daimens.algorithm.november;

/**
 * 
 * @author Demon Song
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * Examples:
 * s = "leetcode"
 * return 0.
 * s = "loveleetcode",
 * return 2.
 *
 */
public class SolutionDay18 {
	
	public int firstUniqChar(String s) {
		int[] characterMap = new int[26];
		char[] characters = s.toCharArray();
		//这种情况导致了map无需存放
		for (char temp : characters){
			characterMap[temp-'a'] +=1;
		}
		//find the first characterMap 为1的情况
		for (int i =0; i<s.length();i++){
			if (characterMap[s.charAt(i)-'a'] ==1){
				return i;
			}
		}
		return -1;
    }
}
