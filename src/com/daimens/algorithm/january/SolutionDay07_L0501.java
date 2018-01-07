package com.daimens.algorithm.january;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SolutionDay07_L0501 {
	
	class P implements Comparable<P>{
		int start;
		int end;
		
		P(int start, int end){
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(P o) {
			return this.start == o.start ? this.end - o.end : this.start - o.start;
		}
		
		@Override
		public String toString() {
			return "[" + start + "," + end + "]";
		}
	}
	
    public String boldWords(String[] words, String S) {
    	int n = S.length();
    	Set<String> set = new HashSet<>();
    	for (String word : words) set.add(word);
    	List<P> list = new ArrayList<>();
    	for (int i = 0; i < n; ++i) {
    		for (String word : words) {
    			if (i + word.length() <= n && word.equals(S.substring(i, i + word.length()))) {
    				list.add(new P(i, i + word.length() - 1));
    			}
    		}
    	}
    	
    	List<P> mergeList = merge(list);
    	StringBuilder sb = new StringBuilder();
    	Map<Integer, Integer> map = new HashMap<>();
    	for (P s : mergeList) {
    		map.put(s.start, s.end);
    	}
    	int j = 0;
    	while (j < n) {
    		while (j < n && !map.containsKey(j)) {
    			sb.append(S.charAt(j));
    			j ++;
    		}
    		if (j >= n) break;
    		if (map.containsKey(j)) {
    			sb.append("<b>" + S.substring(j, map.get(j) + 1) + "</b>");
    			j = map.get(j) + 1;
    		}
    	}
    	return sb.toString();
    }
    
    public List<P> merge(List<P> unSorted) {
    	Collections.sort(unSorted);
    	List<P> ans = new ArrayList<>();
    	int n = unSorted.size();
    	for (int i = 0; i < n; ++i) {
    		P now = unSorted.get(i);
    		P can = new P(now.start, now.end);
    		int j = i + 1;
    		while (j < n && unSorted.get(j).start <= can.end + 1) {
    			can.end = Math.max(can.end, unSorted.get(j).end);
    			j ++;
    		}
    		ans.add(can);
    		i = j - 1;
    	}
    	return ans;
    }
	
	public static void main(String[] args) {
		SolutionDay07_L0501 day = new SolutionDay07_L0501();
		String[] words = {"ab", "bc"};
		System.out.println(day.boldWords(words, "aabcd"));
	}

}
