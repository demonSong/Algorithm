package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


/**
 * @author DemonSong
 * 
 *         30. Substring with Concatenation of All Words
 * 
 *         You are given a string, s, and a list of words, words, that are all
 *         of the same length. Find all starting indices of substring(s) in s
 *         that is a concatenation of each word in words exactly once and
 *         without any intervening characters.
 * 
 *         For example, given: s: "barfoothefoobarman" words: ["foo", "bar"]
 * 
 *         You should return the indices: [0,9]. (order does not matter).
 * 
 */
public class SolutionDay12_L0030 {
	
	class Bucket{
		int index;
		String word;
		public Bucket(int index, String word){
			this.index = index;
			this.word = word;
		}
		
		@Override
		public String toString() {
			return "{index: "+index+", word: "+word+"}";
		}
	}
	
	public List<Integer> findSubstring(String s, String[] words) {
		Bucket[] buckets = new Bucket[s.length() + 1];
		int len = 0;
		for (String word : new HashSet<>(Arrays.asList(words))){
			int index = s.indexOf(word);
			while (index != -1){
				len++;
				buckets[index] = new Bucket(index,word);
				index = s.indexOf(word, index + 1);
			}
		}
		Bucket[] sorted = new Bucket[len];
		for (int i = 0, k = 0; i < buckets.length; ++i){
			if (buckets[i] != null){
				sorted[k++] = buckets[i]; 
			}
		}
		
		if (sorted.length == 0) return new ArrayList<>();
		
		Map<String, Integer> map = new HashMap<>();
		for (String word : words) map.put(word, map.getOrDefault(word, 0) + 1);
		
		if (words.length == 0) return new ArrayList<>();
		
		int M = words[0].length();
		
		List<Integer> ans = new ArrayList<>();
		Map<String, Integer> window = new HashMap<>();
		int slow = 0;
		int prev = sorted[0].index;
		for (int fast = 0; fast < sorted.length; ++fast){
			String cur = sorted[fast].word;
			if (!window(map, window)){
				if (window.containsKey(cur) && window.get(cur) < map.get(cur) && sorted[fast].index == prev + M){
					window.put(cur, window.get(cur) + 1);
				}
				else{
					slow = fast;
					window.clear();
					window.put(cur, 1);
				}
			}
			else{
				ans.add(sorted[slow].index);
				if (sorted[slow].equals(sorted[fast])) slow++;
				else{
					slow = fast;
					window.clear();
				}
			}
			prev = sorted[fast].index;
		}
		return ans;
	}
	
	private boolean window(Map<String, Integer> map, Map<String, Integer> window){
		if (window.size() < map.size()) return false;
		for (String key : window.keySet()){
			if (window.get(key) < map.get(key)) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		SolutionDay12_L0030 day = new SolutionDay12_L0030();
		System.out.println("abdasjsa".indexOf("z"));
		String s = "barfoofoobarthefoobarman";
		String[] words = {"foo","bar","the"};
		System.out.println(day.findSubstring(s, words));
	}

}
