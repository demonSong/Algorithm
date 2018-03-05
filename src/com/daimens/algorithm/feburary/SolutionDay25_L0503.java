package com.daimens.algorithm.feburary;

public class SolutionDay25_L0503 {
	
    public int numTilings(int N) {
    	long[][] dp = new long[N+1][4];
    	int mod = 1000000007;
    	dp[1][0] = 1;
    	dp[1][1] = 1;
    	dp[1][2] = 1;
    	dp[1][3] = 1;
    	for (int i = 2; i <= N; ++i) {
    		dp[i][0] = (dp[i - 1][0] + dp[i - 1][3] + dp[i - 2][1] + dp[i - 2][2]) % mod;
    		dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod;
    		dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
    		dp[i][3] = dp[i - 1][0] % mod;
    	}
    	return (int)dp[N][0];
    }
}
