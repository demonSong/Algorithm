package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 260.Single Number II
 * Given an array of numbers nums,in which exactly two elements appear only once
 * and all the other elements appear exactly twice.Find the two elements that appear only once.
 * For example:
 * Given nums = [1,2,1,3,2,5],return [3,5].
 * Note:
 * 1.The order of the result is not important. So in the above example,[5,3] is also correct.
 * 2.Your algorithm should run in linear runtime complexity.Could you implement it using only constant space complexity.
 *
 */
public class SolutionDay30_260 {
	
	public int[] singleNumber(int[] nums){
		int[] res = new int[2];
		int result = nums[0];
		for (int i = 1; i < nums.length; i++) {
			result = result ^ nums[i];
		}
		res[0] = 0;
		res[1] = 0;
		
		int n = result & (~(result - 1));
		for(int i = 0; i < nums.length; i++){
			if((n & nums[i])!=0){
				res[0] = res[0] ^ nums[i];
			}else{
				res[1] = res[1] ^ nums[i];
			}
		}
		return res;
	}

}
