package com.daimens.algorithm.january;

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

public class SolutionDay16_P2151 {
	
	String INPUT = "./data/judge/201801/P2151.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay16_P2151().run();
	}
	
	void read() {
		while(true) {
			int m = ni();
			int t = ni();
			int k = ni();
			if (m + t + k == 0) break;
			
			double[][] pij = new double[t][m];
			for (int i = 0; i < t; ++i) {
				for (int j = 0; j < m; ++j) {
					double p = nd();
					pij[i][j] = p;
				}
			}
			
			// 第t个队， 前m到题，做对k道题的概率
			double[][][] f = new double[t + 16][m + 16][m + 16];
			
			for (int i = 0; i < t; ++i) {
				f[i][0][0] = 1;
				
				// 答对0题的概率
				for (int j = 0; j < m; ++j) {
					f[i][j + 1][0] = f[i][j][0] * (1 - pij[i][j]);
				}
				for (int j = 0; j < m; ++j) {
					for (int l = 1; l <= m; ++l) {
						f[i][j + 1][l] = f[i][j][l - 1] * pij[i][j] + f[i][j][l] * (1 - pij[i][j]);
					}
				}
			}
			// 每个队 答对 k道题的概率
			double[][] s = new double[t + 16][m + 16];
			for (int i = 0; i < t; ++i) {
				s[i][0] = f[i][m][0];
				for (int j = 1; j <= m; ++j) {
					s[i][j] = s[i][j - 1] +  f[i][m][j];
				}
			}
			
			double P1 = 1.0; // 所有队至少答对一题的概率
			for (int i = 0; i < t; ++i) {
				P1 = P1 * (s[i][m] - s[i][0]);
			}
			double P2 = 1.0; // 所有队至少答对1~k-1题的概率
			for (int i = 0; i < t; ++i) {
				P2 = P2 * (s[i][k - 1] - s[i][0]);
			}
			
			out.printf("%.3f\n", P1 - P2);
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

