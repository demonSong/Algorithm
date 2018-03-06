package com.daimens.algorithm.codeforce.round23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionDay25_C6252 {
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int b = in.nextInt();
		long max = 0;
		for (long x = 0; x <= m * b; ++x){
			int y = b - (int)Math.ceil(x / (m * 1.0));
			max = Math.max(max, sum(x,y));
		}
//		int x = m * b / 2;
//		max = Math.max(max, sum(x,b - (int)Math.ceil(x / (m * 1.0))));
//		max = Math.max(max, sum(x + 1, b - (int)Math.ceil((x + 1) / (m * 1.0))));
//		max = Math.max(max, sum(x - 1, b - (int)Math.ceil((x - 1) / (m * 1.0))));
		System.out.println(max);
	}
	
	public static long sum(long x, long y){
		long sum = 0;
		sum += (x + 1) * (y * (y + 1)) / 2;
		sum += y * (x * (x + 1)) / 2;
		sum += x * (x + 1) / 2;	
		return sum;
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
