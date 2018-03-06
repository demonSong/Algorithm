package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         229.Majority Element II
 * 
 *         Given an integer array of size n, find all elements that appear more
 *         than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in
 *         O(1) space.
 *
 */
public class SolutionDay05_L0229 {
	
	// 优化空间
//	public List<Integer> majorityElement(int[] nums) {
//		Map<Integer, Integer> map = new HashMap<>();
//		for (int i : nums){
//			map.put(i, map.getOrDefault(i, 0) + 1);	
//		}
//		int n = nums.length;
//		List<Integer> ans = new ArrayList<>();
//		for (int key : map.keySet()){
//			if (map.get(key) > n / 3) ans.add(key);
//		}
//		return ans;
//    }
	
	public List<Integer> majorityElement(int[] nums) {
		int num1 = 0, num2 = 1, cnt1 = 0, cnt2 = 0;
		for (int num : nums){
			if (num == num1) cnt1++;
			else if (num == num2) cnt2++;
			else if (cnt1 == 0){
				num1 = num;
				cnt1 = 1;
			}
			else if (cnt2 == 0){
				num2 = num;
				cnt2 = 1;
			}
			else{
				cnt1--;
				cnt2--;
			}
		}
		List<Integer> ans = new ArrayList<>();
		cnt1 = 0;
		cnt2 = 0;
		for (int num : nums){
			if (num == num1) cnt1++;
			if (num == num2) cnt2++;
		}
		
		if (cnt1 > nums.length / 3) ans.add(num1);
		if (cnt2 > nums.length / 3) ans.add(num2);
		return ans;
	}


}
