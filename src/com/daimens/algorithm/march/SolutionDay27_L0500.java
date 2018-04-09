package com.daimens.algorithm.march;

import java.util.Arrays;
import java.util.Scanner;

public class SolutionDay27_L0500 {
	
	static int sum;
	
	public static void dfs(int[] goods, int pos, int v, int w) {
		if (v > w) return;
		if (pos == goods.length) {
			if (v <= w) sum += 1;
		}
		else {
			dfs(goods, pos + 1, v + goods[pos], w);
			dfs(goods, pos + 1, v, w);
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int w = in.nextInt();
			int[] goods = new int[n];
			for (int i = 0; i < n; ++i) goods[i] = in.nextInt();
			dfs(goods, 0, 0, w);
			System.out.println(sum);
		}
		int[][] arra = {{12, 30},{11, 40}};
		Arrays.sort(arra, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
	
		in.close();
	}
	
	

}
