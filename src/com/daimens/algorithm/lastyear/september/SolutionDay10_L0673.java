package com.daimens.algorithm.september;

public class SolutionDay10_L0673 {
//	public int findNumberOfLIS(int[] nums) {
//		if (nums.length == 0) return 0;
//		if (nums.length == 1) return 1;
//        int n = nums.length;
//        int[][] dp = new int[n + 16][n + 16];
//        for (int i = 0; i < nums.length; ++i) {
//        	dp[i + 1][0] = 1;
//        }
//        int max = 1;
//        for (int i = 1; i < n; ++i) {
//        	for (int j = 0; j < i; ++j) {
//        		if (nums[i] > nums[j]) {
//        			for (int k = 0; k < n; ++k) {
//        				dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[j + 1][k] + 1);
//        				max = Math.max(max, dp[i + 1][j + 1]);
//        			}
//        		}
//        	}
//        }
//        
//		return 0;
//    }
	
	// dp[i][j] //路径数
	public int findNumberOfLIS(int[] nums) {
		int n = nums.length, max_len = 0;
		int[] len = new int[n];
		int[] cnt = new int[n];
		for (int i = 0; i < n; ++i) {
			len[i] = 1;
			cnt[i] = 1;
			for (int j = 0; j < i; ++j) {
				if (nums[i] > nums[j]) {
					if (len[i] == len[j] + 1) cnt[i] += cnt[j];
					else if (len[i] < len[j] + 1) {
						len[i] = len[j] + 1;
						cnt[i] = cnt[j];
					}
				}
				max_len = Math.max(max_len, len[i]);
			}
		}
		
		int res = 0;
		for (int i = 0; i < n; ++i) {
			if (len[i] == max_len) res += cnt[i];
		}
		return res;
	}
	
	public static void main(String[] args) {
		SolutionDay10_L0673 day = new SolutionDay10_L0673();
		System.out.println(day.findNumberOfLIS(new int[] {1,2,4,3,5,4,7,2}));
//		System.out.println(day.findNumberOfLIS(new int[] {1, 3, 5, 4, 7}));
	}
}
