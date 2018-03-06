package com.daimens.algorithm.april;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.daimens.algorithm.april.SolutionDay09_503.Result;

/**
 * 
 * @author DemonSong
 * 
 *         472.Concatenated Words
 * 
 *         Given a list of words (without duplicates), please write a program
 *         that returns all concatenated words in the given list of words.
 * 
 *         A concatenated word is defined as a string that is comprised entirely
 *         of at least two shorter words in the given array.
 * 
 *         Example: Input:
 *         ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * 
 *         Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 
 *         Explanation: "catsdogcats" can be concatenated by "cats", "dog" and
 *         "cats"; "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 *         "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 *         Note: The number of elements of the given array will not exceed
 *         10,000 The length sum of elements in the given array will not exceed
 *         600,000. All the input string will only include lower case letters.
 *         The returned elements order does not matter.
 *
 */
public class SolutionDay24_472 {
	
	//my
	
	// public List<String> findAllConcatenatedWordsInADict(String[] words) {
	//
	// List<String> ans = new ArrayList<>();
	// for (int i = 0; i < words.length; i++){
	// String target = words[i];
	// List<String> wordDict = new ArrayList<>();
	// for (int j = 0; j < words.length; j++ ){
	// if (i == j) continue;
	// wordDict.add(words[j]);
	// }
	//
	// if (wordBreak(target, wordDict) && target.length() != 0){
	// ans.add(target);
	// }
	// }
	//
	// return ans;
	// }
	
	
	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		
		List<String> ans = new ArrayList<>();
		Set<String>  set = new HashSet<>();
		
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});
		
		for (int i = 0; i < words.length;i++){
			if(wordBreak(words[i],set)){
				ans.add(words[i]);
			}
			set.add(words[i]);
		}
		
		return ans;
    }

	//139. Word Break
	private boolean wordBreak(String s, Set<String> wordDict){
		
		if(wordDict.isEmpty()) return false;
		
		boolean[] f = new boolean[s.length() + 1];
		
		f[0] = true;
		
		for (int i = 1; i <= s.length(); i++){
			for (int j = 0; j < i; j++){
				if (f[j] && wordDict.contains(s.substring(j, i))){
					f[i] = true;
					break;
				}
			}
		}
		return f[s.length()];
	}
}
