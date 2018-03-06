package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay24_P3729 {

	String INPUT = "./data/judge/201709/P3729.txt";

	public static void main(String[] args) throws IOException {
		new SolutionDay24_P3729().run();
	}

	class SuffixArray {
		int k = 1;
		int n;
		Integer[] sa;
		int[] rank, tmp;
		int[] lcp;

		SuffixArray(int[] arra) {
			n = arra.length;
			sa = new Integer[n + 1];
			rank = new int[n + 1];
			tmp = new int[n + 1];
			lcp = new int[n];

			for (int i = 0; i <= n; ++i) {
				sa[i] = i;
				rank[i] = i < n ? arra[i] : -1;
			}

			for (k = 1; k <= n; k <<= 1) {
				Arrays.sort(sa, cmp);
				tmp[sa[0]] = 0;
				for (int i = 1; i <= n; ++i) {
					tmp[sa[i]] = tmp[sa[i - 1]] + (cmp.compare(sa[i - 1], sa[i]) < 0 ? 1 : 0);
				}
				for (int i = 0; i <= n; ++i) {
					rank[i] = tmp[i];
				}
			}

			// lcp
			int h = 0;
			for (int i = 0; i <= n; ++i)
				rank[sa[i]] = i;
			for (int i = 0; i < n; ++i) {
				int j = sa[rank[i] - 1];
				if (h > 0)
					h--;
				for (; i + h < n && j + h < n; ++h) {
					if (arra[i + h] != arra[j + h])
						break;
				}
				lcp[rank[i] - 1] = h;
			}
		}

		Comparator<Integer> cmp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int i = o1;
				int j = o2;
				if (rank[i] != rank[j])
					return rank[i] - rank[j];
				else {
					int ri = i + k <= n ? rank[i + k] : -1;
					int rj = j + k <= n ? rank[j + k] : -1;
					return ri - rj;
				}
			}
		};

	}

	void read() {
		while (more()) {
			int m = ni();
			int n = ni();
			int k = ni();
			int[] arra = new int[m + n + 1];
			for (int i = 0; i < m; ++i) {
				arra[i] = ni();
				arra[i] ++;
			}
			arra[m] = 10000 + 8;
			for (int i = 0; i < n; ++i) {
				arra[i + m + 1] = ni();
				arra[i + m + 1] ++;
			}
			
			SuffixArray sa = new SuffixArray(arra);
			out.println(solve(k, m, sa) - solve(k + 1, m, sa));
		}
	}

	int solve(int k, int m, SuffixArray sa) {
		int ans = 0, A = 0, B = 0;
		for (int i = 0; i < sa.n; i++) {// 统计lcp[i]>=k的连续区间内的A子串
			if (sa.lcp[i] < k) {
				if (B > 0)
					ans += A;
				A = 0;
				B = 0;
			}
			if (sa.sa[i + 1] < m)
				A++;
			else
				B++;
		}
		return ans;
	}

	FastScanner in;
	PrintWriter out;

	void run() throws IOException {
		boolean oj;
		try {
			oj = !System.getProperty("user.dir").equals("F:\\java_workspace\\leetcode");
		} catch (Exception e) {
			oj = System.getProperty("ONLINE_JUDGE") != null;
		}

		InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
		in = new FastScanner(is);
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		read();
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

	static class D {

		public static void pp(int[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
		}

		public static void pp(char[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
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

	static class Num {
		public static <K> void inc(Map<K, Integer> mem, K k) {
			if (!mem.containsKey(k))
				mem.put(k, 0);
			mem.put(k, mem.get(k) + 1);
		}
	}
}
