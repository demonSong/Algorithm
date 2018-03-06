package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay22_P1083 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P1083.txt";
	
	class Pair implements Comparable<Pair>{
		int s;
		int e;
		public Pair(int s, int e){
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(Pair that) {
			return this.s == that.s ? this.e - that.e : this.s - that.s;
		}
	}
//	void solve() {
//		int t = ni();
//		while (t --> 0){
//			int n = ni();
//			Pair[] ps = new Pair[n];
//			for (int i = 0; i < n; ++i){
//				int s = ni();
//				int e = ni();
//				int sum = s + e;
//				s = Math.min(s, e);
//				e = sum - s;
//				ps[i] = new Pair(s, e);
//			}
//			Arrays.sort(ps);
//			int[] dp = new int[n + 16];
//			dp[0] = ps[0].e;
//			int max = 1;
//			int cnt = 1;
//			for (int i = 1; i < n; ++i){
//				if (ps[i].s > dp[i - 1] + (dp[i - 1] % 2 != 0 ? 1 : 0)){ // + (dp[i - 1] % 2 != 0 ? 1 : 0)
//					dp[i] = ps[i].e;
//					cnt = 1;
//				}
//				else{
//					cnt ++;
//					dp[i] = Math.min(dp[i - 1], ps[i].e);
//					max = Math.max(max, cnt);
//				}
//			}
//			out.println(max * 10);
//		}
//	}
	
	void solve() {
		int t = ni();
		while (t --> 0){
			int n = ni();
			
			int[] accu = new int[400 + 16];
			for (int i = 0; i < n; ++i){
				int s = ni();
				int e = ni();
				int sum = s + e;
				s = Math.min(s, e);
				e = sum - s;
				if ((s & 1) == 0) s--;
				if ((e & 1) != 0) e++;
				accu[s] ++;
				accu[e] --;
			}
			int max = 0;
			for (int i = 1; i < accu.length; ++i){
				accu[i] += accu[i - 1];
				max = Math.max(max, accu[i]);
			}
			out.println(max * 10);
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
		new SolutionDay22_P1083().run();
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


