package com.daimens.algorithm.august;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay31_P3532 {
	
	String INPUT = "./data/judge/201708/P3532.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay31_P3532().run();
	}
	
	static final int MAX_N = 100;
	static final double EPS = 1E-8;
			
	int N;
	void solve() {
		while (more()){
			N = ni();
			int M = ni();
			double[][] matrix = new double[MAX_N][MAX_N];
			for (int i = 0; i < M; ++i){
				int x = ni();
				int y = ni();
				int r = ni();
				--x;
				--y;
				double s = 1.0 / r;
				matrix[x][x] += s;
				matrix[y][y] += s;
				matrix[x][y] -= s;
				matrix[y][x] -= s;
			}
			N++;
			matrix[0][N] = 1.0;
			matrix[N - 2][N] = -1.0;
			matrix[N - 1][N - 2] = 1.0;
			out.printf("%.2f\n", gaussian(matrix));
		}
	}
	
	public double gaussian(double[][] cal){
		int n = N;
		for (int i = 0; i < n; ++i){
			int r;
			for (r = i; r < n; ++r){
				if (Math.abs(cal[r][i]) >= EPS){
					break;
				}
			}
			
			if (r == n) continue;
			
			swap(cal, i, r);
			
			for (int j = i + 1; j <= n; ++j){
				cal[i][j] /= cal[i][i];
			}
			
			cal[i][i] = 1.0;
			for (int j = 0; j < n; ++j){
				if (i != j){
					for (int k = i + 1; k <= n; ++k){
						cal[j][k] -= (cal[j][i] * cal[i][k]);
					}
					cal[j][i] = 0.0;
				}
			}
		}
		
		return cal[0][N] / cal[0][0];
	}
	
	public void swap(double[][] matrix, int i, int j){
		int n = matrix[0].length;
		for (int col = 0; col < n; ++col){
			double tmp = matrix[i][col];
			matrix[i][col] = matrix[j][col];
			matrix[j][col] = tmp;
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
}
