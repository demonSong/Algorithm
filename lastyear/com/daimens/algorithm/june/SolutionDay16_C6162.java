package com.daimens.algorithm.june;

import java.util.Scanner;

public class SolutionDay16_C6162 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] array = new int[N];
		for (int i = 0; i < N; ++i){
			array[i] = in.nextInt();
		}
		System.out.println(solve(array));
		in.close();
	}
	
	private static int solve(int[] array){
		return 0;
	}
	
	public static long cnk(int n, int k) {
		int fenzi = 1;
		int fenmu = 1;
		for (int i = n - k + 1; i <= n; i++) {
			fenzi *= i;
		}
		for (int j = 1; j <= k; j++) {
			fenmu *= j;
		}
		int result = fenzi / fenmu;
		return result;
	}
}
