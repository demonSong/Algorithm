package com.daimens.algorithm.april;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 
 *         560.Subarray Sum Equals K
 * 
 *         Given an array of integers and an integer k, you need to find the
 *         total number of continuous subarrays whose sum equals to k.
 * 
 *         Example 1: Input:nums = [1,1,1], k = 2 Output: 2 Note: The length of
 *         the array is in range [1, 20,000]. The range of numbers in the array
 *         is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 *
 */
public class SolutionDay30_560 {

//	public int subarraySum(int[] nums, int k) {
//
//		if (nums.length == 0)
//			return 0;
//
//		int n = nums.length;
//		int[] sums = new int[n + 1];
//
//		sums[0] = 0;
//
//		for (int i = 1; i < sums.length; i++) {
//			sums[i] += sums[i - 1] + nums[i - 1];
//		}
//
//		int count = 0;
//		for (int i = 0; i < sums.length; i++) {
//			int target = sums[i] + k;
//			for (int j = i + 1; j < sums.length; j++) {
//				if (target == sums[j]) {
//					count++;
//				}
//			}
//		}
//
//		return count;
//	}
	
//	public int subarraySum(int[] nums, int k) {
//		int n = nums.length;
//		if (n == 0) return 0;
//		
//		int[] sums = new int[n+1];
//		sums[0] = 0;
//		for (int i = 0; i < nums.length;i++){
//			sums[i+1] = nums[i] + sums[i];
//		}
//		
//		
//		int count = 0;
//		for (int i = 1; i <= n;i++){
//			for (int j = 0; j + i - 1 < n; j++){
//				int sum = sums[j+i]- sums[j];
//				if (sum == k) count++;
//			}
//		}
//		
////		int count = 0;
////		for (int i = 1; i <= n;i++){
////			for (int j = 0; j + i - 1 < n; j++){
////				int sum = 0;
////				for (int l = j; l < i+j; l++){
////					sum += nums[l];
////				}
////				if (sum == k) count++;
////			}
////		}
//		return count;
//	}

	
	public int subarraySum(int[] nums, int k) {
		
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		
		int sum = 0;
		int res = 0;
		
		for (int num : nums){
			sum += num;
			
			if (map.containsKey(sum-k)){
				res += map.get(sum-k);
			}
			map.put(sum, map.getOrDefault(sum, 0)+1);
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		SolutionDay30_560 day = new SolutionDay30_560();

		int[] nums = { 3, 4, 7, 2, -3, 1, 4, 2, 1 };
		day.subarraySum(nums,7);
	}

}
