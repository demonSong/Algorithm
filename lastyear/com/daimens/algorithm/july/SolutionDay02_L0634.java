package com.daimens.algorithm.july;

public class SolutionDay02_L0634 {

	
	static final int MOD = 1000000000 + 7;
	public int findDerangement(int n) {
		long[] dp = new long[n + 1];
		if (n == 1) return 0;
		if (n == 2) return 1;
		dp[1] = 0;
		dp[2] = 1;
		for (int i = 3; i <= n; ++i){
			dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
			dp[i] = (dp[i] * (i - 1)) % MOD;
		}
		return (int)dp[n];
	}
	
	public static void main(String[]  args) {
		SolutionDay02_L0634 day = new SolutionDay02_L0634();
	}
	
}
