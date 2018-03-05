package com.daimens.algorithm.feburary;

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

public class SolutionDay04_P3274 {
	
	String INPUT = "./data/judge/201802/P3274.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay04_P3274().run();
	}
	
	
	int mod = 99997;
	List<Integer>[] hash = new ArrayList[mod];
	int n, m = 0;
	int[][] c = new int[100001][31];
	int[][] sum = new int[100001][31];
	
	boolean cmp(int x, int y) {
		int t = 0;
		while (c[x][t] == c[y][t] && t < m) t++;
		return t == m ? true : false;
	}
	
	void read() {
		n = ni();
		m = ni();
		sum = new int[100001][31];
		c   = new int[100001][31];
		for (int i = 0; i < mod; ++i) hash[i] = new ArrayList<Integer>();
		for (int i = 1; i <= n; ++i) {
			int lo = ni();
			for (int j = 0; j < m; ++j) {
				int x = lo % 2;
				lo >>= 1;
				sum[i][j] = sum[i - 1][j] + x;
			}
		}
		for (int i = 0; i <= n; ++i) {
			int su = 0;
			for (int j = 0; j < m; ++j) {
				c[i][j] = sum[i][j] - sum[i][0];
				su = su % mod + c[i][j] << 2;
				su = su % mod;
			}
			if (su < 0) su *= -1;
			hash[su].add(i);
		}
		
		int ma = 0;
		for (int i = 0; i < mod; ++i) {
			if (hash[i].size() > 1) {
				for (int j = 0; j < hash[i].size() - 1; ++j) {
					for (int k = j + 1; k < hash[i].size(); ++k) {
						if (cmp(hash[i].get(j), hash[i].get(k)) && hash[i].get(k) - hash[i].get(j) > ma) {
							ma = hash[i].get(k) - hash[i].get(j);
						}
					}
				}
			}
		}
		
		out.println(ma);
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

