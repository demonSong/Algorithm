package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 334.Increasing Triplet Subsequence
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * Formally the function should:
 * Return true if there exists i,j,k
 * such that arr[i] < arr[j] < arr[k] given 0 <= i < j <= n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * Examples:
 * Given [1,2,3,4,5]
 * return true.
 * Given [5,4,3,2,1]
 * return false.
 *
 */
public class SolutionDay26_334 {
//	public boolean increasingTriplet(int[] nums) {
//		if(nums.length < 3){
//			return false;
//		}
//		
//		int min = nums[0],mid = nums[1],max = nums[2];
//		if(max > mid && mid > min){
//			return true;
//		}
//		int pos = -1;
//		
//		while (++pos < nums.length-1 && nums[pos] > nums[pos+1]){
//			min = nums[pos+1];
//		}
//		
//		if(pos < nums.length - 2){
//			mid = nums[pos+1];
//			max = nums[pos+2];
//		}
//		
//		
//		while ((++pos) < nums.length-1 && nums[pos] > nums[pos+1] && nums[pos+1] > min){ //为何会直接跳下去 不去执行了？
//			mid = nums[pos+1];
//		}
//		
//		if(pos < nums.length - 1){
//			max = nums[pos+1];
//		}
//		
//		
//		while (++pos < nums.length-1 && nums[pos] > nums[pos+1] && nums[pos+1] > mid){
//			max = nums[pos+1];
//		}
//			
//		
//		if(max > mid && mid > min){
//			return true;
//		}
//        return false;
//    }
	
	//遍历的时候已经加入了判别顺序
	public boolean increasingTriplet(int[] nums) {
		// start with two largest values, as soon as we find a number bigger
		// than both, while both have been updated, return true.
		int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
		for (int n : nums) {
			if (n <= small) {
				small = n;
			} // update small if n is smaller than both
			else if (n <= big) {
				big = n;
			} // update big only if greater than small but smaller than big
			else
				return true; // return if you find a number bigger than both
		}
		return false;
	}
	
	public static void main(String[] args) {
		SolutionDay26_334 day = new SolutionDay26_334();
		int[] nums = {2,1,5,0,4,6};
		day.increasingTriplet(nums);
	}
}
