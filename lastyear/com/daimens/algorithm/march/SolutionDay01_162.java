package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 162.Find Peak Element
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that num[-1] = num[n] = -∞.
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * Note:
 * Your solution should be in logarithmic complexity.
 *
 */
public class SolutionDay01_162 {
	//描述递增递减关系 binary search 排除不需要的那一部分
	public int findPeakElement(int[] nums) {
		int N = nums.length;
		if (N == 1) {
			return 0;
		}

		int left = 0, right = N - 1;
		while (right - left > 1) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < nums[mid + 1]) { //描述的是一种递增关系，而自己想的那种mid和mid+1在peak的两边，这种情况是不会发生的
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return (left == N - 1 || nums[left] > nums[left + 1]) ? left : right;
	}
}
