package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay05_C428B {
	
	String INPUT = "./data/judge/201709/C428B.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay05_C428B().run();
	}
	
	void solve() {
		int n = ni();
		int k = ni();
		int m = ni();
		long[] arra = new long[k + 1];
		int sum = 0;
		for (int i = 0; i < k; ++i) {
			arra[i] = nl();
			sum += arra[i];
		}
		
		arra[k] = sum;
		long max = 0;
		
		long[][] dp = new long[m + 16][n + 1];
		for (int i = 0; i < k; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (arra[i] * j < dp.length) {
					dp[(int) (arra[i] * j)][j] = j;
					max = Math.max(max, j);
				}
			}
		}
		
		for (int j = 1; j <= n; ++j) {
			if (arra[k] * j < dp.length) {
				dp[(int)(arra[k] * j)][j] = j * (k + 1);
				max = Math.max(max, j * (k + 1));
			}
		}
		
		
		for (int i = 1; i < k; ++i) {
			for (int j = m; j >= arra[i]; --j) {
				for (int l = 1; l <= n; ++l) {
					for (int h = 1; h <= n; ++h) {
						if ((j - l * arra[i] >= 0 && dp[(int) (j - l * arra[i])][h] != 0)) {
							dp[j][l] = Math.max(dp[j][l], 1 * l + dp[(int) (j - l * arra[i])][h]);
							max = Math.max(max, dp[j][l]);
						}
					}
				}
			}
		}
		
		for (int j = m; j >= arra[k]; --j) {
			for (int l = 1; l <= n; ++l) {
				if ((j - l * arra[k] >= 0 && dp[(int) (j - l * arra[k])][n - l] != 0)) {
					dp[j][l] = Math.max(dp[j][l], (k + 1) * l + dp[(int) (j - l * arra[k])][n - l]);
					max = Math.max(max, dp[j][l]);
				}
			}
		}
		
		out.println(max);
	}

	FastScanner in;
	PrintWriter out;
	
	void run() throws IOException {
		boolean oj;
		try {
			oj = ! System.getProperty("user.dir").equals("F:\\java_workspace\\leetcode");
		} catch (Exception e) {
			oj = System.getProperty("ONLINE_JUDGE") != null;
		}
		
		InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
		in = new FastScanner(is);
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		if (!oj){
			System.out.println("[" + (System.currentTimeMillis() - s) + "ms]");
		}
	}
	
	public boolean more(){
		return in.hasNext();
	}
	
	public int ni(){
		return in.nextInt();
	}
	
	public long nl(){
		return in.nextLong();
	}
	
	public double nd(){
		return in.nextDouble();
	}
	
	public String ns(){
		return in.nextString();
	}
	
	public char nc(){
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
		public boolean hasNext(){
			next = nextToken();
			return hasNext;
		}

		public int nextInt() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Integer.parseInt(more);
		}

		public long nextLong() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Long.parseLong(more);
		}

		public double nextDouble() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Double.parseDouble(more);
		}
		
		public String nextString(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more;
		}
		
		public char nextChar(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more.charAt(0);
		}
	}
}
