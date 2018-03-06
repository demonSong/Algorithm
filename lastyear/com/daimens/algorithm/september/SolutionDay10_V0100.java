package com.daimens.algorithm.september;

import java.util.Arrays;
import java.util.Scanner;

public class SolutionDay10_V0100 {
	
	static final int INF = 1 << 29;
	static long[] sum;
	static long[] dp;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int N = in.nextInt();
			int M = in.nextInt();
			int[] C = new int[N];
			sum = new long[N + 1];
			for (int i = 0; i < N; ++i) {
				C[i] = in.nextInt();
				sum[i + 1] = sum[i] + C[i];
			}
			
			dp = new long[N + 1];
			Arrays.fill(dp, INF);
			int s = 0, t = 1;
			int[] deq = new int[N + 16];
			dp[0] = C[0] * C[0] + M;
			deq[0] = 0;
			for (int i = 1; i < N; ++i) {
				while (s + 1 < t && f(deq[s], i) >= f(deq[s + 1], i)) {
					s++;
				}
				dp[i] = M + sum[i + 1] * sum[i + 1] + f(deq[s], i);
				while (t - s >= 2 && check(deq[t - 2], deq[t - 1], i)) {
					t--;
				}
				deq[t++] = i;
			}
			System.out.println(dp[N - 1]);
		}
		in.close();
	}
	
	public static long f(int j, int i) {
		return - 2 * sum[i + 1] * sum[j + 1] + dp[j] + sum[j + 1] * sum[j + 1];
	}
	
	// 均是关于j的变量
	public static boolean check(int f1, int f2, int f3) {
		long a1 = -2 * sum[f1 + 1];
		long b1 = dp[f1] + sum[f1 + 1] * sum[f1 + 1];
		
		long a2 = -2 * sum[f2 + 1];
		long b2 = dp[f2] + sum[f2 + 1] * sum[f2 + 1];
		
		long a3 = -2 * sum[f3 + 1];
		long b3 = dp[f3] + sum[f3 + 1] * sum[f3 + 1];
		
		return (a2 - a1) * (b3 - b2) >= (b2 - b1) * (a3 - a2);
	}
}
