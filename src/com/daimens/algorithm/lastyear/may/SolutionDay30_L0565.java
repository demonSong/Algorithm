package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         565. Array Nesting
 * 
 *         A zero-indexed array A consisting of N different integers is given.
 *         The array contains all integers in the range [0, N - 1].
 * 
 *         Sets S[K] for 0 <= K < N are defined as follows:
 * 
 *         S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }.
 * 
 *         Sets S[K] are finite for each K and should NOT contain duplicates.
 * 
 *         Write a function that given an array A consisting of N integers,
 *         return the size of the largest set S[K] for this array.
 * 
 *         Example 1: Input: A = [5,4,0,3,1,6,2] Output: 4 Explanation: A[0] =
 *         5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 * 
 *         One of the longest S[K]: S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2,
 *         0} Note: N is an integer within the range [1, 20,000]. The elements
 *         of A are all distinct. Each element of array A is an integer within
 *         the range [0, N-1].
 *
 */
public class SolutionDay30_L0565 {
	
//	public int arrayNesting(int[] nums) {
//		for (int i = 0; i < nums.length; i++){
//			if (nums[i] != -1)
//				dfs(i, nums[i], nums, 1);
//		}
//		return size;
//    }
//	
//	int size = 0;
//	private void dfs(int start, int next, int[] nums, int step){
//		if (next == start){
//			size = Math.max(size, step);
//			return;
//		}
//		else{
//			int tmp = nums[next];
//			nums[next] = -1;
//			dfs(start, tmp, nums, step+1);
//		}
//	}
	
	public int arrayNesting(int[] nums) {
		int size = 0;
		for (int i = 0; i < nums.length; i++){
			if (nums[i] == -1) continue;
			int next = i;
			int step = 0;
			while (next >= 0){
				step++;
				int tmp = nums[next];
				nums[next] = -1;
				next = tmp;
			}
			size = Math.max(size, step);
		}
		return size;
	}
	
	public static void main(String[] args) {
		SolutionDay30_L0565 day = new SolutionDay30_L0565();
		int[] nums = {5,4,0,3,1,6,2};
		day.arrayNesting(nums);
	}
}
