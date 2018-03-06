package com.daimens.algorithm.july;

public class SolutionDay23_L0645 {
	
//	public int[] findErrorNums(int[] nums) {
//		int[] map = new int[nums.length + 1];
//		
//		for (int i = 0; i < nums.length; ++i){
//			map[nums[i]]++;
//		}
//		
//		int a1 = 0;
//		int a2 = 0;
//		for (int i = 1; i <= nums.length; ++i){
//			if (map[i] == 2){
//				a1 = i;
//			}
//			if (map[i] == 0){
//				a2 = i;
//			}
//		}
//		
//		return new int[]{a1, a2};
//	}
	
	public int[] findErrorNums(int[] nums) {
		 int[] res = new int[2];
		 for (int i : nums){
			 if (nums[Math.abs(i) - 1] < 0) res[0] = Math.abs(i);
			 else nums[Math.abs(i) - 1] *= -1;
		 }
		 for (int i = 0; i < nums.length; ++i){
			 if (nums[i] > 0) res[1] = i + 1;
		 }
		 return res;
	}
	
	public static void main(String[] args) {
		SolutionDay23_L0645 day = new SolutionDay23_L0645();
	}

}
