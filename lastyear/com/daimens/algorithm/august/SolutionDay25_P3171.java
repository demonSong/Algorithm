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

public class SolutionDay25_P3171 {
	
	String INPUT = "./data/judge/201708/P3171.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay25_P3171().run();
	}
	
	static final int INF = 1 << 29;
	
	class Cow implements Comparable<Cow>{
		int s;
		int e;
		int c;
		public Cow(int s, int e, int c){
			this.s = s;
			this.e = e;
			this.c = c;
		}
		@Override
		public int compareTo(Cow that) {
			return this.e == that.e ? this.s - that.s : this.e - that.e;
		}
	}
	
	void solve() {
		int N = ni();
		int M = ni();
		int E = ni();
		
		Cow[] cows = new Cow[N];
		for (int i = 0; i < N; ++i){
			cows[i] = new Cow(ni(), ni(), ni());
		}
		
		int[] dp = new int[E + 16];
		Arrays.fill(dp, INF);
		dp[M] = 0;
		
		init(E);
		Arrays.sort(cows);
		
		update(M, 0);
		
		for (int i = 0; i < N; ++i){
			int s = cows[i].s;
			int e = cows[i].e;
			s = s == 0 ? 0 : s - 1;
			int min = Math.min(dp[e], query(0, s, e, 0, n_) + cows[i].c);
			dp[e] = min;
			update(e, min);
		}
		
		out.println(dp[E] >= INF ? -1 : dp[E]);
	}
	
	/*****************RMQ*******************/
	
	static final int MAX_N = (1 << 18) - 1;
	int n_;
	
	int[] dat = new int[MAX_N];
	
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
			dat[k] = Math.min(dat[2 * k + 1], dat[2 * k + 2]);
		}
	}
	
	public int query(int k, int i, int j, int l, int r){
		if (j <= l || i >= r) return INF;
		else if (i <= l && j >= r) return dat[k];
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
