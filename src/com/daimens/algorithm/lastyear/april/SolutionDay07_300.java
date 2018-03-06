package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 300.Longest Increasing Subsequence
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example,
 * Given [10,9,2,5,3,7,101,18],
 * The longest increasing subsequence is [2,3,7,101], therefore the length is 4. Note that
 * there may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n^2) complexity.
 * Follow up: Could you improve it to O(nlogn) time complexity.
 *
 */
public class SolutionDay07_300 {
//	public int lengthOfLIS(int[] nums) {
//		
//		if (nums.length == 0) return 0;
//		
//		int[] dp = new int[nums.length+1];
//		int max = 0;
//		for (int i = 0; i < nums.length; i++){
//			dp[i] = 1;
//			for (int j = 0; j < i; j ++){
//				if (nums[i] > nums[j]){
//					dp[i] = Math.max(dp[i], dp[j]+1);
//				}
//			}
//			max = Math.max(max, dp[i]);
//		}
//        return max;
//    }
	
	public int lengthOfLIS(int[] nums) {
		
		if(nums.length == 0) return 0;
		
		int[] dp = new int[nums.length];
		int len = 1;
		dp[0] = nums[0];
		for (int i = 1; i < nums.length; i++){
			int index = binarySearch(dp, 0, len-1, nums[i]);
			dp[index + 1] = nums[i];
			if (index + 1 == len){
				len ++;
			}
		}
		
		return len;
		
	}
	
	
	private int binarySearch(int[] nums, int lf, int rt, int key){
		
		while (lf < rt){
			int mid = lf + (rt + 1 - lf) / 2;
			
			if(nums[mid] >= key){
				rt = mid - 1;
			}else{
				lf = mid;
			}
		}
		
		if (nums[lf] < key) return lf; 
		
		return -1;
	}
	
	public static void main(String[] args) {
		SolutionDay07_300 day = new SolutionDay07_300();
		int[] nums = {10,9,2,5,3,7,101,18};
		day.lengthOfLIS(nums);
	}
	
}
