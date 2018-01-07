package com.daimens.algorithm.december;

import java.util.Arrays;

public class SolutionDay24_L0747 {
	
//    public int dominantIndex(int[] nums) {
//    	int maxIndex = -1;
//    	int max = Integer.MIN_VALUE;
//    	for (int i = 0; i < nums.length; ++i) {
//    		if (nums[i] > max) {
//    			max = nums[i];
//    			maxIndex = i;
//    		}
//    	}
//    	int n = nums.length;
//    	Arrays.sort(nums);
//    	if (max > nums[n - 2] * 2) return maxIndex;
//    	return -1;
//    }
    
//    public int dominantIndex(int[] nums) {
//    	int n = nums.length;
//    	for (int i = 0; i < n; ++i) {
//    		boolean ok = true;
//    		for (int j = 0; j < n; ++j) {
//    			if (i != j && nums[i] < nums[j] * 2) {
//    				ok = false;
//    				break;
//    			}
//    		}
//    		if (ok) return i;
//    	}
//    	return -1;
//    }
	
	class P implements Comparable<P>{
		int idx;
		int val;
		P(int idx, int val){
			this.idx = idx;
			this.val = val;
		}
		
		@Override
		public int compareTo(P that) {
			return that.val - this.val;
		}
	}
	
	public int dominantIndex(int[] nums) {
		int n = nums.length;
		if (n == 1) return -1;
		P[] ps = new P[n];
		for (int i = 0; i < n; ++i) {
			ps[i]  = new P(i, nums[i]);
		}
		Arrays.sort(ps);
		if (ps[0].val >= ps[1].val * 2) return ps[0].idx;
		return -1;
	}
	
	public static void main(String[] args) {
		SolutionDay24_L0747 day = new SolutionDay24_L0747();
	}

}
