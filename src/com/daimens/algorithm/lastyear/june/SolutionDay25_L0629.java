package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         629. K Inverse Pairs Array My SubmissionsBack To Contest User
 *         Accepted: 81 User Tried: 363 Total Accepted: 83 Total Submissions:
 *         1074 Difficulty: Medium Given two integers n and k, find how many
 *         different arrays consist of numbers from 1 to n such that there are
 *         exactly k inverse pairs.
 * 
 *         We define an inverse pair as following: For ith and jth element in
 *         the array, if i < j and a[i] > a[j] then it's an inverse pair;
 *         Otherwise, it's not.
 * 
 *         Since the answer may very large, the answer should be modulo 109 + 7.
 * 
 *         Example 1: Input: n = 3, k = 0 Output: 1 Explanation: Only the array
 *         [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse
 *         pair. Example 2: Input: n = 3, k = 1 Output: 2 Explanation: The array
 *         [1,3,2] and [2,1,3] have exactly 1 inverse pair. Note: The integer n
 *         is in the range [1, 1000] and k is in the range [0, 1000].
 *
 */
public class SolutionDay25_L0629 {

//	static int MOD = 1000000000 + 7;
//	public int kInversePairs(int n, int k) {
//		int[][] dp = new int[n + 1][k + 1];
//		for (int i = 1; i <= n; ++i)
//			dp[i][0] = 1;
//		for (int i = 2; i <= n; ++i) {
//			for (int j = 1; j <= k; ++j) {
//				for (int l = Math.min(i - 1, j); l >= 0; --l) {
//					dp[i][j] = (dp[i][j] + dp[i - 1][j - l]) % MOD;
//				}
//			}
//		}
//		return dp[n][k];
//	}

	static int MOD = 1000000000 + 7;
	public int kInversePairs(int n, int k) {
		int[][] dp = new int[n + 1][k + 1];
		for (int i = 1; i <= n; ++i) dp[i][0] = 1;
		for (int i = 2; i <= n; ++i) {
			for (int j = 1; j <= k; ++j) {
				int val = (dp[i-1][j] - ((j - i < 0) ? 0 : dp[i-1][j-i]) + MOD) % MOD;
				dp[i][j] = (dp[i][j-1] + val) % MOD;
			}
		}
		return dp[n][k];
	}
	
	public static void main(String[] args) {
		SolutionDay25_L0629 day = new SolutionDay25_L0629();
		System.out.println(day.kInversePairs(2, 1));
	}

}
