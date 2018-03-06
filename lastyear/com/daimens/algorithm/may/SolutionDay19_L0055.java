package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 * 		   55.Jump Game
 * 
 *         Given an array of non-negative integers, you are initially positioned
 *         at the first index of the array.
 * 
 *         Each element in the array represents your maximum jump length at that
 *         position.
 * 
 *         Determine if you are able to reach the last index.
 * 
 *         For example: A = [2,3,1,1,4], return true.
 * 
 *         A = [3,2,1,0,4], return false.
 *
 */
public class SolutionDay19_L0055 {
	
//	public boolean canJump(int[] nums) {
//        boolean[] dp = new boolean[nums.length];
//		return canJump(nums,0,dp);
//    }
//
//	private boolean canJump(int[] nums, int pos,boolean[] dp){
//		if (dp[pos]) return false;
//		if (pos >= nums.length - 1) return true;
//		else{
//			int step = nums[pos];
//			for (int i = 1; i <= step; i++){
//				if (canJump(nums,pos + i,dp)){
//					return true;
//				}
//			}
//		}
//		dp[pos] = true;
//		return false;
//	}
	
//	public boolean canJump(int[] nums) {
//		boolean[] dp = new boolean[nums.length];
//		dp[0] = true;
//		for (int i = 0; i < nums.length; i++) {
//			if (!dp[i]) continue;
//			int step = nums[i];
//			for (int j = 1; j <= step; j++) {
//				if (i + j >= nums.length)
//					continue;
//				dp[i + j] = dp[i] || dp[i + j];
//			}
//		}
//		return dp[nums.length - 1];
//	}
	
//	public boolean canJump(int[] nums) {
//		boolean[] dp = new boolean[nums.length];
//		dp[0] = true;
//		for (int i = 0; i < nums.length; i++) {
//			if (!dp[i]) continue;
//			int step = nums[i];
//			for (int j = 1; j <= step; j++) {
//				if (i + j >= nums.length)
//					continue;
//				dp[i + j] = dp[i] || dp[i + j];
//			}
//		}
//		return dp[nums.length - 1];
//	}
	
	public boolean canJump(int[] nums) {
		int max = 0;
		for (int i = 0; i < nums.length; i++){
			if (i > max) return false;
			max = Math.max(max, nums[i] + i);
			if (max >= nums.length-1) return true;
		}
		return true;
	}
}
