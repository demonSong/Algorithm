package com.daimens.algorithm.april;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.StartDocument;

/**
 * 
 * @author DemonSong 
 * 312.Burst Balloons 
 *         Given n balloons, indexed from 0 to n-1.
 *         Each balloon is painted with a number on it represented by array
 *         nums. You are asked to burst all the balloons. If the you burst
 *         balloon i you will get nums[left] * nums[i] * nums[right] coins. Here
 *         left and right are adjacent indices of i. After the burst, the left
 *         and right then becomes adjacent.
 * 
 *         Find the maximum coins you can collect by bursting the balloons
 *         wisely.
 * 
 *         Note: (1) You may imagine nums[-1] = nums[n] = 1. They are not real
 *         therefore you can not burst them. (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 
 *         Example:
 * 
 *         Given [3, 1, 5, 8]
 * 
 *         Return 167
 * 
 *         nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> [] coins = 3*1*5 +
 *         3*5*8 + 1*3*8 + 1*8*1 = 167
 *
 */
public class SolutionDay14_312 {
	
//	public int maxCoins(int[] nums) {
//
//		if (nums.length == 0)
//			return 0;
//
//		List<Integer> ans = new ArrayList<>();
//		ans.add(1);
//		for (int i = 1; i < nums.length + 1; i++) {
//			ans.add(nums[i - 1]);
//		}
//		ans.add(1);
//		
//		return helper(ans, 1);
//	}
//	
//	private int helper(List<Integer> ans, int start) {
//		
//		int value = 0;
//
//		if (start == ans.size() - 1) {
//			value = 0;
//		}
//		
//		else{
//			
//			List<Integer> res = new ArrayList<>();
//			int sum = ans.get(start) * ans.get(start + 1) * ans.get(start - 1);
//			
//			for (int i = 0; i < ans.size(); i++) {
//				if (i == start)
//					continue;
//				res.add(ans.get(i));
//				
//			}
//			
//			value = Math.max(helper(ans, start + 1), helper(res, 1) + sum);
//		}
//		return value;
//	}
	
	public int maxCoins(int[] nums) {

		if (nums.length == 0)
			return 0;
		
		int[] arrays = new int[nums.length + 2];
		
		arrays[0] = 1;
		arrays[arrays.length-1] = 1;
		
		for (int i = 0; i < nums.length; i++){
			arrays[i+1] = nums[i];
		}
		
		int[][] memo = new int[arrays.length][arrays.length];
		
		return burst(memo,arrays,0,arrays.length-1);
	}
	
	//是一种自底向上构建解的过程
	public int burst(int[][] memo, int[] nums, int left, int right) {
	    if (left + 1 == right) return 0;
	    if (memo[left][right] > 0) return memo[left][right];
	    int ans = 0;
	    for (int i = left + 1; i < right; ++i)
	        ans = Math.max(ans, nums[left] * nums[i] * nums[right] 
	        + burst(memo, nums, left, i) + burst(memo, nums, i, right));
	    memo[left][right] = ans;
	    return ans;
	}
	
	
//	private int findMax(int[] nums, int start, int end){
//		
//		int value = 0;
//		
//		if (start + 1 == end){ //遇到数组中只有两个元素
//			return 0;
//		}
//		
//		for (int i = start + 1; i <= end; i++){
//			int sum = nums[start] * nums[i] * nums[end];
//			value = Math.max(value, findMax(nums,start, i) + findMax(nums, i, end) + sum);
//		}
//		
//		return value;
//	}

	public static void main(String[] args) {
		SolutionDay14_312 day = new SolutionDay14_312();
		int[] nums = {3,1,5,8};
		day.maxCoins(nums);
	}
}
