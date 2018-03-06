package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         540. Single Element in a Sorted Array
 * 
 *         Given a sorted array consisting of only integers where every element
 *         appears twice except for one element which appears once. Find this
 *         single element that appears only once.
 * 
 *         Example 1: Input: [1,1,2,3,3,4,4,8,8] Output: 2 Example 2: Input:
 *         [3,3,7,7,10,11,11] Output: 10 Note: Your solution should run in O(log
 *         n) time and O(1) space.
 *
 */
public class SolutionDay14_L0540 {
	
//	public int singleNonDuplicate(int[] nums) {
//		int num = 0;
//		for (int n : nums){
//			num ^= n;
//		}
//		return num;
//    }
	
	public int singleNonDuplicate(int[] nums) {
		int lf = 0, rt = nums.length / 2;
		while (lf < rt){
			int mid = lf + (rt - lf) / 2;
			if (nums[2 * mid] == nums[2 * mid + 1]) lf = mid + 1;
			else rt = mid;
		}
		return nums[2 * lf];
	}
	
	public static void main(String[] args) {
		SolutionDay14_L0540 day = new SolutionDay14_L0540();
		int[] nums = {1,1,2,2,3,3,4,4,5};
		System.out.println(day.singleNonDuplicate(nums));
	}

}
