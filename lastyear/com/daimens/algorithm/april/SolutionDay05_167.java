package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 167.Two sum II - Input array is sorted
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a 
 * specific target number. The function twoSum should return indices of the two numbers such that they add up to the target, where index1
 * must be less than index2.
 * Please note that you return answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 * 
 * Input: numbers = {2,7,11,15},target = 9
 * Output: index1 = 1, index2 = 2
 *
 */
public class SolutionDay05_167 {
		
	public int[] twoSum(int[] numbers, int target) {

		if (numbers.length <= 1)
			return new int[] { -1, -1 };

		for (int i = 0; i < numbers.length && numbers[i] <= target; i++) {
			int x = target - numbers[i];

			int lf = i + 1, rt = numbers.length - 1;

			while (lf <= rt) {
				int mid = lf + (rt - lf) / 2;
				if (numbers[mid] < x) {
					lf = mid + 1;
				} else if (numbers[mid] > x) {
					rt = mid - 1;
				} else {
					return new int[] { i + 1, mid + 1 };
				}
			}

		}

		return new int[] { -1, -1 };

	}

}
