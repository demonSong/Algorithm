package com.daimens.algorithm.july;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay29_P3420 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/3420.txt";
	
	void solve() {
		while (true){
			int N = ni();
			int M = ni();
			if (N + M == 0) break;
			int[][] a = {{1,1,1,1,0},{1,0,0,0,0},{1,0,0,0,1},{2,0,0,1,0},{0,0,1,0,0}};
			Mat A = new Mat(a);
			A = A.pow(A, N, M);
			out.println(A.mat[0][0]);
		}
	}
	
	class Mat{
		int[][] mat;
		int n;
		int m;
		
		public Mat(int[][] mat){
			this.mat = mat;
			this.n = mat.length;
			this.m = mat[0].length;
		}
		
		public Mat mul(Mat A, Mat B, int MOD){
			int[][] a = A.mat;
			int[][] b = B.mat;
			int[][] res = new int[A.n][B.m];
			for (int i = 0; i < A.n; ++i){
				for (int j = 0; j < B.m; ++j){
					for (int ll = 0; ll < A.m; ++ll){
						res[i][j] = (res[i][j] + a[i][ll] * b[ll][j]) % MOD;
					}
				}
			}
			return new Mat(res);
		}
		
		public Mat pow(Mat A, int n, int MOD){
			int[][] one = new int[A.n][A.n];
			for (int i = 0; i < A.n; ++i) one[i][i] = 1;
			Mat res = new Mat(one);
			while (n > 0){
				if ((n & 1) != 0){
					res = mul(res, A, MOD);
				}
				n >>= 1;
				A = mul(A, A, MOD);
			}
			return res;
		}
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
		new SolutionDay29_P3420().run();
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
