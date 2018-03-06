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

public class SolutionDay12_P1655 {
	
	String INPUT = "./data/judge/201709/P1655.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay12_P1655().run();
	}
	
	static final int MAX_N = 20000 + 16;
	static final int INF   = 0x3f3f3f3f;
	
	class Edge{
		int to;
		int nxt;
		Edge(){}
		Edge(int to, int nxt){
			this.to = to;
			this.nxt = nxt;
		}
	}
	
	Edge[] edge = new Edge[2 * MAX_N];
	int[] son;
	int[] head;
	boolean[] visited;
	
	int tot;
	int N;
	int minv;
	int balance;
	
	public void init() {
		son = new int[MAX_N];
		head = new int[MAX_N];
		visited = new boolean[MAX_N];
		Arrays.fill(head, -1);
		tot = 0;
		balance = INF;
	}
	
	public void addEdge(int from, int to) {
		edge[tot] = new Edge(to, head[from]);
		head[from] = tot++;
	}
	
	void dfs(int cur) {
		int tmp = 0;
		son[cur] = 0;
		visited[cur] = true;
		for (int i = head[cur]; i != -1; i = edge[i].nxt) {
			int v = edge[i].to;
			if (!visited[v]) {
				dfs(v);
				son[cur] += son[v] + 1;
				tmp = Math.max(tmp, son[v] + 1);
			}
		}
		
		tmp = Math.max(tmp, N - 1 - son[cur]);
		if (tmp < balance || tmp == balance && cur < minv) {
			minv = cur;
			balance = tmp;
		}
	}
	
	void read() {
		int T = ni();
		while (T --> 0) {
			N = ni();
			init();
			for (int i = 1; i < N; ++i) {
				int from = ni();
				int to   = ni();
				addEdge(from, to);
				addEdge(to, from);
			}
			
			dfs(1);
			out.println(minv + " " + balance);
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

