package com.daimens.algorithm.february;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 524.Longest Word in Dictionary through Deleting
 * Given a string and a string dictionary,find the longest string in the dictionary that can be 
 * formed by deleting some characters of the given string. If there are more than one possible results,
 * return the longest word with the smallest lexicographical order. If there is no possible result,return the empty string.
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * Output:
 * "apple"
 * 
 * Example 2:
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 * Output:
 * "a"
 * Note:
 * 1. All the Strings in the input will only contain lower-case letters.
 * 2. The size of the dictionary won't exceed 1000.
 * 3. The length of all the strings in the input won't exceed 1,000.
 *
 */
public class SolutionDay26_502 {
//	public String findLongestWord(String s, List<String> d) {
//		String res = "";
//		
//		for (String tmp : d){
//			int pos = 0;
//			for (int i = 0; i < tmp.length();i++){
//				while(pos < s.length()-1 && tmp.charAt(i) != s.charAt(pos)){
//					pos ++;
//				}
//			}
//			
//			if(pos < s.length() - 1 || (pos == s.length()-1 && tmp.charAt(tmp.length()-1) == s.charAt(pos))){
//				if(res.length() > tmp.length()){
//					res = tmp;
//					pos = 0;
//				}
//				
//				if(res.length() == tmp.length()){
//					int res_len = res.length();
//					int tmp_len = tmp.length();
//					
//					int res_bigger = 0;
//					int tmp_bigger = 0;
//					
//					for (int i = 0; i < res_len; i++){
//						res_bigger += res.charAt(i) * Math.pow(10, res_len-i);
//						tmp_bigger += tmp.charAt(i) * Math.pow(10, tmp_len-i);
//					}
//					
//					if(tmp_bigger <= res_bigger){
//						res = tmp;
//						pos = 0;
//					}
//				}
//			}
//		}
//		
//        return res;
//    }
	
	public String findLongestWord(String s, List<String> d) {
		// 按照数组的长度排序 以及 lexicographical order 没有想到
		Collections.sort(d,(a,b) -> a.length() != b.length() ? -Integer.compare(a.length(), b.length()) : a.compareTo(b));
		for (String dictWord : d){
			int i = 0;
			for (char c : s.toCharArray()){
				if(i < dictWord.length() && c == dictWord.charAt(i)) i++;
			}
			if(i == dictWord.length()) return dictWord;
		}
		return "";
	}
	
	
	
	public static void main(String[] args) {
		SolutionDay26_502 day = new SolutionDay26_502();
		String s = "aewfafwafjlwajflwajflwafj";
		List<String> d = new ArrayList<>();
		String tmp = "apple";
		d.add(tmp);
		tmp = "ewaf";
		d.add(tmp);
		System.out.println(day.findLongestWord(s, d));
	}
}
