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

public class SolutionDay08_P1180 {
	
	String INPUT = "./data/judge/201709/P1180.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay08_P1180().run();
	}
	
	static final int MAX_N = 10000 + 16;
	static final int INF   = 0x3f3f3f3f;
	
	int N, S;
	int[] dp = new int[MAX_N];
	
	class Job{
		int t;
		int c;
		public Job(int t, int c) {
			this.t = t;
			this.c = c;
		}
	}
	
	Job[] jobs;
	
	void solve() {
		N = ni();
		S = ni();
		jobs = new Job[N];
		for (int i = 0; i < N; ++i) {
			jobs[i] = new Job(ni(), ni());
		}
		
		int[] C = new int[N + 1];
		for (int i = 0; i < N; ++i) {
			C[i + 1] = C[i] + jobs[i].c;
		}
		
		Arrays.fill(dp, INF);
		dp[N] = 0;
		for (int i = N - 1; i >= 0; --i) { // 未知 求已知
			int cc = C[N] - C[i];
			int tt = S;
			for (int j = 1; j + i <= N && j <= 200; ++j) {
				tt += jobs[i + j - 1].t;
				dp[i] = Math.min(dp[i], tt * cc + dp[j + i]); // 有个技巧 和 求解思路在里头 ，主要观察 tt的 结构易知
			}
		}
		
		out.println(dp[0]);
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

////这很难用记忆化搜索的方式来实现
//public int f(int i, int t) {
//	if (i >=  N) return 0;
//	
//	int cost = 0;
//	int time = t + S;
//	int res  = INF;
//	for (int j = i; j < N; ++j) {
//		cost += jobs[j].c;
//		time += jobs[j].t;
//		res  =  Math.min(res, f(j + 1, time) + cost * time);
//	}
//	return res;
//}

