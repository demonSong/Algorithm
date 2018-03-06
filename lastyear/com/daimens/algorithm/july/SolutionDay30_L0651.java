package com.daimens.algorithm.july;

public class SolutionDay30_L0651 {

	public int maxA(int N) {
		int[] dp = new int[N + 16];
		for (int i = 0; i <= N; ++i) dp[i] = i;
		for (int i = 1; i <= N; ++i){
			for (int j = i + 3; j <= N; ++j){
				dp[j] = Math.max(dp[j], (j - i - 1) * dp[i]);
			}
		}
		return dp[N];
	}

	public static void main(String[] args) {
		SolutionDay30_L0651 day = new SolutionDay30_L0651();
		System.out.println(day.maxA(3));
	}

}
