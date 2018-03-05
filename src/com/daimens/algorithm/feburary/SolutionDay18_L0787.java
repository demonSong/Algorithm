package com.daimens.algorithm.feburary;

import java.util.Arrays;

public class SolutionDay18_L0787 {
	
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		int inf = 0x3f3f3f3f;
		int[] ds = new int[n];
		Arrays.fill(ds, inf);
		ds[src] = 0;
		
		int ans = ds[dst];
		for (int k = 0; k <= K; ++k) {
			int[] nds = new int[n];
			Arrays.fill(nds, inf);
			for (int[] e : flights) {
				nds[e[1]] = Math.min(nds[e[1]], ds[e[0]] + e[2]);
			}
			ds = nds;
			ans = Math.min(ans, ds[dst]);
		}
		if (ans == inf) return -1; else return ans;
	}
	
//	static final int INF = 0x3f3f3f3f;
//	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
//		int[][] cost = new int[n][n];
//		N = n;
//		dp = new int[n][n];
//		canVis = new boolean[n][n];
//		for (int i = 0; i < n; ++i) {
//			for (int j = 0; j < n; ++j) {
//				cost[i][j] = INF;
//				dp[i][j] = INF;
//			}
//		}
//		for (int[] flight : flights) {
//			int from = flight[0];
//			int to = flight[1];
//			int val = flight[2];
//			cost[from][to] = val;
//			dp[from][to] = val;
//		}
//
//		for (int i = 0; i < N; ++i) {
//			for (int j = 0; j < N; ++j) {
//				for (int k = 0; k < N; ++k) {
//					dp[j][k] = Math.min(dp[j][k], dp[j][i] + dp[i][k]);
//				}
//			}
//		}
//		
//		int ans = dfs(cost, src, dst, K, 0, -1);
//		if (ans >= INF) return -1;
//		return ans;
//	}
//
//	int N;
//	int[][] dp;
//	boolean[][] canVis;
//
//	int dfs(int[][] cost, int src, int dst, int K, int step, int father) {
//		if (canVis[src][dst]) return dp[src][dst];
//		if (src == dst) return 0;
//		if (step > K) return INF;
//		int min = INF;
//		for (int i = 0; i < N; ++i) {
//			if (cost[src][i] < 0x3f3f3f3f && i != father) {
//				min = Math.min(min, dfs(cost, i, dst, K, step + 1, src) + cost[src][i]);
//			}
//		}
//		if (min < INF) {
//			canVis[src][dst] = true;
//		}
//		return min;
//	}

	public static void main(String[] args) {
		SolutionDay18_L0787 day = new SolutionDay18_L0787();
		int[][] flights = { { 0, 1, 100 }, { 1, 2, 100 }, { 0, 2, 500 } };
		System.out.println(day.findCheapestPrice(3, flights, 0, 2, 1));
	}

}
