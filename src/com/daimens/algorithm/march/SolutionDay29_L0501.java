package com.daimens.algorithm.march;

import java.util.Scanner;

public class SolutionDay29_L0501 {

	public static int integerBreak(int n) {
		int[] dp = new int[n + 1];
		dp[2] = 1;
		for (int i = 3; i <= n; ++i) {
			dp[i] = i - 1;
			for (int j = 2; j < i; ++j) {
				dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			System.out.println(integerBreak(n));
		}
		in.close();
	}

}
