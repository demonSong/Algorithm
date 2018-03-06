package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 
 *         15.3Sum
 * 
 *         Given an array S of n integers, are there elements a, b, c in S such
 *         that a + b + c = 0? Find all unique triplets in the array which gives
 *         the sum of zero.
 * 
 *         Note: The solution set must not contain duplicate triplets.
 * 
 *         For example, given array S = [-1, 0, 1, 2, -1, -4],
 * 
 *         A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
 *
 */
public class SolutionDay06_L0015 {
	
//	class Pair{
//		int idx1;
//		int idx2;
//		
//		int num1;
//		int num2;
//		
//		@Override
//		public String toString() {
//			return "{" + idx1 + ", " + idx2 + "}";
//		}
//	}
//	
//	public List<List<Integer>> threeSum(int[] nums) {
//		Map<Integer,List<Pair>> map = new HashMap<>();
//		for (int i = 0; i < nums.length; ++i){
//			for (int j = i + 1; j < nums.length; ++j){
//				Pair p = new Pair();
//				p.idx1 = i;
//				p.idx2 = j;
//				p.num1 = nums[i];
//				p.num2 = nums[j];
//				int sum = nums[i] + nums[j];
//				map.computeIfAbsent(sum, a -> new ArrayList<>()).add(p);
//			}
//		}
//		
//		Set<List<Integer>> ans = new HashSet<>();
//		for (int i = 0; i < nums.length; i++){
//			int target = - nums[i];
//			if (map.containsKey(target)){
//				List<Pair> ps = map.get(target);
//				for (Pair p : ps){
//					int idx1 = p.idx1;
//					int idx2 = p.idx2;
//					if (i != idx1 && i != idx2){
//						List<Integer> array = new ArrayList<>();
//						array.add(nums[i]);
//						array.add(p.num1);
//						array.add(p.num2);
//						nums[idx1] = 1 << 30;
//						nums[idx2] = 1 << 30;
//						ans.add(array);
//					}
//				}
//			}
//		}
//		
//        return new ArrayList<>(ans);
//    }
	
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++){
			int target = -nums[i];
			int lf = i + 1;
			int rt = nums.length - 1;
			while (lf < rt){
				int sum = nums[lf] + nums[rt];
				if (sum < target) lf++;
				if (sum > target) rt--;
				if (sum == target){
					Integer[] triple = new Integer[3];
					triple[0] = nums[i];
					triple[1] = nums[lf];
					triple[2] = nums[rt];
					ans.add(Arrays.asList(triple));
					while (lf < rt && nums[lf] == triple[1]) lf++;
					while (lf < rt && nums[rt] == triple[2]) rt--;
				}
			}
			
			while (i + 1 < nums.length && nums[i+1] == nums[i]) i++;
		}
		return ans;
	}
	
	public static void main(String[] args) {
		SolutionDay06_L0015 day = new SolutionDay06_L0015();
//		int[] nums = {-1, 0, 1, 2, -1, -4};
		int[] nums = {0,0,0,0};
		day.threeSum(nums);
	}

}
