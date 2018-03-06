package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         18. 4Sum
 * 
 *         Given an array S of n integers, are there elements a, b, c, and d in
 *         S such that a + b + c + d = target? Find all unique quadruplets in
 *         the array which gives the sum of target.
 * 
 *         Note: The solution set must not contain duplicate quadruplets.
 * 
 *         For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 * 
 *         A solution set is: [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
 * 
 *
 */
public class SolutionDay05_L0018 {
	
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> ans = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; ++i){
			for (int j = i + 1; j < nums.length; ++j){
				int key = target - nums[i] - nums[j];
				int lf = j + 1;
				int rt = nums.length - 1;
				while (lf < rt){
					int sum = nums[lf] + nums[rt];
					if (sum < key) lf++;
					if (sum > key) rt--;
					if (sum == key){
						ans.add(Arrays.asList(nums[i],nums[j],nums[lf],nums[rt]));
						while (lf < rt && nums[lf + 1] == nums[lf]) lf++;
						while (lf < rt && nums[rt - 1] == nums[rt]) rt--;
						lf++;
						rt--;
					}
				}
				while (j + 1 < nums.length && nums[j+1] == nums[j]) j++;
			}
			while (i + 1 < nums.length && nums[i+1] == nums[i]) i++;
		}
		
        return ans;
    }

}
