package com.daimens.algorithm.september;

public class NumArray {
	
	int[] sum;
    public NumArray(int[] nums) {
    	int n = nums.length;
    	sum = new int[n + 1];
    	for (int i = 0; i < n; ++i) {
    		sum[i + 1] = sum[i] + nums[i];
    	}
    }
    
    public int sumRange(int i, int j) {
    	return sum[j + 1] - sum[i];
    }
    
}
