package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 494.Target Sum
 * You are given a list of non-negative integers, a1,a2,...,an,and a target,S.Now you have 2 symbols + and -. 
 * For each integer,you should choose one from + and - as its new symbol.
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * Example 1:
 * Input:nums is [1,1,1,1,1],S is 3.
 * Output: 5
 * Explanation:
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * 
 * Note:
 * 1. The length of the given array is positive and will not exceed 20.
 * 2. The sum of elements in the given array will not exceed 1000.
 * 3. Your output answer is guaranteed to be fitted in a 32-bit integer.
 * 
 *
 */
public class SolutionDay05_494 {
	//值得讲解的一道dp题
//	public int findTargetSumWays(int[] nums, int S) {
//		int sum = 0;
//		for (int i : nums) {
//			sum += i;
//		}
//		if (S > sum || S < -sum) {
//			return 0;
//		}
//		int[] dp = new int[2 * sum + 1];
//		dp[0 + sum] = 1;
//		for (int i = 0; i < nums.length; i++) {
//			int[] next = new int[2 * sum + 1];
//			for (int k = 0; k < 2 * sum + 1; k++) {
//				if (dp[k] != 0) {//从后往前求！！！求得是sum
//					next[k + nums[i]] += dp[k];
//					next[k - nums[i]] += dp[k];
//				}
//			}
//			dp = next;
//		}
//		return dp[sum + S];
//	}
	
	public int findTargetSumWays(int[] nums, int S) {
		int sum = 0;
		for (int num : nums) sum += num;
		
		if (sum < S) return 0;
		
		int[][] dp = new int[nums.length+1][2 * sum + S];
		return helper(nums, 0, S,dp,sum);
	}
	
	private int helper(int[] nums, int start,int target,int[][] dp,int sum){
		if (start == nums.length && target == 0) return 1;
		if (start == nums.length && target != 0) return 0;
		if(dp[start][target + sum] != 0) return dp[start][target+sum];
		int value = 0;
		value = helper(nums, start+1, target + nums[start],dp,sum) + helper(nums, start+1, target - nums[start],dp,sum);
		return dp[start][target + sum] = value;
	}
	
}
