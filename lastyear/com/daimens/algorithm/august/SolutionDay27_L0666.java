package com.daimens.algorithm.august;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SolutionDay27_L0666 {
	
//	public int pathSum(int[] nums) {
//		int sum = 0;
//		int n = nums.length;
//		boolean[] leaf = new boolean[nums.length];
//		Arrays.fill(leaf, true);
//		for (int i = n - 1; i >= 0; --i){
//			if (leaf[i]){
//				int val = nums[i] % 10;
//				int pos = nums[i] / 10 % 10;
//				int dep = nums[i] / 100;
//				int layer = dep;
//				sum += val;
//				for (int k = 1; k < dep; ++k){
//					for (int j = i - 1; j >= 0; --j){
//						int pp = nums[j] / 10 % 10;
//						int vl = nums[j] % 10;
//						int ll = nums[j] / 100;
//						if ((pos + 1) / 2== pp && ll < layer){
//							leaf[j] = false;
//							sum += vl;
//							pos = pp;
//							layer = ll;
//							break;
//						}
//					}
//				}
//			}
//		}
//		return sum;
//    }
	
	public int pathSum(int[] nums) {
		int sum = 0;
		int n = nums.length;
		
		boolean[][] nleaf = new boolean[5][12];
		
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums){
			int val = num % 10;
			int pos = num / 10 % 10;
			int dep = num / 100;
			map.put(dep * 8 + pos, val);
		}
		
		for (int i = n - 1; i >= 0; --i){
				int val = nums[i] % 10;
				int pos = nums[i] / 10 % 10;
				int dep = nums[i] / 100;
				if (!nleaf[dep][pos]){
					sum += val;
					for (int k = 1; k < dep; ++k){
						pos = (pos + 1 ) / 2;
						int key = (dep - k) * 8 + pos;
						nleaf[dep - k][pos] = true;
						sum += map.get(key);
					}
				}
		}
		return sum;
    }
	
	public static void main(String[] args) {
		SolutionDay27_L0666 day = new SolutionDay27_L0666();
		int[] nums = {113,215,221};
		System.out.println(day.pathSum(nums));
	}

}
