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

public class SolutionDay26_P2723 {
	
	String INPUT = "./data/judge/201709/P2723.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay26_P2723().run();
	}
	
	int[][] doors;
	int[] keys;
	int N, M;
	
	class SCC {
		int V;
		List<Integer>[] g;
		List<Integer>[] rg;
		List<Integer> po;
		boolean[] used;
		int[] cmp;
		
		SCC(int V){
			this.V = V;
			g  = new List[V];
			rg = new List[V];
			po = new ArrayList<Integer>();
			used = new boolean[V];
			cmp  = new int[V];
			for (int i = 0; i < V; ++i) g[i]  = new ArrayList<Integer>();
			for (int i = 0; i < V; ++i) rg[i] = new ArrayList<Integer>();
		}
		
		void add(int from, int to) {
			g[from].add(to);
			rg[to].add(from);
		}
		
		void dfs(int v) {
			used[v] = true;
			for (int u : g[v]) {
				if (!used[u]) dfs(u);
			}
			po.add(v);
		}
		
		void rdfs(int v, int k) {
			used[v] = true;
			cmp[v]  = k;
			for (int u : rg[v]) {
				if (!used[u]) rdfs(u, k);
			}
		}
		
		int kosarajuSCC() {
			for (int i = 0; i < V; ++i) {
				if (!used[i]) dfs(i);
			}
			Arrays.fill(used, false);
			int k = 0;
			for (int i = po.size() - 1; i >= 0; --i) {
				int v = po.get(i);
				if (!used[v]) rdfs(v, k++);
			}
			return k;
		}
	}
	
	void read() {
		while (true) {
			N = ni();
			M = ni();
			if (N + M == 0) break;
			
			keys = new int[2 * N];
			for (int i = 0; i < N; ++i) {
				int A = ni();
				int B = ni();
				keys[A] = B;
				keys[B] = A;
			}
			
			doors = new int[M][2];
			for (int i = 0; i < M; ++i) {
				doors[i] = new int[] {ni(), ni()};
			}
			
			solve();
		}
	}
	
	void solve() {
		int lf = 0, rt = M;
		while (lf < rt) {
			int mid = lf + (rt - lf + 1) / 2;
			if (!valid(mid)) {
				rt = mid - 1;
			}
			else {
				lf = mid;
			}
		}
		out.println(lf);
	}
	
	boolean valid(int m) {
		SCC scc = new SCC(2 * N);
		for (int i = 0; i < m; ++i) {
			scc.add(keys[doors[i][0]], doors[i][1]);
			scc.add(keys[doors[i][1]], doors[i][0]);
		}
		
		scc.kosarajuSCC();
		for (int i = 0; i < scc.V; ++i) {
			if (scc.cmp[i] == scc.cmp[keys[i]]) {
				return false;
			}
		}
		return true;
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

