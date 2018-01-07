package com.daimens.algorithm.december;

import java.util.HashSet;
import java.util.Set;

public class SolutionDay24_L0753 {
	
    public String crackSafe(int n, int k) {
    	int size = (int) Math.pow(k, n);
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < n; ++i) {
    		sb.append("0");
    	}
    	String init = sb.toString();
    	Set<String> vis = new HashSet<>();
    	vis.add(init);
    	ans = init;
    	valid = false;
    	int len = n - 1;
    	dfs(init, vis, k, size, len);
    	return ans;
    }
	
    String ans;
    boolean valid = false;
    void dfs(String init, Set<String> vis, int k, int size, int len) {
    	if (vis.size() == size) {
    		ans = init;
    		valid = true;
    	}
    	else {
    		if (valid) return;
    		int n = init.length();
    		for (int j = 0; j < k; ++j) {
    			String ns = init.substring(n - len, n) + j;
    			if (!vis.contains(ns)) {
    				String nxt = new String(init + j);
    				vis.add(ns);
    				dfs(nxt, vis, k, size, len);
    				vis.remove(ns);
    			}
    		}
    	}
    }
    
	public static void main(String[] args) {
		SolutionDay24_L0753 day = new SolutionDay24_L0753();
		System.out.println(day.crackSafe(4, 8));
	}

}
