package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SolutionDay17_C6172 {
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int q = in.nextInt();
		int[][] coffe = new int[n][2];
		for (int i = 0; i < n; ++i){
			coffe[i][0] = in.nextInt();
			coffe[i][1] = in.nextInt();
		}
		Arrays.sort(coffe, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
			}
		});
		
		for (int i = 0; i < q; ++i){
			int[] interval = new int[2];
			interval[0] = in.nextInt();
			interval[1] = in.nextInt();
			
			int[][] cost = new int[n-k+1][2];
			for (int l = 0; l < n - k + 1; ++l){
				int lf = coffe[l][0];
				int rt = coffe[l][1];
				for (int h = 0; h < k; ++h){
					lf = Math.max(lf, coffe[h][0]);
					rt = Math.min(rt, coffe[h][1]);
				}
				cost[l][0] = lf;
				cost[l][1] = rt;
			}
			
			int j = 0;
			while (j < cost.length && cost[j][1] < interval[0]) j++;
			int lf = interval[0];
			int rt = interval[1];
			int sum = 0;
			for (int l = j; l < cost.length && cost[l][0] <= interval[1]; ++l){
				
			}
		}
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
