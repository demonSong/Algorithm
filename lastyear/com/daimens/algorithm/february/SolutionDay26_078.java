package com.daimens.algorithm.february;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 78.Subsets
 * Given a set of distinct integers,nums,return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * For example,
 * if nums = [1,2,3], a solution is:
 * [
 *  [3],
 *  [1],
 *  [2],
 *  [1,2,3],
 *  [1,3],
 *  [2,3],
 *  [1,2],
 *  []
 * ]
 *
 */
public class SolutionDay26_078 {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		helper(res, new ArrayList<>(), 0, nums);
		return res;
    }
	
	private void helper(List<List<Integer>> res, List<Integer> ans, int start,int[] nums){
		if(ans.size() <= nums.length){
			res.add(new ArrayList<>(ans));
		}
		for (int i = start; i < nums.length; i++) {
			ans.add(nums[start]);
			helper(res, ans, ++start, nums);
			ans.remove(ans.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		SolutionDay26_078 day = new SolutionDay26_078();
		int[] nums = {1,2,3};
		day.subsets(nums);
	}
	
}
