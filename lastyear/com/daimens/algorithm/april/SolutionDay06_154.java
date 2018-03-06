package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 154.Find Minimum in Rotated Sorted Array II
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * The Array may contain duplicates.
 *
 */
public class SolutionDay06_154 {
	
	 public int findMin(int[] nums) {
	       
			
			int lf = 0, rt = nums.length -1;
			
			while (lf < rt){
				
				int mid = lf + (rt - lf) / 2;
				
				if (nums[mid] > nums[rt]){
					lf = mid + 1;
				}else if(nums[mid] < nums[rt]){
					rt = mid;
				}else{
				    rt --;
				}
				
			}
			
			return nums[rt];
	    }
	
	public static void main(String[] args) {
		SolutionDay06_154 day = new SolutionDay06_154();
		int[] nums = {10,10,10,1,10};
		day.findMin(nums);
	}
	//
}
