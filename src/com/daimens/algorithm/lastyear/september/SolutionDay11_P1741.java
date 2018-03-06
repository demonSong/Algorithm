package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SolutionDay11_P1741 {
	
	String INPUT = "./data/judge/201709/P1741.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay11_P1741().run();
	}
	
	static final int MAX_N = 10007;
	static final int INF   = 0x3f3f3f3f;
	
	class Edge{
		int v, w, nxt;
		Edge(){}
		Edge(int a, int b, int c){
			v   = a;
			w   = b;
			nxt = c;
		}
		
		@Override
		public String toString() {
			return v + " " + w + " " + nxt;
		}
	}
	
	Edge[] edge = new Edge[MAX_N << 1];
	int[]  head = new int[MAX_N];
	int[]  sum  = new int[MAX_N];
	int[]  dis  = new int[MAX_N];
	boolean[] vis = new boolean[MAX_N];
	int tot, root, minv, point;
	int n, k;
	int ans;
	
	void init() {
		ans = point = tot = 0;
		Arrays.fill(head, -1);
		Arrays.fill(vis, false);
	}
	
	void add(int u, int v, int w) {
		edge[tot] = new Edge(v, w, head[u]);  // head 指向 同一个结点的多个子结点
		head[u] = tot++;
	}
	
	void dfs(int u, int fa) {
		sum[u] = 1;
		int maxv = 0;
		for (int i = head[u]; i != -1; i = edge[i].nxt) {
			int v = edge[i].v;
			if (v == fa || vis[v]) continue; //如果下一结点为它的父亲结点则不继续遍历
			dfs(v, u);
			sum[u] += sum[v];
			maxv = Math.max(maxv, sum[v]);
		}
		maxv = Math.max(maxv, point - sum[u]);
		if (maxv < minv) {
			minv = maxv;
			root = u;
		}
	}
	
	void getDis(int u, int pre, int dist) {
		dis[tot++] = dist;
		for (int i = head[u]; i != -1; i = edge[i].nxt) {
			int v = edge[i].v;
			int w = edge[i].w;
			if (v == pre || vis[v]) continue;
			getDis(v, u, dist + w);
		}
	}
	
	int counts(int u, int dist) {
		tot = 0;
		getDis(u, -1, dist);
		Arrays.sort(dis, 0, tot);
		int l = 0, r = tot - 1, res = 0;
		while (l < r) {
			if (dis[l] + dis[r] <= k) {
				res += r - l;
				l ++;
			}
			else r --;
		}
		return res;
	}
	
	void solve(int u) {
		minv = INF;
		point = point != 0 ? sum[u] : n;
		dfs(u, -1);
		u = root;
		vis[u] = true;
		ans += counts(u, 0);
		for (int i = head[u]; i != -1; i = edge[i].nxt) {
			int v = edge[i].v;
			if (vis[v]) continue;
			ans -= counts(v, edge[i].w);
			solve(v);
		}
	}
	
	void read() {
		while (true) {
			n = ni();
			k = ni();
			if (n == 0 && k == 0) break;
			init();
			for (int i = 1; i < n; ++i) {
				int u = ni();
				int v = ni();
				int w = ni();
				add(u, v, w);
				add(v, u, w);
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

