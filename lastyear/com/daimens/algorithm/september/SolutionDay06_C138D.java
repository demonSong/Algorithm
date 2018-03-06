package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SolutionDay06_C138D {
	
	String INPUT = "./data/judge/201709/C138D.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay06_C138D().run();
	}
	
	int n, m;
	char[][] board;
	void solve() {
		n = ni();
		m = ni();
		
		board = new char[n][m];
		for (int i = 0; i < n; ++i) {
			board[i] = ns().toCharArray();
		}
		
		out.println((calc(0) ^ calc(1)) != 0 ? "WIN" : "LOSE");
	}
	
	int[][][][] dp;
	private int calc(int mod) {
		int N = n + m;	
		dp = new int[N + 2][N + 2][N + 2][N + 2];
		ArrayUtils.fill(dp, -1);
		return grundy(0, 0, N, N, mod);
	}
	
	int grundy(int row_min, int col_min, int row_max, int col_max, int mod) {
		if (dp[row_min][col_min][row_max][col_max] != -1) return dp[row_min][col_min][row_max][col_max];
		
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (((i + j) & 1) == mod) {
					int ni = i + j;
					int nj = j - i + n;
					if (inside(ni, nj, row_min, col_min, row_max, col_max)) {
						if (board[i][j] == 'L') {
							int g1 = grundy(row_min, col_min, ni,      col_max, mod);
							int g2 = grundy(ni + 1,  col_min, row_max, col_max, mod);
							set.add(g1 ^ g2);
						}
						
						if (board[i][j] == 'R') {
							int g1 = grundy(row_min, col_min, row_max, nj,      mod);
							int g2 = grundy(row_min, nj + 1,  row_max, col_max, mod);
							set.add(g1 ^ g2);
						}
						
						if (board[i][j] == 'X') {
							int g1 = grundy(row_min, col_min, ni,      nj,      mod);
							int g2 = grundy(row_min, nj + 1,  ni,      col_max, mod);
							int g3 = grundy(ni + 1,  col_min, row_max, nj,      mod);
							int g4 = grundy(ni + 1,  nj + 1,  row_max, col_max, mod);
							set.add(g1 ^ g2 ^ g3 ^ g4);
						}
					}
				}
			}
		}
		
		int res = 0;
		while (set.contains(res)) res ++;
		return dp[row_min][col_min][row_max][col_max] = res;
	}
	
	boolean inside(int x, int y, int row_min, int col_min, int row_max, int col_max) {
		return x >= row_min && x < row_max && y >= col_min && y < col_max;
	}

	FastScanner in;
	PrintWriter out;
	
	void run() throws IOException {
		boolean oj;
		try {
			oj = ! System.getProperty("user.dir").equals("F:\\java_workspace\\leetcode");
		} catch (Exception e) {
			oj = System.getProperty("ONLINE_JUDGE") != null;
		}
		
		InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
		in = new FastScanner(is);
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		solve();
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
}

