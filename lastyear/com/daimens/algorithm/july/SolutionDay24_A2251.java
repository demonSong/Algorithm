package com.daimens.algorithm.july;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class SolutionDay24_A2251 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/2251.txt";
	
	static final int INF = 1 << 28;
	void solve() {
		while (true){
			int N = ni();
			int M = ni();
			int L = ni();
			if(N == 0 && M == 0 && L == 0) break;
			
			int[][] distance = new int[N][N];
			for (int i = 0; i < N; ++i){
				Arrays.fill(distance[i], INF);
			}
			
			for (int i = 0; i < M; ++i){
				int from = ni();
				int to = ni();
				int dis = ni();
				distance[from][to] = dis;
				distance[to][from] = dis;
			}

			for (int k = 0; k < N; ++k){
				distance[k][k] = 0;
				for (int i = 0; i < N; ++i){
					for (int j = 0; j < N; ++j){
						distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
					}
				}
			}
			
			int[] p = new int[L];
			int[] t = new int[L];
			for (int i = 0; i < L; ++i){
				p[i] = ni();
				t[i] = ni();
			}
			
			init(2 * L);
			for (int i = 0; i < L; ++i){
				for (int j = 0; j < L; ++j){
					if (i != j && t[i] + distance[p[i]][p[j]] <= t[j]){
						addEdge(2 * i, 2 * j + 1);
					}
				}
			}
			out.println(L - bipartiteMatching());
		}
	}
	
	List<Integer>[] g;
	int V;
	int[] matching;
	@SuppressWarnings("unchecked")
	public void init(int n){
		V = n;
		g = new ArrayList[V];
		for (int i = 0; i < V; ++i) g[i] = new ArrayList<Integer>();
		matching = new int[V];
	}
	
	public void addEdge(int from, int to){
		g[from].add(to);
		g[to].add(from);
	}
	
	public boolean dfs(int v, boolean[] visited){
		visited[v] = true;
		for (int u : g[v]){
			int w = matching[u];
			if (w == -1 || !visited[w] && dfs(w, visited)){
				matching[u] = v;
				matching[v] = u;
				return true;
			}
		}
		return false;
	}
	
	public int bipartiteMatching(){
		int res = 0;
		Arrays.fill(matching, -1);
		for (int i = 0; i < V; ++i){
			if (matching[i] < 0){
				if (dfs(i, new boolean[V])){
					res ++;
				}
			}
		}
		return res;
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
		new SolutionDay24_A2251().run();
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


