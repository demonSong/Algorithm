package com.daimens.algorithm.april;

import java.awt.image.RescaleOp;
import java.util.Arrays;

/**
 * 
 * @author DemonSong 152.Maximum Product Subarray
 * 
 *         Find the contiguous subarray within an array (containing at least one
 *         number) which has the largest product.
 * 
 *         For example, given the array [2,3,-2,4], the contiguous subarray
 *         [2,3] has the largest product = 6.
 *
 */
public class SolutionDay18_152 {
	
//	public int maxProduct(int[] nums) {
//		
//		int[][] dp = new int[nums.length + 1][2];
//		int max = Integer.MIN_VALUE;
//		Arrays.fill(dp[0], max);
//		Arrays.fill(dp[1], max);
//		
//		dp[0][0] = 1;
//		dp[0][1] = 1;
//
//		
//		for (int i = 0; i < nums.length; i++){
//			if (nums[i] > 0){
//				dp[i+1][0] = dp[i][0] * nums[i];
//			}
//			
//			else if (nums[i] < 0){
//				dp[i+1][0] = 1;
//				dp[i+1][1] = Math.max(dp[i][0] * nums[i],dp[i+1][1] * nums[i]);
//			}else{
//				dp[i+1][0] = 1;
//				dp[i+1][1] = 1;
//			}
//			
//			max = Math.max(dp[i+1][0], Math.max(max, dp[i+1][1]));
//			
//		}
//		
//		return max;
//	}
	
//	public int maxProduct(int[] A) {
//	    if (A == null || A.length == 0) {
//	        return 0;
//	    }
//	    int[] f = new int[A.length];
//	    int[] g = new int[A.length];
//	    f[0] = A[0];
//	    g[0] = A[0];
//	    int res = A[0];
//	    for (int i = 1; i < A.length; i++) {
//	        f[i] = Math.max(Math.max(f[i - 1] * A[i], g[i - 1] * A[i]), A[i]);
//	        g[i] = Math.min(Math.min(f[i - 1] * A[i], g[i - 1] * A[i]), A[i]);
//	        res = Math.max(res, f[i]);
//	    }
//	    return res;
//	  }
	
	// 如果存在负值 那么最小情况一定是负的
	public int maxProduct(int[] nums){
		if (nums == null || nums.length == 0) return 0;
		int[] f = new int[nums.length];
		int[] g = new int[nums.length];
		f[0] = nums[0];
		g[0] = nums[0];
		
		int max = nums[0];
		for (int i = 1; i < nums.length; i++){
			f[i] = Math.max(nums[i], Math.max(nums[i] * f[i-1], nums[i] * g[i-1]));
			g[i] = Math.min(nums[i], Math.min(nums[i] * f[i-1], nums[i] * g[i-1]));
			max = Math.max(max, f[i]);
		}
		
		return max;
	}
	
	
	public static void main(String[] args) {
		SolutionDay18_152 day = new SolutionDay18_152();
		int[] nums = {2,3,-2,4,6};
	}

}
