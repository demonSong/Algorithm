package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong 
 * 
 * 53.Maximum Subarray 
 * 
 * 		   Find the contiguous subarray within an
 *         array (containing at least one number) which has the largest sum.
 * 
 *         For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous
 *         subarray [4,-1,2,1] has the largest sum = 6.
 *
 */
public class SolutionDay18_053 {
	
//	public int maxSubArray(int[] nums) {
//		
//		int[] sums = new int[nums.length];
//		sums[0] = nums[0];
//		for (int i = 1; i < nums.length; i++){
//			sums[i] = sums[i-1] + nums[i];
//		}
//		
//		return 0;
//	}
	
	//time limit
//	public int maxSubArray(int[] nums) {
//		
//		if (nums.length == 1) return nums[0];
//
//		for (int i = 1; i < nums.length; i++){
//			nums[i] += nums[i-1];
//		}
//		
//		int max = Integer.MIN_VALUE;
//		
//		for (int i = 0; i < nums.length-1; i++){
//			for (int j = i+1; j< nums.length; j++){
//				max = Math.max(max, nums[j]-nums[i]);
//			}
//		}
//		
//		for (int i = 0; i < nums.length; i++){
//			max = Math.max(max, nums[i]);
//		}
//		
//		return max;
//	}
		
	
	/**
	 * main idea:
	 * figure out is the format of the sub problem (or the state of each sub problem)
	 * @param nums
	 * @return
	 */
	
	// 最优化原理 后效性
	public int maxSubArray(int[] nums){
		
		
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int max = dp[0];
		
		for (int i = 1; i < nums.length; i++){
			dp[i] = dp[i-1] > 0 ? dp[i-1] + nums[i] : 0 + nums[i];
			max = Math.max(max, dp[i]);
		}
		
		
		return max;
	}
	
	
	
	public static void main(String[] args) {
		SolutionDay18_053 day = new SolutionDay18_053();
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		day.maxSubArray(nums);
	}

}
