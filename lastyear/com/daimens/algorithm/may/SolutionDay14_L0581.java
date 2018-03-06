package com.daimens.algorithm.may;

import java.util.Arrays;

/**
 * 
 * @author DemonSong
 * 
 *         581.Shortest Unsorted Continuous Subarray
 * 
 *         Given an integer array, you need to find one continuous subarray that
 *         if you only sort this subarray in ascending order, then the whole
 *         array will be sorted in ascending order, too.
 * 
 *         You need to find the shortest such subarray and output its length.
 * 
 *         Example 1: Input: [2, 6, 4, 8, 10, 9, 15] Output: 5 Explanation: You
 *         need to sort [6, 4, 8, 10, 9] in ascending order to make the whole
 *         array sorted in ascending order. Note: Then length of the input array
 *         is in range [1, 10,000]. The input array may contain duplicates, so
 *         ascending order here means <=.
 * 
 * 
 *
 */
public class SolutionDay14_L0581 {

	// public int findUnsortedSubarray(int[] nums) {
	// int count = 1;
	// for (int i = 1; i < nums.length; i++){
	// if (nums[i] >= nums[i-1]){
	// count ++;
	// }else{
	// break;
	// }
	// }
	//
	// int right = 1;
	// for (int j = nums.length-2; j >=0; j--){
	// if (nums[j] <= nums[j+1]){
	// right++;
	// }else{
	// break;
	// }
	// }
	// return Math.max(0,nums.length-count-right);
	// }

	// public int findUnsortedSubarray(int[] nums) {
	// return helper(nums, 0, nums.length-1);
	// }
	//
	// private int helper(int[] nums, int start, int end){
	// if (start > end){
	// return 0;
	// }
	// int min = Integer.MIN_VALUE;
	// int max = Integer.MAX_VALUE;
	// for (int i = start; i <= end; i++){
	// min = Math.min(min, nums[i]);
	// max = Math.max(max, nums[i]);
	// }
	//
	// if (min == nums[start] && max == nums[end]){
	// return helper(nums, start+1, end-1);
	// }
	//
	// if (min == nums[start]){
	// return helper(nums, start+1, end);
	// }
	//
	// if (max == nums[end]){
	// return helper(nums, start, end-1);
	// }
	// return end-start+1;
	// }

	public int findUnsortedSubarray(int[] nums) {
		int[] sorted = nums.clone();
		Arrays.sort(sorted);
		int len = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == sorted[i])
				len++;
			else
				break;
		}
		for (int j = nums.length - 1; j >= 0; j--) {
			if (nums[j] == sorted[j])
				len++;
			else
				break;
		}
		return Math.max(0, nums.length - len);
	}

	public static void main(String[] args) {
		SolutionDay14_L0581 day = new SolutionDay14_L0581();
		int[] nums = {};
	}

}
