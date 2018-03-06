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

public class SolutionDay12_U2161 {
	
	String INPUT = "./data/judge/201709/U2161.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay12_U2161().run();
	}
	
	static final int MAX_N = 30000 + 16;
	static final int INF   = 0x3f3f3f3f;
	
	class Edge{
		int v;
		int d;
		int l;
		int nxt;
		Edge(int v, int d, int l, int nxt){
			this.v = v;
			this.d = d;
			this.l = l;
			this.nxt = nxt;
		}
	}
	
	class Pair implements Comparable<Pair>{
		int d;
		int l;
		Pair(int d, int l){
			this.d = d;
			this.l = l;
		}
		@Override
		public int compareTo(Pair that) {
			return this.d - that.d;
		}
	}
	
	Edge[] edges = new Edge[MAX_N << 1];
	int tot, minv, cenv, point;
	int maxLen;
	int[] head = new int[MAX_N];
	int[] son  = new int[MAX_N];
	boolean[] vis = new boolean[MAX_N];
	Pair[] dis = new Pair[MAX_N];
	
	int N, M;
	
	void init() {
		cenv = point = tot = 0;
		minv = INF;
		Arrays.fill(vis, false);
		Arrays.fill(head, -1);
	}
	
	void add(int from, int to, int d, int l) {
		edges[tot] = new Edge(to, d, l, head[from]);
		head[from] = tot++;
	}
	
	void dfs(int u, int fa) {
		int maxv = 0;
		son[u] = 0;
		for (int i = head[u]; i != -1; i = edges[i].nxt) {
			int v = edges[i].v;
			if (v == fa || vis[v]) continue;
			dfs(v, u);
			son[u] += son[v] + 1;
			maxv = Math.max(maxv, son[v] + 1);
		}
		
		maxv = Math.max(maxv, point - 1 - son[u]);
		if (maxv < minv) {
			minv = maxv;
			cenv = u;
		}
	}
	
	void getDis(int u, int fa, int d, int l) {
		dis[tot++] = new Pair(d, l);
		for (int i = head[u]; i != -1; i = edges[i].nxt) {
			int v = edges[i].v;
			if (v == fa || vis[v]) continue;
			getDis(v, u, d + edges[i].d, l + edges[i].l);
		}
	}
	
	void divide(int u, int d, int l) {
		tot = 0;
		getDis(u, -1, d, l);
		Arrays.sort(dis, 0, tot);
		int ll = 0, rr = tot - 1;
		while (ll < rr) {
			if (dis[ll].d + dis[rr].d <= M) {
				for (int j = ll + 1; j <= rr; ++j) maxLen = Math.max(maxLen, dis[ll].l + dis[j].l);
				ll++;
			}
			else rr--;
		}
	}
	
	void solve(int u) {
		minv = INF;
		point = point != 0 ? son[u] + 1 : N;
		dfs(u, -1);
		int root = cenv;
		vis[root] = true;
		divide(root, 0, 0);
		for (int i = head[root]; i != -1; i = edges[i].nxt) {
			int v = edges[i].v;
			if (vis[v]) continue;
			solve(v);
		}
	}
	
	void read() {
		int T = ni();
		int C = 1;
		while (T --> 0) {
			N = ni();
			M = ni();
			init();
			for (int i = 1; i < N; ++i) {
				int a = ni();
				int b = ni();
				int d = ni();
				int l = ni();
				add(a, b, d, l);
				add(b, a, d, l);
			}
			
			solve(1);
			out.println("Case " + (C++) + ": " + maxLen);
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

