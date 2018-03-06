package com.daimens.algorithm.july;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SolutionDay21_P2112 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/2112.txt";
	
	int[][] graph;
	int V;
	int K;
	int C;
	int M;
	void solve() {
		K = ni();
		C = ni();
		M = ni();
		V = K + C + 2;
		
		graph = new int[K + C][K + C];
		for (int i = 0; i < K + C; ++i){
			for (int j = 0; j < K + C; ++j){
				int d = ni();
				graph[i][j] = d != 0 ? d : INF;
			}
		}
		
		int n = K + C;
		for (int k = 0; k < n; ++k){
			for (int i = 0; i < n; ++i){
				for (int j = 0; j < n; ++j){
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		int lb = 0, ub = 200 * n;
		while (ub - lb > 1){
			int mid = (ub + lb) / 2;
			if (check(mid)) ub = mid;
			else lb = mid;
		}
		out.println(ub);
	}
	
	public boolean check(int limit){
		g = new ArrayList[V];
		for (int i = 0; i < V; ++i) g[i] = new ArrayList<Edge>();
		
		int s = 0;
		int t = V - 1;
		
		//源点到奶牛
		for (int id = 0; id < C; ++id){
			addEdge(s, id + 1 + K, 1);
		}
		
		//机器到汇点
		for (int id = 0; id < K; ++id){
			addEdge(id + 1, t, M);
		}
		
		for (int cow = 0; cow < C; ++cow){
			for (int ml = 0; ml < K; ++ml){
				if (graph[cow + K][ml] <= limit){
					addEdge(cow + K + 1, ml + 1, 1);
				}
			}
		}
		
		return maxFlow(s, t) == C;
	}
	
	public void addEdge(int from, int to, int cap){
		g[from].add(new Edge(from, to, cap, g[to].size()));
		g[to].add(new Edge(to, from, 0, g[from].size() - 1));
	}
	
	
	/************dinic************/
	class Edge{
		int from;
		int to;
		int cap;
		int rev;
		public Edge(int from, int to, int cap, int rev){
			this.from = from;
			this.to = to;
			this.cap = cap;
			this.rev = rev;
		}
	}
	
	static final int INF = 1 << 28;
	List<Edge>[] g;
	public int maxFlow(int s, int t){
		int flow = 0;
		for (;;){
			bfs(s);
			if (level[t] < 0) break;
			int f = 0;
			while ((f = dfs(s, t, INF, new boolean[V])) > 0) flow += f;
		}
		return flow;
	}
	
	int[] level;
	public void bfs(int s){
		level = new int[V];
		Arrays.fill(level, -1);
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
	
	public int dfs(int s, int t, int f, boolean[] visited){
		if (s == t) return f;
		visited[s] = true;
		for (Edge e : g[s]){
			int to = e.to;
			if (!visited[to] && e.cap > 0 && level[e.from] + 1 == level[to]){
				int d = dfs(to, t, Math.min(f, e.cap), visited);
				if (d > 0){
					e.cap -= d;
					g[to].get(e.rev).cap += d;
					return d;
				}
			}
		}
		return 0;
	}
	
	void run() throws Exception {
		is = oj ? System.in : new FileInputStream(new File(INPUT));
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	public static void main(String[] args) throws Exception {
		new SolutionDay21_P2112().run();
	}

	private byte[] inbuf = new byte[1024];
	public int lenbuf = 0, ptrbuf = 0;

	private int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	private boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	private int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	private double nd() {
		return Double.parseDouble(ns());
	}

	private char nc() {
		return (char) skip();
	}

	private String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != '
									// ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	private char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	private char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	private int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	private void tr(Object... o) {
		if (!oj)
			System.out.println(Arrays.deepToString(o));
	}
}


