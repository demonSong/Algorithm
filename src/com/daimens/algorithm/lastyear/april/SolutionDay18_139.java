package com.daimens.algorithm.april;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author DemonSong 
 * 139.Word Break 
 * 		   Given a non-empty string s and a dictionary
 *         wordDict containing a list of non-empty words, determine if s can be
 *         segmented into a space-separated sequence of one or more dictionary
 *         words. You may assume the dictionary does not contain duplicate
 *         words.
 * 
 *         For example, given s = "leetcode", dict = ["leet", "code"].
 * 
 *         Return true because "leetcode" can be segmented as "leet code".
 *
 */
public class SolutionDay18_139 {
	
//	public boolean wordBreak(String s, List<String> wordDict) {
//		
//		boolean canBreak = false;
//	
//		String splitWords = "";
//		for (String split : wordDict){
//			String[] hh = s.split(split);
//			if (hh.length != 1){
//				canBreak = true;
//				splitWords = split;
//				break;
//			}
//		}
//		
//		if(!canBreak) return false;
//		
//		String[] arrays = s.split(splitWords);
//		wordDict.remove(splitWords);
//		
//		boolean tmp = true;
//		for (String ss : arrays){
//			if (ss.length() == 0) continue;
//			
//			tmp = tmp && wordBreak(ss, wordDict);
//		}
//		
//		return tmp;
//    }
	
	
	//循环遍历一次s就够了，必然能够用wordDict表示任何s
	public boolean wordBreak(String s, List<String> wordDict) {
		boolean[] f = new boolean[s.length()+1];
		
		f[0] = true;
		
		for (int i = 1; i <= s.length(); i++){
			for (int j = 0; j < i; j++){
				if(f[j] && wordDict.contains(s.substring(j, i))){
					f[i] = true;
					break;
				}
			}
		}
		
		return f[s.length()];
	}
	
//	public boolean wordBreak(String s, List<String> wordDict) {
//		Set<String> mem = new HashSet<>();
//		return wordBreak(s, wordDict,mem);
//	}
//	
//	private boolean wordBreak(String s, List<String> wordDict, Set<String> mem) {
//		
//		if (s.length() == 0) return true;
//
//		if (mem.contains(s)) return false;
//		mem.add(s);
//		
//		//针对每一种可能的划分情况
//		for (int i = 1; i <= s.length(); i++){
//			String ss = s.substring(0, i);
//			if (wordDict.contains(ss) && wordBreak(s.substring(i,s.length()),wordDict,mem)){
//				return true;
//			}
//		}
//		
//		
//		
//		return false;
//	}
	
	
//	private boolean wordBreak(String s, List<String> wordDict,Set<String> mem) {
//		if (s.isEmpty()) return true;
//		
//		//wordDict no duplicate elements
//		if (mem.contains(s)) return false;
//		mem.add(s);
//		
//		for (String ss : wordDict){
//			if (s.startsWith(ss) && wordBreak(s.substring(ss.length()), wordDict, mem)) return true;
//		}
//		return false;
//	}
	
	
	
	public static void main(String[] args) {
		SolutionDay18_139 day = new SolutionDay18_139();
		
//		String abc = "leetcode";
//		String[] hh = abc.split("leetcode");
		
		List<String> wordDict = new ArrayList<>();
		wordDict.add("leet");
		wordDict.add("code");
		//wordDict.add("ee");
		
		String s = "leetcode";
		boolean canForm = day.wordBreak(s, wordDict);
		System.out.println(canForm);
		
//		for (int i = 1; i <= s.length(); i++) {
//			for (int j = 0; j < i; j++) {
//				System.out.println(s.substring(j, i));
//			}
//		}
//		
//		for (int i = 0; i < s.length(); i++) {
//			for (int j = i + 1; j <= s.length(); j++) {
//				System.out.println(s.substring(i, j));
//			}
//		}
	}
}
