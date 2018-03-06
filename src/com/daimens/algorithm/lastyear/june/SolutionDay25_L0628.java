package com.daimens.algorithm.june;

import java.util.Arrays;

/**
 * 
 * @author DemonSong
 * 
 *         628. Maximum Product of Three Numbers My SubmissionsBack To Contest
 *         User Accepted: 1291 User Tried: 1427 Total Accepted: 1335 Total
 *         Submissions: 3079 Difficulty: Easy Given an integer array, find three
 *         numbers whose product is maximum and output the maximum product.
 * 
 *         Example 1: Input: [1,2,3] Output: 6 Example 2: Input: [1,2,3,4]
 *         Output: 24 Note: The length of the given array will be in range
 *         [3,104] and all elements are in the range [-1000, 1000].
 *         Multiplication of any three numbers in the input won't exceed the
 *         range of 32-bit signed integer.
 *
 */
public class SolutionDay25_L0628 {

	// public int maximumProduct(int[] nums) {
	// Queue<Integer> neg = new PriorityQueue<>((a, b) -> a - b);
	// Queue<Integer> pos = new PriorityQueue<>((a, b) -> b - a);
	// Arrays.sort(nums);
	// for (int i = 0; i < nums.length; ++i) {
	// if (nums[i] >= 0)
	// pos.offer(nums[i]);
	// else
	// neg.offer(nums[i]);
	// }
	// int n = nums.length;
	// int max = - 1 << 30;
	// if (pos.isEmpty())
	// return nums[0] * nums[1] * nums[2];
	// if (neg.isEmpty())
	// return nums[n - 1] * nums[n - 2] * nums[n - 3];
	// else {
	// int res = 1;
	// res *= pos.poll();
	// res *= neg.poll();
	// res *= neg.poll();
	// max = Math.max(res, max);
	// max = Math.max(res, nums[n-1] * nums[n-2] * nums[n-3]);
	// return max;
	// }
	// }

	public int maximumProduct(int[] nums) {
		Arrays.sort(nums);
		int max = Integer.MIN_VALUE;
		int n = nums.length;
		max = Math.max(max, nums[0] * nums[1] * nums[2]);
		max = Math.max(max, nums[n - 1] * nums[n - 2] * nums[n - 3]);
		max = Math.max(max, nums[0] * nums[1] * nums[n - 1]);
		return max;
	}

	public static void main(String[] args) {
		SolutionDay25_L0628 day = new SolutionDay25_L0628();
		int[] nums = { -710, -107, -851, 657, -14, -859, 278, -182, -749, 718, -640, 127, -930, -462, 694, 969, 143,
				309, 904, -651, 160, 451, -159, -316, 844, -60, 611, -169, -73, 721, -902, 338, -20, -890, -819, -644,
				107, 404, 150, -219, 459, -324, -385, -118, -307, 993, 202, -147, 62, -94, -976, -329, 689, 870, 532,
				-686, 371, -850, -186, 87, 878, 989, -822, -350, -948, -412, 161, -88, -509, 836, -207, -60, 771, 516,
				-287, -366, -512, 509, 904, -459, 683, -563, -766, -837, -333, 93, 893, 303, 908, 532, -206, 990, 280,
				826, -13, 115, -732, 525, -939, -787 };
		System.out.println(day.maximumProduct(nums));
	}
}
