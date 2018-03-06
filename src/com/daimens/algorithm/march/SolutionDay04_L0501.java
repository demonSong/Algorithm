package com.daimens.algorithm.march;

public class SolutionDay04_L0501 {
	
    public int numMatchingSubseq(String S, String[] words) {
    	int n = S.length();
    	int[][] dp = new int[n][32];
    	
    	for (int i = 0; i < n + 1; ++i) {
    		for (int j = 0; j < 32; ++j) {
    			dp[i][j] = -1;
    		}
    	}
    	
    	for (int j = n - 2; j >= 0; --j) {
    		for (int i = 0; i < 32; ++i) {
    			dp[j][i] = dp[j + 1][i];
    		}
    		dp[j][S.charAt(j + 1) - 'a'] = j + 1;
    	}
    	
    	int cnt = 0;
    	for (String word : words) {
    		int prv = 0;
    		boolean ok = true;
    		for (int j = 0; j < word.length(); ++j) {
    			int nxt = dp[prv][word.charAt(j) - 'a'];
    			if (nxt != -1) {
    				prv = nxt;
    			}
    			else ok = false;
    		}
    		if (ok) cnt ++;
    	}
    	
    	return cnt;
    }
    
    boolean match(String a, String b) {
    	int n = b.length();
    	int j = 0;
    	for (int i = 0; i < n; ++i) {
    		while (j < a.length() && a.charAt(j) != b.charAt(i)) {
    			j ++;
    		}
    		if (j == a.length()) return false;
    		if (a.charAt(j) == b.charAt(i)) j ++;
    	}
    	return true;
    }
}
