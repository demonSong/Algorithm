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

public class SolutionDay25_P2186 {
	
	String INPUT = "./data/judge/201709/P2186.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay25_P2186().run();
	}
	
	/******************强连通分量的求解*******************/
	static final int MAX_N = 10000 + 16;
	List<Edge>[] g  = new List[MAX_N];
	List<Edge>[] rg = new List[MAX_N];
	List<Integer> postOrder = new ArrayList<Integer>();
	boolean[] used = new boolean[MAX_N];
	int[] cmp = new int[MAX_N];
	int N;
	
	class Edge{
		int from;
		int to;
		Edge(int from, int to){
			this.from = from;
			this.to = to;
		}
		
		@Override
		public String toString() {
			return from + " " + to;
		}
	}
	
	void init(int n) {
		this.N = n;
		Arrays.fill(used, false);
		for (int i = 0; i < N; ++i) g[i]  = new ArrayList<Edge>();
		for (int i = 0; i < N; ++i) rg[i] = new ArrayList<Edge>(); 
	}
	
	void add(int from, int to) {
		g[from].add(new Edge(from, to));
		rg[to].add(new Edge(to, from));
	}
	
	void dfs(int v) {
		used[v] = true;
		for (Edge e : g[v]) {
			if (!used[e.to]) dfs(e.to);
		}
		postOrder.add(v);
	}
	
	void rdfs(int v, int k) {
		used[v] = true;
		cmp[v] = k;
		for (Edge e : rg[v]) {
			if (!used[e.to]) rdfs(e.to, k);
		}
	}
	
	int kosarajuSCC(int V) {
		for (int i = 0; i < V; ++i) {
			if (!used[i]) dfs(i);
		}
		Arrays.fill(used, false);
		int k = 0;
		for (int i = postOrder.size() - 1; i >= 0; --i) {
			if (!used[postOrder.get(i)]) rdfs(postOrder.get(i), k++);
		}
		return k;
	}
	
	
	
	void read() {
		int n = ni();
		int m = ni();
		init(n);
		for (int i = 0; i < m; ++i) {
			int from = ni();
			int to   = ni();
			from --;
			to   --;
			add(from, to);
		}
		int scc = kosarajuSCC(n);
		int u = 0, num = 0;
		for (int i = 0; i < n; ++i) {
			if (cmp[i] == scc - 1) {
				u = i;
				num ++;
			}
		}
		Arrays.fill(used, false);
		rdfs(u, 0);
		for (int v = 0; v < n; ++v) {
			if (!used[v]) {
				num = 0;
				break;
			}
		}
		out.println(num);
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
}

