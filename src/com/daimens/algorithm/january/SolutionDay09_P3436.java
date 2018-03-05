package com.daimens.algorithm.january;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class SolutionDay09_P3436 {
	
	String INPUT = "./data/judge/201801/P3436.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay09_P3436().run();
	}
	
	static final int INF = 0x3f3f3f3f;
	
	int[][] G;
	int[] level;
	int V;
	
	
	void bfs(int s, int t) {
		level = new int[V];
		Arrays.fill(level, -1);
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(s);
		level[s] = 0;
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int i = 0; i < V; ++i) {
				if (G[now][i] > 0 && level[i] < 0) {
					level[i] = level[now] + 1;
					q.offer(i);
				}
			}
		}
	}
	
	int dfs(int s, int t, int f, boolean vis[]) {
		if (s == t) return f;
		vis[s] = true;
		for (int i = 0; i < V; ++i) {
			if (!vis[i] && G[s][i] > 0 && level[s] + 1 == level[i]) {
				int d = dfs(i, t, Math.min(G[s][i], f), vis);
				if (d > 0) {
					G[s][i] -= d;
					G[i][s] += d;
					return d;
				}
			}
		}
		return 0;
	}
	
	
	int dinic(int s, int t) {
		int flow = 0;
		for(;;) {
			bfs(s, t);
			if (level[t] < 0) break;
			int f = 0;
			while ((f = dfs(s, t, INF, new boolean[V])) > 0) flow += f;
		}
		return flow;
	}
	
	void read() {
		while(more()) {
			int p = ni();
			int n = ni();
			int[] w = new int[n];
			int[][] input  = new int[n][p];
			int[][] output = new int[n][p];
			for (int i = 0; i < n; ++i) {
				w[i] = ni();
				for (int j = 0; j < p; ++j) {
					input[i][j] = ni();
				}
				for (int j = 0; j < p; ++j) {
					output[i][j] = ni();
				}
			}
			// build G
			V = 2 * n + 2;
			G = new int[2 * n + 2][2 * n + 2];
			for (int i = 1; i <= n; ++i) {
				G[i][i + n] = w[i - 1];
			}
			for (int i = 1; i <= n; ++i) {
				for (int j = 1; j <= n; ++j) {
					boolean ok = true;
					for (int k = 0; k < p; ++k) {
						if (input[j - 1][k] == 0 && output[i - 1][k] == 1 || input[j - 1][k] == 1 && output[i - 1][k] == 0) {
							ok = false;
						}
					}
					if (ok) {
						G[i + n][j] = Math.min(w[i - 1], w[j - 1]);
					}
				}
			}
			for (int i = 1; i <= n; ++i) {
				boolean ok = true;
				for (int k = 0; k < p; ++k) {
					if (input[i - 1][k] == 1) ok = false;
				}
				if (ok) G[0][i] = INF;
				ok = true;
				for (int k = 0; k < p; ++k) {
					if (output[i - 1][k] == 0) ok = false;
				}
				if (ok) G[i + n][2 * n + 1] = INF;
			}
			
			int[][] G2 = arrayCopy(G);
			int flow = dinic(0, 2 * n + 1);
			int tot = 0;
			int[][] ans = new int[n * n][3];
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					if (G2[i + 1 + n][j + 1] > G[i + 1 + n][j + 1]) {
						ans[tot][0] = i + 1;
						ans[tot][1] = j + 1;
						ans[tot][2] = G2[i + 1 + n][j + 1] - G[i + 1 + n][j + 1];
						tot ++;
					}
				}
			}
			out.println(flow + " " + tot);
			for (int i = 0; i < tot; ++i) {
				out.println(ans[i][0] + " " + ans[i][1] + " " + ans[i][2]);
			}
		}
	}
	
	int[][] arrayCopy(int[][] G){
		int n = G.length;
		int m = G[0].length;
		int[][] aux = new int[n][m];
		for (int i = 0; i < n; ++i) 
			for (int j = 0; j < m; ++j)
				aux[i][j] = G[i][j];
		return aux;
	}
	
	FastScanner in;
	PrintWriter out;
	
	void run() throws IOException {
		boolean oj;
		try {
			oj = ! System.getProperty("user.dir").equals("F:\\oxygen_workspace\\Algorithm");
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
	
	static class D{
		
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
	
	static class Num{
		public static <K> void inc(Map<K, Integer> mem, K k) {
			if (!mem.containsKey(k)) mem.put(k, 0);
			mem.put(k, mem.get(k) + 1);
		}
	}
}

