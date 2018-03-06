package com.daimens.algorithm.august;

import java.util.Scanner;

public class SolutionDay13_B1001 {
	
	
	static final int MOD = 1000000007;
	static final int MAX_N = 1000 + 16;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		long[][] dp = new long[MAX_N][MAX_N];
		dp[1][1] = 1;
		for (int i = 1; i < 1002; ++i){
			dp[1][i] = 1;
		}
		
		for (int j = 2; j < 1002; ++j){
			long sum = 0;
			for (int i = 2; i < 1002; ++i){
				sum = (sum + dp[j - 1][i - 1]) % MOD;
				dp[j][i] = sum;
			}
		}
		
		while (T --> 0){
			int N = in.nextInt();
			int M = in.nextInt();
			int min = Math.min(N, M);
			long sum = 0;
			for (int i = 1; i <= N + M - min; ++i){
				sum = (sum + dp[min][i]) % MOD;
			}
			System.out.println(sum);
		}
		in.close();
	}
}
