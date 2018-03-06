package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 14.Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 */
public class SolutionDay16_014 {
	
	public String longestCommonPrefix(String[] strs) {
		if(strs.length == 0){
			return "";
		}
		
		int minLength = Integer.MAX_VALUE;
		int minIndex = 0;
		for (int i = 0; i < strs.length; i++){
			if(strs[i].length() < minLength){
				minLength = strs[i].length();
				minIndex = i;
			}
		}
		
		for (int j =0; j < strs[minIndex].length(); j++){
			char tmp = strs[minIndex].charAt(j);
			for (int i = 0; i < strs.length; i++){
				if(i == minIndex){
					continue;
				}
				if(tmp != strs[i].charAt(j)){
					return strs[minIndex].substring(0, j);
				}
			}
		}
		
		return strs[minIndex];
    }
	
	public static void main(String[] args) {
		SolutionDay16_014 day16_014 = new SolutionDay16_014();
		String[] strs = {"avbbf","avb","avccsdas"};
		System.out.println(day16_014.longestCommonPrefix(strs));
	}

}
