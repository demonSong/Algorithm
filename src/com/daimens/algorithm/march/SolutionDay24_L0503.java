package com.daimens.algorithm.march;

import java.util.Arrays;
import java.util.Scanner;

public class SolutionDay24_L0503 {
	
	static int best = 0;
	
	public static void dfs(int[] height, int k, int h, int now) {
		if (k == 0) {
			best = Math.max(best, now);
			return;
		}
		for (int i = 0; i < height.length; ++i) {
			if (Math.abs(height[i] - now) <= h) {
				int next = now + (height[i] - now) * 2;
				if (next < 0) continue;
				dfs(height, k - 1, h, next);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int n = in.nextInt();
			int k = in.nextInt();
			int h = in.nextInt();
			int[] height = new int[n];
			for (int i = 0; i < n; ++i) {
				height[i] = in.nextInt();
			}
			best = 0;
			Arrays.sort(height);
			dfs(height, k, h, 0);
			System.out.println(best);
		}
		in.close();
	}
}
