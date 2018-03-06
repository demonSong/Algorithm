package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         228.Summary Ranges 
 *         
 *         Given a sorted integer array without duplicates,
 *         return the summary of its ranges.
 * 
 *         For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 *
 */
public class SolutionDay26_L0228 {
	
//	public List<String> summaryRanges(int[] nums) {
//
//		if (nums.length == 0)
//			return new ArrayList<>();
//
//		int[] dp = new int[nums.length];
//		dp[0] = 0;
//		for (int i = 1; i < nums.length; i++) {
//			if (nums[i] == nums[i - 1] + 1) {
//				dp[i] = dp[i - 1] + 1;
//			} else {
//				dp[i] = 0;
//			}
//		}
//
//		Map<Integer, Integer> map = new HashMap<>();
//		for (int i = 0; i < nums.length; i++) {
//			map.put(nums[i] - dp[i], nums[i]);
//		}
//
//		List<String> ans = new ArrayList<>();
//		int[] keys = new int[map.keySet().size()];
//		int cnt = 0;
//		for (int key : map.keySet()) {
//			keys[cnt++] = key;
//		}
//		Arrays.sort(keys);
//		for (int key : keys) {
//			int val = map.get(key);
//			if (val == key)
//				ans.add(val + "");
//			else {
//				ans.add(key + "->" + val);
//			}
//		}
//		return ans;
//	}
	
	public List<String> summaryRanges(int[] nums) {

		if (nums.length == 0)
			return new ArrayList<>();

		int[] dp = new int[nums.length];
		dp[0] = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1] + 1) {
				dp[i] = dp[i - 1] + 1;
			} else {
				dp[i] = 0;
			}
		}
		
		List<String> ans = new ArrayList<>();
		for (int i = 0; i < nums.length; i++){
			int same = nums[i];
			while (i < nums.length && nums[i] - dp[i] == same) i++;
			if (same == nums[i-1]) ans.add(same+"");
			else ans.add(same + "->" + nums[i-1]);
			i--;
		}

		return ans;
	}
	
	public static void main(String[] args) {
		SolutionDay26_L0228 day = new SolutionDay26_L0228();
		int[] nums = {0,1,2,4,5,7};
		day.summaryRanges(nums);
	}

}
