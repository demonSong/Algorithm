package com.daimens.algorithm.october;

import java.util.Arrays;

public class SolutionDay15_L0502 {
	
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        int max = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
        	sum += nums[i];
        	max = Math.max(max, nums[i]);
        }
        if (sum % k != 0) return false;
        tar = sum / k;
        if (max > tar) return false;
        Arrays.sort(nums);
        return go(nums, n - 1, k, new int[k]);
    }
    
    int tar = 0;
    boolean go(int[] nums, int pos, int k, int[] sums) {
    	if (pos == -1) {
    		boolean check = true;
	    	for (int i = 0; i < k; ++i) {
	    		if (sums[i] != tar) check = false;
	    	}
	    	return check;
    	}
    	for (int i = 0; i < k; ++i) {
    		sums[i] += nums[pos];
    		if (sums[i] <= tar) {
    			if (go(nums, pos - 1, k, sums)) return true;
    		}
    		sums[i] -= nums[pos];
    	}
    	return false;
    }
    
	public static void main(String[] args) {
		SolutionDay15_L0502 day = new SolutionDay15_L0502();
		int[] nums = {4, 3, 2, 3, 5, 2, 1};
		System.out.println(day.canPartitionKSubsets(nums, 4));
	}

}
