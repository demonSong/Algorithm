package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 33.Search in Rotated Sorted Array
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 3 4 5 6 7 might become 4 5 6 7 0 1 2)
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 *
 */
public class SolutionDay16_033 {
	
//	public int search(int[] nums, int target) {
//		
//		if(nums.length == 0) return -1;
//		
//		//nums[mid] < target 
//		int lf = 0 , rt = nums.length-1;
//		
//		while(lf < rt){
//			int mid = lf + (rt - lf) /2;
//			
//			if(nums[mid] < target){
//				lf = mid + 1;
//			}else{
//				search(nums, target);
//			}
//		}
//		
//		return 0;
//    }
	
	public int search(int[] nums, int target) {
		
		int start = 0, end = nums.length -1;
		while(start <= end){
			int mid = (start + end) / 2;
			if(nums[mid] == target)
				return mid;
			
			if(nums[start] <= nums[mid]){  //这个是可以判断出来的 说明左半部分的顺序是正确的
				if (target < nums[mid] && target >= nums[start]) {  //这部分小的 还有可能在右半边，所以也需要判断一下
					end = mid - 1;
				} else {
					start = mid + 1;
				}
				
			}
			
			if(nums[mid] <= nums[end]){
				if(target > nums[mid] && target <= nums[end]){
					start = mid + 1;
				}else{
					end = mid -1;
				}
			}
		}
		
		return -1;
	}
	
	
}
