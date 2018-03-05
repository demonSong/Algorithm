package com.daimens.algorithm.january;

import java.util.ArrayList;
import java.util.List;

public class SolutionDay12_L0763 {
	
//    public List<Integer> partitionLabels(String S) {
//    	List<Integer> ans = new ArrayList<>();
//    	char[] cs = S.toCharArray();
//    	StringBuilder sb = new StringBuilder();
//    	for (int i = 0; i < cs.length; ++i) {
//    		sb.append(cs[i]);
//    		if (part(sb, i, S)) {
//    			ans.add(sb.length());
//    			sb = new StringBuilder();
//    		}
//    	}
//    	return ans;
//    }
//    
//    public boolean part(StringBuilder sb, int i, String S) {
//    	if (sb.toString().isEmpty()) return false;
//    	for (int j = i + 1; j < S.length(); ++j) {
//    		String letter = S.substring(j, j + 1);
//    		if (sb.toString().contains(letter)) return false;
//    	}
//    	return true;
//    }
	
	public List<Integer> partitionLabels(String S) {
    	List<Integer> ans = new ArrayList<>();
    	char[] cs  = S.toCharArray();
    	int n = cs.length;
    	int[] last = new int[32];
    	for (int i = 0; i < n; ++i) {
    		last[cs[i] - 'a'] = i;
    	}
    	int pre = -1;
    	int max = 0;
    	for (int i = 0; i < n; ++i) {
    		if (last[cs[i] - 'a'] > max) max = last[cs[i] - 'a'];
    		if (max == i) {
    			ans.add(max - pre);
    			pre = max;
    			max = max + 1;
    		}
    	}
    	return ans;
    }
	
	public static void main(String[] args) {
		SolutionDay12_L0763 day = new SolutionDay12_L0763();
//		String S = "ababcbacadefegdehijhklij";
		String S = "caedbdedda";
		System.out.println(day.partitionLabels(S));
	}
}	
