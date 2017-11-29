package com.daimens.algorithm.november;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SolutionDay26_L0734 {
	
//	public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
//		Map<String, Set<String>> mem1 = new HashMap<>();
//		Map<String, Set<String>> mem2 = new HashMap<>();
//		for (String[] p : pairs) {
//			mem1.computeIfAbsent(p[0], k -> new HashSet<>()).add(p[1]);
//			mem2.computeIfAbsent(p[1], k -> new HashSet<>()).add(p[0]);
//		}
//		int n1 = words1.length;
//		int n2 = words2.length;
//		
//		if (n1 != n2) return false;
//		
//		for (int i = 0; i < words1.length; ++i) {
//			String w1 = words1[i];
//			String w2 = words2[i];
//			if (!w1.equals(w2)) {
//				
//				if (!valid(mem1, w1, w2) && !valid(mem2, w1, w2)) return false;
//			}
//		}
//		
//		return true;
//    }
//	
//	boolean valid(Map<String, Set<String>> mem, String w1, String w2) {
//		if (!mem.containsKey(w1) && !mem.containsKey(w2)) return false;
//		
//		if (mem.containsKey(w1)) {
//			Set<String> tmp = mem.get(w1);
//			if (tmp.contains(w2)) return true;
//		}
//		
//		if (mem.containsKey(w2)) {
//			Set<String> tmp = mem.get(w2);
//			if (tmp.contains(w1)) return true;
//		}
//		
//		return false;
//	}
	
	public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
		if (words1.length != words2.length) return false;
		Map<String, Set<String>> map = new HashMap<>();
		for (String[] p : pairs) {
			map.computeIfAbsent(p[0], k -> new HashSet<>()).add(p[1]);
		}
		
		for (int i = 0; i < words1.length; ++i) {
			String a = words1[i];
			String b = words2[i];
			if (!a.equals(b)) {
				if (!map.getOrDefault(a, new HashSet<>()).contains(b) && !map.getOrDefault(b, new HashSet<>()).contains(a)) return false;
			}
		}
		return true;
    }
	
}
