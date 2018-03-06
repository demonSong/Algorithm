package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         624.Maximum Distance in Arrays
 * 
 *         Given m arrays, and each array is sorted in ascending order. Now you
 *         can pick up two integers from two different arrays (each array picks
 *         one) and calculate the distance. We define the distance between two
 *         integers a and b to be their absolute difference |a-b|. Your task is
 *         to find the maximum distance.
 * 
 *         Example 1: Input: [[1,2,3], [4,5], [1,2,3]] Output: 4 Explanation:
 *         One way to reach the maximum distance 4 is to pick 1 in the first or
 *         third array and pick 5 in the second array. Note: Each given array
 *         will have at least 1 number. There will be at least two non-empty
 *         arrays. The total number of the integers in all the m arrays will be
 *         in the range of [2, 10000]. The integers in the m arrays will be in
 *         the range of [-10000, 10000].
 * 
 *
 */
public class SolutionDay18_L0624 {

	class Pair {
		int index;
		int value;

		Pair(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}

	// public int maxDistance(int[][] arrays) {
	// if (arrays == null || arrays.length == 0) return 0;
	// int row = arrays.length;
	// Pair[] p = new Pair[row];
	// for (int i = 0; i < row; ++i){
	// p[i] = new Pair(i,arrays[i][arrays[i].length - 1]);
	// }
	// Arrays.sort(p,(a,b) -> b.value - a.value);
	// int max = 0;
	// for (int i = 0; i < row; ++i){
	// int x = arrays[i][0];
	// int c = p[0].index != i ? p[0].value : p[1].value;
	// max = Math.max(max, Math.abs(c - x));
	// }
	// return max;
	// }

	public int maxDistance(int[][] arrays) {
		if (arrays == null || arrays.length == 0)
			return 0;
		int min = arrays[0][0];
		int max = arrays[0][arrays[0].length - 1];

		int diff = 0;
		for (int i = 1; i < arrays.length; ++i) {
			int head = arrays[i][0];
			int tail = arrays[i][arrays[i].length - 1];
			diff = Math.max(diff, Math.abs(max - head));
			diff = Math.max(diff, Math.abs(tail - min));
			max = Math.max(max, tail);
			min = Math.min(min, head);
		}
		return diff;
	}

	// public int maxDistance(int[][] arrays) {
	// int row = arrays.length;
	// Pair max = new Pair(-1, Integer.MIN_VALUE);
	// Pair nmax = new Pair(-1, Integer.MIN_VALUE);
	//
	// for (int i = 0; i < row; ++i){
	// if (arrays[i][arrays[i].length-1] > max.value){
	// max.index = i;
	// max.value = arrays[i][arrays[i].length-1];
	// }
	// else if (arrays[i][arrays[i].length-1] <= max.value &&
	// arrays[i][arrays[i].length-1] > nmax.value){
	// nmax.index = i;
	// nmax.value = arrays[i][arrays[i].length-1];
	// }
	// }
	//
	// int res = 0;
	// for (int i = 0; i < row; ++i){
	// int value = max.index != i ? max.value : nmax.value;
	// res = Math.max(res, Math.abs(arrays[i][0] - value));
	// }
	//
	// return res;
	// }

	public static void main(String[] args) {
		SolutionDay18_L0624 day = new SolutionDay18_L0624();
	}

}
