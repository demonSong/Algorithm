package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionDay07_P3260 {
	
	String INPUT = "./data/judge/201709/P3260.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay07_P3260().run();
	}
	
	static final int MAX_T = 10000 + 4;
	static final int MAX_N = 100 + 2;
	static final int MAX_V = 120 + 1;
	static final int INF   = 1 << 29;
	
	int N, T;
	int[] V = new int[MAX_N];
	int[] C = new int[MAX_N];
	int max_v;
	int[] dp_change = new int[MAX_T + MAX_V * MAX_V];
	int[] dp_pay    = new int[MAX_T + MAX_V * MAX_V];
	
	//完全背包
	void dp_complete_pack(int n, int W) {
		Arrays.fill(dp_change, INF);
		dp_change[0] = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = V[i]; j <= W; ++j) {
				dp_change[j] = Math.min(dp_change[j], dp_change[j - V[i]] + 1);
			}
		}
	}
	
	//多重背包转二进制
	void dp_multiple_pack(int n, int W) {
		Arrays.fill(dp_pay, INF);
		dp_pay[0] = 0;
		for (int i = 0; i < n; ++i) {
			int num = C[i];
			for (int k = 1; num > 0; k <<= 1) {
				int mul = Math.min(k, num);
				for (int j = W; j >= mul * V[i]; --j) {
					dp_pay[j] = Math.min(dp_pay[j], dp_pay[j - mul * V[i]] + mul);
				}
				num -= mul;
			}
		}
	}
	
	
	
	void solve() {
		N = ni();
		T = ni();
		
		for (int i = 0; i < N; ++i) {
			V[i] = ni();
			max_v = Math.max(max_v, V[i]);
		}
		
		int sum = 0;
		for (int i = 0; i < N; ++i) {
			C[i] = ni();
			sum += V[i] * C[i];
		}
		
		int min = INF;
		
		max_v = Math.min(max_v * max_v, sum - T);
		
		dp_multiple_pack(N, T + max_v);
		dp_complete_pack(N, T + max_v);
		
		for (int i = max_v; i >= 0; --i) {
			min = Math.min(min, dp_change[i] + dp_pay[i + T]);
		}
		
		if (min == INF) {
			out.println("-1");
		}
		else {
			out.println(min);
		}
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
	
	static class ArrayUtils {

		public static void fill(int[][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				Arrays.fill(f[i], value);
			}
		}
		
		public static void fill(int[][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}
		
		public static void fill(int[][][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}
	}
}

