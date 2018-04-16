package com.daimens.algorithm.april;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SolutionDay16_L0819 {

    public String mostCommonWord(String paragraph, String[] banned) {
    	paragraph = paragraph.toLowerCase();
    	paragraph = paragraph.replaceAll("[!?',;.]", "");
    	String[] words = paragraph.split(" ");
    	Map<String, Integer> cnt = new HashMap<>();
    	for (String word : words) {
    		cnt.put(word, cnt.getOrDefault(word, 0) + 1);
    	}
    	Set<String> seen = new HashSet<>();
    	for (String ban : banned) seen.add(ban);
    	int max = 0;
    	String ans = "";
    	for (String word : cnt.keySet()) {
    		if (!seen.contains(word)) {
    			int len = cnt.get(word);
    			if (len > max) {
    				max = len;
    				ans = word;
    			}
    		}
    	}
    	return ans;
    }
	
	public static void main(String[] args) {
		SolutionDay16_L0819 day = new SolutionDay16_L0819();
	}
}
