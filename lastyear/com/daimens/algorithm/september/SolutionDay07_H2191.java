package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SolutionDay07_H2191 {

	String INPUT = "./data/judge/201709/H2191.txt";
	
	static Scanner ii = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		new SolutionDay07_H2191().solve();
	}

	int[][] dp;
	int N, M;
	void solve() {
		while (ii.hasNext()) {
			int T = ii.nextInt();
			while (T-- > 0) {
				M = ii.nextInt();
				N = ii.nextInt();
				dp = new int[N + 16][M + 16];
				v = new int[N];
				w = new int[N];
				m = new int[N];
				
				for (int i = 0; i < N; ++i) {
					w[i] = ii.nextInt();
					v[i] = ii.nextInt();
					m[i] = ii.nextInt();
				}

//				for (int i = 0; i < N; ++i) {
//					for (int k = 1; k <= M; ++k) {
//						for (int j = 0; j <= m[i]; ++j) {
//							if (k - w[i] * j >= 0) {
//								dp[i + 1][k] = Math.max(dp[i + 1][k], dp[i][k - w[i] * j] + j * v[i]);
//							}
//						}
//					}
//				}

				System.out.println(dp_multiple_pack());
				
			}
		}
	}
	
	int[] v;
	int[] w;
	int[] m;
	
	static final int MAX_W = 100 + 16;
	int[] dd = new int[MAX_W];
	
	public int dp_multiple_pack() {
		for (int i = 0; i < N; ++i) {
			int num = m[i];
			for (int k = 1; num > 0; k <<= 1) {
				int mul = Math.min(num, k);
				for (int j = M; j >= mul * w[i]; --j) {
					dd[j] = Math.max(dd[j], dd[j - mul * w[i]] + mul * v[i]);
				}
				num -= mul;
			}
		}
		return dd[M];
	}

	FastScanner in;
	PrintWriter out;

	void run() throws IOException {
		boolean oj;
		try {
			oj = !System.getProperty("user.dir").equals("F:\\java_workspace\\leetcode");
		} catch (Exception e) {
			oj = true;
		}

		InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
		in = new FastScanner(is);
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		if (!oj) {
			System.out.println("[" + (System.currentTimeMillis() - s) + "ms]");
		}
	}

	public boolean more() {
		return in.hasNext();
	}

	public int ni() {
		return in.nextInt();
	}

	public long nl() {
		return in.nextLong();
	}

	public double nd() {
		return in.nextDouble();
	}

	public String ns() {
		return in.nextString();
	}

	public char nc() {
		return in.nextChar();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean hasNext;

		public FastScanner(InputStream is) throws IOException {
			br = new BufferedReader(new InputStreamReader(is));
			hasNext = true;
		}

		public String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					hasNext = false;
					return "##";
				}
			}
			return st.nextToken();
		}

		String next = null;

		public boolean hasNext() {
			next = nextToken();
			return hasNext;
		}

		public int nextInt() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return Integer.parseInt(more);
		}

		public long nextLong() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return Long.parseLong(more);
		}

		public double nextDouble() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return Double.parseDouble(more);
		}

		public String nextString() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return more;
		}

		public char nextChar() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return more.charAt(0);
		}
	}

	static class ArrayUtils {

		public static void fill(int[][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				Arrays.fill(f[i], value);
			}
		}

		public static void fill(int[][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}

		public static void fill(int[][][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}
	}
}
