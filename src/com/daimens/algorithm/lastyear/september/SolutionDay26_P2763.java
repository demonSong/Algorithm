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

public class SolutionDay26_P2763 {
	
	String INPUT = "./data/judge/201709/P2763.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay26_P2763().run();
	}
	
	static final int MAX_V = 100001 + 16;
	int N, Q, S;
	int root;
	int[] w;
	List<Edge>[] g;
	
	class Edge {
		int id;
		int to;
		int cost;
		Edge(int id, int to, int cost){
			this.id   = id;
			this.to   = to;
			this.cost = cost;
		}
	}
	
	class BIT {
		static final int MAX_N  = MAX_V * 2 + 16;
		int[] BIT;
		int n;
		
		BIT(int n){
			this.n = n;
			BIT = new int[MAX_N];
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
	
	class RMQ {
		
		int n_;
		int[] dat;
		int[] data;
		
		RMQ(int N){
			this.n_ = 1;
			while (n_ < N) n_ *= 2;
			dat = new int[2 * n_];
			for (int i = 0; i < 2 * n_ - 1; ++i) dat[i] = -1;
		}
		
		
		RMQ(int[] data, int N){
			this(N);
			this.data = data;
			for (int i = 0; i < N; ++i) {
				update(i, i);
			}
		}
		
		public void update(int k, int i) {
			k += (n_ - 1);
			dat[k] = i;
			while (k > 0) {
				k = (k - 1) / 2;
				dat[k] = min(dat[2 * k + 1], dat[2 * k + 2]);
			}
		}
		
		public int query(int k, int i, int j, int l, int r) {
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
		
		public int min(int i, int j) {
			if (i == -1 && j != -1) return j;
			if (j == -1 && i != -1) return i;
			if (i == -1 && j == -1) return -1;
			return data[i] < data[j] ? i : j;
		}
	}
	
	void read() {
		N = ni();
		Q = ni();
		S = ni();
		g = new ArrayList[MAX_V];
		w = new int[N];
		for (int i = 0; i < N; ++i) g[i] = new ArrayList<Edge>();
		for (int i = 0; i < N - 1; ++i) {
			int from = ni();
			int to   = ni();
			int cost = ni();
			from --;
			to   --;
			g[from].add(new Edge(i, to, cost));
			g[to].add(new Edge(i, from, cost));
			w[i] = cost;
		}
		
		root = N / 2;
		init(N);
		int v = S - 1;
		for (int i = 0; i < Q; ++i) {
			int type = ni();
			if (type == 0) {
				int u = ni();
				u --;
				int p = lca(u, v);
				out.println(bit.sum(id[v]) + bit.sum(id[u]) - bit.sum(id[p]) * 2);
				v = u;
			}
			else {
				int x = ni() - 1;
				int c = ni();
				bit.add(es[2 * x], c - w[x]);
				bit.add(es[2 * x + 1], w[x] - c);
				w[x] = c;
			}
		}
	}

	BIT bit;
	RMQ rmq;
	void init(int N) {
		bit = new BIT(2 * N);
		// 预处理 vs, depth, id 和 es
		k = 0;
		dfs(root, -1, 0);
		rmq = new RMQ(depth, 2 * N);
	}
	
	int[] vs    = new int[MAX_V * 2 - 1]; //DFS访问的顺序，每个结点至多被访问两次
	int[] depth = new int[MAX_V * 2 - 1]; //结点的深度
	int[] id    = new int[MAX_V];         //各个顶点在vs中首次出现的下标
	int[] es    = new int[MAX_V * 2 - 1]; //边的下标（i * 2 + (叶子方向：0，根方向：1)）
	
	int k; // 当前访问的位置
	void dfs(int v, int p, int d) {
		id[v] = k;
		vs[k] = v;
		depth[k++] = d;
		for (Edge e : g[v]) {
			if (e.to != p) {
				bit.add(k, e.cost);
				es[e.id * 2] = k;
				dfs(e.to, v, d + 1);
				vs[k] = v;
				bit.add(k, -e.cost);
				es[e.id * 2 + 1] = k;
				depth[k++] = d;
			}
		}
	}
	
	int lca(int u, int v) {
		return vs[rmq.query(0, Math.min(id[u], id[v]), Math.max(id[u], id[v]) + 1, 0, rmq.n_)];
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

//class RMQ {
//
//static final int INF = 0x3f3f3f3f;
//
//int n_;
//Pair[] dat;
//
//class Pair{
//	int id;
//	int val;
//	Pair(int id, int val){
//		this.id = id;
//		this.val = val;
//	}
//}
//
//RMQ(int N){
//	this.n_ = 1;
//	while (n_ < N) n_ *= 2;
//	dat = new Pair[2 * n_];
//	for (int i = 0; i < 2 * n_ - 1; ++i) dat[i] = new Pair(i, INF);
//}
//
//RMQ(int[] data, int N){
//	this(N);
//	for (int i = 0; i < N; ++i) {
//		update(i, new Pair(i, data[i]));
//	}
//}
//
//public void update(int k, Pair p) {
//	k += (n_ - 1);
//	dat[k] = new Pair(p.id, p.val);
//	while (k > 0) {
//		k = (k - 1) / 2;
//		dat[k] = min(dat[2 * k + 1], dat[2 * k + 2]);
//	}
//}
//
//public Pair query(int k, int i, int j, int l, int r) {
//	if (j <= l || i >= r) return new Pair(-1, INF);
//	else if (i <= l && j >= r) {
//		return dat[k];
//	}
//	else {
//		int lch = 2 * k + 1;
//		int rch = 2 * k + 2;
//		int mid = (l + r) / 2;
//		Pair lf = query(lch, i, j, l, mid);
//		Pair rt = query(rch, i, j, mid, r);
//		if (lf.val < rt.val) return lf;
//		else return rt;
//	}
//}
//
//public Pair min(Pair p1, Pair p2) {
//	Pair min = new Pair(-1, INF);
//	if (p1.val < p2.val) {
//		min.val = p1.val;
//		min.id  = p1.id;
//	}
//	else {
//		min.val = p2.val;
//		min.id  = p2.id;
//	}
//	return min;
//}
//}
