package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         552. Student Attendance Record II
 * 
 *         Given a positive integer n, return the number of all possible
 *         attendance records with length n, which will be regarded as
 *         rewardable. The answer may be very large, return it after mod 109 +
 *         7.
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
public class SolutionDay25_552 {

//	public int checkRecord(int n) {
//		build(n, 1, "");
//		return count;
//	}
//
//	int count = 0;
//	private void build(int n, int start, String ans) {
//
//		if (!checkRecord(ans) && !ans.isEmpty()) {
//			return;
//		}
//
//		if (start == n + 1) {
//			count++;
//			return;
//		}
//
//		build(n, start + 1, ans + "A");
//		build(n, start + 1, ans + "L");
//		build(n, start + 1, ans + "P");
//	}
//
//	public boolean checkRecord(String s) {
//		int[] map = new int[26];
//		for (int i = 0; i < s.length(); i++) {
//			map[s.charAt(i) - 'A']++;
//		}
//
//		if (map['A' - 'A'] > 1)
//			return false;
//
//		int countL = 1;
//		for (int i = 1; i < s.length(); i++) {
//			if (s.charAt(i - 1) == 'L' && s.charAt(i) == s.charAt(i - 1)) {
//				countL++;
//			} else {
//				if (countL > 2)
//					return false;
//				countL = 1;
//			}
//		}
//
//		if (countL > 2)
//			return false;
//
//		return true;
//	}
	final int MOD = 1000000007;
	
	public int checkRecord(int n) {
	    
	    int[][][] f = new int[n + 1][2][3];
	    
	    {
	    	f[1][0][0] = 1;
	    	f[1][0][1] = 1;
	    	f[1][0][2] = 0;
	    	f[1][1][0] = 1;
	    	f[1][1][1] = 0;
	    	f[1][1][2] = 0;
	    }
	    
	    for (int i = 2; i <= n; i++){
	    	f[i][0][0] = sum(f, i-1, 0);
	    	f[i][0][1] = f[i-1][0][0];
	    	f[i][0][2] = f[i-1][0][1];
	    	f[i][1][0] = (sum(f, i-1, 0) + sum(f,i-1,1)) % MOD;
	    	f[i][1][1] = f[i-1][1][0];
	    	f[i][1][2] = f[i-1][1][1];
	    }
	    
	    return (sum(f, n, 0) + sum(f, n, 1)) % MOD;
	}
	
	private int sum(int[][][] f, int i, int j){
		long sum = 0;
		for (int k = 0; k < 3; k ++){
			sum += f[i][j][k];
		}
		return (int) (sum % MOD);
	}
	
	public static void main(String[] args) {
		SolutionDay25_552 day = new SolutionDay25_552();
		day.checkRecord(8);
	}
}
