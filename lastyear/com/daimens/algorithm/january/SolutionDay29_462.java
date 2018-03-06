package com.daimens.algorithm.january;

import java.util.Arrays;

/**
 * 
 * @author Demon Song
 * 462.Minimum Moves to Equal Array Elements II
 * Given a non-empty integer array,find the minimum number of moves required to make all
 * array elements equal,where a move is incrementing a selected element by 1 or decrementing a
 * selected element by 1.
 * You may assume the array's length is at most 10,000.
 * Example:
 * Input:
 * [1,2,3]
 * Output:
 * 2
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one element)
 * 
 * [1,2,3] => [2,2,3] => [2,2,2]
 *
 */
public class SolutionDay29_462 {
	
	//生成二维矩阵来做吧
//	public int minMoves2(int[] nums){
//		//最后一行记录累加的值
//		int[][] result = new int[nums.length][nums.length];
//		int min = Integer.MAX_VALUE;
//		for(int i = 0; i < nums.length; i++){
//			long sum = 0;
//			for(int j = i; j < nums.length; j++){
//				int difference = Math.abs(nums[i]-nums[j]);
//				result[i][j] = difference;
//			}
//			
//			for(int j = 0; j < nums.length;j++){
//				if(i == j){
//					continue;
//				}
//				if(result[i][j] == 0){
//					sum += result[j][i];
//				}else{
//					sum += result[i][j];
//				}
//			}
//			
//			if(sum < min){
//				min = (int) sum;
//			}
//			
//		}
//		
//		return min;
//	}
	
	public int minMoves2(int[] nums){
		if(nums == null || nums.length == 0) return 0;
		Arrays.sort(nums);
		int median = nums[nums.length / 2];
		int sum = 0;
		for(int i = 0; i < nums.length; i++){
			sum += Math.abs(nums[i]- median);
		}
		return sum;
	}
	
	public static void main(String[] args) {
		SolutionDay29_462 day29_462 = new SolutionDay29_462();
		int[] nums = {203125577,-349566234,230332704,48321315,66379082,386516853,50986744,-250908656,-425653504,-212123143};
		day29_462.minMoves2(nums);
	}
	
}
