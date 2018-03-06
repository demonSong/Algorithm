package com.daimens.algorithm.august;

import java.util.Scanner;

public class SolutionDay31_M1001 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			int N = in.nextInt();
			int[] nums = new int[N];
			for (int i = 0; i < N; ++i){
				nums[i] = in.nextInt();
			}
			
			System.out.println(solve(nums));
		}
		in.close();
	}
	
	
	static final int INF = 1 << 29;
	public static int solve(int[] nums){
		int[] dp = new int[nums.length + 1];
		dp[0] = 1;
		for (int i = 1; i < nums.length; ++i){
			for (int j = 0; j < i; ++j){
				if ((nums[i] ^ nums[j]) == 1){
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		return dp[nums.length - 1];
	}
}
