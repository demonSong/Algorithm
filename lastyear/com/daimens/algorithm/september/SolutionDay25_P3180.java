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

public class SolutionDay25_P3180 {
	
	String INPUT = "./data/judge/201709/P3180.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay25_P3180().run();
	}
	
	static final int MAX_N = 10000 + 16;
	List<Edge>[] g, rg;
	List<Integer> po;
	boolean[] used;
	int[] cmp;
	int V;
	
	class Edge{
		int from;
		int to;
		Edge(int from, int to){
			this.from = from;
			this.to   = to;
		}
	}
	
	void init(int V) {
		this.V = V;
		g    = new ArrayList[MAX_N];
		rg   = new ArrayList[MAX_N];
		po   = new ArrayList<Integer>();
		used = new boolean[MAX_N];
		cmp  = new int[MAX_N];
		Arrays.fill(used, false);
		for (int i = 0; i < V; ++i) g[i]  = new ArrayList<Edge>();
		for (int i = 0; i < V; ++i) rg[i] = new ArrayList<Edge>();
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
		po.add(v);
	}
	
	void rdfs(int v, int k) {
		used[v] = true;
		cmp[v]  = k;
		for (Edge e : rg[v]) {
			if (!used[e.to]) rdfs(e.to, k);
		}
	}
	
	int kosarajuSCC() {
		for (int i = 0; i < V; ++i) {
			if (!used[i]) dfs(i);
		}
		Arrays.fill(used, false);
		int k = 0;
		for (int i = po.size() - 1; i >= 0; --i) {
			if (!used[po.get(i)]) rdfs(po.get(i), k++);
		}
		return k;
	}
	
	void read() {
		int N = ni();
		int M = ni();
		init(N);
		for (int i = 0; i < M; ++i) {
			int from = ni();
			int to   = ni();
			from --;
			to   --;
			add(from, to);
		}
		int scc = kosarajuSCC();
		int[] count = new int[scc + 1];
		for (int i = 0; i < V; ++i) {
			count[cmp[i]]++;
		}
		int ans = 0;
		for (int i = 0; i <= scc; ++i) {
			if (count[i] >= 2) ans ++;
		}
		out.println(ans);
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

