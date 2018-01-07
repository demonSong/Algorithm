package com.daimens.algorithm.december;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay19_P3295 {
	
	String INPUT = "./data/judge/201712/P3295.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay19_P3295().run();
	}
	
	int tot = 0;
	boolean dfs(char[] cs, int p, int q, int r, int s, int t) {
		char c = cs[tot++];
		switch (c) {
		case 'K':
			boolean a = dfs(cs, p, q, r, s, t);
			boolean b = dfs(cs, p, q, r, s, t);
			if (a && b) return true;
			return false;
		case 'A':
			a = dfs(cs, p, q, r, s, t);
			b = dfs(cs, p, q, r, s, t);
			if (!a && !b) return false;
			else return true;
		case 'N':
			boolean not = dfs(cs, p, q, r, s, t);
			return !not;
		case 'C':
			a = dfs(cs, p, q, r, s, t);
			b = dfs(cs, p, q, r, s, t);
			if (a && !b) return false;
			else return true;
		case 'E':
			a = dfs(cs, p, q, r, s, t);
			b = dfs(cs, p, q, r, s, t);
			if (a && b || !a && !b) return true;
			return false;
		case 'p':
			return p == 1;
		case 'q':
			return q == 1;
		case 'r':
			return r == 1;
		case 's':
			return s == 1;
		case 't':
			return t == 1;
		}
		return true;
	}
	
	void read() {
		while (true) {
			String data = ns();
			if (data.equals("0")) break;
			boolean judge = true;
			for (int p = 0; p < 2 && judge; ++p) {
				for (int q = 0; q < 2 && judge; ++q) {
					for (int r = 0; r < 2 && judge; ++r) {
						for (int s = 0; s < 2 && judge; ++s) {
							for (int t = 0; t < 2 && judge; ++t) {
								tot = 0;
								if (!dfs(data.toCharArray(), p, q, r, s, t)) {
									judge = false;
									break;
								}
							}
						}
					}
				}
			}
			if (judge) out.println("tautology");
			else out.println("not");
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

