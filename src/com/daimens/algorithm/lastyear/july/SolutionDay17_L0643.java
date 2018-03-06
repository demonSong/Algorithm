package com.daimens.algorithm.july;

public class SolutionDay17_L0643 {
	
//	public double findMaxAverage(int[] nums, int k) {
//		int sum = 0;
//		for (int i = 0; i < k; ++i){
//			sum += nums[i];
//		}
//		double max = sum / (1.0 * k);
//		for (int i = 0; i < nums.length - k; ++i){
//			sum -= nums[i];
//			sum += nums[i + k];
//			max = Math.max(max, sum / (1.0 * k));
//		}
//		
//		return max;
//    }
	
	public double findMaxAverage(int[] nums, int k) {
		int[] sums = new int[nums.length + 1];
		for (int i = 0; i < nums.length; ++i){
			sums[i + 1] = sums[i] + nums[i];
		}
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i + k < sums.length; ++i){
			max = Math.max(max, (sums[i + k] - sums[i]) / (1.0 * k));
		}
		return max;
	}
	
}
