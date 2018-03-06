package com.daimens.algorithm.february;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Demon Song
 * 491.Increasing Subsequences
 * Given an integer array,your task is to find all the different possible increasing subsequences of the
 * given array,and the length of an increasing subsequence should be at least 2.
 * Example:
 * Input: [4,6,7,7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * Note:
 * 1. The length of the given array will not exceed 15.
 * 2. The range of integer in the given array is [-100,100].
 * 3. The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 *
 */
public class SolutionDay23_491 {
	
	public List<List<Integer>> findSubsequences(int[] nums){
		Set<List<Integer>> res= new HashSet<List<Integer>>();
		helper(res, new ArrayList<>(), 0,nums);
		return new ArrayList<>(res);
	}
	
	private void helper(Set<List<Integer>> res, List<Integer> ans, int index, int[] nums){
		if (ans.size() >= 2){
			res.add(new ArrayList<>(ans));
		}
		
		for (int i = index; i < nums.length; i++) {
			if (ans.size() == 0 || ans.get(ans.size() - 1) <= nums[i]) {
				ans.add(nums[i]);
				helper(res, ans, i + 1, nums);
				ans.remove(ans.size() - 1);
			}
		}
	}
	
	public static void main(String[] args) {
		SolutionDay23_491 day = new SolutionDay23_491();
		int[] nums = {4,6,7,7};
		day.findSubsequences(nums);
		
	}
	
}
