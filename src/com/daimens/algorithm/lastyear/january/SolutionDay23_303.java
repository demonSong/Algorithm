package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 303.Range Sum Query - Immutable
 * Given an integer array nums,find the sum of the elements between indices i and j (i <= j ),inclusive.
 * Example:
 * Given nums = [-2,0,3,-5,2,-1]
 * sumRange(0,2) -> 1
 * sumRange(2,5) -> -1
 * sumRange(0,5) -> -3
 * 
 * Note:
 * 1.You may assume that the array dose not change.
 * 2.There are many calls to sumRange function.
 *
 */
public class SolutionDay23_303 {

}

class NumArray{
	
	private int[] sums;
	
	public NumArray(int[] nums){
		sums = new int[nums.length];
		System.arraycopy(nums, 0, sums, 0, sums.length);
		for (int i = 1; i < sums.length; i++){
			sums[i] += sums[i-1];
		}
		
	}
	
	public int sumRange(int i ,int j){
		if(i > j || i <0 || j < 0 || j >= sums.length) return 0;
		return i == 0 ? sums[j] : (sums[j] - sums[i-1]);
	}
	
	public static void main(String[] args) {
	}
}