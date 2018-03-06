package com.daimens.algorithm.september;

import java.util.Arrays;

public class SolutionDay10_L0674 {
	
//	public int findLengthOfLCIS(int[] nums) {
//        if (nums.length == 0) return 0;
//		if (nums.length == 1) return 1;
//        int[] dp = new int[nums.length];
//		Arrays.fill(dp, 1);
//		int max = 1;
//		for (int i = 1; i < nums.length; ++i) {
//			if (nums[i] > nums[i - 1]) {
//				dp[i] = dp[i - 1] + 1;
//			}
//			max = Math.max(max, dp[i]);
//		}
//		return max;
//    }
	
//	public int findLengthOfLCIS(int[] nums) {
//        if (nums.length == 0) return 0;
//        int[] dp = new int[nums.length];
//		Arrays.fill(dp, 1);
//		int max = 1;
//		for (int i = 1; i < nums.length; ++i) {
//			if (nums[i] > nums[i - 1]) {
//				dp[i] = dp[i - 1] + 1;
//			}
//			max = Math.max(max, dp[i]);
//		}
//		return max;
//    }
	
	public int findLengthOfLCIS(int[] nums) {
		int n = nums.length;
		if (n == 0) return 0;
		int max = 1;
		for (int i = 1, k = 1; i < n; ++i) {
			if (nums[i] > nums[i - 1]) {
				k ++;
				max = Math.max(max, k);
			}
			else {
				k = 1;
			}
		}
		return max;
    }
	
	public static void main(String[] args) {
		SolutionDay10_L0674 day = new SolutionDay10_L0674();
	}

}
