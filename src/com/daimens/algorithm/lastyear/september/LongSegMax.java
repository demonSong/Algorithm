package com.daimens.algorithm.september;

import java.util.Arrays;

public class LongSegMax {
	public int N;
	public long[] ls, mul, add;
	
	public LongSegMax(int n) {
		N = Integer.highestOneBit(n) << 1;
		ls = new long[N * 2];
		Arrays.fill(ls, Long.MIN_VALUE);
		mul = new long[N * 2];
		add = new long[N * 2];
		Arrays.fill(mul, 1);
	}
	
	int s, t;
	long m, a;
	
	public void update(int s, int t, long m, long a) {
		this.s = s;
		this.t = t;
		this.m = m;
		this.a = a;
		update(1, 0, N , 1, 0);
	}
	
	public void update(int o, int L, int R, long m, long a) {
		if (s <= L && R <= t) {
			m = this.m * m;
			a = this.m * a + this.a;
		}
		mul[o] = m * mul[o];
		add[o] = m * add[o] + a;
		if (t <= L || R <= s || s <= L && R <= t) {
			ls[o] = m * ls[o] + a;
		}
		else {
			int M = (L + R) / 2;
			update(o * 2, L, M, mul[o], add[o]);
			update(o * 2 + 1, M, R, mul[o], add[o]);
			
			mul[o] = 1;
			add[o] = 0;
			ls[o] = Math.max(ls[o * 2], ls[o * 2 + 1]);
		}
	}
	
	
	public static void main(String[] args) {
		LongSegMax seg = new LongSegMax(8);
		seg.update(0, 8, 0, 0);
		seg.update(0, 1, 1, 1);
		System.out.println(seg.ls[1]);
		seg.update(0, 2, 1, 1);
		System.out.println(seg.ls[1]);
		seg.update(0, 3, 1, 1);
		System.out.println(seg.ls[1]);
		seg.update(0, 4, 1, 1);
		System.out.println(seg.ls[1]);
	}
}
