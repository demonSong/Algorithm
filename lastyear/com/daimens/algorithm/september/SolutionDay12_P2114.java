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
import java.util.StringTokenizer;

public class SolutionDay12_P2114 {
	
	String INPUT = "./data/judge/201709/P2114.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay12_P2114().run();
	}
	
	static final int MAX_N = 11111 + 16;
	static final int INF   = 0x3f3f3f3f;
	
	class Edge{
		int to;
		int cost;
		Edge(int to, int cost){
			this.to = to;
			this.cost = cost;
		}
	}
	
	List<Edge>[] g =  new ArrayList[MAX_N];
	int N, K, tot, minv, cenv, point, ans;
	int[] son  = new int[MAX_N];
	int[] dis;
	boolean[] vis = new boolean[MAX_N];
	
	void init() {
		tot = cenv = point = 0;
		minv = INF;
		for (int i = 0; i < N + 16; ++i) g[i] = new ArrayList<Edge>();
		Arrays.fill(vis, false);
	}

	void add(int from, int to, int cost) {
		g[from].add(new Edge(to, cost));
	}
	
	void dfs(int u, int fa) {
		son[u] = 0;
		int maxv = 0;
		for (Edge e : g[u]) {
			int v = e.to;
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
	
	void getDis(int u, int fa, int d) {
		dis[tot++] = d;
		for (Edge e : g[u]) {
			int v = e.to;
			int w = e.cost;
			if (v == fa || vis[v]) continue;
			getDis(v, u, d + w);
		}
	}
	
	boolean count(int u, int d) {
		tot = 0;
		getDis(u, -1, d);
		Arrays.sort(dis, 0, tot);
		int l = 0, r = tot - 1;
		while (l < r) {
			if (dis[l] + dis[r] == K) {
				return true;
			}
			else if (dis[l] + dis[r] < K) {
				l++;
			}
			else r--;
		}
		return false;
	}
	
	
	boolean solve(int u) {
		minv = INF;
		point = point != 0 ? son[u] + 1 : N;
		dfs(u, -1);
		int root = cenv;
		vis[root] = true;
		
		boolean contain = false;
		contain |= count(root, 0);
		
		for (Edge e : g[root]) {
			int v = e.to;
			if (vis[v]) continue;
			contain |= solve(v);
		}
		return contain;
	}
	
	void read() {
		while (true) {
			N = ni();
			if (N == 0) break;
			init();
			int cnt = 0;
			for (int i = 1; i <= N; ++i) {
				while (true) {
					int to   = ni();
					if (to == 0) break;
					int cost = ni();
					add(i - 1, to - 1, cost);
					add(to - 1, i - 1, cost);	
					cnt += 2;
				}
			}
			
			dis = new int[cnt + 16];
			
			while (true) {
				K = ni();
				if (K == 0) break;
				vis = new boolean[MAX_N];
				ans = cenv = point = 0;
				out.println(solve(0) ? "AYE" :"NAY");
			}
			
			out.println(".");
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

//int count(int u, int d) {
//	tot = 0;
//	getDis(u, -1, d);
//	Arrays.sort(dis, 0, tot);
//	int l = 0, r = tot - 1, res = 0;
//	while (l < r) {
//		if (dis[l] + dis[r] <= K) {
//			res += r - l;
//			l++;
//		}
//		else r--;
//	}
//	l = 0;
//	r = tot - 1;
//	while (l < r) {
//		if (dis[l] + dis[r] < K) {
//			res -= r - l;
//			l++;
//		}
//		else r--;
//	}
//	return res;
//}
//
//
//void solve(int u) {
//	minv = INF;
//	point = point != 0 ? son[u] + 1 : N;
//	dfs(u, -1);
//	int root = cenv;
//	vis[root] = true;
//	ans += count(root, 0);
//	for (Edge e : g[root]) {
//		int v = e.to;
//		if (vis[v]) continue;
//		ans -= count(v, e.cost);
//		solve(v);
//	}
//}

