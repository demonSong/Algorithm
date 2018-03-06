package com.daimens.algorithm.july;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class SolutionDay25_P1018 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/1018.txt";
	
//	class Pair{
//		int B;
//		int P;
//		public Pair(int B, int P){
//			this.B = B;
//			this.P = P;
//		}
//		
//		public boolean cmp(Pair that){
//			double thatBP = that.B / (1.0 * that.P);
//			double thisBP = this.B / (1.0 * this.P);
//			return thisBP > thatBP;
//		}
//		
//		@Override
//		public String toString() {
//			return B + " " + P;
//		}
//	}
	
	class Pair{
		int B;
		int P;
		public Pair(int B, int P){
			this.B = B;
			this.P = P;
		}
	}
	
	void solve() {
		int T = ni();
		for (int i = 0; i < T; ++i){
			int N = ni();
			List<Pair>[] g = new ArrayList[N];
			for (int j = 0; j < N; ++j) g[j] = new ArrayList<Pair>();
			for (int j = 0; j < N; ++j){
				int m = ni();
				for (int k = 0; k < m; ++k){
					g[j].add(new Pair(ni(), ni()));
				}
			}
			
			double max = 0;
			out.printf("%.3f\n",max);
		}
	}
	
//	void solve() {
//		int T = ni();
//		for (int i = 0; i < T; ++i){
//			int N = ni();
//			
//			
//			Pair[][] dp = new Pair[N + 16][100 + 16];
//			for (int k = 0; k < dp.length; ++k){
//				for (int l = 0; l < dp[0].length; ++l){
//					dp[k][l] = new Pair(0, 1);
//				}
//			}
//			//阶段0
//			int m = ni();
//			for (int j = 0; j < m; ++j){
//				int b = ni();
//				int p = ni();
//				dp[0][j].B = b;
//				dp[0][j].P = p;
//			}
//			
//			//阶段1开始
//			int prev = m;
//			for (int j = 1; j < N; ++j){
//				m = ni();
//				for (int k = 0; k < m; ++k){
//					int b = ni();
//					int p = ni();
//					for (int l = 0; l < prev; ++l){
//						Pair tmp = new Pair(Math.min(dp[j-1][l].B, b),dp[j - 1][l].P + p);
//						if (tmp.cmp(dp[j][k])){
//							dp[j][k] = tmp;
//						}
//					}
//				}
//				prev = m;
//			}
//			
//			double max = 0;
//			for (int j = 0; j < prev; ++j){
//				max = Math.max(max, dp[N - 1][j].B / (1.0 * dp[N - 1][j].P));
//			}
//			out.printf("%.3f\n",max);
//		}
//	}
	
	void run() throws Exception {
		is = oj ? System.in : new FileInputStream(new File(INPUT));
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	public static void main(String[] args) throws Exception {
		new SolutionDay25_P1018().run();
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


