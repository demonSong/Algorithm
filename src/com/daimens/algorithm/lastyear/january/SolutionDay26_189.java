package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 189.Rotate Array
 * Rotate an array of n elements to the right by k steps.
 * For example,with n = 7 and k = 3,the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 *
 */
public class SolutionDay26_189 {
	
	public void rotate(int[] nums,int k){
		k = k % nums.length;
		int count = 0;
		for (int start = 0; count < nums.length; start++){
			int current = start;
			int prev = nums[start];
			do{
				int next = (current + k) % nums.length;
				int temp = nums[next];
				nums[next] = prev;
				prev = temp;
				current = next;
				count ++;
			}while(start != current);
		}
	}

}
