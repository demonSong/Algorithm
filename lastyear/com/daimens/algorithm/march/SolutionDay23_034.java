package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 34.Search for a Range
 * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array,return [-1,-1].
 * 
 * For example,
 * Given [5,7,7,8,8,10] and target value 8,
 * return [3,4]
 *
 */
public class SolutionDay23_034 {

	public int[] searchRange(int[] nums, int target) {
		
		if(nums.length == 0) return new int[]{-1,-1};
		
		int lf = 0, rt = nums.length-1;
		
		int[] range = new int[]{-1,-1};
		
		while(lf <= rt){
			int mid = lf + (rt - lf) / 2;
			if(nums[mid] <target){
				int rr = mid;
				while(rr <= nums.length-1 && nums[rr] == nums[mid]) rr++;
				lf = rr;
			}
			else if( nums[mid] > target){
				int lr = mid;
				while(lr >= 0 && nums[lr] == nums[mid]) lr--;
				rt = lr;
			}
			else{
				int lr = mid;
				while(lr >= 0 && nums[lr] == nums[mid]) lr--;
				int rr = mid;
				while(rr <= nums.length-1 && nums[rr] == nums[mid]) rr++;
				
				range[0] = lr+1;
				range[1] = rr-1;
				
				return range;
			}
		}
		
		return range;
    }
	
	public static void main(String[] args) {
		SolutionDay23_034 day = new SolutionDay23_034();
		int[] nums = {5,7,7,8,8,10};
		day.searchRange(nums, 8);
	}
	
	
}
