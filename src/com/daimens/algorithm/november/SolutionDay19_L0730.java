package com.daimens.algorithm.november;

import java.util.Arrays;
import java.util.TreeSet;

public class SolutionDay19_L0730 {
	
    public int countPalindromicSubsequences(String S) {
    	int n = S.length();
    	int[][] next = new int[4][1010];
    	int[][] prev = new int[4][1010];
    	
    	
    	char[] cs = S.toCharArray();
    	
    	for (int i = 0; i < 4; ++i) Arrays.fill(next[i], n);
    	for (int i = n - 1; i >= 0; --i) {
    		int c = cs[i] - 'a';
    		for (int j = 0; j < 4; ++j) next[j][i] = i + 1 == n ? n : next[j][i + 1];
    		next[c][i] = i;
    	}
    	
    	for (int i = 0; i < 4; ++i) Arrays.fill(prev[i], -1);
    	for (int i = 0; i < n; ++i) {
    		int c = cs[i] - 'a';
    		for (int j = 0; j < 4; ++j) prev[j][i] = i - 1 == -1 ? -1 : prev[j][i - 1];
    		prev[c][i] = i;
    	}
    	dp = new int[1010][1010];
    	return f(cs, next, prev, 0, n - 1);
    }
    
    int mod = 1000000000 + 7;
    int[][] dp;
    
    int f(char[] cs, int[][] next, int[][] prev, int s, int e) {
    	if (s > e) return 0;
    	if (dp[s][e] > 0) return dp[s][e];
    	long ans = 0;
    	for (int i = 0; i < 4; ++i) {
    		int ns = next[i][s];
    		int ne = prev[i][e];
    		if (ns > ne) continue;
    		if (ns != ne) ans += 1;
    		ans ++;
    		ans += f(cs, next, prev, ns + 1, ne - 1);
    	}
    	dp[s][e] = (int)(ans % mod);
    	return dp[s][e];
    }
    
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
		SolutionDay19_L0730 day = new SolutionDay19_L0730();
		System.out.println(day.countPalindromicSubsequences("abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"));
	}

}
