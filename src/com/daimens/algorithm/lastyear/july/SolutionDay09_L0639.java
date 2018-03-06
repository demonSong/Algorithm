package com.daimens.algorithm.july;

public class SolutionDay09_L0639 {

//	static final int MOD = 1000000000 + 7;
//
//	// public int numDecodings(String s) {
//	// return helper(s.toCharArray(), 0);
//	// }
//
//	public int numDecodings(String s) {
//		int n = s.length();
//		char[] cs = s.toCharArray();
//		if (n == 0)
//			return 0;
//		if (n == 1 && cs[0] == '*')
//			return 9;
//		if (n == 1 && cs[0] == '0')
//			return 0;
//		if (n == 1 && cs[0] != '*')
//			return 1;
//		int[][] dp = new int[n + 1][2];
//		dp[n][0] = dp[n][1] = 0;
//		
//		if (cs[n - 1] == '*')
//			dp[n - 1][0] = 9;
//		if (cs[n - 1] != '*' && cs[n - 1] != '0')
//			dp[n - 1][0] = 1;
//		if (cs[n - 1] == '0')
//			dp[n - 1][0] = 0;
//
////		if (n - 2 >= 0)
////			dp[n - 2][0] = dp[n - 1][0];
////		if (n - 2 >= 0 && cs[n - 2] != '*') {
////			if (cs[n - 1] == '*') {
////				for (int i = 1; i <= 9; ++i) {
////					int valid = (cs[n - 2] - '0') * 10 + i;
////					if (valid >= 1 && valid <= 26) {
////						dp[n - 2][1] += 1;
////					}
////				}
////			} else {
////				int valid = (cs[n - 2] - '0') * 10 + cs[n - 1] - '0';
////				if (valid >= 1 && valid <= 26) {
////					dp[n - 2][1] += 1;
////				}
////			}
////		}
////
////		if (n - 2 >= 0 && cs[n - 2] == '*') {
////			if (cs[n - 1] == '*') {
////				for (int i = 1; i <= 9; ++i) {
////					for (int j = 1; j <= 9; ++j) {
////						int valid = j * 10 + i;
////						if (valid >= 1 && valid <= 26) {
////							dp[n - 2][1] += 1;
////						}
////					}
////				}
////			} else {
////				for (int i = 1; i <= 9; ++i) {
////					int valid = 10 * i + cs[n - 1] - '0';
////					if (valid >= 1 && valid <= 26) {
////						dp[n - 2][1] += 1;
////					}
////				}
////			}
////		}
//
//		for (int i = n - 2; i >= 0; --i) {
//			if (cs[i] == '*') {
//				dp[i][0] = (9 * (dp[i + 1][0] + dp[i + 1][1]) % MOD) % MOD;
//				if (cs[i + 1] == '*'){
//					dp[i][1] = (17 * (dp[i + 2][1] + dp[i + 2][0]) % MOD) % MOD;
//				}
//				else{
//					int cnt = 0;
//					for (int j = 1; j <= 9; ++j){
//						int valid = j * 10 + cs[i + 1] - '0';
//						if (valid >= 1 && valid <= 26) cnt++;
//					}
//					dp[i][1] = (cnt * (dp[i + 2][0] + dp[i + 2][1]) % MOD) % MOD;
//				}
//			} 
//			else if (cs[i] == '0'){
//				dp[i][0] = dp[i + 1][0];
//				dp[i][1] = dp[i + 1][1];
//			}
//			else{
//				dp[i][0] = (1 * (dp[i + 1][0] + dp[i + 1][1]) % MOD) % MOD;
//				if (cs[i + 1] == '*'){
//					int cnt = 0;
//					for (int j = 1; j <= 9; ++j){
//						int valid = (cs[i] - '0') * 10 + j;
//						if (valid >= 1 && valid <= 26){
//							cnt ++;
//						}
//					}
//					dp[i][1] = (cnt * (dp[i + 2][0] + dp[i + 2][1]) % MOD) % MOD;
//				}
//				else{
//					int valid = (cs[i] - '0' )* 10 + cs[i + 1] - '0';
//					int cnt = (valid >= 1 && valid <= 26) ? 1 : 0;
//					dp[i][1] = (cnt * (dp[i + 2][0] + dp[i + 2][1]) % MOD) % MOD;
//				}
//			}
//		}
//		return dp[0][1] + dp[0][0];
//	}

	// Map<String, Integer> mem = new HashMap<>();
	// public int helper(char[] s, int pos){
	// if (pos == s.length) return 1;
	// if (pos == s.length - 1 && s[pos] == '*') return 9;
	// if (pos == s.length - 1 && s[pos] != '*') return 1;
	// if (pos == s.length - 1 && s[pos] == '0') return 0;
	// if (mem.containsKey(new String(s).substring(pos))) return mem.get(new
	// String(s).substring(pos));
	// if (s[pos] != '*'){
	// if (s[pos] == '0') return 0;
	// int num = 0;
	// num = (helper(s, pos + 1) + num) % MOD;
	// if (pos + 1 < s.length && s[pos + 1] != '*'){
	// int valid = 10 * (s[pos] - '0') + s[pos+1] - '0';
	// if (valid >= 1 && valid <= 26){
	// num = (helper(s, pos + 2) + num) % MOD;
	// }
	// }
	// else if (pos + 1 < s.length && s[pos + 1] == '*'){
	// for (int i = 1; i <= 9; ++i){
	// int valid = 10 * (s[pos] - '0') + i;
	// if (valid >=1 && valid <= 26){
	// num = (num + helper(s, pos + 2)) % MOD;
	// }
	// }
	// }
	// mem.put(new String(s).substring(pos), num);
	// return num;
	// }else{
	// int num = 0;
	// for (int i = 1; i <= 9; ++i){
	// num = (helper(s, pos + 1) + num) % MOD;
	// }
	//
	// if (pos + 1 < s.length && s[pos + 1] != '*'){
	// for (int i = 1; i <= 9; ++i){
	// int valid = 10 * i + s[pos + 1] - '0';
	// if (valid >= 1 && valid <= 26){
	// num = (num + helper(s, pos + 2)) % MOD;
	// }
	// }
	// }
	// else if (pos + 1 < s.length && s[pos + 1] == '*'){
	// for (int i = 1; i <= 9; ++i){
	// for (int j = 1; j <= 9; ++j){
	// int valid = 10 * i + j;
	// if (valid >= 1 && valid <= 26){
	// num = (num + helper(s, pos + 2)) % MOD;
	// }
	// }
	// }
	// }
	// mem.put(new String(s).substring(pos), num);
	// return num;
	// }
	// }
	
	
	static final int MOD = 1000000000 + 7;
	public int numDecodings(String s) {
		char[] c = s.toCharArray();
		int n = c.length;
		long[] dp = new long[n + 16];
		dp[n] = 1;
		if (c[n - 1] == '*') dp[n - 1] = 9;
		else if (c[n - 1] == '0') dp[n - 1] = 0;
		else dp[n - 1] = 1;

		for (int i = n - 1; i >= 0; --i) {
			if (c[i] == '0') {
				dp[i] = 0;
			} 
			else if (c[i] == '*') {
				dp[i] = (dp[i] + 9 * dp[i + 1]) % MOD;
				if (c[i + 1] == '*') {
					dp[i] = (dp[i] + 15 * dp[i + 2]) % MOD;
				} else if (c[i + 1] - '0' <= 6) {
					dp[i] = (dp[i] + 2 * dp[i + 2]) % MOD;
				} else {
					dp[i] = (dp[i] + dp[i + 2]) % MOD;
				}
			} 
			else {
				dp[i] = (dp[i] + dp[i + 1]) % MOD;
				if (c[i] == '1') {
					if (c[i + 1] == '*') {
						dp[i] = (dp[i] + 9 * dp[i + 2]) % MOD;
					} else {
						dp[i] = (dp[i] + dp[i + 2]) % MOD;
					}
				} else if (c[i] == '2') {
					if (c[i + 1] == '*') {
						dp[i] = (dp[i] + 6 * dp[i + 2]) % MOD;
					} else if (c[i + 1] - '0' <= 6) {
						dp[i] = (dp[i] + dp[i + 2]) % MOD;
					}
				}
			}
		}
		return (int) dp[0];
	}
	
	
	public static void main(String[] args) {
		SolutionDay09_L0639 day = new SolutionDay09_L0639();
		System.out.println(day.numDecodings("1*"));
	}

}
