package com.daimens.algorithm.july;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class SolutionDay25_P2441 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/2441.txt";
	
	void solve() {
		int N = ni();
		int M = ni();
		List<Integer>[] g = new ArrayList[N];
		for (int i = 0; i < N; ++i) g[i] = new ArrayList<Integer>();
		
		for (int i = 0; i < N; ++i){
			int m = ni();
			for (int j = 0; j < m; ++j){
				g[i].add(ni() - 1);
			}
		}
		
		int[] dp = new int[1 << M];
		
		for (int u : g[0]){
			dp[0 | 1 << u] = 1;
		}
		
		for (int i = 1; i < N; ++i){
			for (int comb = (1 << i) - 1, x, y; comb < 1 << M; x = comb & -comb, y = comb + x, comb = ((comb & ~y) / x >> 1) | y){
				if (dp[comb] != 0){
					for (int j : g[i]){
						if ((comb & 1 << j) == 0){
							dp[comb | (1 << j)] += dp[comb];
						}
					}
				}
			}
		}
		
		int sum = 0;
		for (int comb = (1 << N) - 1, x, y; comb < 1 << M; x = comb & -comb, y = comb + x, comb = ((comb & ~y) / x >> 1) | y){
			sum += dp[comb];
		}
		out.println(sum);
	}
	
	
	
	void run() throws Exception {
		is = oj ? System.in : new FileInputStream(new File(INPUT));
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	public static void main(String[] args) throws Exception {
		new SolutionDay25_P2441().run();
	}

	private byte[] inbuf = new byte[1024];
	public int lenbuf = 0, ptrbuf = 0;

	private int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	private boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	private int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	private double nd() {
		return Double.parseDouble(ns());
	}

	private char nc() {
		return (char) skip();
	}

	private String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != '
									// ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	private char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	private char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	private int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	private void tr(Object... o) {
		if (!oj)
			System.out.println(Arrays.deepToString(o));
	}
}

//void solve() {
//int N = ni();
//int M = ni();
//List<Integer>[] g = new ArrayList[N];
//for (int i = 0; i < N; ++i) g[i] = new ArrayList<Integer>();
//
//for (int i = 0; i < N; ++i){
//	int m = ni();
//	for (int j = 0; j < m; ++j){
//		g[i].add(ni() - 1);
//	}
//}
//
//int[][] dp = new int[2][1 << M];
////阶段1
//for (int barn : g[0]){
//	dp[0][0 | (1 << barn)] = 1;
//}
//
//for (int i = 1; i < N; ++i){
//	for (int barn : g[i]){
//		for (int s = 0; s < (1 << M); ++s){
//			if ((s >> barn & 1) == 0){
//				dp[i % 2][s | (1 << barn)] += dp[(i - 1) % 2][s];
//			}
//		}
//	}
//	Arrays.fill(dp[(i - 1) % 2], 0);
//}
//
//int sum = 0;
//for (int s = 0; s < (1 << M); ++s){
//	sum += dp[(N - 1) % 2][s];
//}
//
//out.println(sum);
//}



