package com.daimens.algorithm.september;

public class SolutionDay13_T0001 {
	
	char[] AGCT = {'A','G','C','T'};
	
	public void solve(int n, String S) {
		int[][] next = new int[S.length()][4];
		int K = S.length();
		for (int i = 0; i < S.length(); ++i) {
			for (int j = 0; j < 4; ++j) {
				String s = S.substring(0, i) + AGCT[j];
				while (!S.substring(0, s.length()).equals(s)) {
					s = s.substring(1);
				}
				next[i][j] = s.length();
			}
		}
		int[][] dp = new int[n + 1][S.length() + 1];
		dp[0][0] = 1;
		for (int i = 1; i < K; ++i) dp[0][i] = 0;
		for (int i = 1; i <= n; ++i) {
			for (int j = 0; j < K; ++j) {
				for (int l = 0; l < 4; ++l) {
					int ti = next[j][l];
					if (ti == K) continue;
					dp[i][ti] = dp[i][ti] + dp[i - 1][j];
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < K; ++i) ans += dp[n][i];
		System.out.println(ans);
	}
	
	public static void main(String[] args) {
		SolutionDay13_T0001 day = new SolutionDay13_T0001();
		day.solve(3, "ATCATCG");
	}

}
