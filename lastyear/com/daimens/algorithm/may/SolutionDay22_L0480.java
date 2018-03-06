package com.daimens.algorithm.may;

import java.util.Arrays;

/**
 * 
 * @author DemonSong
 * 
 *         480.Sliding Window Median
 * 
 *         Median is the middle value in an ordered integer list. If the size of
 *         the list is even, there is no middle value. So the median is the mean
 *         of the two middle value.
 * 
 *         Examples: [2,3,4] , the median is 3
 * 
 *         [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 *         Given an array nums, there is a sliding window of size k which is
 *         moving from the very left of the array to the very right. You can
 *         only see the k numbers in the window. Each time the sliding window
 *         moves right by one position. Your job is to output the median array
 *         for each window in the original array.
 * 
 *         For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * 
 *         Window position Median --------------- ----- [1 3 -1] -3 5 3 6 7 1 1
 *         [3 -1 -3] 5 3 6 7 -1 1 3 [-1 -3 5] 3 6 7 -1 1 3 -1 [-3 5 3] 6 7 3 1 3
 *         -1 -3 [5 3 6] 7 5 1 3 -1 -3 5 [3 6 7] 6 Therefore, return the median
 *         sliding window as [1,-1,-1,3,5,6].
 *
 */
public class SolutionDay22_L0480 {
	
	public double[] medianSlidingWindow(int[] nums, int k) {
		double[] ans = new double[nums.length-k+1];
		int[] window = new int[k];
		for (int i = 0; i < k; i++){
			window[i] = nums[i];
		}
		Arrays.sort(window);
		ans[0] = median(window);
		
		for (int i = 1; i < ans.length; i++){
			//丢弃 i-1的元素 加入 i + k -1 的元素
			int remove = nums[i-1];
			int add = nums[i+k-1];
			for (int j = 0; j < k; j++){
				if (window[j] == remove){
					window[j] = add;
					break;
				}
			}
			Arrays.sort(window);
			ans[i] = median(window);
		}
		return ans;
    }
	
	private double median(int[] nums) {
		int n = nums.length;
		return ((long)nums[(n - 1) / 2] + (long)nums[n / 2]) / 2.0;
	}
	
	public static void main(String[] args) {
		SolutionDay22_L0480 day = new SolutionDay22_L0480();
		int[] nums = {2147483647,2147483647};
		day.medianSlidingWindow(nums, 2);
	}

}
