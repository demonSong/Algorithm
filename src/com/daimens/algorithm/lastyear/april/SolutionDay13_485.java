package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 485.Max Consecutive Ones
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 * Example 1:
 * Input: [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s.
 * 
 * Note:
 * 1. The input array will only contain 0 and 1.
 * 2. The length of input array is a positive integer and will not exceed 10,000
 *
 */
public class SolutionDay13_485 {
//	public int findMaxConsecutiveOnes(int[] nums) {
//        if (nums.length == 0) return 0;
//        int[] dp = new int[nums.length];
//        for (int i = 0; i < nums.length;i++){
//        	dp[i] = nums[i]; 
//        }
//        
//        int max = dp[0];
//        for (int i = 1; i < nums.length; i++){
//        	if (nums[i] == 1){
//        		dp[i] += dp[i-1];
//        	}
//        	
//        	max = Math.max(dp[i], max);
//        }
//        
//        return max;
//    }
	
	public int findMaxConsecutiveOnes(int[] nums) {
		
		int maxHere = 0, max = 0;
		for (int i = 0; i < nums.length; i++){
			if (nums[i] == 1){
				maxHere ++;
			}else{
				maxHere = 0;
			}
			
			max = Math.max(max, maxHere);
		}
		
		return max;
	}
}
