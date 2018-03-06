package com.daimens.algorithm.december;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 438.Find All Anagrams in a String
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only 
 * and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 * Example 1:
 * Input:
 * s: "cbaebabacd" p: "abc"
 * Output:
 * [0, 6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 */
public class SolutionDay30_438 {
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> res = new ArrayList<Integer>();
		//把p放在一个集合中,来记录p中每个char的个数
		int[] pSet = new int[26];
		for(char tmp : p.toCharArray()){
			pSet[tmp-'a'] ++;
		}
		char[] sArray = s.toCharArray();
		for (int i = 0; i < sArray.length - p.length() + 1; i++) {
			int[] tmp = Arrays.copyOf(pSet, pSet.length);
			for (int j = i; j < i + p.length(); j++) {
				tmp[sArray[j] - 'a']--;
			}
			// 如果tmp中的所有元素为0，则是一个合法的子序列
			boolean isZero = true;
			for (int k = 0; k < tmp.length; k++) {
				if(tmp[k] !=0){
					isZero =false;
				}
			}
			if(isZero){
				res.add(i);
			}
		}
        return res;
    }
	
	public static void main(String[] args) {
		SolutionDay30_438 day30_438 = new SolutionDay30_438();
		day30_438.findAnagrams("cbaebabacd", "aabc");
	}
}
