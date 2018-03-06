package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 47.Permutations II
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * For example,
 * [1,1,2] having the following unique permutations:
 * [
 * 	[1,1,2],
 * 	[1,2,1],
 *  [2,1,1]
 * ]
 *
 */
public class SolutionDay20_047 {
	
	
	public List<List<Integer>> permuteUnique(int[] nums) {
        
		List<List<Integer>> ans = new ArrayList<>();
		
		if(nums == null || nums.length == 0) return ans;
		boolean[] used = new boolean[nums.length];
		Arrays.sort(nums);
		backtrack(ans, new ArrayList<>(), used, nums);
		return ans;
    }
	
	private void backtrack(List<List<Integer>> ans, List<Integer> cur, boolean[] used,int[] nums){
		
		if(cur.size() == nums.length){
			ans.add(new ArrayList<>(cur));
			return;
		}
		
		for(int i = 0; i < nums.length; i++){
			if(used[i]) continue;
			if(i > 0 && nums[i-1] == nums[i] && !used[i-1]) continue;
			used[i] = true;
			cur.add(nums[i]);
			backtrack(ans, cur, used, nums);
			used[i] = false;
			cur.remove(cur.size() - 1);
		}
		
	}
	
	public static void main(String[] args) {
		SolutionDay20_047 day = new SolutionDay20_047();
		int[] nums = {1,1,2};
		day.permuteUnique(nums);
	}

}
