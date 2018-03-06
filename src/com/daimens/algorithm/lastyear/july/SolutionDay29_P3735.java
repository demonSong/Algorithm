package com.daimens.algorithm.july;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Stack;

public class SolutionDay29_P3735 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/3735.txt";
	
	int N;
	void solve() {
		while (true){
			N = ni();
			int M = ni();
			int K = ni();
			if (N + M + K == 0) break;
			Stack<Mat> stack = new Stack<Mat>();
			for (int i = 0; i < K; ++i){
				char c = nc();
				if (c == 'g'){
					stack.push(createMat(c, ni() - 1, 0));
				}
				else if (c == 's'){
					stack.push(createMat(c, ni() - 1, ni() - 1));
				}
				else{
					stack.push(createMat(c, ni() - 1, 0));
				}
			}
			
			long[][] one = new long[N + 1][N + 1];
			for (int i = 0; i < N + 1; ++i) one[i][i] = 1;
			Mat A = new Mat(one);
			while (!stack.isEmpty()){
				A = mul(A, stack.pop());
			}
			A = pow(A, M);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; ++i){
				sb.append(" " + A.mat[i][N]);
			}
			out.println(sb.deleteCharAt(0).toString());
		}
	}
	
	public Mat createMat(char command, int i, int j){
		long[][] one = new long[N + 1][N + 1];
		for (int l = 0; l < one.length; ++l) one[l][l] = 1;
		switch (command) {
		case 'g':
			one[i][N] = 1;
			break;
		case 's':
			one[i][i] = 0;
			one[j][j] = 0;
			one[i][j] = 1;
			one[j][i] = 1;
			break;
		case 'e':
			one[i][i] = 0;
			break;
		default:
			break;
		}
		return new Mat(one);
	}
	
	class Mat{
		long[][] mat;
		int n;
		int m;
		public Mat(long[][] mat){
			this.mat = mat;
			this.n = mat.length;
			this.m = mat[0].length;
		}
	}
	
	public Mat mul(Mat A, Mat B){
		long[][] a = A.mat;
		long[][] b = B.mat;
		long[][] res = new long[A.n][B.m];
		for (int i = 0; i < A.n; ++i){
			for (int ll = 0; ll < A.m; ++ll){
				if (a[i][ll] != 0){
					for (int j = 0; j < B.m; ++j){
						res[i][j] += a[i][ll] * b[ll][j];
					}
				}
			}
		}
		return new Mat(res);
	}
	
	public Mat pow(Mat A, int n){
		long[][] one = new long[A.n][A.n];
		for (int i = 0; i < A.n; ++i) one[i][i] = 1;
		Mat res = new Mat(one);
		while (n > 0){
			if ((n & 1) != 0){
				res = mul(res, A);
			}
			n >>= 1;
			A = mul(A, A);
		}
		return res;
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
		new SolutionDay29_P3735().run();
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