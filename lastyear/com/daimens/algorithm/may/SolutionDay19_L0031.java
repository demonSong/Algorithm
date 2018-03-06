package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.BitSet;

/**
 * 
 * @author DemonSong
 * 
 *         31.Next Permutation
 * 
 *         Implement next permutation, which rearranges numbers into the
 *         lexicographically next greater permutation of numbers.
 * 
 *         If such arrangement is not possible, it must rearrange it as the
 *         lowest possible order (ie, sorted in ascending order).
 * 
 *         The replacement must be in-place, do not allocate extra memory.
 * 
 *         Here are some examples. Inputs are in the left-hand column and its
 *         corresponding outputs are in the right-hand column. 1,2,3 → 1,3,2
 *         3,2,1 → 1,2,3 1,1,5 → 1,5,1
 *
 */
public class SolutionDay19_L0031 {

	public void nextPermutation(int[] nums) {
		int n = nums.length;
		int pos = n - 2;
		while (pos >= 0 && nums[pos] - nums[pos + 1] >= 0) {
			pos--;
		}
		if (pos == -1) {
			reverse(nums, 0, n - 1);
			return;
		}

		int j = n - 1;
		while (j > pos && nums[j] <= nums[pos]) {
			j--;
		}
		swap(nums, pos, j);
		reverse(nums, pos + 1, n - 1);
	}

	// 确保交换后的元素也符合递增序
	// public int[] nextPermutation(int[] nums){
	// int n = nums.length;
	// int pos = n - 2;
	// while (pos >= 0 && nums[pos] - nums[pos+1] >= 0){
	// pos --;
	// }
	// if (pos == -1) return nums;
	//
	// int j = n - 1;
	// while (j > pos && nums[j] <= nums[pos]){
	// j --;
	// }
	// swap(nums, pos, j);
	// reverse(nums, pos+1, n-1);
	// return nums;
	// }

	private void swap(int[] nums, int x, int y) {
		int tmp = nums[x];
		nums[x] = nums[y];
		nums[y] = tmp;
	}

	private void reverse(int[] nums, int s, int e) {
		while (s < e) {
			swap(nums, s, e);
			s++;
			e--;
		}
	}

	private boolean end(int[] nums, int[] next) {
		int[] clone = new int[nums.length];
		System.arraycopy(nums, 0, clone, 0, nums.length);
		Arrays.sort(clone);
		for (int i = 0; i < nums.length; i++) {
			if (clone[nums.length - 1 - i] != next[i])
				return false;
		}
		return true;
	}

	private String toString(int[] nums) {
		String ans = "";
		for (int i = 0; i < nums.length; i++) {
			ans += nums[i];
		}
		return ans;
	}
	
	private static int solve2(int[] digits){
		int len = 1 << digits.length;
		while (len-- != 0){
			String binary = Integer.toBinaryString(len);
			System.out.println(binary);
		}
		return 0;
	}

	public static void main(String[] args) {
		SolutionDay19_L0031 day = new SolutionDay19_L0031();
		int[] nums = { 0, 2, 3, 4, 6, 5 };
		// int[] next = day.nextPermutation(nums);
		// while(!day.end(nums,next)){
		// System.out.println(day.toString(next));
		// next = day.nextPermutation(next);
		// }
		System.out.println(1<<4);
		day.solve2(nums);

	}

}
