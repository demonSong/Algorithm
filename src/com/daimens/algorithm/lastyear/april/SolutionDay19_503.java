package com.daimens.algorithm.april;

import java.util.Arrays;
import java.util.Stack;

/**
 * 
 * @author DemonSong 
 * 503.Next Greater Element II
 * 
 *         Given a circular array (the next element of the last element is the
 *         first element of the array), print the Next Greater Number for every
 *         element. The Next Greater Number of a number x is the first greater
 *         number to its traversing-order next in the array, which means you
 *         could search circularly to find its next greater number. If it
 *         doesn't exist, output -1 for this number.
 * 
 *         Example 1: Input: [1,2,1] Output: [2,-1,2] Explanation: The first 1's
 *         next greater number is 2; The number 2 can't find next greater
 *         number; The second 1's next greater number needs to search
 *         circularly, which is also 2. Note: The length of given array won't
 *         exceed 10000.
 *
 */
public class SolutionDay19_503 {
//	public int[] nextGreaterElements(int[] nums) {
//		
//		int[] ans = new int[nums.length];
//		Arrays.fill(ans, -1);
//		
//		int len = nums.length;
//		for (int i = 0; i < len; i++){
//			int compare = nums[i];
//			for (int j = i + 1; j < 2 * len; j++) {
//				if (nums[j % len] > compare){
//					ans[i] = nums[j % len];
//					break;
//				}
//			}
//		}
//		
//        return ans;
//    }
	
	public int[] nextGreaterElements(int[] nums) {
		int len = nums.length;
		int[] ans = new int[len];
		Arrays.fill(ans, -1);
		
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < 2 * len; i++){
			int num = nums[i % len];
			while(!stack.isEmpty() && nums[stack.peek()] < num)
				ans[stack.pop()] = num;
			
			if (i < len) stack.push(i);
		}
		
		return ans;
	}
}
