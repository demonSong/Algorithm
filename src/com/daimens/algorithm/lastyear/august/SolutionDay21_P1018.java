package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class SolutionDay21_P1018 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P1018.txt";
	
	class Pair{
		int B;
		int P;
		public Pair(int B, int P){
			this.B = B;
			this.P = P;
		}
	}
	void solve() {
		int t = ni();
		while (t --> 0){
			int n = ni();
			List<Pair>[] ps = new ArrayList[n];
			for (int i = 0; i < n; ++i) ps[i] = new ArrayList<Pair>();
			int max = 0;
			int min = 1 << 29;
			for (int i = 0; i < n; ++i){
				int m = ni();
				for (int j = 0; j < m; ++j){
					int B = ni();
					min = Math.min(min, B);
					max = Math.max(max, B);
					int P = ni();
					ps[i].add(new Pair(B, P));
				}
			}
			int[] dp = new int[max + 16];
			for (int i = 0; i < n; ++i){	
				for (Pair p : ps[i]){
					dp[p.B] ++;
				}
			}
			
			double ans = 0;
			for (int i = min; i <= max; ++i){
				if (dp[i] != 0){
					long sum = 0;
					for (int j = 0; j < n; ++j){
						int money = 1 << 29;
						for (Pair p : ps[j]){
							if (p.B >= i){
								money = Math.min(money, p.P);
							}
						}
						sum += money;
					}
					if (sum < 1 << 29)
						ans = Math.max(ans, i / (1.0 * sum));
				}
			}
			
			out.printf("%.3f\n", ans);
			
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
		new SolutionDay21_P1018().run();
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


