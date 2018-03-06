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
import java.util.Scanner;

public class SolutionDay21_P1274 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/1274.txt";
	
	List<Integer>[] g;
	int V;
	int N;
	void solve() {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			N = in.nextInt();
			int M = in.nextInt();
			V = N + M;
			g = new ArrayList[V];
			for (int i = 0; i < V; ++i) g[i] = new ArrayList<Integer>();
			
			for (int i = 0; i < N; ++i){
				int m = in.nextInt();
				for (int j = 0; j < m; ++j){
					int stall = in.nextInt();
					addEdge(i, stall + N - 1);
				}
			}
			
			System.out.println(bipartite_matching());
		}
		in.close();
	}
	
	public void addEdge(int from, int to){
		g[from].add(to);
		g[to].add(from);
	}

	public int bipartite_matching(){
		int res = 0;
		matching = new int[V];
		Arrays.fill(matching, -1);
		for (int i = 0; i < N; ++i){
			if (matching[i] < 0){
				if (dfs(i, new boolean[V])){
					res ++;
				}
			}
		}
		return res;
	}
	
	int[] matching;
	public boolean dfs(int v, boolean[] used){
		used[v] = true;
		for (int u : g[v]){
			int w = matching[u];
			if (w < 0 || !used[w] && dfs(w, used)){
				matching[u] = v;
				matching[v] = u;
				return true;
			}
		}
		return false;
	}
	
	
	
	
	void run() throws Exception {
		solve();
	}

	public static void main(String[] args) throws Exception {
		new SolutionDay21_P1274().run();
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


