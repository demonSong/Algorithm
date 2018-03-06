package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         45.Jump Game II
 * 
 *         Given an array of non-negative integers, you are initially positioned
 *         at the first index of the array.
 * 
 *         Each element in the array represents your maximum jump length at that
 *         position.
 * 
 *         Your goal is to reach the last index in the minimum number of jumps.
 * 
 *         For example: Given array A = [2,3,1,1,4]
 * 
 *         The minimum number of jumps to reach the last index is 2. (Jump 1
 *         step from index 0 to 1, then 3 steps to the last index.)
 * 
 *         Note: You can assume that you can always reach the last index.
 *
 */
public class SolutionDay22_L0045 {

//	public int jump(int[] nums) {
//		if (nums.length == 1)
//			return 0;
//		int step = 1;
//		int range = 0 + nums[0];
//		int now = 0;
//		while (range < nums.length - 1) {
//			step++;
//			int maxRange = 0;
//			int index = -1;
//			for (int i = now + 1; i <= range; i++) {
//				if (i + nums[i] >= maxRange) {
//					maxRange = i + nums[i];
//					index = i;
//				}
//			}
//			range = maxRange;
//			now = index;
//		}
//		return step;
//	}
	
//	public int jump(int[] nums) {
//		int step = 0, range = 0, now = -1;
//		while (range < nums.length - 1) {
//			step++;
//			int maxRange = 0;
//			int index = -1;
//			for (int i = now + 1; i <= range; i++) {
//				if (i + nums[i] >= maxRange) {
//					maxRange = i + nums[i];
//					index = i;
//				}
//			}
//			range = maxRange;
//			now = index;
//		}
//		return step;
//	}
	
	public int jump(int[] nums) {
		int step = 0, edge = 0, maxRange = 0;
		int len = nums.length;
		for (int i = 0; i < len; i++){
			if (i > edge){
				step ++;
				edge = maxRange;
				maxRange = 0;
			}
			maxRange = Math.max(maxRange, i + nums[i]);
		}
		return step;
	}

	public static void main(String[] args) {
		SolutionDay22_L0045 day = new SolutionDay22_L0045();
		int[] nums = { 2, 1 };
		day.jump(nums);
	}

}
