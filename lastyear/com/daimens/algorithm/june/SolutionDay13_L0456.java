package com.daimens.algorithm.june;

import java.util.Arrays;
import java.util.Stack;

/**
 * 
 * @author DemonSong
 * 
 *         456. 132 Pattern
 * 
 *         Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a
 *         subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design
 *         an algorithm that takes a list of n numbers as input and checks
 *         whether there is a 132 pattern in the list.
 * 
 *         Note: n will be less than 15,000.
 * 
 *         Example 1: Input: [1, 2, 3, 4]
 * 
 *         Output: False
 * 
 *         Explanation: There is no 132 pattern in the sequence. Example 2:
 *         Input: [3, 1, 4, 2]
 * 
 *         Output: True
 * 
 *         Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 *         Example 3: Input: [-1, 3, 2, 0]
 * 
 *         Output: True
 * 
 *         Explanation: There are three 132 patterns in the sequence: [-1, 3,
 *         2], [-1, 3, 0] and [-1, 2, 0].
 *
 */
public class SolutionDay13_L0456 {
		
//	public boolean find132pattern(int[] nums) {
//		if (nums.length == 0) return false;
//		for (int j = 0; j < nums.length; ++j){
//			Stack<Integer> stack = new Stack<>();
//			stack.push(nums[j]);
//			for (int i = j+1; i < nums.length; ++i){
//				if (stack.isEmpty() || nums[i] >= stack.peek()){
//					stack.push(nums[i]);
//				}
//				else{
//					Stack<Integer> tmp = new Stack<>();
//					while (!stack.isEmpty() && stack.peek() >= nums[i]){
//						tmp.push(stack.pop());
//					}
//					if (!stack.isEmpty()) return true;
//					while (!tmp.isEmpty()){
//						stack.push(tmp.pop());
//					}
//				}
//			}
//		}
//		return false;
//    }
	
//	public boolean find132pattern(int[] nums) {
//		for (int i = 1, min = Integer.MAX_VALUE; i < nums.length; ++i){
//			min = Math.min(min, nums[i-1]);
//			for (int j = i + 1; j < nums.length; ++j){
//				if (min < nums[j] && nums[j] < nums[i]) return true;
//			}
//		}
//		return false;
//	}
	
	public boolean find132pattern(int[] nums) {
		int[] min = Arrays.copyOf(nums, nums.length);
		for (int i = 1; i < min.length; ++i){
			min[i] = Math.min(min[i-1], nums[i-1]);
		}
		Stack<Integer> stack = new Stack<>();
		for (int i = nums.length - 1; i >= 0; --i){
			if (nums[i] <= min[i]) continue;
			while (!stack.isEmpty() && stack.peek() <= min[i]) stack.pop();
			if (!stack.isEmpty() && stack.peek() < nums[i]) return true;
			stack.push(nums[i]);
		}
		return false;
	}
	
	public static void main(String[] args) {
		SolutionDay13_L0456 day = new SolutionDay13_L0456();
		int[] nums = {3,1,4,2};
		System.out.println(day.find132pattern(nums));
	}
}
