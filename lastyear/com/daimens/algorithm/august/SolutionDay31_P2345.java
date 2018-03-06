package com.daimens.algorithm.august;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay31_P2345 {
	
	String INPUT = "./data/judge/201708/P2345.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay31_P2345().run();
	}
	
	static final int MAX_N = 255;
	int[][] matrix = new int[MAX_N][MAX_N];
	
	void solve() {
		while (more()){
			int n = ni();
			for (int i = 0; i < n; ++i){
				int j = ni();
				while (j != -1){
					j--;
					matrix[j][i] = 1;
					matrix[j][n] = 1;
					j = ni();
				}
			}
			int[] ans = gauss(matrix, n);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < ans.length; ++i){
				if (ans[i] == 1){
					sb.append(" " + (i + 1));
				}
			}
			out.println(sb.deleteCharAt(0).toString());
		}
	}

	/*******************高斯消元法*********************/
	public int[] gauss(int[][] matrix, int n){
		int[] ans = new int[n];
		for (int i = 0; i < n; ++i){
			
			int row = i;
			for (int j = i; j < n; ++j){
				if (matrix[j][i] > matrix[row][i]){
					row = j;
				}
			}
			
			swap(matrix, i, row);
			
			for (int j = i + 1; j < n; ++j){
				if (matrix[j][i] == 1){
					for (int k = i; k <= n; ++k){
						matrix[j][k] ^= matrix[i][k];
					}
				}
			}
		}
		
		for (int i = n - 1; i >= 0; --i){
			ans[i] = matrix[i][n];
			for (int j = i - 1; j >= 0; --j){
				matrix[j][n] ^= (ans[i] & matrix[j][i]);
			}
		}
		
		return ans;
	}
	
	public void swap(int[][] matrix, int i, int j){
		int n = matrix[0].length;
		for (int col = 0; col < n; ++col){
			int tmp = matrix[i][col];
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
