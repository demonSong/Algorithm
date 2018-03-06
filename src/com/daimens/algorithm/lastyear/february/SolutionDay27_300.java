package com.daimens.algorithm.february;

import java.util.Arrays;

/**
 * 
 * @author DemonSong
 * 300.Longest Increasing Subsequence
 * Given an unsorted array of integers,find the length of longest increasing subsequence.
 * For example,
 * Given [10,9,2,5,3,7,101,18]
 * The longest increasing subsequence is [2,3,7,101], therefore the length is 4. Note that there may be more than one LIS
 * combination,it is only necessary for you to return the length.
 * Your algorithm should run in O(n^2) complexity.
 * 
 *
 */
public class SolutionDay27_300 {
	//最优化问题，存在问题 如 [10,9,2,5,3,4]
//	public int lengthOfLIS(int[] nums) {
//		if(nums.length == 0) return 0;
//		
//        int max = Integer.MIN_VALUE;
//        for (int i = 0; i < nums.length; i++){
//        	int cnt = 0, cmp = nums[i];
//        	for (int j = i+1; j < nums.length; j++){
//        		if(nums[j] > cmp){
//        			cnt++;
//        			cmp = nums[j];
//        		}
//        	}
//        	if(cnt > max){
//        		max = cnt;
//        	}
//        }
//        return max;
//    }
	
//	public int lengthOfLIS(int[] nums) {
//		if(nums.length == 0) return 0;
//		
//		int len = nums.length;
//		int[] dp = new int[nums.length];
//		
//		for (int i = 0; i < len; i++){
//			for (int j = i+1; j < len ; j++){
//				if(nums[j] > nums[i]){
//					dp[j] += 1;
//				}
//			}
//		}
//		return dp[len-1];
//	}
	
	// working solution
//	public int lengthOfLIS(int[] nums) {            
//        int[] dp = new int[nums.length];
//        int len = 0;
//        //利用的是binarySearch的特性 只需要明确当前dp中最后一个非零元素和下个元素的比较
//        for(int x : nums) {
//            int i = Arrays.binarySearch(dp, 0, len, x);
//            if(i < 0) i = -(i + 1);
//            dp[i] = x;
//            if(i == len) len++;
//        }
//
//        return len;
//    }
	
	public int lengthOfLIS(int[] nums) {
		int[] tails = new int[nums.length];
		int size = 0;
		for (int x : nums){
			//二分法
			int i = 0, j = size;
			while (i != j){
				int m = (i+j) / 2;
				if(tails[m] < x){
					i = m +1;
				}else{
					j = m;
				}
			}
			
			tails[i] = x;
			if( i== size) ++size;
		}
		
		return 0;
	}
	
	public static void main(String[] args) {
		SolutionDay27_300 day = new SolutionDay27_300();
		int[] nums = {10,9,2,5,5,7,101,18};
		day.lengthOfLIS(nums);
	}

}
