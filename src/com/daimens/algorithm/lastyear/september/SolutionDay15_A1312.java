package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay15_A1312 {
	
	String INPUT = "./data/judge/201709/A1312.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay15_A1312().run();
	}
	
	static final long B1 = 9973;
	static final long B2 = 1000000007;
	
	int base64(char ch) {
		if (ch >= 'A' && ch <= 'Z') {
			return ch - 'A';
		}
		if (ch >= 'a' && ch <= 'z') {
			return ch - 'a' + 26;
		}
		if (ch >= '0' && ch <= '9') {
			return ch - '0' + 52;
		}
		return ch == '+' ? 62 : 63;
	}
	
	void base64_to_bit(char[][] raw, int[][] bit, int n) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < raw[i].length; ++j) {
				int base = base64(raw[i][j]);
				for (int k = 0; k < 6; ++k) {
					bit[i][j * 6 + 5 - k]  = (base >> k) & 1;
				}
			}
		}
	}
	
	long computePatternHash(int[][] bit, int n, int m){
		long[][] hs = new long[n][m];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				hs[i][j] = (j > 0 ? hs[i][j - 1] : 0) * B1 + bit[i][j];
			}
		}
		
		long[][] hs2 = new long[n][m];
		for (int j = 0; j < m; ++j) {
			for (int i = 0; i < n; ++i) {
				hs2[i][j] = (i > 0 ? hs2[i - 1][j] : 0) * B2 + hs[i][j];
			}
		}
		
		
		return hs2[n - 1][m - 1];
	}
	
	long[][] computeHash(int[][] imageBit, int n, int m, int p){
		long[][] hs = new long[n][m];
		for (int i = 0; i < n; ++i) {
			long pw = 1;
			for (int j = 0; j < p; ++j) {
				hs[i][j] = (j > 0 ? hs[i][j - 1] : 0) * B1 + imageBit[i][j];
				pw *= B1;
			}
			
			for (int j = p; j < m; ++j) {
				hs[i][j] = hs[i][j - 1] * B1 + imageBit[i][j] - pw * imageBit[i][j - p];
			}
		}
		
		long[][] hs2 = new long[n][m];
		for (int j = 0; j < m; ++j) {
			long pw = 1;
			for (int i = 0; i < p; ++i) {
				hs2[i][j] = (i > 0 ? hs2[i - 1][j] : 0) * B2 + hs[i][j];
				pw *= B2;
			}
			
			for (int i = p; i < n; ++i) {
				hs2[i][j] = hs2[i - 1][j] * B2 + hs[i][j] - pw * hs[i - p][j];
			}
		}
		
		return hs2;
	}
	
	void rotate(int[][] bit, int n) {
		int[][] tmp = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				tmp[i][j] = bit[i][j];
			}
		}
		
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				bit[j][n - i - 1] = tmp[i][j];
			}
		}
	}
	
	void overturn(int[][] bit, int n) {
		int[][] tmp = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				tmp[i][j] = bit[i][j];
			}
		}
		
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				bit[i][n - j - 1] = tmp[i][j];
			}
		}
	}
	
	void read() {
		while (true) {
			int W = ni();
			int H = ni();
			int P = ni();
			if (W + P + H == 0) break;
			
			char[][] image = new char[H][];
			char[][] pattern = new char[P][];
			for (int i = 0; i < H; ++i) {
				image[i] = ns().toCharArray();
			}
			
			for (int i = 0; i < P; ++i) {
				pattern[i] = ns().toCharArray();
			}
			

			if (H < P || W < P) {
				out.println(0);
				continue;
			}
			
			int[][] imageBit   = new int[H][1024];
			int[][] patternBit = new int[P][128];
			
			base64_to_bit(image, imageBit, H);
			base64_to_bit(pattern, patternBit, P);
			
			Map<Long, Integer> mem = new HashMap<>();
			
			for (int d = 0; d < 8; ++d) {
				rotate(patternBit, P);
				if (d == 4) {
					overturn(patternBit, P);
				}
				long pat = computePatternHash(patternBit, P, P);
				Num.inc(mem, pat);
			}
			
			long[][] hs = computeHash(imageBit, H, W, P);
			int ans = 0;
			for (int i = 0; i < H; ++i) {
				for (int j = 0; j < W; ++j) {
					if (i >= P - 1 && j >= P - 1) {
						if (mem.containsKey(hs[i][j])) {
							ans ++;
						}
					}
				}
			}
			
			out.println(ans);
		}
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
}

