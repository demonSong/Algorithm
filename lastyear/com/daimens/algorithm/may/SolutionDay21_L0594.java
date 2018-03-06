package com.daimens.algorithm.may;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 
 *         594. Longest Harmonious Subsequence
 * 
 *         We define a harmonious array is an array where the difference between
 *         its maximum value and its minimum value is exactly 1.
 * 
 *         Now, given an integer array, you need to find the length of its
 *         longest harmonious subsequence among all its possible subsequences.
 * 
 *         Example 1: Input: [1,3,2,2,5,2,3,7] Output: 5 Explanation: The
 *         longest harmonious subsequence is [3,2,2,2,3]. Note: The length of
 *         the input array will not exceed 20,000.
 * 
 * 
 * 
 */
public class SolutionDay21_L0594 {

	// public int findLHS(int[] nums) {
	// Map<Integer,Integer> count = new HashMap<>();
	// for (int i = 0; i < nums.length; i++){
	// count.put(nums[i], count.getOrDefault(nums[i], 0)+1);
	// }
	// int max = 0;
	// for (int key : count.keySet()){
	// if (count.containsKey(key+1)){
	// max = Math.max(max, count.get(key) + count.get(key+1));
	// }
	// if (count.containsKey(key-1)){
	// max = Math.max(max, count.get(key) + count.get(key-1));
	// }
	// }
	// return max;
	// }

	public int findLHS(int[] nums) {
		Map<Integer, Integer> count = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
		}
		int max = 0;
		for (int key : count.keySet()) {
			if (count.containsKey(key + 1)) {
				max = Math.max(max, count.get(key) + count.get(key + 1));
			}
		}
		return max;
	}

	public static void main(String[] args) {
		SolutionDay21_L0594 day = new SolutionDay21_L0594();
	}

}
