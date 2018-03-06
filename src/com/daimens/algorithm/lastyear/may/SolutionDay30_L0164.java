package com.daimens.algorithm.may;

import java.util.Arrays;

import javax.swing.text.AbstractDocument.LeafElement;

/**
 * 
 * @author DemonSong
 * 
 *         164.Maximum Gap
 * 
 *         Given an unsorted array, find the maximum difference between the
 *         successive elements in its sorted form.
 * 
 *         Try to solve it in linear time/space.
 * 
 *         Return 0 if the array contains less than 2 elements.
 * 
 *         You may assume all elements in the array are non-negative integers
 *         and fit in the 32-bit signed integer range.
 *
 */
public class SolutionDay30_L0164 {

	// public int maximumGap(int[] nums) {
	// if (nums.length <= 1) return 0;
	// Arrays.sort(nums);
	// int max = 0;
	// for (int i = 1; i < nums.length; i++){
	// max = Math.max(nums[i] - nums[i-1], max);
	// }
	// return max;
	// }

	public int maximumGap(int[] nums) {
		if (nums.length <= 1)
			return 0;
		radixSort(nums);
		int max = 0;
		for (int i = 1; i < nums.length; i++) {
			max = Math.max(nums[i] - nums[i - 1], max);
		}
		return max;
	}

	private static void radixSort(int[] nums) {
		int exp = 1;
		int radix = 10;

		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			max = Math.max(max, nums[i]);
		}

		while (max / exp != 0) {
			int[] count = new int[radix + 1];
			int[] aux = new int[nums.length];

			for (int i = 0; i < nums.length; i++) {
				count[nums[i] / exp % 10 + 1]++;
			}
			
			for (int i = 1; i < count.length; i++){
				count[i] += count[i-1];
			}
			
			for (int i = 0; i < nums.length; i++){
				aux[count[nums[i] / exp % 10]++] = nums[i];
			}
			
			for (int i = 0; i < nums.length; i++){
				nums[i] = aux[i];
			}

			exp *= 10;
		}
	}
}
