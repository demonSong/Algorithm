package com.daimens.algorithm.december;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class SolutionDay28_P1789 {
	
	String INPUT = "./data/judge/201712/P1789.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay28_P1789().run();
	}
	
	static final int INF = 0x3f3f3f3f;
	
	int dist(String a, String b) {
		int n = 7;
		int sum = 0;
		char[] ca = a.toCharArray();
		char[] cb = b.toCharArray();
		for (int i = 0; i < n; ++i) {
			sum += ca[i] != cb[i] ? 1 : 0;
		}
		return sum;
	}
	
	class Node implements Comparable<Node>{
		int v;
		int dist;
		Node(int v, int dist){
			this.v = v;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
		
		@Override
		public String toString() {
			return "(" + v + ", " + dist + ")";
		}
	}
	
	void read() {
		while (true) {
			int n = ni();
			if (n == 0) break;
			String[] ns = new String[n];
			for (int i = 0; i < n; ++i) {
				ns[i] = ns();
			}
			
			int[][] graph = new int[n][n];
			for (int i = 0; i < n; ++i) {
				Arrays.fill(graph[i], INF);
			}
			
			for (int i = 0; i < n; ++i) {
				for (int j = i + 1; j < n; ++j) {
					graph[i][j] = graph[j][i] = dist(ns[i], ns[j]);
				}
			}
			
			int[] mincost = new int[n];
			Arrays.fill(mincost, INF);
			boolean[] used = new boolean[n];
			mincost[0] = 0; // 从0出发能够找到距离0的边为1
			
			int sum = 0;
			while (true) {
				int v = -1; // 下一个顶点
				for (int u = 0; u < n; ++u) {
					if (!used[u] && (v == -1 || mincost[u] < mincost[v])) v = u;
				}
				
				if (v == -1) break;
				sum += mincost[v];
				used[v] = true;
				for (int u = 0; u < n; ++u) {
					mincost[u] = Math.min(mincost[u], graph[v][u]);
				}
			}

			out.println("The highest possible quality is " + "1/" + sum + ".");
		}
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

