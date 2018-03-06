package com.daimens.algorithm.april;

import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.RescaleOp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import sun.launcher.resources.launcher_fr;

/**
 * 
 * @author DemonSong
 * 
 *         42.Trapping rain water
 * 
 *         Given n non-negative integers representing an elevation map where the
 *         width of each bar is 1, compute how much water it is able to trap
 *         after raining.
 * 
 *         For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * 
 *         The above elevation map is represented by array
 *         [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue
 *         section) are being trapped. Thanks Marcos for contributing this
 *         image!
 * 
 * 
 */
public class SolutionDay23_042 {
	
	// 2 0 2 bug 边界条件
//	public int trap(int[] height) {
//		if (height.length == 0) return 0;
//		
//		int sum = 0;
//		int pos = 0;
//		
//		int[] hh = Arrays.copyOf(height, height.length+1);
//		hh[hh.length-1] = 0;
//		
//		next(hh);
//		
//		while (pos < hh.length){
//			int now = hh[pos];
//			if(!map.containsKey(pos)){
//				pos ++;
//				continue;
//			}
//			
//			int nextIndex = map.get(pos);
//			int nex = hh[nextIndex];
//			
//			sum += Math.min(now, nex) * (nextIndex - pos + 1 - 2);
//			for (int i = pos + 1; i < nextIndex; i++) sum -= hh[i];
//			
//			pos = nextIndex;
//		}
//		
//		return sum;
//    }
//	
//	Map<Integer, Integer> map = new HashMap<>();
//	private void next(int[] height){
//		Stack<Integer> stack = new Stack<>();
//		
//		for (int i = 0; i < height.length; i++){
//			int curr = height[i];
//			while(i != 0 && i + 1 != height.length && !stack.isEmpty() && height[i-1] <= curr && curr > height[i+1]){
//				int index = stack.pop();
//				map.put(index, i);
//			}
//			stack.push(i);
//		}
//	}
	
//	public int trap(int[] height) {
//		int left = 0, right = height.length-1;
//		int res = 0;
//		
//		int maxLeft = 0, maxRight = 0;
//		while (left <= right){
//			if(height[left] <= height[right]){
//				if(height[left] >= maxLeft) maxLeft = height[left];
//				else res += maxLeft - height[left];
//				left ++;
//			}else{
//				if(height[right] >= maxRight) maxRight = height[right];
//				else res += maxRight - height[right];
//				right--;
//			}
//		}
//		
//		return res;
//	}
	
	public int trap(int[] height) {
		
		if(height.length == 0) return 0;
		
		int max = -1, maxId = -1;
		
		for (int i = 0; i < height.length; i++){
			if (height[i] > max){
				max = height[i];
				maxId = i;
			}
		}
		
		
		int area = 0, maxLeft = height[0];
		for (int i = 0; i < maxId; i++){
			if (height[i] >= maxLeft) maxLeft = height[i];
			else area += maxLeft-height[i];
		}
		
		for (int i = height.length - 1, maxRight = height[height.length-1]; i > maxId; i--){
			if(height[i] >= maxRight) maxRight = height[i];
			else area += maxRight - height[i];
		}
		
		return area;
	}
	
	public static void main(String[] args) {
		SolutionDay23_042 day = new SolutionDay23_042();
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		//int[] height = {5,4,1,2};
		day.trap(height);
	}
}
