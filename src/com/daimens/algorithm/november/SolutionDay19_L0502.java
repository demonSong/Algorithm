package com.daimens.algorithm.november;

import java.util.TreeSet;

public class SolutionDay19_L0502 {
	
    public int countPalindromicSubsequences(String S) {
    	int n = S.length();
    	TreeSet<Integer>[] characters = new TreeSet[4];
    	for (int i = 0; i < 4; ++i) characters[i] = new TreeSet<>();
    	for (int i = 0; i < n; ++i) {
    		int pos = S.charAt(i) - 'a';
    		characters[pos].add(i);
    	}
    	dp = new int[1010][1010];
    	return f(S, characters, 0, n);
    }
    
    int mod = 1000000000 + 7;
    int[][] dp;
    
    int f(String S, TreeSet<Integer>[] characters, int s, int e) {
    	if (s >= e) return 0;
    	if (dp[s][e] > 0) return dp[s][e];
    	long ans = 0;
    	for (int i = 0; i < 4; ++i) {
    		Integer ns = characters[i].ceiling(s);
    		Integer ne = characters[i].lower(e);
    		if (ns == null || ns >= e) continue;
    		ans += ns != ne ? 2 : 1;
    		ans += f(S, characters, ns + 1, ne);
    	}
    	dp[s][e] = (int)(ans % mod);
    	return dp[s][e];
    }
	
	public static void main(String[] args) {
		SolutionDay19_L0502 day = new SolutionDay19_L0502();
	}

}
