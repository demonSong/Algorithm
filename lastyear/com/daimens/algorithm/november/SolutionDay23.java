package com.daimens.algorithm.november;

import java.util.HashMap;

/**
 * 
 * @author Demon Song
 * 217. Contains Duplicate
 * Given an array of integers, find if the array contains any duplicates. 
 * Your function should return true if any value appears at least twice in the array, 
 * and it should return false if every element is distinct.
 *
 */
public class SolutionDay23 {
	public boolean containsDuplicate(int[] nums){
		//根据hashMap统计个数,一旦遇到大于2，就直接返回
		HashMap<Integer, Integer> numsMap = new HashMap<Integer,Integer>();
		for (int i = 0 ; i < nums.length; i++){
			//如果这是一个新元素
			if(!numsMap.containsKey(nums[i])){
				int value =0;
				numsMap.put(nums[i], ++value);
			}
			else{ //否则是一老元素
				return true;
			}
		}
		return false;
	}
}
