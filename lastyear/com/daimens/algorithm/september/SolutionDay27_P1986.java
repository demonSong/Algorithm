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

public class SolutionDay27_P1986 {
	
	String INPUT = "./data/judge/201709/P1986.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay27_P1986().run();
	}
	
	int N, M;
	int root;         // 选定树的任意一个根
	List<Edge>[] g;   // 树的邻接表
	
	int[] id;         // 记录每个结点最先访问的时间戳
	int[] vs;         // dfs访问顺序
	int[] dp;         // 访问过程中对应结点的深度
	
	void init(int n) {
		g = new ArrayList[n];
		for (int i = 0; i < n; ++i) g[i] = new ArrayList<Edge>();
		
		id = new int[n * 2];
		vs = new int[n * 2];
		dp = new int[n * 2];
	}
	
	void add(int from, int to, int cost) {
		g[from].add(new Edge(to, cost));
		g[to].add(new Edge(from, cost));
	}
	
	class Edge{
		int to;
		int cost;
		Edge(int to, int cost){
			this.to   = to;
			this.cost = cost;
		}
	}
	
	class RMQ {
		int n_;
		int[] dat;
		int[] data;
		
		
		RMQ (int N) {
			n_ = 1;
			while (n_ < N) n_ *= 2;
			dat = new int[2 * n_];
			for (int i = 0; i < 2 * n_ - 1; ++i) dat[k] = -1;
		}
		
		RMQ (int[] data, int N) {
			this(N);
			this.data = data;
			for (int i = 0; i < N; ++i) {
				update(i, i);
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
		
		int min(int i, int j) { // -1表示无穷大 
			if (i == -1 && j != -1) return j;
			if (j == -1 && i != -1) return i;
			if (j == -1 && i == -1) return -1;
			return data[i] < data[j] ? i : j;
		}
		
		int query(int k, int i, int j, int l, int r) {
			if (j <= l || i >= r) return -1;
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
	
	class BIT{
		int n;
		int[] BIT;

		BIT(int n){
			this.n = n;
			BIT = new int[n + 16];
		}
		
		void add(int i, int val) {
			while (i <= n) {
				BIT[i] += val;
				i += i & -i;
			}
		}
		
		long sum(int i) {
			long s = 0;
			while (i > 0) {
				s += BIT[i];
				i -= i & -i;
			}
			return s;
		}
	}
	
	BIT bit;
	RMQ rmq;
	void solve() {
		bit = new BIT(2 * N);
		k = 0;
		dfs(root, -1, 0);
		rmq = new RMQ(dp, 2 * N);
	}
	
	int k = 0;
	void dfs(int v, int p, int d) {
		id[v] = k;
		vs[k] = v;
		dp[k++] = d;
		for (Edge e : g[v]) {
			if (e.to != p) {
				bit.add(k, e.cost);
				dfs(e.to, v, d + 1);
				vs[k] = v;
				bit.add(k, -e.cost);
				dp[k++] = d;
			}
		}
	}
	
	int lca(int u, int v) {
		return vs[rmq.query(0, Math.min(id[u], id[v]), Math.max(id[u], id[v]) + 1, 0, rmq.n_)];
	}
	
	void read() {
		N = ni();
		M = ni();
		root = N / 2;
		
		init(N);
		
		for (int i = 0; i < M; ++i) {
			int from = ni();
			int to   = ni();
			int cost = ni();
			char r   = nc();
			from --;
			to   --;
			add(from, to, cost);
		}
		
		solve();
		
		int q = ni();
		for (int i = 0; i < q; ++i) {
			int u = ni();
			int v = ni();
			u --;
			v --;
			int p = lca(u, v);
			long ans = bit.sum(id[u]) + bit.sum(id[v]) - 2 * bit.sum(id[p]);
			out.println(ans);
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

