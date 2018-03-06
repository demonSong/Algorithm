package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         11.Container With Most Water
 * 
 *         Given n non-negative integers a1, a2, ..., an, where each represents
 *         a point at coordinate (i, ai). n vertical lines are drawn such that
 *         the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
 *         which together with x-axis forms a container, such that the container
 *         contains the most water.
 *
 */
public class SolutionDay16_L0011 {
	
//	public int maxArea(int[] height) {
//		return helper(height, 0, height.length-1);
//    }
//	
//	private int helper(int[] height, int start, int end){
//		if (start > end) return 0;
//		int minH = Math.min(height[start], height[end]);
//		int maxArea = minH * (end - start);
//		maxArea = Math.max(maxArea, helper(height, start+1, end-1));
//		maxArea = Math.max(maxArea, helper(height, start, end-1));
//		maxArea = Math.max(maxArea, helper(height, start+1, end));
//		return maxArea;
//	}
	
	public int maxArea(int[] height) {
		int left = 0, right = height.length-1;
		int maxArea = 0;
		while (left < right){
			int area = Math.min(height[left], height[right]) * (right - left);
			maxArea = Math.max(maxArea, area);
			if(height[left] < height[right]){
				left ++;
			}else{
				right --;
			}
		}
		return maxArea;
	}
	
	public static void main(String[] args) {
		SolutionDay16_L0011 day = new SolutionDay16_L0011();
		day.maxArea(new int[]{1,1});
	}

}
