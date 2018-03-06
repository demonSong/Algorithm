package com.daimens.algorithm.july;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SolutionDay18_P2987 {
	
	static class Edge{
		int from;
		int to;
		long cap;
		int rev;
		public Edge(int from, int to, long cap, int rev){
			this.from = from;
			this.to = to;
			this.cap = cap;
			this.rev = rev;
		}
	}
	
	static long INF = 1 << 62;
	static List<Edge>[] g;
	static int V;
	static int S;
	static int T;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		V = N + 2;
		S = 0;
		T = N + 1;
		count = 0;
		visited = new boolean[V];
		level = new int[V]; 
		g = new ArrayList[V];
		for (int i = 0; i < V; ++i){
			g[i] = new ArrayList<Edge>();
		}
		
		long pos = 0;
		for (int i = 0; i < N; ++i){
			long cap = in.nextInt();
			if (cap  > 0){
				addEdge(S, i + 1, cap);
				pos += cap;
			}
			else{
				addEdge(i + 1, T, -cap);
			}
		}
		
		for (int i = 0; i < M; ++i){
			int from = in.nextInt();
			int to = in.nextInt();
			addEdge(from, to, INF);
		}
		long min = dinic();
		solve(S);
		System.out.println((--count) + " " + (pos - min));
	}
	
	public static void addEdge(int from, int to, long cap){
		g[from].add(new Edge(from, to, cap, g[to].size()));
		g[to].add(new Edge(to, from, 0, g[from].size() - 1)); 
	}
	
	static int[] level;
	public static void bfs(int s){
		for (int i = 0; i < V; ++i) level[i] = -1;
		level[s] = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(s);
		while (!queue.isEmpty()){
			int v = queue.poll();
			for (Edge e : g[v]){
				int to = e.to;
				if (level[to] < 0 && e.cap > 0){
					level[to] = level[v] + 1;
					queue.offer(to);
				}
			}
		}
	}
	
	public static long dfs(int s, int t, long F, boolean[] visited){
		if (s == t) return F;
		visited[s] = true;
		for (Edge e : g[s]){
			int from = e.from;
			int to = e.to;
			if (!visited[to] && level[from] + 1 == level[to] && e.cap > 0){
				long d = dfs(to, t, Math.min(F, e.cap), visited);
				if (d > 0){
					e.cap -= d;
					g[to].get(e.rev).cap += d;
					return d;
				}
			}
		}
		return 0;
	}
	
	public static long dinic(){
		long flow = 0;
		for (;;){
			bfs(S);
			if (level[T] < 0) break;
			long f = 0;
			while ((f = dfs(S, T, INF + 16, new boolean[V])) > 0) flow += f;
		}
		return flow;
	}
	
	static int count;
	static boolean[] visited;
	public static void solve(int s){
		count ++;
		visited[s] = true;
		for (Edge e : g[s]){
			int to = e.to;
			if (e.cap > 0 && !visited[to]){
				solve(to);
			}
		}
	}
	
	static class Scanner {

		private BufferedReader br;
		private StringTokenizer tok;

		public Scanner(InputStream is) throws IOException {
			br = new BufferedReader(new InputStreamReader(is));
			getLine();
		}

		private void getLine() throws IOException {
			while (tok == null || !tok.hasMoreTokens()) {
				tok = new StringTokenizer(br.readLine());
			}
		}

		private boolean hasNext() {
			return tok.hasMoreTokens();
		}
		
		public String next() throws IOException {
			if (hasNext()) {
				return tok.nextToken();
			} else {
				getLine();
				return tok.nextToken();
			}
		}

		public int nextInt() throws IOException {
			if (hasNext()) {
				return Integer.parseInt(tok.nextToken());
			} else {
				getLine();
				return Integer.parseInt(tok.nextToken());
			}
		}

		public long nextLong() throws IOException {
			if (hasNext()) {
				return Long.parseLong(tok.nextToken());
			} else {
				getLine();
				return Long.parseLong(tok.nextToken());
			}
		}

		public double nextDouble() throws IOException {
			if (hasNext()) {
				return Double.parseDouble(tok.nextToken());
			} else {
				getLine();
				return Double.parseDouble(tok.nextToken());
			}
		}
	}
	
}
