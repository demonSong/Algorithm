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

public class SolutionDay12_P1741 {
	
	String INPUT = "./data/judge/201709/P1741.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay12_P1741().run();
	}
	
	static final int MAX_N = 10000 + 16;
	static final int INF   = 0x3f3f3f3f;
	
	class Edge{
		int to;
		int cost;
		int next;
		Edge(int to, int cost, int next){
			this.to = to;
			this.cost = cost;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return to + " " + cost + " " + next;
		}
	
	}
	
	Edge[] edges = new Edge[MAX_N << 1];
	int N, K, tot, cenv, minv, ans, point;
	int[] head, son;
	int[] dis;
	
	boolean[] visited;
	
	void init() {
		head = new int[MAX_N];
		son  = new int[MAX_N];
		dis  = new int[MAX_N];
		visited = new boolean[MAX_N];
		cenv = point = tot = 0;
		minv = INF;
		Arrays.fill(head,  -1);
	}
	
	void add(int from, int to, int cost) {
		edges[tot] = new Edge(to, cost, head[from]);
		head[from] = tot++;
	}
	
	void dfs(int u, int fa) {
		son[u] = 0;
		int maxv = 0;
		for (int i = head[u]; i != -1; i = edges[i].next) {
			int v = edges[i].to;
			if (v == fa || visited[v]) continue;
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
	
	void getDis(int u, int fa, int dist) {
		dis[tot++] = dist;
		for (int i = head[u]; i != -1; i = edges[i].next) {
			int v = edges[i].to;
			if (v == fa || visited[v]) continue;
			getDis(v, u, dist + edges[i].cost);
		}
	}
	
	int count(int u, int d) {
		tot = 0;
		getDis(u, -1, d);
		Arrays.sort(dis, 0, tot);
		int l = 0, r = tot - 1, res = 0;
		while (l < r) {
			if (dis[l] + dis[r] <= K) {
				res += r - l;
				l ++;
			}
			else r --;
		}
		return res;
	}
	
	void solve(int u) {
		minv = INF;
		point = point != 0 ? son[u] + 1 : N;
		dfs(u, -1);
		int root = cenv;
		visited[root] = true;
		ans += count(root, 0);
		for (int i = head[root]; i != -1; i = edges[i].next) {
			int v = edges[i].to;
			if (visited[v]) continue;
			ans -= count(v, edges[i].cost);
			solve(v);
		}
	}
	
	
	void read() {
		while (true) {
			N = ni();
			K = ni();
			if (N == 0 && K == 0) break;
			
			init();
			for (int i = 1; i < N; ++i) {
				int from = ni();
				int to   = ni();
				int cost = ni();
				add(from, to, cost);
				add(to, from, cost);
			}
			
			ans = 0;
			solve(1);
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


