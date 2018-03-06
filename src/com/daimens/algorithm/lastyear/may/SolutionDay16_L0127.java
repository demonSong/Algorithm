package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.effect.Light.Distant;

/**
 * 
 * @author DemonSong
 * 
 *         127.Word Ladder
 * 
 *         Given two words (beginWord and endWord), and a dictionary's word
 *         list, find the length of shortest transformation sequence from
 *         beginWord to endWord, such that:
 * 
 *         Only one letter can be changed at a time. Each transformed word must
 *         exist in the word list. Note that beginWord is not a transformed
 *         word. For example,
 * 
 *         Given: beginWord = "hit" endWord = "cog" wordList =
 *         ["hot","dot","dog","lot","log","cog"] As one shortest transformation
 *         is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
 * 
 *         Note: Return 0 if there is no such transformation sequence. All words
 *         have the same length. All words contain only lowercase alphabetic
 *         characters. You may assume no duplicates in the word list. You may
 *         assume beginWord and endWord are non-empty and are not the same.
 *         UPDATE (2017/1/20): The wordList parameter had been changed to a list
 *         of strings (instead of a set of strings). Please reload the code
 *         definition to get the latest changes.
 *
 */
public class SolutionDay16_L0127 {
	
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		List<String> reached = new ArrayList<>();
		reached.add(beginWord);
		Set<String> wordSet = new HashSet<>(wordList);
		if(!wordSet.contains(endWord)) return 0;
		wordSet.add(endWord);
		
		int distance = 1;
		while (!reached.contains(endWord)){ //达到该目的地
			List<String> toAdd = new ArrayList<>();
			for (String each : reached){
				for (int i = 0; i < each.length(); i++){
					char[] chars = each.toCharArray();
					for (char c = 'a';  c <= 'z'; c++){
						chars[i] = c;
						String wd = new String(chars);
						if (wordSet.contains(wd)){
							toAdd.add(wd);
							wordSet.remove(wd);
						}
					}
				}
			}
			distance ++;
			if (toAdd.size() == 0) return 0;
			reached = toAdd;
		}
		return distance;
	}
	
	//TLE
//	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//		Map<String, List<String>> map = new HashMap<>();
//		map.put(beginWord, new ArrayList<>());
//		for (String word : wordList){
//			map.put(word, new ArrayList<>());
//		}
//		for (String key : map.keySet()){
//			List<String> container = map.get(key);
//			for (String word : wordList){
//				if (oneDiff(key, word)){
//					container.add(word);
//				}
//			}
//			map.put(key, container);
//		}
//		int ans = helper(map, beginWord, endWord, new HashSet<>());
//		return ans >= 1 << 30 ? 0 : ans;
//    }
//	
//	private int helper(Map<String, List<String>> map,String beginWord, String endWord, Set<String> visited){
//		if (visited.contains(beginWord)) return 1<<30;
//		visited.add(beginWord);
//		for (String find : map.get(beginWord)){
//			if (find.equals(endWord)){
//				visited.remove(beginWord);
//				return 2;
//			}
//		}
//		int min = Integer.MAX_VALUE;
//		for (String find : map.get(beginWord)){
//			int x = 1 + helper(map, find, endWord, visited);
//			min = Math.min(min, x);
//		}
//		visited.remove(beginWord);
//		return min;
//	}
//	
//	
//	private boolean oneDiff(String a, String b){
//		if (a.equals(b)) return false;
//		char[] aa = a.toCharArray();
//		char[] bb = b.toCharArray();
//		
//		int oneDiff = 0;
//		for (int i = 0; i < aa.length; i++){
//			if (aa[i] != bb[i]){
//				oneDiff ++;
//				if (oneDiff >= 2) return false;
//			}
//		}
//		return true;
//	}

	private void test(){
		String each = "ccc";
		for (int i = 0; i < 3; i++) {
            char[] chars = each.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                chars[i] = ch;
                String word = new String(chars);
                System.out.println(word);
            }
		}
	}
	
	public static void main(String[] args) {
		SolutionDay16_L0127 day = new SolutionDay16_L0127();
		String beginWord = "hot";
		String endWord = "dog";
		day.test();
		//String[] words = {"hot","dot","dog","lot","log","cog"};
		//String[] words = {"hot","dog","cog","pot","dot"};
		String[] words = {"hot","dog"};
		List<String> wordList = Arrays.asList(words);
		day.ladderLength(beginWord, endWord, wordList);
		
	}
}
