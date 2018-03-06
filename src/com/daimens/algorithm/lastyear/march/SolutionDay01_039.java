package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 39.Combination Sum
 * Given a set of candidate numbers (c) (without duplicates) and a target number(T),find all unique combinations in C where
 * the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 * Note:
 * 1. All numbers(including target) will be positive integers.
 * 2. The solution set must not contain duplicate combinations.
 * For example,given candidate set [2,3,6,7] and target 7.
 * A solution set is:
 * [
 * 	[7],
 *  [2,2,3]
 * ]
 *
 */
public class SolutionDay01_039 {
	//使用dp的话，一个7有多个状态，明显不对啊
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		helper(res, new ArrayList<>(), candidates, target,0);
		return res;
    }
	
	private void helper(List<List<Integer>> res, List<Integer> ans, int[] candidates,int target,int start){
		if(target == 0){
			res.add(new ArrayList<>(ans));
			return;
		}
		
		if(target < 0){
			return;
		}
		
		for (int i = start; i < candidates.length; i++){
			ans.add(candidates[i]);
			helper(res, ans, candidates, target-candidates[i],i);
			ans.remove(ans.size()-1);
		}
		
		
	}
	
	public static void main(String[] args) {
		SolutionDay01_039 day = new SolutionDay01_039();
		int[] nums = {2,3,6,7};
		day.combinationSum(nums,7);
	}
}
