package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 40.Combination Sum II
 * Given a collection of candidate numbers (C) and a target number (T),find all unique combinations in C where
 * the candidate numbers sums to T.
 * Each number in C may only be used once in the combination.
 * Note:
 * 1. All numbers (including target) will be positive integers.
 * 2. The solution set must not contain duplicate combinations.
 * 
 * For example, given candidate set[10,1,2,7,6,1,5] and target 8,
 * A solution set is:
 * [
 *  [1, 7],
 *  [1, 2, 5],
 *  [2, 6],
 *  [1, 1, 6]
 * ]
 *
 */
public class SolutionDay19_040 {
	
	//backtrack 非常容易超时
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Set<List<Integer>> ans = new HashSet<>();
		Arrays.sort(candidates);
		backtrack(ans, new ArrayList<>(), candidates, 0, target);
        return new ArrayList<>(ans);
    }
	
	private void backtrack(Set<List<Integer>> ans, List<Integer> tar, int[] candidates,int start,int target){
		
		if(target == 0 && tar.size() > 0){
			ans.add(new ArrayList<>(tar));
			return;
		}
		
		//加个条件
		if(target < 0 ) return;
		
		for (int i = start; i < candidates.length;i++){
			tar.add(candidates[i]);
			backtrack(ans, tar, candidates, ++start, target-candidates[i]);
			tar.remove(tar.size()-1);
		}
		
	}
	
	public static void main(String[] args) {
		SolutionDay19_040 day = new SolutionDay19_040();
		int[] candidates = {10,1,2,7,6,1,5};
		
		day.combinationSum2(candidates, 8);
		
	}
}
