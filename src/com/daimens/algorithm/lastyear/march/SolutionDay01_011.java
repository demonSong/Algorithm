package com.daimens.algorithm.march;

/**
 * 
 * @author Demon Song
 * 11.Container With Most Water
 * Given n non-negative integers a1,a2,...,an,where each represents a point at coordinate. n vertical
 * lines are drawn such that the two endpoints of line i is at (i,ai) and (i,0).Find two lines,which 
 * together with x-axis forms a container,such that the container contains the most water.
 * Note:
 * You may not slant the container and n is at least 2.
 *
 */
public class SolutionDay01_011 {
	
	//可以包含子集
//	public int maxArea(int[] height) {
//		int min = height[0];
//		int area = 0;
//		for (int i = 1; i < height.length; i++) {
//			if (min > height[i])
//				min = height[i];
//
//			if (area < min * i)
//				area = min * i;
//		}
//		return area;
//	}
	
	public int maxArea(int[] height) {
		int left = 0, right = height.length - 1;
		
		int maxArea = 0;
		while (left < right){
			maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right-left));
			if(height[left] < height[right]){
				left ++;
			}
			else{
				right --;
			}
		}
		return maxArea;
	}
}
