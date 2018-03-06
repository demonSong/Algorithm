package com.daimens.algorithm.june;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay28_P2785 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/2785.txt";
	
	void solve() {
		int n = ni();
		int[] A = new int[n];
		int[] B = new int[n];
		int[] C = new int[n];
		int[] D = new int[n];
		for (int i = 0; i < n; ++i){
			A[i] = ni();
			B[i] = ni();
			C[i] = ni();
			D[i] = ni();
		}
		
		int[] arra = new int[n * n];
		for (int i = 0, k = 0; i < n; ++i){
			for (int j = 0; j < n; ++j){
				arra[k++] = C[i] + D[j];
			}
		}
		Arrays.sort(arra);
		
		long cnt = 0;
		for (int i = 0; i < n; ++i){
			for (int j = 0; j < n; ++j){
				int key = -(A[i] + B[j]);
				int lo = lowBound(arra, key);
				int hi = upBound(arra, key);
				if (lo == -1 || hi == -1) continue;
				cnt += (hi - lo + 1);
			}
		}
		out.println(cnt);
	}
	
	private int lowBound(int[] arra, int key){
		int lf = 0, rt = arra.length - 1;
		while (lf < rt){
			int mid = lf + (rt - lf) / 2;
			if (arra[mid] < key) lf = mid + 1;
			else rt = mid;
		}
		if (arra[rt] == key) return rt;
		return -1;
	}
	
	private int upBound(int[] arra, int key){
		int lf = 0, rt = arra.length - 1;
		while (lf < rt){
			int mid = lf + (rt - lf + 1) / 2;
			if (arra[mid] > key) rt = mid - 1;
			else lf = mid;
		}
		if (arra[lf] == key) return lf;
		return -1;
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
		new SolutionDay28_P2785().run();
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


