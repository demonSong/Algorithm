package com.daimens.algorithm.august;

public class SolutionDay27_L0665 {
	
	class Pair implements Comparable<Pair>{
		int val;
		int id;
		
		public Pair(int val, int id){
			this.id = id;
			this.val = val;
		}
		@Override
		public int compareTo(Pair o) {
			return this.val == o.val ? this.id - o.id : this.val - o.val;
		}
	}
	
//	public boolean checkPossibility(int[] nums) {
//		int n = nums.length;
//		int cnt = 0;
//		int j = -1;
//		for (int i = 1; i < n; ++i){
//			if (nums[i] < nums[i - 1]){
//				cnt ++;
//				j = i;
//			}
//		}
//		
//		if (cnt == 0) return true;
//		if (cnt == 1){
//			if (j == 1 || (j - 2 >= 0) && nums[j] >= nums[j - 2]) return true;
//			if ((j + 1 < n) && nums[j + 1] >= nums[j - 1]) return true;
//		}
//		return false;
//	}
	
	public boolean checkPossibility(int[] nums) {
		int n = nums.length;
		int cnt = 0;
		for (int i = 1; i < n; ++i){
			if (nums[i] < nums[i - 1]){
				cnt ++;
				if (i - 2 >= 0 && nums[i - 2] > nums[i]) nums[i] = nums[i - 1];
				else nums[i - 1] = nums[i];
			}
		}
		return cnt <= 1;
	}
	
	
	public static void main(String[] args) {
		SolutionDay27_L0665 day = new SolutionDay27_L0665();
		int[] nums = {4, 2, 3}; // true
		System.out.println(day.checkPossibility(nums));
	}

}
