package com.daimens.algorithm.april;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 448.Find All Numbers Disappeared in an array
 * Given an array of integers where 1 <= a[j] <= n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements of [1,n] inclusive that do not appear in this array.
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 * 
 * Example:
 * Input: [4,3,2,7,8,2,3,1]
 * 
 * Output:[5,6]
 * 
 */
public class SolutionDay13_448 {
	public List<Integer> findDisappearedNumbers(int[] nums) {
		if (nums.length == 0) return new ArrayList<>();
		
		int[] map = new int[nums.length+1];
		for (int num : nums){
			map[num]++;
		}
		
		List<Integer> ans = new ArrayList<>();
		for (int i = 1; i < map.length; i++){
			if (map[i] == 0){
				ans.add(i);
			}
		}
		
		return ans;
    }
}
