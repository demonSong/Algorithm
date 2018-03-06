package com.daimens.algorithm.april;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author DemonSong 
 * 548.Split Array with Equal Sum 
 *         Given an array with n
 *         integers, you need to find if there are triplets (i, j, k) which
 *         satisfies following conditions:
 * 
 *         0 < i, i + 1 < j, j + 1 < k < n - 1 Sum of subarrays (0, i - 1), (i +
 *         1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal. where
 *         we define that subarray (L, R) represents a slice of the original
 *         array starting from the element indexed L to the element indexed R.
 * 
 *         Example: 
 *         Input: [1,2,1,2,1,2,1] 
 *         Output: True Explanation: i = 1, j =
 *         3, k = 5. sum(0, i - 1) = sum(0, 0) = 1 sum(i + 1, j - 1) = sum(2, 2)
 *         = 1 sum(j + 1, k - 1) = sum(4, 4) = 1 sum(k + 1, n - 1) = sum(6, 6) =
 *         1
 *         
 *         Note:
 *         1. 1 <= n <= 20000.
 *         2. Elements in the given array will be in range[-1,000,000,1,000,000]
 *
 */
public class SolutionDay02_503 {
//	
//	public boolean splitArray(int[] nums) {
//		
//		if(nums.length <= 6) return false;
//		
//		for (int i = 1; i + 4 < nums.length - 1; i++) {
//			for (int j = i + 2; j + 2 < nums.length - 1; j++) {
//				for (int k = j + 2; k < nums.length - 1; k++) {
//					
//					int sum1 = 0;
//					int sum2 = 0;
//					int sum3 = 0;
//					int sum4 = 0;
//					
//					
//					for (int l = 0; l < nums.length; l++) {
//						if (l >= 0 && l <= i - 1)
//							sum1 += nums[l];
//
//						if (l >= i + 1 && l <= j - 1)
//							sum2 += nums[l];
//
//						if (l >= j + 1 && l <= k - 1)
//							sum3 += nums[l];
//
//						if (l >= k + 1 && l <= nums.length - 1)
//							sum4 += nums[l];
//					}
//					
//					if (sum1 == sum2 && sum3 == sum4 && sum2 == sum3)
//						return true;
//				}
//			}
//		}
//		
//		return false;
//    }
	
	public boolean splitArray(int[] nums) {
		
		if(nums.length <= 6) return false;
		
		int[] sum  = new int[nums.length];
		
		sum[0] = nums[0];
		for (int i = 1; i < nums.length; i++){
			sum[i] =  sum[i-1]+nums[i];
		}
		
		for (int j = 3; j + 2 < nums.length -1; j++){
			
			
			Set<Integer> set = new HashSet<>();
			for (int i = 1; i < j -1; i++){
				if ((sum[j-1]-nums[i]) == (sum[i]-nums[i]) * 2){
					set.add(sum[i-1]);
				}
			}
			
			for (int k = j + 2; k < nums.length -1; k++){
				if (sum[nums.length-1]-sum[j] - nums[k] == (sum[k]-nums[k]-sum[j]) * 2 && set.contains(sum[k]-nums[k]-sum[j]))
					return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		SolutionDay02_503 day = new SolutionDay02_503();
		
		int[] nums ={1,2,1,3,0,0,2,2,1,3,3};
		day.splitArray(nums);
	}

}
