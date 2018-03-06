package com.daimens.algorithm.june;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay28_P3276 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/3276.txt";
	
//	void solve() {
//		int N = ni();
//		char[] cow = new char[N];
//		for (int i = 0; i < N; ++i) cow[i] = nc();
//		int k = N;
//		int min = 1 << 30;
//		int minK = k;
//		for (int i = k; i >= 1; --i){
//			int cnt = 0;
//			char[] clone = Arrays.copyOf(cow, N);
//			
//			for (int j = 0; j < N - i + 1; ++j){
//				if(clone[j] == 'F' ) continue;
//				for (int l = j; l < i + j; ++l){
//					clone[l] = clone[l] == 'F' ? 'B' : 'F';
//				}
//				cnt++;
//			}
//			boolean isValid = true;
//			for (int j = N - i + 1; j < N; ++j){
//				if(clone[j] == 'B'){
//					isValid = false;
//					break;
//				}
//			}
//			if (isValid && cnt != 0 && cnt < min){
//				min = cnt;
//				minK = i;
//			}
//		}
//		
//		out.println(minK + " " + min);
//	}
	
//	void solve() {
//		int N = ni();
//		char[] cow = new char[N];
//		for (int i = 0; i < N; ++i) cow[i] = nc();
//		int[] f = new int[N];
//		int min = 1 << 30;
//		int minK = N;
//		for (int k = N; k >= 1; --k){
//			f = new int[N];
//			for (int i = 0; i < N - k + 1; ++i){
//				int sum = 0;
//				for (int j = Math.max(0, i - k + 1); j < i; ++j){
//					sum += f[j];
//				}
//				if ((sum & 1) != 0 && cow[i] == 'F') f[i] = 1;
//				else if ((sum % 2) == 0 && cow[i] == 'B') f[i] = 1;
// 			}
//			boolean isValid = true;
//			for (int i = N - k + 1; i < N; ++i){
//				int sum = 0;
//				for (int j = Math.max(0, i - k + 1); j < i; ++j){
//					sum += f[j];
//				}
//				if ((sum & 1) != 0 && cow[i] == 'F') isValid = false;
//				else if ((sum % 2) == 0 && cow[i] == 'B') isValid = false;
//			}
//			
//			if(isValid){
//				int cnt = 0;
//				for (int i = 0; i < N; ++i){
//					cnt += f[i];
//				}
//				if (cnt != 0 && cnt < min){
//					min = cnt;
//					minK = k;
//				}
//			}
//		}
//		out.println(minK + " " + min);
//	}
	
	void solve() {
		int N = ni();
		char[] cow = new char[N];
		for (int i = 0; i < N; ++i) cow[i] = nc();
		int[] f = new int[N];
		int min = 1 << 30;
		int minK = N;
		for (int k = N; k >= 1; --k){
			f = new int[N];
			int sum = 0;
			for (int i = 0; i < N - k + 1; ++i){
				if ((sum & 1) != 0 && cow[i] == 'F') f[i] = 1;
				else if ((sum % 2) == 0 && cow[i] == 'B') f[i] = 1;
				sum += f[i];
				if (i - k + 1 >= 0){
					sum -= f[i - k + 1];
				}
 			}
			boolean isValid = true;
			for (int i = N - k + 1; i < N; ++i){
				if ((sum & 1) != 0 && cow[i] == 'F') isValid = false;
				else if ((sum % 2) == 0 && cow[i] == 'B') isValid = false;
				if (i - k + 1 >= 0){
					sum -= f[i - k + 1];
				}
			}
			
			if(isValid){
				int cnt = 0;
				for (int i = 0; i < N; ++i){
					cnt += f[i];
				}
				if (cnt != 0 && cnt < min){
					min = cnt;
					minK = k;
				}
			}
		}
		out.println(minK + " " + min);
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
		new SolutionDay28_P3276().run();
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