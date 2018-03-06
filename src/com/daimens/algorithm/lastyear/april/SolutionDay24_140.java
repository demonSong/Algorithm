package com.daimens.algorithm.april;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.TransducedAccessor_field_Long;

/**
 * 
 * @author DemonSong
 * 
 *         140. Word Break II
 * 
 *         Given a non-empty string s and a dictionary wordDict containing a
 *         list of non-empty words, add spaces in s to construct a sentence
 *         where each word is a valid dictionary word. You may assume the
 *         dictionary does not contain duplicate words.
 * 
 *         Return all such possible sentences.
 * 
 *         For example, given s = "catsanddog", dict = ["cat", "cats", "and",
 *         "sand", "dog"].
 * 
 *         A solution is ["cats and dog", "cat sand dog"].
 * 
 *
 */
public class SolutionDay24_140 {
//	public List<String> wordBreak(String s, List<String> wordDict) {
//        return canForm(s, wordDict, new HashMap<>());
//    }
//	
//	private List<String> canForm(String s, List<String> wordDict, Map<String, List<String>> map){
//		
//		if (map.containsKey(s)) return map.get(s);
//		
//		List<String> ans = new ArrayList<>();
//		if (s.length() == 0){
//			ans.add("");
//			return ans;
//		}
//		
//		for (String word : wordDict){
//			if (s.startsWith(word)){
//				
//				List<String> subList = canForm(s.substring(word.length()), wordDict, map);
//				for (String sub : subList){
//					ans.add(word + (sub.isEmpty() ? "" : " ") + sub);
//				}
//			}	
//		}
//		map.put(s, ans);
//		return ans;
//	}
	
	public List<String> wordBreak(String s, List<String> wordDict) {
		List<String> ans = new ArrayList<>();
		return null;
	}
	
	public static void main(String[] args) {
		SolutionDay24_140 day = new SolutionDay24_140();
		List<String> wordDict = new ArrayList<>();
		wordDict.add("cat");
		wordDict.add("cats");
		wordDict.add("and");
		wordDict.add("sand");
		wordDict.add("dog");
		//wordDict.add("ee");
		
		String s = "catsanddog";
		List<String> ans = day.wordBreak(s, wordDict);
		System.out.println();
	}
}
