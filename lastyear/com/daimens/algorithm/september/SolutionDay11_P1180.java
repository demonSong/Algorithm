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

public class SolutionDay11_P1180 {
	
	String INPUT = "./data/judge/201709/P1180.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay11_P1180().run();
	}
	
	static final int MAX_N = 10000 + 16;
	static final int INF   = 0x3f3f3f3f;
	
	int N, S;
	int[] T;
	int[] C;
	int[] time;
	int[] cost;
	int[] dp;
	int[] deq;
	
	void solve() {
		N = ni();
		S = ni();
		time = new int[N];
		cost = new int[N];
		for (int i = 0; i < N; ++i) {
			time[i] = ni();
			cost[i] = ni();
		}
		
		T = new int[N + 1];
		C = new int[N + 1];
		for (int i = 0; i < N; ++i) {
			T[i + 1] = T[i] + time[i];
			C[i + 1] = C[i] + cost[i];
		}
		
		dp = new int[MAX_N];
		deq = new int[MAX_N];
		Arrays.fill(dp, INF);

		int s = 0, t = 1;
		dp[N] = 0;
		deq[0] = N;
		for (int i = N - 1; i >= 0; --i) {
			while (t - s > 1 && f(i, deq[s]) >= f(i, deq[s + 1])) s++;
			dp[i] = f(i, deq[s]);
			while (t - s > 1 && check(deq[t - 2], deq[t - 1], i)) t--;
			deq[t++] = i;
		}
		
		out.println(dp[0]);
	}
	
	public int f(int i, int j) {
		return (C[N] - C[i]) * (S - T[i]) + dp[j] + C[N] * T[j] - C[i] * T[j];
	}
	
	public boolean check(int f1, int f2, int f3) {
		long a1 = -T[f1], b1 = dp[f1] + T[f1] * C[N];
		long a2 = -T[f2], b2 = dp[f2] + T[f2] * C[N];
		long a3 = -T[f3], b3 = dp[f3] + T[f3] * C[N];
		return (a2 - a1) * (b3 - b2) <= (b2 - b1) * (a3 - a2);
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

