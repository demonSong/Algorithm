package com.daimens.algorithm.december;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionDay31_L0756 {
	
    public boolean pyramidTransition(String bottom, List<String> allowed) {
    	Map<String, List<String>> mem = new HashMap<>();
    	boolean[][] dp = new boolean[20][7];
    	int n = bottom.length();
    	
    	for (String allow : allowed) {
    		mem.computeIfAbsent(allow.substring(0, 2), k -> new ArrayList<>()).add(allow.substring(2));
    	}
    	
    	for (int i = 0; i < n; ++i) {
    		dp[i][bottom.charAt(i) - 'A'] = true;
    	}
    	
    	for (int i = n - 1; i >= 1; --i) {
    		boolean[][] ndp = new boolean[20][7];
    		for (int j = 0; j < i; ++j) {
    			for (int l = 0; l < 7; ++l) {
    				for (int r = 0; r < 7; ++r) {
    					if (dp[j][l] && dp[j + 1][r]) {
    						if (mem.containsKey((char)(l + 'A') + "" + (char)(r + 'A'))) {
    							for (String s : mem.get((char)(l + 'A') + "" + (char)(r + 'A'))) {
    								ndp[j][s.charAt(0) - 'A'] = true;
    							}
    						}
    					}
    				}
    			}
    		}
    		dp = ndp;
    	}
    	
    	for (int i = 0; i < 7; ++i) {
    		if (dp[0][i]) return true;
    	}
    	return false;
    }
    
    public boolean go(String bottom, Map<String, List<Character>> allowed) {
    	int len = bottom.length();
    	if (len == 2 && allowed.containsKey(bottom)) return true;
    	else {
    		for (int i = 0; i < len - 1; ++i) {
    			String key = bottom.substring(i, i + 2);
    			String tmp = "";
    			Map<String, Character> remove = new HashMap<>();
    			if (allowed.containsKey(key)) {
    				List<Character> cands = allowed.get(key);
    				for (char c : cands) {
    					
    				}
    			}
    			if (tmp.length() == len - 1) {
    				if (go(tmp, allowed)) return true;
    			}
    			else {
    				return false;
    			}
    		}
    		return false;
    	}
    }
	
	public static void main(String[] args) {
		SolutionDay31_L0756 day = new SolutionDay31_L0756();
		System.out.println(day.pyramidTransition("ABC", Arrays.asList(new String[]{"ABD", "BCE", "DEF", "FFF"})));
	}

}
