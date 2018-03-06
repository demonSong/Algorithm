package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 35.Search Insert Position
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be
 * if it were inserted in order.
 * You may assume no duplicates in the array.
 * 
 * Here are examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 *
 */
public class SolutionDay06_035 {
	
	public int searchInsert(int[] nums, int target) {
		
		
		int lf = 0, rt = nums.length-1;
		
		while (lf < rt){
			
			int mid = lf + (rt - lf) / 2;
			
			if (nums[mid] < target){
				lf = mid + 1;
			}else{
				rt = mid;
			}
		}
		
		if (nums[rt] < target) return rt + 1; 
		
		return rt;
    }
}
