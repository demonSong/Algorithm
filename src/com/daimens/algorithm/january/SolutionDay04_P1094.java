package com.daimens.algorithm.january;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class SolutionDay04_P1094 {
	
	String INPUT = "./data/judge/201801/P1094.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay04_P1094().run();
	}
	
	int n;
	
	void read() {
		
		while (true) {
			n = ni();
			int m = ni();
			if (n + m == 0) break;
			List<Integer>[] graph = new ArrayList[n];
			for (int i = 0; i < n; ++i) graph[i] = new ArrayList<Integer>();
			
			int[] indegree = new int[n];
			boolean hasAns = false;
			hasCircle = false;
			for (int i = 0; i < m; ++i) {
				String ns = ns();
				int u = ns.charAt(0) - 'A';
				int v = ns.charAt(2) - 'A';
				indegree[v] ++;
				graph[u].add(v);
				if (!hasAns) {
					if (top(graph, indegree)) {
						out.println("Sorted sequence determined after " + (i + 1) + " relations: " + ans + ".");
						hasAns = true;
					}
					else {
						if(hasCircle) {
							out.println("Inconsistency found after "+ (i + 1) +" relations.");
							hasAns = true;
						}
					}
				}
			}
			if (!hasAns)
				out.println("Sorted sequence cannot be determined.");
		}
	}
	
	String ans = "";
	boolean hasCircle = false;
	boolean top(List<Integer>[] graph, int[] indegree) {
		int[] copy = new int[n];
		System.arraycopy(indegree, 0, copy, 0, n);
		
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] vis = new boolean[n];
		
		for (int i = 0; i < n; ++i) {
			if (copy[i] == 0) {
				q.offer(i);
				vis[i] = true;
			}
		}
		
		boolean once = true;
		while (!q.isEmpty()) {
			if (q.size() >= 2) once = false;
			int now = q.poll();
			char c = (char) (now + 'A');
			sb.append(c);
			for (int to : graph[now]) {
				copy[to] --;
			}
			for (int i = 0; i < n; ++i) {
				if (!vis[i] && copy[i] == 0) {
					q.offer(i);
					vis[i] = true;
				}
			}
		}
		if (sb.length() == n && once) {
			ans = sb.toString();
			return true;
		}
		else if (sb.length() == n && !once) return false;
		hasCircle = true;
		return false;
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

