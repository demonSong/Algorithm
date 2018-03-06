package com.daimens.algorithm.march;

import java.util.Arrays;

public class SolutionDay04_L0792 {
	
    public int numMatchingSubseq(String S, String[] words) {
    	int n = S.length();
    	int[][] dp = new int[n + 1][32];
    	for (int i = 0; i < n + 1; ++i) Arrays.fill(dp[i], -1);
    	for (int i = n - 1; i >= 0; --i) dp[0][S.charAt(i) - 'a'] = i + 1;
        
    	for (int j = n - 2; j >= 0; --j) {
    		for (int i = 0; i < 32; ++i) {
    			dp[j + 1][i] = dp[j + 2][i];
    		}
    		dp[j + 1][S.charAt(j + 1) - 'a'] = j + 2;
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
 
}
