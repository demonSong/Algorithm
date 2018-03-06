package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay24_P3264 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P3264.txt";
	
	static final int B = 1000;
	static final int MAX_N = 50000 * 2;
	int[] max = new int[MAX_N / B + 1];
	int[] min = new int[MAX_N / B + 1];
	
	static final int INF = 1 << 29;
	void solve() {
		int N = ni();
		int Q = ni();
		
		int[] cows = new int[N];
		
		Arrays.fill(max, -INF);
		Arrays.fill(min, INF);
		
		for (int i = 0; i < N; ++i){
			cows[i] = ni();
			max[i / B] = Math.max(max[i / B], cows[i]);
			min[i / B] = Math.min(min[i / B], cows[i]);
		}
		
		for (int q = 0; q < Q; ++q){
			int i = ni();
			int j = ni();
			i--;
			// [i, j)
			int minHeight = INF;
			int maxHeight = -INF;
			
			int l = i, r = j;
			while (l < r && l % B != 0){
				minHeight = Math.min(minHeight, cows[l]);
				maxHeight = Math.max(maxHeight, cows[l++]);
			}
			
			while (l < r && r % B != 0){
				minHeight = Math.min(minHeight, cows[--r]);
				maxHeight = Math.max(maxHeight, cows[r]);
			}
			
			while (l < r){
				int b = l / B;
				minHeight = Math.min(minHeight, min[b]);
				maxHeight = Math.max(maxHeight, max[b]);
				l += B;
			}
			
			out.println(maxHeight - minHeight);
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
		new SolutionDay24_P3264().run();
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


