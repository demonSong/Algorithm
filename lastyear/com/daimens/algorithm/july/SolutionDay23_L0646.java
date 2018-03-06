package com.daimens.algorithm.july;

import java.util.Arrays;

public class SolutionDay23_L0646 {
	
//	public int findLongestChain(int[][] pairs) {
//		int n = pairs.length;
//		Arrays.sort(pairs, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
//		int[] dp = new int[n];
//		dp[0] = 1;
//		for (int i = 1; i < n; ++i){
//			for (int j = 0; j < i; ++j){
//				if (pairs[i][0] > pairs[j][1]){
//					dp[i] = Math.max(dp[i], dp[j] + 1);
//				}
//				else{
//					dp[i] = Math.max(dp[i], dp[j]);
//				}
//			}
//		}
//		return dp[n - 1];
//    }
	
	public int findLongestChain(int[][] pairs) {
		int n = pairs.length;
		Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
		int ans = 0;
		int end = Integer.MIN_VALUE;
		for (int i = 0; i < n; ++i){
			while (i < n && pairs[i][0] <= end){
				i ++;
			}
			if (i < n){
				end = pairs[i][1];
				ans ++;
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		SolutionDay23_L0646 day = new SolutionDay23_L0646();
//		int[][] pairs = {{-6,9},{1,6},{8,10},{-1,4},{-6,-2},{-9,8},{-5,3},{0,3}};
		int[][] pairs = {{1,2},{2,3},{3,4}};
		System.out.println(day.findLongestChain(pairs));
	}
}
