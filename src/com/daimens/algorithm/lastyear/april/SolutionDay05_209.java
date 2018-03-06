package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 209.Minimum Size Subarray Sum
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which 
 * the sum of >= s. If there isn't one, return 0 instead.
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray[4,3] has the minimal length under the problem constraint.
 *
 */
public class SolutionDay05_209 {
	
//	public int minSubArrayLen(int s, int[] nums) {
//		
//		if (s <= 0 || nums.length == 0) return 0;
//		
//		int minLen = nums.length + 1;
//
//		for (int i = 0;i < nums.length; i++){
//			int sum = nums[i];
//			if (sum >= s) return 1;
//			for (int j = i+1; j < nums.length; j++){
//				sum += nums[j];
//				if(sum >= s && j-i+1 < minLen){
//					minLen = j - i + 1;
//				}
//			}
//		}
//		
//        return minLen == nums.length + 1 ? 0 : minLen;
//    }
	
//	public int minSubArrayLen(int s, int[] nums) {
//		if (s<= 0 || nums.length == 0) return 0;
//		
//		int[] sums = new int[nums.length];
//		
//		sums[0] = nums[0];
//		for (int i = 1; i < nums.length; i++){
//			sums[i] = sums[i-1] + nums[i];
//		}
//		
//		int step = 0;
//		
//		while(step <= nums.length){
//			
//			for (int i = 0; i + step < nums.length; i++) {
//				int sum = sums[i+step]-sums[i]+nums[i];
//				if (sum >= s){
//					return step+1;
//				}
//			}
//			step ++;
//		}
//		
//		return step == nums.length + 1 ?  0 : step;
//	}
	
	public int minSubArrayLen(int s, int[] nums) {
        return solveNLogN(s, nums);
    }
    
    private int solveN(int s, int[] nums) {
        int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE;
        while (end < nums.length) {
            while (end < nums.length && sum < s) sum += nums[end++];
            if (sum < s) break;
            while (start < end && sum >= s) sum -= nums[start++];
            if (end - start + 1 < minLen) minLen = end - start + 1;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    private int solveNLogN(int s, int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i - 1];
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length; i++) {
            int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
            if (end == sums.length) break;
            if (end - i < minLen) minLen = end - i;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    private int binarySearch(int lo, int hi, int key, int[] sums) {
        while (lo <= hi) {
           int mid = (lo + hi) / 2;
           if (sums[mid] >= key){
               hi = mid - 1;
           } else {
               lo = mid + 1;
           }
        }
        return lo;
    }
	
	public static void main(String[] args) {
		SolutionDay05_209 day = new SolutionDay05_209();
		int[] nums = {10,5,13,4,8,4,5,11,14,9,16,10,20,8};
		day.minSubArrayLen(80, nums);
	}
}
