package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         600. Non-negative Integers without Consecutive Ones 
 *         My SubmissionsBack To Contest User Accepted: 126 User Tried: 460 Total
 *         Accepted: 130 Total Submissions: 1416 Difficulty: Hard Given a
 *         positive integer n, find the number of non-negative integers less
 *         than or equal to n, whose binary representations do NOT contain
 *         consecutive ones.
 * 
 *         Example 1: Input: 5 Output: 5 Explanation: Here are the non-negative
 *         integers <= 5 with their corresponding binary representations: 0 : 0
 *         1 : 1 2 : 10 3 : 11 4 : 100 5 : 101 Among them, only integer 3
 *         disobeys the rule (two consecutive ones) and the other 5 satisfy the
 *         rule. Note: 1 <= n <= 109
 *
 */
public class SolutionDay30_L0600 {
	
//	public int findIntegers(int num) {
//		int[][] dp = new int[num+1][2];
//		dp[0][0] = 0;
//		dp[1][1] = 0;
//		for (int i = 2; i <= num; i++){
//			if ((i & 0x01) == 0){
//				dp[i][0] = dp[i>>1][0] + dp[i>>1][1];
//			}else{
//				dp[i][1] = dp[i>>1][1] + 1;
//			}
//		}
//		return num - dp[num][num & 0x01];
//	}
	
	public int findIntegers(int num) {
		int[] f = new int[32];
		f[0] = 1;
		f[1] = 2;
		for (int i = 2; i <= 31; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}

		int pos = 31;
		while (pos >= 0 && ((num & (1 << pos)) == 0))
			pos--;
		if (pos == -1)
			return 1;

		int res = 0;
		int pre = 0;
		for (int i = pos; i >= 0; i--) {
			if (((num >> i) & 0x01) == 1) {
				res += f[i];
				if (pre == 1){
					res --;
					break;
				}
				pre = 1;
			}else{
				pre = 0;
			}
		}
		return res + 1;
	}
	
	
	public static void main(String[] args) {
		SolutionDay30_L0600 day = new SolutionDay30_L0600();
		System.out.println(4 & (1 << 2));
		System.out.println(day.findIntegers(4));
	}
}
