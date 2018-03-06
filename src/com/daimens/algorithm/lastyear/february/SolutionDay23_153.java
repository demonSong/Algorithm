package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 153.Find Minimum in Rotated Sorted Array
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 *
 */
public class SolutionDay23_153 {
//	public int findMin(int[] nums) {
//		Arrays.sort(nums);
//        return nums[0];
//    }
	
//	public int findMin(int[] nums) {
//		
//		int min = nums[0];
//		for (int i =0 ; i < nums.length - 1;i++){
//			if(nums[i+1] < nums[i] && nums[i+1] < min){
//				min = nums[i+1];
//			}
//		}
//		return min < nums[nums.length-1] ? min : nums[nums.length-1];
//	}
	
	public int findMin(int[] nums) {
		int left = 0, right = nums.length - 1, mid = 0;
		while (left < right) {
			mid = (left + right) >> 1;
			if (nums[mid] > nums[right])
				left = mid + 1;
			else
				right = mid;
		}
		return nums[right];
	}
}
