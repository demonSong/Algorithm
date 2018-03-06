package com.daimens.algorithm.march;

import java.util.Arrays;

/**
 * 
 * @author DemonSong
 * 16.3Sum Closest
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number,target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 */
public class SolutionDay30_016 {
	
//	public int threeSumClosest(int[] nums, int target){
//		
//		if(nums.length < 3) return 0;
//		
//		int sum = 0;
//		int min = Integer.MAX_VALUE;
//		for (int i = 0; i < nums.length; i++){
//			for(int j = i+1; j < nums.length; j++){
//				for(int k = j+1; k < nums.length; k++){
//					int diff = Math.abs(target - nums[i]-nums[j]-nums[k]);
//					if(diff < min){
//						min = diff;
//						sum = nums[i] + nums[j] + nums[k];
//					}
//				}
//			}
//		}
//		return sum;
//	}
	
	public int threeSumClosest(int[] nums, int target) {
		int result = nums[0] + nums[1] + nums[nums.length-1];
		Arrays.sort(nums);
		
		for(int i = 0; i < nums.length - 2; i++){
			int start = i + 1, end = nums.length-1;
			
			while(start < end){
				int sum = nums[i] + nums[start] + nums[end];
				if(sum > target) end--;
				else start ++;
				if(Math.abs(sum-target) < Math.abs(result-target)){
					result = sum;
				}
			}
		}
		
		return result;
	}
}
