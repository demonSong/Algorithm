package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionDay07_P3494 {
	
	String INPUT = "./data/judge/201709/P3494.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay07_P3494().run();
	}
	
	int N, M;
	int[][] matrix;
	void solve() {
		while (more()) {
			N = ni();
			M = ni();
			matrix = new int[N][M];
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < M; ++j) {
					matrix[i][j] = ni();
				}
			}
			
			for (int j = 0; j < M; ++j) {
				for (int i = 1; i < N; ++i) {
					if (matrix[i][j] == 1) {
						matrix[i][j] += matrix[i - 1][j];
					}
				}
			}
			
			int sum = 0;
			for (int i = 0; i < N; ++i) {
				sum = Math.max(sum, area(matrix[i]));
			}
			
			out.println(sum);
		}
	}
	
	int area(int[] h) {
		int sum = 0;
		int n   = h.length;
		int[] stack = new int[n + 16];
		int[] L     = new int[n + 16];
		int[] R     = new int[n + 16];
		
		int t = 0;
		for (int i = 0; i < n; ++i) {
			while (t > 0 && h[i] <= h[stack[t - 1]]) t--;
			L[i] = t == 0 ? 0 : stack[t - 1] + 1;
			stack[t++] = i;
		}
		
		t = 0;
		for (int i = n - 1; i >= 0; --i) {
			while (t > 0 && h[i] <= h[stack[t - 1]]) t--;
			R[i] = t == 0 ? n : stack[t - 1];
			stack[t++] = i;
		}
		
		for (int i = 0; i < n; ++i) {
			sum = Math.max(sum, h[i] * (R[i] - L[i]));
		}
		
		return sum;
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

