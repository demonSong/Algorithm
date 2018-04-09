package com.daimens.algorithm.april;

import java.util.Scanner;

public class SolutionDay05_L0501 {
	
	static int mod = 1000000007;
	static int[] mem;
	public static int f(int a, int x, int b, int y, int sum) {
		if (mem[sum] > 0) return mem[sum];
		if (sum == a) {
			return x;
		}
		if (sum == b) {
			return y;
		}
		
		int ans = 0;
		for (int i = 1; i <= x; ++i) {
			int re = sum - i * a;
			if (re > 0 && re % b == 0 && re / b <= y) {
				ans = (f(a, x - i, b, y, re) * f(a, x, b, y - re / b, sum - re) % mod + ans) % mod;
			}
		}
		mem[sum] = ans;
		return ans;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int k = in.nextInt();
			int a = in.nextInt();
			int x = in.nextInt();
			int b = in.nextInt();
			int y = in.nextInt();
			mem = new int[2000];
			System.out.println(f(a, x, b, y, k));
		}
		in.close();
	}
}
