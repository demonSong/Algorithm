package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 90.Subsets II
 * Given a collection of integers that might contain duplicates,nums,return all possible subsets.
 * Note:
 * The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,2], a solution is:
 * [
 *  [2],
 *  [1],
 *  [1,2,2],
 *  [2,2],
 *  [1,2],
 *　 []
 * ]
 * 
 *
 */
public class SolutionDay08_090 {
	
	//排序完之后重复的元素可以不用考虑
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		Set<List<Integer>> res = new HashSet<>();
		backtrack(res, new ArrayList<>(), 0, nums);
        return new ArrayList<>(res);
    }
	
	private void backtrack(Set<List<Integer>> res,List<Integer> help,int start, int[] nums){
		if(help.size() <=  nums.length){
			res.add(new ArrayList<>(help));
		}
		
		for (int i = start; i < nums.length;i++){
			help.add(nums[i]);
			backtrack(res, help, ++start, nums);
			help.remove(help.size()-1);
		}
	}
	
	public static void main(String[] args) {
		SolutionDay08_090 day = new SolutionDay08_090();
		int[] nums = {1,2,2};
		day.subsetsWithDup(nums);
	}
}
