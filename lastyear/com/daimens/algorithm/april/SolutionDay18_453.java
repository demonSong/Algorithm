package com.daimens.algorithm.april;

import java.util.Arrays;

/**
 * 
 * @author DemonSong 453.Minimum Moves to Equal Array Elements Given a non-empty
 *         integer array of size n, find the minimum number of moves required to
 *         make all array elements equal, where a move is incrementing n - 1
 *         elements by 1.
 * 
 *         Example:
 * 
 *         Input: [1,2,3]
 * 
 *         Output: 3
 * 
 *         Explanation: Only three moves are needed (remember each move
 *         increments two elements):
 * 
 *         [1,2,3] => [2,3,3] => [3,4,3] => [4,4,4]
 *
 */
public class SolutionDay18_453 {
	
	
//	public int minMoves(int[] nums) {
//		if (nums.length == 1 || nums.length == 0) return 0;
//		
//		int count = 0;
//		int last = nums.length-1;
//		Arrays.sort(nums);
//		while (!isEqual(nums)){
//			int dis = nums[last] - nums[0];
//			count += dis;
//			for (int i = 0; i < nums.length-1; i++){
//				nums[i] += dis;
//			}
//			Arrays.sort(nums);
//		}
//		
//		return count;
//    }
//	
//	private boolean isEqual(int[] nums){
//		for (int i = 1; i < nums.length; i++){
//			if(nums[i] != nums[0]){
//				return false;
//			}
//		}
//		return true;
//	}
	
	
	//对偶问题 变动n-1个元素 改为 变动1个元素，加法 变成 减法  ，再看最终状态，从最小到最大，变成最大到最小
	public int minMoves(int[] nums) {
		int sum = 0, min = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++){
			sum += nums[i];
			if (nums[i] < min){
				min = nums[i];
			}
		}
		
		return sum - nums.length * min;
	}
}
