package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong 
 * 552. Student Attendance Record II 
 * My SubmissionsBack To
 *         Given a positive integer n, return
 *         the number of all possible attendance records with length n, which
 *         will be regarded as rewardable. The answer may be very large, return
 *         it after mod 109 + 7.
 * 
 *         A student attendance record is a string that only contains the
 *         following three characters:
 * 
 *         'A' : Absent. 'L' : Late. 'P' : Present. A record is regarded as
 *         rewardable if it doesn't contain more than one 'A' (absent) or more
 *         than two continuous 'L' (late).
 * 
 *         Example 1: Input: n = 2 Output: 8 Explanation: There are 8 records
 *         with length 2 will be regarded as rewardable: "PP" , "AP", "PA",
 *         "LP", "PL", "AL", "LA", "LL" Only "AA" won't be regarded as
 *         rewardable owing to more than one absent times. Note: The value of n
 *         won't exceed 100,000.
 *
 */
public class SolutionDay16_503 {

	// public int checkRecord(int n) {
	// build(n, 1, "");
	// return count;
	// }
	//
	// int count = 0;
	// private void build(int n, int start, String ans){
	//
	// if (!checkRecord(ans) && !ans.isEmpty()){
	// return;
	// }
	//
	// if (start == n + 1){
	// count ++;
	// return;
	// }
	//
	// build(n, start+1, ans + "A");
	// build(n, start+1, ans + "L");
	// build(n, start+1, ans + "P");
	// }
	//
	//
	// public boolean checkRecord(String s){
	// int[] map = new int[26];
	// for (int i = 0; i < s.length(); i++){
	// map[s.charAt(i)-'A']++;
	// }
	//
	// if (map['A'-'A'] > 1) return false;
	//
	// int countL = 1;
	// for (int i = 1; i < s.length(); i++){
	// if (s.charAt(i-1) == 'L' && s.charAt(i) == s.charAt(i-1)){
	// countL ++;
	// }else{
	// if (countL > 2) return false;
	// countL = 1;
	// }
	// }
	//
	// if (countL > 2) return false;
	//
	// return true;
	// }

	public int checkRecord(int n) {
		int MOD = 1000000007;

		int[][][] dp = new int[n + 1][2][3];

		dp[0] = new int[][] { { 1, 1, 1 }, { 1, 1, 1 } };

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 3; k++) {
					int val = dp[i - 1][j][2];
					if (j > 0)
						val = (val + dp[i - 1][j - 1][2]) % MOD;
					if (k > 0)
						val = (val + dp[i - 1][j][k - 1]) % MOD;
					dp[i][j][k] = val;
				}
			}
		}

		return dp[n][1][2];
	}

	public static void main(String[] args) {
		SolutionDay16_503 day = new SolutionDay16_503();
		day.checkRecord(2);
		// day.bottomUp(2, 1);
	}

}
