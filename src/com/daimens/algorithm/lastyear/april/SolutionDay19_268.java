package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong 
 * 
 * 268.Missing Number 
 * 		   Given an array containing n distinct
 *         numbers taken from 0, 1, 2, ..., n, find the one that is missing from
 *         the array.
 * 
 *         For example, Given nums = [0, 1, 3] return 2.
 * 
 *         Note: Your algorithm should run in linear runtime complexity. Could
 *         you implement it using only constant extra space complexity?
 *
 */
public class SolutionDay19_268 {
//	public int missingNumber(int[] nums) {
//        int[] map = new int[nums.length + 1];
//        
//        for (int n : nums){
//        	map[n] = 1;
//        }
//        
//        for (int i = 0; i < map.length; i++){
//        	if (map[i] == 0) return i;
//        }
//        
//        return -1;
//    }
	
	public int missingNumber(int[] nums) { //xor
	    int res = nums.length;
	    for(int i=0; i<nums.length; i++){
	        res ^= i;
	        res ^= nums[i];
	    }
	    return res;
	}
}
