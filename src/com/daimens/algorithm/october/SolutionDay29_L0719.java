package com.daimens.algorithm.october;

import java.util.Arrays;

public class SolutionDay29_L0719 {
    
	public int smallestDistancePair(int[] nums, int k) {
		int n = nums.length;
		Arrays.sort(nums);
		int lf = -1;
		int rt = nums[n - 1] - nums[0];
		
		while (rt - lf > 1) {
			int mid = lf + (rt - lf) / 2;
			if (count(nums, mid) < k) {
				lf = mid;
			}
			else {
				rt = mid;
			}
		}
		return rt;
    }
	
	public int count(int[] nums, int mid) {  // < mid 的个数
		int n = nums.length;
		int cnt = 0;
		
		int j = 0;
		
		for (int i = 1; i < n; ++i) {
			while (j < i && nums[i] - nums[j] > mid) j++;
			cnt += i - j;
		}
		
		return cnt;
	}
	
	public static void main(String[] args) {
		SolutionDay29_L0719 day = new SolutionDay29_L0719();
		int[] nums = {1, 3, 1};
		Arrays.sort(nums);
		int k = 1;
		System.out.println(day.count(nums, 0));
		System.out.println(day.smallestDistancePair(nums, k));
	}
}
