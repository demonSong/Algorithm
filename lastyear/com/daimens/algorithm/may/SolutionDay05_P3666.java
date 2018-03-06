package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         3666.Making the Grade
 * 
 *         Description
 * 
 *         A straight dirt road connects two fields on FJ's farm, but it changes
 *         elevation more than FJ would like. His cows do not mind climbing up
 *         or down a single slope, but they are not fond of an alternating
 *         succession of hills and valleys. FJ would like to add and remove dirt
 *         from the road so that it becomes one monotonic slope (either sloping
 *         up or down).
 * 
 *         You are given N integers A1, ... , AN (1 ≤ N ≤ 2,000) describing the
 *         elevation (0 ≤ Ai ≤ 1,000,000,000) at each of N equally-spaced
 *         positions along the road, starting at the first field and ending at
 *         the other. FJ would like to adjust these elevations to a new sequence
 *         B1, . ... , BN that is either nonincreasing or nondecreasing. Since
 *         it costs the same amount of money to add or remove dirt at any
 *         position along the road, the total cost of modifying the road is
 * 
 *         |A1 - B1| + |A2 - B2| + ... + |AN - BN | Please compute the minimum
 *         cost of grading his road so it becomes a continuous slope. FJ happily
 *         informs you that signed 32-bit integers can certainly be used to
 *         compute the answer.
 * 
 *         Input
 * 
 *         Line 1: A single integer: N Lines 2..N+1: Line i+1 contains a single
 *         integer elevation: Ai
 * 
 *         Output
 * 
 *         Line 1: A single integer that is the minimum cost for FJ to grade his
 *         dirt road so it becomes nonincreasing or nondecreasing in elevation.
 * 
 *         Sample Input
 * 
 *         7 1 3 2 4 5 3 9 Sample Output
 * 
 *         3
 *
 */
public class SolutionDay05_P3666 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = in.nextInt();
		}
		System.out.println(solve(A));
		in.close();
	}

//	public static int solve(int[] A) {
//		int n = A.length;
//		int[][] dp = new int[n][n];
//
//		int[] B = new int[n];
//		System.arraycopy(A, 0, B, 0, n);
//
//		// 非严格递增
//		Arrays.sort(B);
//
//		for (int j = 0; j < n; ++j) {
//			dp[0][j] = Math.abs(A[0] - B[j]);
//		}
//		for (int i = 1; i < n; ++i) {
//			int pre_min_cost = dp[i - 1][0];
//			for (int j = 0; j < n; ++j) {
//				pre_min_cost = Math.min(pre_min_cost, dp[i - 1][j]);
//				dp[i][j] = pre_min_cost + Math.abs(A[i] - B[j]);
//			}
//		}
//
//		int min = Integer.MAX_VALUE;
//		for (int i = 0; i < n; i++) {
//			min = Math.min(min, dp[n - 1][i]);
//		}
//		return min;
//	}
	
	public static int solve(int[] A) {
		int n = A.length;
		int[][] dp = new int[2][n];

		int[] B = new int[n];
		System.arraycopy(A, 0, B, 0, n);

		// 非严格递增
		Arrays.sort(B);

		for (int j = 0; j < n; ++j) {
			dp[0][j] = Math.abs(A[0] - B[j]);
		}
		for (int i = 1; i < n; ++i) {
			
			int cur = i % 2;
			int pre = (i - 1) % 2;
			
			int pre_min_cost = dp[pre][0];
			for (int j = 0; j < n; ++j) {
				pre_min_cost = Math.min(pre_min_cost, dp[pre][j]);
				dp[cur][j] = pre_min_cost + Math.abs(A[i] - B[j]);
			}
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			min = Math.min(min, dp[(n - 1) % 2][i]);
		}
		return min;
	}
}
