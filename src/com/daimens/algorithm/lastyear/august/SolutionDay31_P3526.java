package com.daimens.algorithm.august;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay31_P3526 {

	String INPUT = "./data/judge/201708/P3526.txt";

	public static void main(String[] args) throws IOException {
		new SolutionDay31_P3526().run();
	}

	static final double EPS = 1E-8;
	static final int MAX_N = 20 + 8;

	int[][] matrix = new int[MAX_N][MAX_N];
	int M, N, SIZE;

	void solve() {

		int[][] C = new int[MAX_N][MAX_N];
		C[0][0] = 1;
		for (int i = 1; i < MAX_N; ++i) {
			C[i][0] = 1;
			C[i][i] = 1;
			for (int j = 1; j < i; ++j) {
				C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
			}
		}

		while (true) {
			int a = ni();
			int m = ni();
			int b = ni();
			int n = ni();
			M = m;
			N = n;
			if (a + m + b + n == 0)
				break;

			SIZE = n * m + 1;
			double[][] matrix = new double[SIZE][SIZE + 1];
			matrix[0][0] = matrix[0][SIZE] = 1;
			for (int i = 0; i < SIZE; ++i) {
				for (int j = 0; j <= i; ++j) {
					matrix[to_index(j % m, (i - j) % n)][i < n * m ? i + 1 : 0] += C[i][j] * Math.pow((double) a, j / m)
							* Math.pow((double) b, (i - j) / n);
				}
			}

			gaussian(matrix);

			StringBuilder sb = new StringBuilder();
			sb.append("1");
			for (int i = SIZE - 1; i >= 1; --i) {
				sb.append(" " + (int)(round(ans[i])));
			}
			out.println(sb.toString());
		}
	}

	double round(double x)
	{
		return x >= 0.0 ? Math.floor(x + 0.5) : Math.ceil(x - 0.5);
	}

	double[] ans;

	public boolean gaussian(double[][] matrix) {
		ans = new double[SIZE];
		for (int i = 0; i < SIZE; ++i) {
			int row = i;
			for (int j = i; j < SIZE; ++j) {
				if (Math.abs(matrix[j][i]) > Math.abs(matrix[row][i])) {
					row = j;
				}
			}

			swap(matrix, i, row);

			if (Math.abs(matrix[i][i]) <= EPS)
				return false;

			for (int j = i + 1; j < SIZE + 1; ++j) matrix[i][j] /= matrix[i][i];
			matrix[i][i] = 1;

			for (int j = 0; j < SIZE; ++j) {
				if (i != j) {
					for (int k = i + 1; k < SIZE + 1; ++k) {
						matrix[j][k] -= (matrix[j][i] * matrix[i][k]);
					}
					matrix[j][i] = 0.0;
				}
			}
		}

		for (int i = 0; i < SIZE; ++i) {
			ans[i] = matrix[i][SIZE];
		}
		return true;
	}

	public void swap(double[][] matrix, int i, int j) {
		int n = matrix[0].length;
		for (int col = 0; col < n; ++col) {
			double tmp = matrix[i][col];
			matrix[i][col] = matrix[j][col];
			matrix[j][col] = tmp;
		}
	}

	public int to_index(int i, int j) {
		return i * N + j + 1;
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
}
