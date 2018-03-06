package com.daimens.algorithm.june;

import java.util.Arrays;

/**
 * 
 * @author DemonSong
 * 
 *         611. Valid Triangle Number
 * 
 *         Given an array consists of non-negative integers, your task is to
 *         count the number of triplets chosen from the array that can make
 *         triangles if we take them as side lengths of a triangle.
 * 
 *         Example 1: Input: [2,2,3,4] Output: 3 Explanation: Valid combinations
 *         are: 2,3,4 (using the first 2) 2,3,4 (using the second 2) 2,2,3 Note:
 *         The length of the given array won't exceed 1000. The integers in the
 *         given array are in the range of [0, 1000].
 *
 */
public class SolutionDay11_L0611 {

//	public int triangleNumber(int[] nums) {
//		if (nums.length <= 2) return 0;
//		Arrays.sort(nums);
//		int cnt = 0;
//		int n = nums.length;
//		for (int i = 0; i < n; ++i) {
//			for (int j = i + 1; j < n - 1; ++j) {
//				int sum = nums[i] + nums[j];
//				int lst = j + 1;
//				int idx = binarySearch(nums, lst, n - 1, sum);
//				if (idx != -1) {
//					cnt += idx - lst + 1;
//				}
//			}
//		}
//		return cnt;
//	}
//
//	public int binarySearch(int[] nums, int s, int e, int key) {
//		int lf = s, rt = e;
//		while (lf < rt){
//			int mid = lf + (rt - lf + 1) / 2;
//			if (nums[mid] >= key) rt = mid - 1;
//			else lf = mid;
//		}
//		if (nums[lf] < key) return lf;
//		return -1;
//	}
	
	public int triangleNumber(int[] nums) {
		if (nums.length <= 2) return 0;
		Arrays.sort(nums);
		int n = nums.length;
		int cnt = 0;
		for (int i = 0; i < n; ++i){
			int pos = i + 2;
			for (int j = i + 1; j < n - 1; ++j){
				int sum = nums[i] + nums[j];
				while (pos < n && sum > nums[pos]) pos++;
				if (j != pos)
					cnt += pos - j - 1;
			}
		}
		return cnt;
	}

	public static void main(String[] args) {
		SolutionDay11_L0611 day = new SolutionDay11_L0611();
		int[] nums = {2,2,3,4};
		System.out.println(day.triangleNumber(nums));
	}

}
