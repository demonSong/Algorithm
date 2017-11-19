package com.daimens.algorithm.november;

public class SolutionDay13_L0724 {
	
	public int pivotIndex(int[] nums) {
		int n = nums.length;
		int[] lf = new int[n];
		int[] rt = new int[n];
		
		int sum = 0;
		for (int i = 0; i < n; ++i) {
			 lf[i] = sum;
			 sum += nums[i];
		}
		
		sum = 0;
		for (int j = n - 1; j >= 0; --j) {
			rt[j] = sum;
			sum += nums[j];
		}
		
		for (int i = 0; i < n; ++i) {
			if (lf[i] == rt[i]) {
				return i;
			}
		}
		
		return -1;
    }

	
	public static void main(String[] args) {
		SolutionDay13_L0724 day = new SolutionDay13_L0724();
	}
}
