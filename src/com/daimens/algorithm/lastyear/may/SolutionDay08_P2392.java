package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         2392.Space Elevator
 * 
 *         Description
 * 
 *         The cows are going to space! They plan to achieve orbit by building a
 *         sort of space elevator: a giant tower of blocks. They have K (1 <= K
 *         <= 400) different types of blocks with which to build the tower. Each
 *         block of type i has height h_i (1 <= h_i <= 100) and is available in
 *         quantity c_i (1 <= c_i <= 10). Due to possible damage caused by
 *         cosmic rays, no part of a block of type i can exceed a maximum
 *         altitude a_i (1 <= a_i <= 40000).
 * 
 *         Help the cows build the tallest space elevator possible by stacking
 *         blocks on top of each other according to the rules. Input
 * 
 *         Line 1: A single integer, K
 * 
 *         Lines 2..K+1: Each line contains three space-separated integers: h_i,
 *         a_i, and c_i. Line i+1 describes block type i. Output
 * 
 *         Line 1: A single integer H, the maximum height of a tower that can be
 *         built Sample Input
 * 
 *         3 7 40 3 5 23 8 2 52 6 Sample Output
 * 
 *         48 Hint
 * 
 *         OUTPUT DETAILS:
 * 
 *         From the bottom: 3 blocks of type 2, below 3 of type 1, below 6 of
 *         type 3. Stacking 4 blocks of type 2 and 3 of type 1 is not legal,
 *         since the top of the last type 1 block would exceed height 40.
 *
 */
public class SolutionDay08_P2392 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int K = in.nextInt();
		int[] H = new int[K];
		int[] A = new int[K];
		int[] C = new int[K];

		for (int i = 0; i < K; i++) {
			H[i] = in.nextInt();
			A[i] = in.nextInt();
			C[i] = in.nextInt();
		}
		System.out.println(solve(H, A, C));
		in.close();
	}

	public static int solve(int[] H, int[] A, int[] C) {
		int n = H.length;
		Pair[] p = new Pair[n];
		for (int i = 0; i < n; i++) {
			p[i] = new Pair(H[i], A[i], C[i]);
		}

		Arrays.sort(p, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.a - o2.a;
			}
		});

		int[][] dp = new int[2][40000 + 16];
		Queue[] q = new Queue[40000 + 16];

		int[] pos = { 0, 1 };
		for (int i = 0; i < n; i++) {
			swap(pos);
			int v = p[i].h; // 价值
			int w = p[i].h; // 重量
			int m = p[i].c; // 数量

			int V = p[i].a;
			
			// 划分成w份
			for (int mod = 0; mod < w; ++mod) {
				int l = 0, r = 0;
				//更新dp[mod],dp[mod+w],dp[mod+2w],dp[mod+3w],...,d[V]
				for (int j = 0; mod + j * w <= V; ++j) {
					// 维持单调队列的递减特性
					while (r > l && (dp[pos[1]][mod + j * w] - j * v) > q[r - 1].first) {
						// 将前面小的元素都挤出去
						r--;
					}
					// 入队
					q[r++] = new Queue(dp[pos[1]][mod + j * w] - j * v, j);
					if (r - l > 0 && j - q[l].second > m) {
						// 队列不为空，最优解对应的缺口无法填满，出队
						l++;
					}
					dp[pos[0]][mod + j * w] = q[l].first + j * v;
				}
			}
		}

		int max = 0;
		for (int i = 0; i < p[n - 1].a + 1; i++) {
			max = Math.max(max, dp[pos[0]][i]);
		}

		return max;
	}

	private static class Pair {
		int h;
		int a;
		int c;

		Pair(int h, int a, int c) {
			this.h = h;
			this.a = a;
			this.c = c;
		}
	}

	private static void swap(int[] pos) {
		int tmp = pos[0];
		pos[0] = pos[1];
		pos[1] = tmp;
	}

	private static class Queue {
		int first;
		int second;

		Queue(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	// public static int solve(int[] H, int[] A, int[] C) {
	// Pair[] p = new Pair[H.length];
	// for (int i = 0; i < H.length; i++) {
	// p[i] = new Pair(H[i], A[i], C[i]);
	// }
	//
	// Arrays.sort(p, new Comparator<Pair>() {
	// @Override
	// public int compare(Pair o1, Pair o2) {
	// return o1.a - o2.a;
	// }
	// });
	//
	// int[][] dp = new int[H.length][11];
	// for (int k = 1; k <= p[0].c; k++) {
	// dp[0][k] = k * p[0].h <= p[0].a ? k * p[0].h : 0;
	// }
	//
	// for (int i = 1; i < H.length; i++) {
	// for (int k = 1; k <= p[i].c; k++) {
	// for (int j = 1; j <= p[i - 1].c; j++) {
	// if (k * p[i].h + dp[i - 1][j] <= p[i].a) {
	// dp[i][k] = Math.max(k * p[i].h + dp[i - 1][j], dp[i][k]);
	// }
	// }
	// }
	// }
	//
	// int max = 0;
	// for (int k = 0; k <= p[H.length - 1].c; k++) {
	// max = Math.max(max, dp[H.length - 1][k]);
	// }
	//
	// return max;
	// }

	// public static int solve(int[] H, int[] A, int[] C) {
	// int n = H.length;
	// Pair[] p = new Pair[n];
	// for (int i = 0; i < n; i++) {
	// p[i] = new Pair(H[i], A[i], C[i]);
	// }
	//
	// Arrays.sort(p, new Comparator<Pair>() {
	// @Override
	// public int compare(Pair o1, Pair o2) {
	// return o1.a - o2.a;
	// }
	// });
	//
	// int[] dp = new int[40000 + 16];
	//
	// for (int i = 0; i < n; i++){
	// for (int k = p[i].c; k >= 1; k--){
	// for (int j = p[i].a; j >= p[i].h; j--){
	// dp[j] = Math.max(dp[j], dp[j-p[i].h] + p[i].h);
	// }
	// }
	// }
	//
	// int max = 0;
	// for (int j = p[n-1].a; j >= 0; j--){
	// max = Math.max(max, dp[j]);
	// }
	// return max;
	// }

	// public static int solve(int[] H, int[] A, int[] C){
	// int n = H.length;
	// Pair[] p = new Pair[n];
	// for (int i = 0; i < n; i++) {
	// p[i] = new Pair(H[i], A[i], C[i]);
	// }
	//
	// Arrays.sort(p, new Comparator<Pair>() {
	// @Override
	// public int compare(Pair o1, Pair o2) {
	// return o1.a - o2.a;
	// }
	// });
	//
	//
	// int[][] dp = new int[p[0].c][p[0].a+1];
	//
	// for (int j = p[0].h; j <= p[0].a; j++){
	// dp[0][j] = p[0].h;
	// }
	//
	// for (int i = 1; i < p[0].c; i++){
	// for (int j = 0; j <= p[0].a; j++){
	// if (j - p[0].h < 0){
	// dp[i][j] = dp[i-1][j];
	// }else{
	// dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-p[0].h] + p[0].h);
	// }
	// }
	// }
	//
	//
	// return 0;
	// }

}
