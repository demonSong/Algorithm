package com.daimens.algorithm.february;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 46.Permutations
 * Given a collection of distinct numbers,return all possible permutations.
 * For example,
 * [1,2,3] have the following permutations:
 * [
 * 	[1,2,3],
 * 	[1,3,2],
 * 	[2,1,3],
 * 	[2,3,1],
 * 	[3,1,2],
 * 	[3,2,1]
 * ]
 *
 */
public class SolutionDay16_046 {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		helper(res, new ArrayList<>(), nums);
		return res;
	}

	private void helper(List<List<Integer>> res, List<Integer> ans, int[] nums) {
		if (ans.size() == nums.length) {
			List<Integer> tmp = new ArrayList<>(ans);
			res.add(tmp);
		}

		for (int i = 0; i < nums.length; i++) {
			if (ans.contains(nums[i])) continue; //这句话 重点关注下
			ans.add(nums[i]);
			helper(res, ans, nums);
			ans.remove(ans.size() - 1); //这句话 重点关注下 backtrack的精髓
		}
	}
	
	public static void main(String[] args) {
		SolutionDay16_046 day = new SolutionDay16_046();
		int[] nums = {1,2,3};
		List<List<Integer>> res = day.permute(nums);
		System.out.println("hello");
	}
}
