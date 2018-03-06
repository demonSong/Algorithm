package com.daimens.algorithm.november;

/**
 * 
 * @author Demon song
 * 242. Valid Anagram
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 *
 */
public class SolutionDay20 {
	
	public boolean isAnagram(String s, String t) {
		//每个字母在另外一个字符串中都出现过
		int[] charbucket = new int[26];
		for (char tempS : s.toCharArray()){
			charbucket[tempS-'a']++;
		}
		
		//桶式结构为map结构
		for (char tempT : t.toCharArray()){
			charbucket[tempT-'a']--;
		}
		
		for (int i =0; i<26;i++){
			if (charbucket[i] !=0){
				return false;
			}
		}
		return true;   
    }
}
