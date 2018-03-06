package com.daimens.algorithm.august;

import java.util.Arrays;
import java.util.Scanner;

public class SolutionDay31_M1002 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			int n = in.nextInt();
			int[] nums = new int[n];
			for (int i = 0; i < n; ++i){
				nums[i] = in.nextInt();
			}
			System.out.println(solve(nums) ? "Yes" : "No");
		}
		in.close();
	}

	public static boolean solve(int[] nums){
		Arrays.sort(nums);
		int sum = 0;
		for (int i = 0; i < nums.length - 1; ++i){
			sum += nums[i];
		}
		return sum > nums[nums.length - 1];
	}
}
