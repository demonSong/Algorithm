package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 377.Combination Sum IV
 * Given an integer array with all positive numbers and no duplicates,find the number of possible
 * combinations that add up to a positive integer target.
 * 
 * Example:
 * nums = [1,2,3]
 * target = 4
 * The possible combination ways are:
 * (1,1,1,1)
 * (1,1,2)
 * (1,2,1)
 * (1,3)
 * (2,1,1)
 * (2,2)
 * (3,1)
 * Note that different sequences are counted as different combinations.
 * Therefore the output is 7.
 * 
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 *
 */
public class SolutionDay15_377 {
	
	// comb[target] = sum(comb[target-nums[i]]),where 0 <= i < nums.length,and target >= nums[i]
	// 递归
//	public int combinationSum4(int[] nums,int target){
//		//先1 
//		int tmp = target;
//		for (int i = 0; i < nums.length; i++){
//			while(tmp > 0){
//				tmp -= nums[i];
//			}
//			tmp += nums[i];
//			
//		}
//		return 0;
//	}
	
	//递归的性质 如何判断
//	public int combinationSum4(int[] nums,int target){
//		if(target == 0)
//			return 1;
//		int res = 0;
//		for(int i= 0; i < nums.length; i++){
//			if(target >= nums[i]){
//				res += combinationSum4(nums, target-nums[i]);
//			}
//		}
//		return res;
//	}
//	
//	private int[] dp;
//	
//	public int combinationSum4(int[] nums,int target){
//		dp = new int[target + 1];
//		Arrays.fill(dp, -1);
//		dp[0] = 1;
//		return helper(nums, target);
//	}
//	
//	private int helper(int[] nums,int target){
//		if(dp[target] != -1){
//			return dp[target];
//		}
//		int res = 0;
//		for(int i = 0; i < nums.length; i++){
//			if(target >= nums[i]){
//				res += helper(nums, target-nums[i]);
//			}
//		}
//		dp[target] = res;
//		return res;
//	}
	
	//这种过渡是相当的有意思的一件事
	public int combinationSum4(int[] nums, int target) {
	    int[] comb = new int[target + 1];
	    comb[0] = 1;
	    for (int i = 1; i < comb.length; i++) {
	        for (int j = 0; j < nums.length; j++) {
	            if (i - nums[j] >= 0) {
	                comb[i] += comb[i - nums[j]];
	            }
	        }
	    }
	    return comb[target];
	}
}
