package com.daimens.algorithm.august;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionDay25_P1769 {
	String INPUT = "./data/judge/201708/P1769.txt";

	public static void main(String[] args) throws IOException {
		new SolutionDay25_P1769().run();
	}
	
	class Pair{
		int s;
		int e;
		public Pair(int s, int e){
			this.s = s;
			this.e = e;
		}
		
		@Override
		public String toString() {
			return s + " " + e;
		}
	}
	
	static final int MAX_N = 50000 + 16;
	static final int SIZE = (1 << 18) + 1;
	static final int INF = 1 << 29;
	
	int[] dat = new int[SIZE];
	
	int[] dp;
	
	void solve() {
		int N = ni();
		int M = ni();
		Pair[] ps = new Pair[M];
		for (int i = 0; i < M; ++i){
			ps[i] = new Pair(ni(), ni());
		}
		
		dp = new int[MAX_N];
		Arrays.fill(dp, INF);
		init(N);
		
		dp[1] = 0;
		update (1, 0);
		for (int i = 0; i < M; ++i){
			int s = ps[i].s;
			int e = ps[i].e;
			int min = Math.min(dp[e],query(0, s, e + 1, 0, n_) + 1);
			dp[e] = min;
			update(e, min);
		}
		
		out.println(dp[N]);
	}
	
	/*********************以下是RMQ的实现*********************/
	
	/**
	 * [L, r)
	 * @param k
	 * @param l
	 * @param r
	 */
	
	int n_;
	
	public void init(int N){
		n_ = 1;
		while (n_ < N) n_ *= 2;
		for (int i = 0; i < 2 * n_ - 1; ++i) dat[i] = INF;
	}
	
	public void update(int k, int val){
		k += (n_ - 1);
		dat[k] = val;
		while (k > 0){
			k = (k - 1) / 2;
			dat[k] = Math.min(dat[2 * k + 1], dat[k * 2 + 2]);
		}
	}
	
	public int query(int k, int i, int j, int l, int r){
		if (j <= l || i >= r) return INF;
		else if (i <= l && j >= r){
			return dat[k];
		}
		else{
			int lch = 2 * k + 1;
			int rch = 2 * k + 2;
			int mid = (l + r) / 2;
			int lf = query(lch, i, j, l, mid);
			int rt = query(rch, i, j, mid, r);
			return Math.min(lf, rt);
		}
	}
	
	

	FastScanner in;
	PrintWriter out;

	void run() throws IOException {
		boolean oj;
		try {
			oj = !System.getProperty("user.dir").equals("F:\\java_workspace\\leetcode");
		} catch (Exception e) {
			oj = System.getProperty("ONLINE_JUDGE") != null;
		}

		InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
		in = new FastScanner(is);
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		if (!oj) {
			System.out.println("[" + (System.currentTimeMillis() - s) + "ms]");
		}
	}

	public boolean more() {
		return in.hasNext();
	}

	public int ni() {
		return in.nextInt();
	}

	public long nl() {
		return in.nextLong();
	}

	public double nd() {
		return in.nextDouble();
	}

	public String ns() {
		return in.nextString();
	}

	public char nc() {
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

		public boolean hasNext() {
			next = nextToken();
			return hasNext;
		}

		public int nextInt() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return Integer.parseInt(more);
		}

		public long nextLong() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return Long.parseLong(more);
		}

		public double nextDouble() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return Double.parseDouble(more);
		}

		public String nextString() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return more;
		}

		public char nextChar() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return more.charAt(0);
		}
	}
}

//void solve() {
//int N = ni();
//int M = ni();
//Pair[] ps = new Pair[M];
//for (int i = 0; i < M; ++i){
//	ps[i] = new Pair(ni(), ni());
//}
//
//int[][] dp = new int[M + 16][N + 16];
//int j = 0;
//while (j < M && ps[j].s != 1) j++;
//
//for (int i = 0; i < dp.length; ++i) Arrays.fill(dp[i], INF);
//dp[j][1] = 1;
//
//for (int i = j + 1; i < M; ++i){
//	int e = ps[i - 1].e;
//	int s = ps[i - 1].s;
//	for (int l = 1; l <= N; ++l){
//		if (e != l){
//			dp[i][l] = dp[i - 1][l];
//		}
//		else{
//			dp[i][l] = dp[i - 1][l];
//			for (int k = s; k <= e; ++k){
//				dp[i][l] = Math.min(dp[i][l], dp[i - 1][k] + 1);
//			}
//		}
//	}
//}
//
//out.println(dp[M - 1][ps[M - 1].s]);
//}

//void solve() {
//int N = ni();
//int M = ni();
//Pair[] ps = new Pair[M];
//for (int i = 0; i < M; ++i){
//	ps[i] = new Pair(ni(), ni());
//}
//
//int[] dp = new int[N + 16];
//Arrays.fill(dp, INF);
//dp[1] = 0;
//
//for (int i = 0; i < M; ++i){
//	int s = ps[i].s;
//	int e = ps[i].e;
//	for (int j = 1; j <= N; ++j){
//		if (j >= s && j <= e){
//			dp[e] = Math.min(dp[e], dp[j] + 1);
//		}
//	}
//}
//
//out.println(dp[ps[M - 1].e]);
//}
