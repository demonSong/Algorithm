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

public class SolutionDay26_P3678 {
	
	String INPUT = "./data/judge/201709/P3678.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay26_P3678().run();
	}
	
	class SCC {
		static final int MAX_V = 1000 + 8;
		List<Integer>[] g  = new List[MAX_V];
		List<Integer>[] rg = new List[MAX_V];
		List<Integer> po   = new ArrayList<Integer>();
		boolean[] used = new boolean[MAX_V];
		int[] cmp = new int[MAX_V];
		int V;
		
		SCC(int V) {
			this.V = V;
			po = new ArrayList<Integer>();
			Arrays.fill(used, false);
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
			for (int v = 0; v < V; ++v) {
				if (!used[v]) dfs(v);
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
		int N = ni();
		int M = ni();
		SCC scc = new SCC(N * 2);
		for (int i = 0; i < M; ++i) {
			int a = ni();
			int b = ni();
			int c = ni();
			String op = ns();
			if (op.equals("AND")) {
				if (c == 0) {
					scc.add(2 * a + 1, 2 * b);
					scc.add(2 * b + 1, 2 * a);
				}
				else {
					scc.add(2 * a, 2 * b + 1);
					scc.add(2 * b, 2 * a + 1);
					scc.add(2 * a, 2 * b);
					scc.add(2 * b + 1, 2 * a + 1);
					scc.add(2 * a + 1, 2 * b + 1);
					scc.add(2 * b, 2 * a);
				}
			}
			else if (op.equals("OR")) {
				if (c == 0) {
					scc.add(2 * a, 2 * b);
					scc.add(2 * b + 1, 2 * a + 1);
					scc.add(2 * a + 1, 2 * b + 1);
					scc.add(2 * b, 2 * a);
					scc.add(2 * a + 1, 2 * b);
					scc.add(2 * b + 1, 2 * a);
				}
				else {
					scc.add(2 * a, 2 * b + 1);
					scc.add(2 * b, 2 * a + 1);
				}
			}
			else {
				if (c == 0) {
					scc.add(2 * a, 2 * b);
					scc.add(2 * b + 1, 2 * a + 1);
					scc.add(2 * a + 1, 2 * b + 1);
					scc.add(2 * b, 2 * a);
				}
				else {
					scc.add(2 * a, 2 * b + 1);
					scc.add(2 * b, 2 * a + 1);
					scc.add(2 * a + 1, 2 * b);
					scc.add(2 * b + 1, 2 * a);
				}
			}
		}
		
		scc.kosarajuSCC();
		for (int i = 0; i < N; ++i) {
			if (scc.cmp[2 * i] == scc.cmp[2 * i + 1]) {
				out.println("NO");
				return;
			}
		}
		out.println("YES");
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

