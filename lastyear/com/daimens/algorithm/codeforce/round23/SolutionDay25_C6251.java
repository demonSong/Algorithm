package com.daimens.algorithm.codeforce.round23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionDay25_C6251 {
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[][] g = new int[N][N];
		for (int i = 0; i < N; ++i){
			for (int j = 0; j < N; ++j){
				g[i][j] = in.nextInt();
			}
		}
		System.out.println(solve(g) ? "Yes" : "No");
	}
	
	public static boolean solve(int[][] g){
		int N = g.length;
		for (int i = 0; i < N; ++i){
			for (int j = 0; j < N; ++j){
				if (g[i][j] != 1){
					boolean yes = false;
					for (int k = 0; k < N; ++k){
						if (j == k) continue;
						for (int l = 0; l < N; ++l){
							if (l == i) continue;
							if (g[i][j] == g[i][k] + g[l][j]){
								yes = true;
								k = N;
								break;
							}
						}
					}
					if (!yes) return false;
				}
			}
		}
		return true;
	}
	
	
	static class Scanner {

		private BufferedReader br;
		private StringTokenizer tok;

		public Scanner(InputStream is) throws IOException {
			br = new BufferedReader(new InputStreamReader(is));
			getLine();
		}

		private void getLine() throws IOException {
			while (tok == null || !tok.hasMoreTokens()) {
				tok = new StringTokenizer(br.readLine());
			}
		}

		private boolean hasNext() {
			return tok.hasMoreTokens();
		}

		public String next() throws IOException {
			if (hasNext()) {
				return tok.nextToken();
			} else {
				getLine();
				return tok.nextToken();
			}
		}

		public int nextInt() throws IOException {
			if (hasNext()) {
				return Integer.parseInt(tok.nextToken());
			} else {
				getLine();
				return Integer.parseInt(tok.nextToken());
			}
		}

		public long nextLong() throws IOException {
			if (hasNext()) {
				return Long.parseLong(tok.nextToken());
			} else {
				getLine();
				return Long.parseLong(tok.nextToken());
			}
		}

		public double nextDouble() throws IOException {
			if (hasNext()) {
				return Double.parseDouble(tok.nextToken());
			} else {
				getLine();
				return Double.parseDouble(tok.nextToken());
			}
		}
	}
}
