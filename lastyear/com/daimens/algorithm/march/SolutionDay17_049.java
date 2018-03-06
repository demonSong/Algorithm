package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 49.Group Anagrams
 * Given an array of strings,group anagrams together.
 * For example,given: ["eat","tea","tan","ate","nat","bat"]
 * Return:
 * [
 * 	["ate", "eat","tea"],
 * 	["nat","tan"],
 * 	["bat"]
 * ]
 * Note: All inputs will be in lower-case.
 *
 */
public class SolutionDay17_049 {
	
	
	//time limit exceeded
//	public List<List<String>> groupAnagrams(String[] strs) {
//		
//		Map<String,List<String>> map = new HashMap<>();
//		
//		if(strs.length == 0) return null;
//		
//		List<String> ans = new ArrayList<>();
//		ans.add(strs[0]);
//		map.put(strs[0], ans);
//		
//		for (int i = 1; i < strs.length; i++) {
//			
//			boolean flag = true;
//			for(String key : map.keySet()){
//				//判断strs[i] 是否符合map中的key
//				
//				flag = true;
//				
//				int[] count = new int[26];
//				for (int j = 0; j < strs[i].length();j++){
//					count[strs[i].charAt(j)-'a']++;
//				}
//				
//				for (int j = 0; j < key.length();j++){
//					count[key.charAt(j)-'a']--;
//				}
//				
//				
//				for (int j = 0; j < count.length; j++){
//					if(count[j] != 0){
//						flag = false;
//						break;
//					}
//				}
//				
//				if(flag){
//					List<String> tmp = map.get(key);
//					tmp.add(strs[i]);
//					map.put(key, tmp);
//					break;
					//map.get(key).add(strs[i]);来代替
//				}
//			}
//			
//			if(!flag){
//				List<String> tmp = new ArrayList<>();
//				tmp.add(strs[i]);
//				map.put(strs[i], tmp);
//			}
//		}
//		
//		List<List<String>> res = new ArrayList<>();
//		for (String key : map.keySet()){
//			res.add(map.get(key));
//		}
//        return res;
//    }
	
	public List<List<String>> groupAnagrams(String[] strs) {
		if(strs == null || strs.length == 0) return new ArrayList<List<String>>();
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Arrays.sort(strs);
		for(String s : strs){
			char[] ca = s.toCharArray();
			Arrays.sort(ca);
			String keyStr = String.valueOf(ca);
			if(!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
			map.get(keyStr).add(s);
		}
		
		
		return new ArrayList<List<String>>(map.values());
	}
	
	public static void main(String[] args) {
		SolutionDay17_049 day = new SolutionDay17_049();
		String[] strs = {"tea","and","ate","eat","dan"};
		day.groupAnagrams(strs);
		
		char[] c = {'a','b','c'};
		System.out.println(String.valueOf(c));
	}
}	
