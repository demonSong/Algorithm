package com.daimens.algorithm.july;

public class SolutionDay17_L0644 {
	
//	public double findMaxAverage(int[] nums, int k) {
//		int[] sums = new int[nums.length + 1];
//		
//		for (int i = 0; i < nums.length; ++i){
//			sums[i + 1] = nums[i] + sums[i]; 
//		}
//		
//		double lf = -100000;
//		double rt = 100000;
//		
//		while (Math.abs(lf - rt) > 10e-5) {
//			double mid = (lf + rt) / 2;
//			if (!ok(sums, k , mid)) lf = mid;
//			else rt = mid;
//		}
//        return rt;
//	}
//	
//	private boolean ok(int[] sums, int k, double mid){
//		boolean isValid = true;
//		for (int j = k; j <= sums.length - 1; ++j){
//			for (int i = 0; i + j < sums.length; ++i){
//				if (sums[i + j] - sums[i] > mid * j){
//					isValid = false;
//				}
//			}
//		}
//		return isValid;
//	}
	
	public double findMaxAverage(int[] nums, int k) {
		double lf = -12345, rt = 12345;
		while (rt - lf > 10e-6){
			double mid = (rt + lf) / 2;
			if (check(nums, mid, k)) lf = mid;
			else rt = mid;
		}
		return rt;
	}
	
	private boolean check(int[] nums, double mid, int k){
		double[] arra = new double[nums.length];
		for (int i = 0; i < nums.length; ++i){
			arra[i] = nums[i] - mid;
		}
		double sum  = 0, pre = 0;
		for (int i = 0; i < k; ++i){
			sum += arra[i];
		}
		if (sum >= 0) return true;
		double min = 0.0;
		for (int i = k; i < nums.length; ++i){
			sum += arra[i];
			pre += arra[i - k];
			min = Math.min(min, pre);
			if (sum >= min) return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		SolutionDay17_L0644 day = new SolutionDay17_L0644();
		int[] nums = {1,12,-5,-6,50,3};
		System.out.println(day.findMaxAverage(nums, 4));
	}
	
}
