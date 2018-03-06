package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author DemonSong
 * 
 *         2139.Six degrees of Cowvin bacon
 * 
 *         Description
 * 
 *         The cows have been making movies lately, so they are ready to play a
 *         variant of the famous game "Six Degrees of Kevin Bacon".
 * 
 *         The game works like this: each cow is considered to be zero degrees
 *         of separation (degrees) away from herself. If two distinct cows have
 *         been in a movie together, each is considered to be one 'degree' away
 *         from the other. If a two cows have never worked together but have
 *         both worked with a third cow, they are considered to be two 'degrees'
 *         away from each other (counted as: one degree to the cow they've
 *         worked with and one more to the other cow). This scales to the
 *         general case.
 * 
 *         The N (2 <= N <= 300) cows are interested in figuring out which cow
 *         has the smallest average degree of separation from all the other
 *         cows. excluding herself of course. The cows have made M (1 <= M <=
 *         10000) movies and it is guaranteed that some relationship path exists
 *         between every pair of cows. Input
 * 
 *         Line 1: Two space-separated integers: N and M
 * 
 *         Lines 2..M+1: Each input line contains a set of two or more
 *         space-separated integers that describes the cows appearing in a
 *         single movie. The first integer is the number of cows participating
 *         in the described movie, (e.g., Mi); the subsequent Mi integers tell
 *         which cows were. Output
 * 
 *         Line 1: A single integer that is 100 times the shortest mean degree
 *         of separation of any of the cows. Sample Input
 * 
 *         4 2 3 1 2 3 2 3 4 Sample Output
 * 
 *         100
 * 
 *
 */
public class SolutionDay15_L2139 {
	
	static int INF = 1 << 29;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		int[][] g = new int[N][N];
		
		for (int i = 0; i < N; ++i) Arrays.fill(g[i],INF);
		for (int i = 0; i < N; ++i) g[i][i] = 0;
		
		for (int i = 0; i < M; ++i){
			int n = in.nextInt();
			int[] x = new int[n];
			for (int j = 0; j < n; ++j){
				x[j] = in.nextInt();
				x[j]--;
			}
			for (int k = 0; k < n; ++k){
				for (int l = k + 1; l < n; ++l){
					g[x[k]][x[l]] = g[x[l]][x[k]] = 1;
				}
			}
		}
		
		for (int i = 0; i < N; ++i){
			for (int j = 0; j < N; ++j){
				for (int k = 0; k < N; ++k){
					g[j][k] = Math.min(g[j][k],g[j][i] + g[i][k]);
				}
			}
		}
		
		int ans = INF;
		for (int i = 0; i < N; ++i){
			int sum = 0;
			for (int j = 0; j < N; ++j){
				sum += g[i][j];
			}
			ans = Math.min(ans, sum);
		}
		
		System.out.println(100 * ans / (N - 1));
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
