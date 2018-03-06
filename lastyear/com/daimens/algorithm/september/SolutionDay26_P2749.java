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

public class SolutionDay26_P2749 {

	String INPUT = "./data/judge/201709/P2749.txt";

	public static void main(String[] args) throws IOException {
		new SolutionDay26_P2749().run();
	}

	int N, A, B;
	int[][] hates, likes;
	int[] s1, s2;
	int[][] vx;
	int[][] distance;
	int dist;
	int maxDistance;
	
	class SCC {
		int V;
		List<Integer>[] g, rg;
		List<Integer> po;
		int[] cmp;
		boolean[] used;

		SCC(int V) {
			this.V = V;
			g  = new ArrayList[V];
			rg = new ArrayList[V];
			po = new ArrayList<Integer>();
			used = new boolean[V];
			cmp = new int[V];
			for (int i = 0; i < V; ++i)
				g[i] = new ArrayList<Integer>();
			for (int i = 0; i < V; ++i)
				rg[i] = new ArrayList<Integer>();
		}

		void add(int from, int to) {
			g[from].add(to);
			rg[to].add(from);
		}

		void dfs(int v) {
			used[v] = true;
			for (int u : g[v]) {
				if (!used[u])
					dfs(u);
			}
			po.add(v);
		}

		void rdfs(int v, int k) {
			used[v] = true;
			cmp[v] = k;
			for (int u : rg[v]) {
				if (!used[u])
					rdfs(u, k);
			}
		}

		int kosarajuSCC() {
			for (int i = 0; i < V; ++i) {
				if (!used[i])
					dfs(i);
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
		N = ni();
		A = ni();
		B = ni();
		s1 = new int[] {ni(), ni()};
		s2 = new int[] {ni(), ni()};
		
		dist = manhattanDistance(s1, s2);
		
		vx = new int[N][2];
		distance = new int[N][2];
		
		for (int i = 0; i < N; ++i) {
			vx[i] = new int[] {ni(), ni()};
			distance[i][0] = manhattanDistance(vx[i], s1);
			distance[i][1] = manhattanDistance(vx[i], s2);
		}
		
		for (int i = 0; i < N; ++i) {
			for (int j = i + 1; j < N; ++j) {
				maxDistance = Math.max(maxDistance, distance[i][0] + dist + distance[j][1]);
				maxDistance = Math.max(maxDistance, distance[i][1] + distance[j][1]);
				maxDistance = Math.max(maxDistance, distance[i][0] + distance[j][0]);
			}
		}
		
		hates = new int[A][2];
		for (int i = 0; i < A; ++i) {
			hates[i] = new int[] {ni() - 1, ni() - 1};
		}
		
		likes = new int[B][2];
		for (int i = 0; i < B; ++i) {
			likes[i] = new int[] {ni() - 1, ni() - 1};
		}
		
		solve();
	}
	
	int manhattanDistance(int[] x, int[] y) {
		return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
	}
	
	boolean check(int d) {
		SCC scc = new SCC(N * 2);
		for (int i = 0; i < N; ++i) {
			for (int j = i + 1; j < N; ++j) {
				for (int k = 0; k < 2; ++k) {
					if (distance[i][k] + distance[j][k] > d) {  //连接同一个站点发生矛盾，不能同选s1或s2
						scc.add(2 * i + k, 2 * j + 1 - k);
						scc.add(2 * j + k, 2 * i + 1 - k);
					}
					if (distance[i][k] + dist + distance[j][1 - k] > d) { //连接不同站点发生矛盾，只能同选s1或s2
						scc.add(2 * i + k, 2 * j + k);
						scc.add(2 * j + 1 - k, 2 * i + 1 - k);
					}
				}
			}
		}
		
		for (int i = 0; i < A; ++i) {
			for (int k = 0; k < 2; ++k) {
				scc.add(2 * hates[i][0] + k, 2 * hates[i][1] + 1 - k);
				scc.add(2 * hates[i][1] + k, 2 * hates[i][0] + 1 - k);
			}
		}
		
		for (int i = 0; i < B; ++i) {
			for (int k = 0; k < 2; ++k) {
				scc.add(2 * likes[i][0] + k, 2 * likes[i][1] + k);
				scc.add(2 * likes[i][1] + 1 - k, 2 * likes[i][0] + 1 - k);
			}
		}
		
		scc.kosarajuSCC();
		for (int i = 0; i < N; ++i) {
			if (scc.cmp[2 * i] == scc.cmp[2 * i + 1]) {
				return false;
			}
		}
		return true;
	}
	
	void solve() {
		maxDistance ++;
		int lf = 0, rt = maxDistance;
		while (lf < rt) {
			int mid = lf + (rt - lf) / 2;
			if (!check(mid)) {
				lf = mid + 1;
			}
			else rt = mid;
		}
		out.println(rt == maxDistance ? -1 :rt);
	}

	FastScanner in;
	PrintWriter out;

	void run() throws IOException {
		boolean oj;
		try {
			oj = !System.getProperty("user.dir").equals("F:\\java_workspace\\leetcode");
		} catch (Exception e) {
			oj = System.getProperty("ONLINE_JUDGE") != null;
		}

		InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
		in = new FastScanner(is);
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		read();
		out.flush();
		if (!oj) {
			System.out.println("[" + (System.currentTimeMillis() - s) + "ms]");
		}
	}

	public boolean more() {
		return in.hasNext();
	}

	public int ni() {
		return in.nextInt();
	}

	public long nl() {
		return in.nextLong();
	}

	public double nd() {
		return in.nextDouble();
	}

	public String ns() {
		return in.nextString();
	}

	public char nc() {
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

		public boolean hasNext() {
			next = nextToken();
			return hasNext;
		}

		public int nextInt() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return Integer.parseInt(more);
		}

		public long nextLong() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return Long.parseLong(more);
		}

		public double nextDouble() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return Double.parseDouble(more);
		}

		public String nextString() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return more;
		}

		public char nextChar() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return more.charAt(0);
		}
	}

	static class D {

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

	static class Num {
		public static <K> void inc(Map<K, Integer> mem, K k) {
			if (!mem.containsKey(k))
				mem.put(k, 0);
			mem.put(k, mem.get(k) + 1);
		}
	}
}
