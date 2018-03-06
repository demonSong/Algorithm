package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay27_P3728 {
	
	String INPUT = "./data/judge/201709/P3728.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay27_P3728().run();
	}
	
	int N;
	int root;
	int[] prices;
	List<Integer>[] g;
	
	int[] id;
	int[] vs;
	int[] dp;
	int[] pr;            //DFS访问顺序上的价格
	int tot;             //访问时间戳
	
	
	class RMQ {
		
		static final int INF = 0x3f3f3f3f;
		
		int n_;
		int[] dat;
		int[] data;
		
		RMQ (int N){
			this.n_ = 1;
			while (n_ < N) n_ *= 2;
			dat = new int[2 * n_];
			for (int i = 0; i < 2 * n_ - 1; ++i) dat[i] = INF;
		}
		
		RMQ (int[] data, int N){
			this(N);
			this.data = data;
			for (int i = 0; i < N; ++i) {
				update (i, i);
			}
		}
		
		void update(int k, int i) {
			k += (n_ - 1);
			dat[k] = i;
			while (k > 0) {
				k = (k - 1) / 2;
				dat[k] = min(dat[2 * k + 1], dat[2 * k + 2]);
			}
		}
		
		int min(int i, int j) {
			if (i == INF || j == INF) return Math.min(i, j);
			return data[i] < data[j] ? i : j;
		}
		
		int query(int k, int i, int j, int l, int r) {
			if (j <= l || i >= r) return INF;
			else if (i <= l && j >= r) {
				return dat[k];
			}
			else {
				int lch = 2 * k + 1;
				int rch = 2 * k + 2;
				int mid = (l + r) / 2;
				int lf = query(lch, i, j, l, mid);
				int rt = query(rch, i, j, mid, r);
				return min(lf, rt);
			}
		}
	}
	
	void init(int n) {
		g = new List[n];
		prices = new int[n];
		for (int i = 0; i < n; ++i) g[i] = new ArrayList<Integer>();
		id = new int[2 * n];
		vs = new int[2 * n];
		pr = new int[2 * n];
		dp = new int[2 * n];
	}
	
	void add(int from, int to) {
		g[from].add(to);
		g[to].add(from);
	}
	
	void dfs(int v, int p, int d) {
		id[v] = tot;
		vs[tot] = v;
		dp[tot] = d;
		pr[tot++] = prices[v];
		for (int u : g[v]) {
			if (u != p) {
				dfs(u, v, d + 1);
				vs[tot] = v;
				dp[tot] = d;
				pr[tot++] = prices[v];
			}
		}
	}

	int lca(int u, int v) {
		return vs[rmq.query(0, Math.min(id[u], id[v]), Math.max(id[u], id[v]) + 1, 0, rmq.n_)];
	}
	
	RMQ rmq;
	void solve() {
		tot = 0;
		dfs(root, -1, 0);
		rmq = new RMQ(dp, 2 * N);
	}
	
	int maxProfit(int u, int v) {   // u -> v
		int lf = id[u];
		int rt = id[v];
		int p = lca(u, v);
		int mid = -1;
		for (int i = Math.min(lf, rt); i <= Math.max(rt, lf); ++i) {
			if (vs[i] == p) {
				mid = i;
				break;
			}
		}
		
		int max = 0;
		int min = 0x3f3f3f3f;
		
		for (int i = lf; i <= mid; ++i) {
			if (pr[i] >= min) {
				max = Math.max(max, pr[i] - min);
			}
			else {
				min = Math.min(min, pr[i]);
			}
		}
		
		for (int i = mid + 1; i <= rt; ++i) {
			if (pr[i] >= min) {
				max = Math.max(max, pr[i] - min);
			}
			else {
				min = Math.min(min, pr[i]);
			}
		}
		return max;
	}
	
	
	void read() {
		N = ni();
		root = N / 2;
		
		init(N);
		
		for (int i = 0; i < N; ++i) {
			prices[i] = ni();
		}
		
		for (int i = 0; i < N - 1; ++i) {
			int from = ni();
			int to   = ni();
			from --;
			to   --;
			add(from, to);
		}
		
		solve();
		
		int Q = ni();
		for (int i = 0; i < Q; ++i) {
			int from = ni();
			int to   = ni();
			from --;
			to   --;
			out.println(maxProfit(from, to));
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
		read();
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
	
	static class D{
		
		public static void pp(int[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
		}
		
		public static void pp(char[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
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
	
	static class Num{
		public static <K> void inc(Map<K, Integer> mem, K k) {
			if (!mem.containsKey(k)) mem.put(k, 0);
			mem.put(k, mem.get(k) + 1);
		}
	}
}

