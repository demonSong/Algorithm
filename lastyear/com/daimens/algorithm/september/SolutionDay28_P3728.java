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

public class SolutionDay28_P3728 {
	
	String INPUT = "./data/judge/201709/P3728.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay28_P3728().run();
	}
	
	static final int MAX_N = 52222;
	static final int INF   = 0x3f3f3f3f;
	
	int N;
	List<Integer>[] g;
	List<Integer>[] st, ed, id, ask, pos;
	
	int[] mx   = new int[MAX_N];
	int[] mi   = new int[MAX_N];
	int[] up   = new int[MAX_N];
	int[] down = new int[MAX_N];
	int[] fa   = new int[MAX_N];
	int[] ans  = new int[MAX_N];
	
	boolean[] vis = new boolean[MAX_N];
	
	void init(int n) {
		g   = new ArrayList[n];
		st  = new ArrayList[n];
		ed  = new ArrayList[n];
		id  = new ArrayList[n];
		ask = new ArrayList[n];
		pos = new ArrayList[n];
		
		for (int i = 0; i < n; ++i) {
			g[i]   = new ArrayList<Integer>();
			st[i]  = new ArrayList<Integer>();
			ed[i]  = new ArrayList<Integer>();
			id[i]  = new ArrayList<Integer>();
			ask[i] = new ArrayList<Integer>();
			pos[i] = new ArrayList<Integer>();
		}
	}
	
	void add(int from, int to) {
		g[from].add(to);
		g[to].add(from);
	}
	
	int find(int x) {
		if (x == fa[x]) return x;
		int y = fa[x];
		fa[x] = find(y);
		up[x] = Math.max(up[x], Math.max(mx[y] - mi[x], up[y]));
		down[x] = Math.max(down[x], Math.max(mx[x] - mi[y], down[y]));
		mx[x] = Math.max(mx[x], mx[y]);
		mi[x] = Math.min(mi[x], mi[y]);
		return fa[x];
	}
	
	void tarjan(int u) {
		vis[u] = true;
		for (int i = 0; i < ask[u].size(); ++i) {
			int v = ask[u].get(i);
			if (vis[v]) {
				int t = find(v);
				int z = pos[u].get(i);
				if (z > 0) {
					st[t].add(u);
					ed[t].add(v);
					id[t].add(z);
				}
				else {
					st[t].add(v);
					ed[t].add(u);
					id[t].add(-z);
				}
			}
		}
		
		for (int v : g[u]) {
			if (!vis[v]) {
				tarjan(v);
				fa[v] = u;
			}
		}
		
		for (int i = 0; i < st[u].size(); ++i) {
			int a = st[u].get(i);
			int b = ed[u].get(i);
			int t = id[u].get(i);
			find(a);
			find(b);
			ans[t] = Math.max(up[a], Math.max(down[b], mx[b] - mi[a]));
		}
	}
	
	void read() {
		N = ni();
		
		init(N);
		
		for (int i = 0; i < N; ++i) {
			int w = ni();
			mx[i] = mi[i] = w;
			fa[i] = i;
		}
		for (int i = 0; i < N - 1; ++i) {
			int from = ni();
			int to   = ni();
			from --;
			to   --;
			add(from, to);
		}
		
		int Q = ni();
		for (int i = 1; i <= Q; ++i) {
			int u = ni();
			int v = ni();
			u --;
			v --;
			ask[u].add(v);
			pos[u].add(i);
			ask[v].add(u);
			pos[v].add(-i);
		}
		tarjan(0);
		for (int i = 1; i <= Q; ++i) out.println(ans[i]);
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

