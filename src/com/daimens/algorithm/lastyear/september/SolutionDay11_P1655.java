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

public class SolutionDay11_P1655 {
	
	String INPUT = "./data/judge/201709/P1655.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay11_P1655().run();
	}
	
	static final int MAX_N = 20000 + 16;
	static final int INF   = 0x3f3f3f3f;
	
	class Edge{
		int from;
		int to;
		
		public Edge(int from, int to) {
			this.from = from; 
			this.to   = to;
		}
		
		@Override
		public String toString() {
			return from + " " + to;
		}
	}
	
	List<Edge>[] tree = new ArrayList[MAX_N];
	int N;
	int minv;
	int balance;
	int son[];
	boolean[] visited;
	
	
	public void init() {
		visited = new boolean[MAX_N];
		son = new int[MAX_N];
		balance = INF;
		for (int i = 0; i < N; ++i) tree[i] = new ArrayList<Edge>();
	}
	
	public void dfs(int s) {
		son[s] = 0;
		visited[s] = true;
		int tmp = 0;
		for (Edge e : tree[s]) {
			int v = e.to;
			if (!visited[v]) {
				dfs(v);
				son[s] += son[v] + 1;
				tmp = Math.max(tmp, son[v] + 1);
			}
		}
		
		tmp = Math.max(tmp, N - 1 - son[s]);
		if (tmp < balance || tmp == balance && s < minv) {
			balance = tmp;
			minv = s;
		}
	}

	
	public void addEdge(int from, int to) {
		tree[from].add(new Edge(from, to));
		tree[to].add(new Edge(to, from));
	}
	
	
	
	void solve() {
		int T = ni();
		while (T --> 0) {
			N = ni();
			init();
			for (int i = 1; i < N; ++i) {
				int from = ni();
				int to   = ni();
				from --;
				to --;
				addEdge(from, to);
			}
			
			dfs(0);
			out.println((minv + 1) + " " + balance);
		}
	}
	
	void solve_problem() {
		
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

