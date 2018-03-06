package com.daimens.algorithm.september;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MagicDictionary {

    /** Initialize your data structure here. */
	
	Map<Integer, List<String>> map;
	public MagicDictionary() {
        map = new HashMap<>();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
    	for (String word : dict) {
    		map.computeIfAbsent(word.length(), k -> new ArrayList<>()).add(word);
    	}
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
    	int len = word.length();
    	if (!map.containsKey(len)) return false;
    	for (String dict : map.get(word.length())) {
    		if (valid(dict, word)) return true;
    	}
    	return false;
    }
    
    public boolean valid(String word, String dict) {
    	int n = word.length();
    	int j = 0;
    	for (int i = 0; i < n; ++i) {
    		if (word.charAt(i) == dict.charAt(i)) j++;
    	}
    	return j == n - 1;
    }
    
}
