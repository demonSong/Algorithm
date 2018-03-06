package com.daimens.algorithm.august;

public class SolutionDay21_L0664 {
	
//	public int strangePrinter(String s) {
//		return helper(s.toCharArray(), 0, s.length() - 1);
//	}
//	
//	public int helper(char[] cs, int i, int j){
//		if (i == j) return 1;
//		if (i > j) return 0;
//		find(cs, i, j);
//		int min = Integer.MAX_VALUE;
//		for (int k = 0; k < 32; ++k){
//			int res = 0;
//			if (lf[k] != -1 && rt[k] != -1){
//				if (lf[k] == rt[k]){ // 只有一个元素
//					res = 1 + helper(cs, i, lf[k] - 1);
//					find(cs, i, j);
//					res += helper(cs, rt[k] + 1, j);
//				}
//				else{
//					int ll = lf[k];
//					int rr = rt[k];
//					while (ll <= rt[k] && cs[ll] == k + 'a') ll ++;
//					while (rr >= lf[k] && cs[rr] == k + 'a') rr --;
//					if (ll > rt[k]){ // 全是相同的元素
//						res = 1 + helper(cs, i, lf[k] - 1);
//						find(cs, i, j);
//						res += helper(cs, rt[k] + 1, j);
//					}else{
//						res = 1 + helper(cs, i, lf[k] - 1);
//						find(cs, i, j);
//						res += helper(cs, rt[k] + 1, j);
//						find(cs, i, j);
//						res += helper(cs, ll, rr);
//					}
//				}
//				find(cs, i, j);
//			}
//			if (res != 0)
//				min = Math.min(min, res);
//		}
//		return min;
//	}
//	
//	int[] lf;
//	int[] rt;
//	public void find(char[] cs, int i, int j){
//		lf = new int[32];
//		rt = new int[32];
//		Arrays.fill(lf, -1);
//		Arrays.fill(rt, -1);
//		for (int pos = i; pos <= j; ++pos){
//			if (lf[cs[pos] - 'a'] == -1){
//				lf[cs[pos] - 'a'] = pos;
//			}
//		}
//		
//		for (int pos = j; pos >= i; --pos){
//			if (rt[cs[pos] - 'a'] == -1){
//				rt[cs[pos] - 'a'] = pos;
//			}
//		}
//		
//	}
	
//	static final int INF = 1 << 29;
//	public int strangePrinter(String s) {
//		return helper(s.toCharArray(), 0, s.length() - 1);
//	}
//	
//	public int helper(char[] cs, int i, int j){
//		Set<Character> set = new HashSet<>();
//		for (int k = i; k <= j; ++k) set.add(cs[k]);
//		int[] cost = new int[32];
//		Arrays.fill(cost, INF);
//		calculCost(cs, cost, i, j, set);
//		
//		int[] dp = new int[1 << 27 - 1];
//		Arrays.fill(dp, INF);
//		
//		int end = 0;
//		for (int l = 0; l < 26; ++l){
//			char c = (char) ('a' + i);
//			if (set.contains(c)){
//				dp[1 << i] = 1;
//				end |= 1 << i;
//			}
//		}
//		
//		if (set.size() == 1) return 1;
//		
//		for (int state = 0; state <= end; ++state){
//			for (int l = 0; l < 26; ++l){
//				if ((state & 1 << l) != 0){
//					dp[state] = Math.min(dp[state], dp[state & ~(1 << l)] + cost[l]);
//					char c = (char) ('a' + l);
//					int lf = i;
//					int rt = j;
//					while (lf <= j && cs[lf] != c) lf ++;
//					while (rt >= i && cs[rt] != c) rt --;
//					dp[state] = Math.min(helper(cs, lf, rt), dp[state]);
//				}
//			}
//		}
//		
//		return dp[end];
//	}
//	
//	public void calculCost(char[] cs, int[] cost, int i, int j, Set<Character> set){
//		for (char c : set){
//			int cnt = 0;
//			for (int k = i; k <= j; ++k){
//				if (cs[i] == c){
//					cnt ++;
//					int l = k;
//					while (l <= j && cs[l] == c) l++;
//					k = l;
//				}
//			}
//			cost[c - 'a'] = cnt;
//		}
//	}
	
//	static final int INF = 1 << 29;
//	public int strangePrinter(String s) {
//		int[][] dp = new int[s.length() + 1][s.length() + 1];
//		for (int i = 0; i < dp.length; ++i) Arrays.fill(dp[i], INF);
//		for (int i = 0; i < s.length(); ++i){
//			dp[i][i] = 1;
//		}
//		for (int j = 1; j < s.length(); ++j) {
//			char append = s.charAt(j);
//			for (int i = j - 1; i >= 0; --i) {
//				char begin = s.charAt(i);
//				if (begin == append) {
//					int lf = i;
//					int rt = j;
//					while (lf <= j && s.charAt(lf) == append)
//						lf++;
//					while (rt >= i && s.charAt(rt) == append)
//						rt--;
//					if (lf > rt) {
//						dp[i][j] = Math.min(dp[i][j], 0 + 1);
//					} else
//						dp[i][j] = Math.min(dp[i][j], dp[lf][rt] + 1);
//				} else {
//					int l = i;
//					while (l < j && s.charAt(l) == begin)
//						l ++;
//					dp[i][j] = Math.min(dp[i][j], dp[l][j] + 1);
//				}
//			}
//		}
//		return dp[0][s.length() - 1];
//	}
	
//	int[][][] dp = new int[101][101][101];
//	public int strangePrinter(String s) {
//		for (int i = 0; i < 101; ++i){
//			for (int j = 0; j < 101; ++j){
//				for (int k = 0; k < 101; ++k){
//					dp[i][j][k] = -1;
//				}
//			}
//		}
//		return f(s.toCharArray(), 0, s.length() - 1, 0, dp);
//	}
//	
//	public int f(char[] cs, int i, int j, int k, int[][][] dp){
//		if (i > j) return 0;
//		if (dp[i][j][k] > 0) return dp[i][j][k];
//		for (;i + 1 <= j && cs[i] == cs[i + 1]; ++i, ++k);
//		int res = 1 + f(cs, i + 1, j, 0, dp);
//		for (int m = i + 1; m <= j; ++m){
//			if (cs[m] == cs[i]){
//				res = Math.min(res, f(cs, i + 1, m - 1, 0, dp) + f(cs, m, j, k + 1, dp));
//			}
//		}
//		return dp[i][j][k] = res;
//	}
	
//	int[][] dp = new int[101][101];
//	public int strangePrinter(String s) {
//		for (int i = 0; i < 101; ++i){
//			for (int j = 0; j < 101; ++j){
//					dp[i][j] = -1;
//			}
//		}
//		return f(s.toCharArray(), 0, s.length() - 1, dp);
//	}
//	
//	public int f(char[] cs, int i, int j, int[][] dp){
//		if (i > j) return 0;
//		if (dp[i][j] > 0) return dp[i][j];
//		for (;i + 1 <= j && cs[i] == cs[i + 1]; ++i);
//		int res = 1 + f(cs, i + 1, j, dp);
//		for (int m = i + 1; m <= j; ++m){
//			if (cs[m] == cs[i]){
//				res = Math.min(res, f(cs, i + 1, m - 1, dp) + f(cs, m, j, dp));
//			}
//		}
//		return dp[i][j] = res;
//	}
	
	// iter
	public int strangePrinter(String s) {
		int[][] dp = new int[101][101];
		int n = s.length();
		if (n == 0) return 0;
		char[] cs = s.toCharArray();
		for (int i = 0; i < n; ++i) dp[i][i] = 1;
		for (int i = n - 1; i >= 0; --i){
			for (int j = i + 1; j < n; ++j){
				dp[i][j] = dp[i + 1][j] + 1;
				char c = cs[i];
				for (int k = i; k < j; ++k){
					if (cs[k + 1] == c) dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] - 1);
				}
			}
		}
		return dp[0][n - 1];
	}
	
	public static void main(String[] args) {
		SolutionDay21_L0664 day = new SolutionDay21_L0664();
		String s = "baacdddaaddaaaaccbddbcabdaabdbbcdcbbbacbddcabcaaa";
		// tbgtgb
		System.out.println(day.strangePrinter("baacdddaaddaaaaccbddbcabdaabdbbcdcbbbacbddcabcaaa"));
	}
	
}
