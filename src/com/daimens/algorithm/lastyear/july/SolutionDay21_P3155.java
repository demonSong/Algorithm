package com.daimens.algorithm.july;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class SolutionDay21_P3155 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/3155.txt";
	
	class Pair{
		int from;
		int to;
		public Pair(int from, int to){
			this.from = from;
			this.to = to;
		}
	}
	
	double[][] graph;
	Pair[] p;
	int[] dv;
	int N;
	static final double esp = 1e-8;
	void solve() {
		N = ni();
		int M = ni();
		if (M == 0){
			out.println("1");
			out.println("1");
		}
		else{
			dv = new int[N + 2];
			graph = new double[N + 2][N + 2];
			
			p = new Pair[M];
			for (int i = 0; i < M; ++i){
				int from = ni();
				int to = ni();
				dv[from] ++;
				dv[to] ++;
				p[i] = new Pair(from, to);
			}
			
			int s = 0, t = N + 1;
			double lo = 1.0 / N;
			double hi = M / 1.0;
			double precision = 1.0 / N / N;
			double hg = 0.0;
			while (hi - lo >= precision){
				double mid = (hi + lo) / 2.0;
				constructGraph(mid, N, M);
				hg = (N * M - dinic(0, N + 1)) / 2.0;
				if (hg > esp) lo = mid;
				else hi = mid;
			}
			constructGraph(lo, N, M);
			dinic(s, t);
			sum = 0;
			boolean[] marked = new boolean[N + 2];
			dfsTravel(s, marked);
			out.println(sum - 1);
			for (int i = 1; i <= N; ++i){
				if (marked[i]){
					out.println(i);
				}
			}
		}
	}
	
	int sum;
	public void dfsTravel(int v, boolean[] visited){
		++sum;
		visited[v] = true;
		for (int j = 0; j < N + 2; ++j){
			double cap = graph[v][j];
			if (cap > 0.0 && !visited[j]){
				dfsTravel(j, visited);
			}
		}
	}
	
	int[] level;
	public void bfs(int s){
		level = new int[N + 2];
		Arrays.fill(level, -1);
		level[s] = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(s);
		while (!queue.isEmpty()){
			int v = queue.poll();
			for (int to = 0; to < N + 2; ++to){
				double e = graph[v][to];
				if (level[to] < 0 && e > 0){
					level[to] = level[v] + 1;
					queue.offer(to);
				}
			}
		}
	}
	
	public double dfs(int s, int t, double f, boolean[] visited){
		if (s == t) return f;
		visited[s] = true;
		for (int to = 0; to < N + 2; ++to){
			double cap = graph[s][to];
			if (!visited[to] && level[s] + 1 == level[to] && cap > 0){
				double d = dfs(to, t, Math.min(f, cap), visited);
				if (d > esp){
					graph[s][to] -= d;
					graph[to][s] += d;
					return d;
				}
			}
		}
		return 0.0;
	}
	
	static final double INF = Double.MAX_VALUE;
	public double dinic(int s, int t){
		double flow = 0;
		for (;;){
			bfs(s);
			if (level[t] < 0) break;
			double f = 0;
			while ((f = dfs(s, t, INF, new boolean[N + 2])) > 0) flow += f;
		}
		return flow;
	}
	
	public void constructGraph(double g, int N, int M){
		graph = new double[N + 2][N + 2];
		
		for (int i = 1; i <= N; ++i){ //s -> v
			addEdge(0, i, M);
			addEdge(i, N + 1, M + 2 * g - dv[i]);
		}
		
		for (Pair pair : p){
			int from = pair.from;
			int to = pair.to;
			addEdge(from, to, 1);
			addEdge(to, from, 1);
		}
	}
	
	public void addEdge(int from, int to, double cap){
		graph[from][to] += cap;
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
		new SolutionDay21_P3155().run();
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


