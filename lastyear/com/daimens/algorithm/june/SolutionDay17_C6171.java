package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionDay17_C6171 {
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String time = in.next();
		String[] hours = time.trim().split(":");
		String pp = new StringBuilder(hours[0]).reverse().toString();
		int h = Integer.parseInt(pp);
		int m = Integer.parseInt(hours[1]);
		if (h >= 60){
			int l = h % 10;
			if (l == 1){
				int o = Integer.parseInt(hours[0]);
				System.out.println(60 - m + 2 + (20 - o -1) * 60);
			}
			else{
				int o = Integer.parseInt(hours[0]);
				System.out.println(60 - m + 1 + (10 - o -1) * 60);
			}
		}
		else if (h >= m){
			System.out.println(h-m);
		}
		else{
			int x = Integer.parseInt(hours[0]);
			x++;
			if (x == 24){
				System.out.println(60-m);
				return;
			}
			String o = "" + x;
			if (x < 10){
				o = "0" + o;
			}
			int oh = Integer.parseInt(new StringBuilder(o).reverse().toString());
			if (oh >= 60){
				int l = oh % 10;
				if (l == 1){
					int xx = Integer.parseInt(hours[0]);
					System.out.println(60 - m + 2 + (20 - xx -1) * 60);
				}
				else{
					int xx = Integer.parseInt(hours[0]);
					System.out.println(60 - m + 1 + (10 - xx -1) * 60);
				}
			}
			else{
				System.out.println(60 - m + oh);
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
