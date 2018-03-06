package com.daimens.algorithm.january;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 442.Find All Duplicates in an Array.
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear
 * twice and others appear once.
 * Find all the elements that appear twice in this array.
 * Could you do it without extra space and in O(n) runtime?
 * 
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 * Output:
 * [2,3]
 *
 */
public class SolutionDay30_442 {
	public List<Integer> findDuplicates(int[] nums){
		int[] ctMap = new int[nums.length]; 
		for(int i = 0; i < nums.length; i++){
			ctMap[nums[i]-1]++;
		}
		List<Integer> ret = new ArrayList<Integer>();
		for(int i = 0; i < ctMap.length; i++){
			if(ctMap[i] == 2){
				ret.add(i+1);
			}
		}
		return ret;
	}
}
