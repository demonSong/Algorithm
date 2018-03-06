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
 *         2976.Dropping tests
 * 
 *         Description
 * 
 *         In a certain course, you take n tests. If you get ai out of bi
 *         questions correct on test i, your cumulative average is defined to be
 * 
 *         .
 * 
 *         Given your test scores and a positive integer k, determine how high
 *         you can make your cumulative average if you are allowed to drop any k
 *         of your test scores.
 * 
 *         Suppose you take 3 tests with scores of 5/5, 0/1, and 2/6. Without
 *         dropping any tests, your cumulative average is . However, if you drop
 *         the third test, your cumulative average becomes .
 * 
 *         Input
 * 
 *         The input test file will contain multiple test cases, each containing
 *         exactly three lines. The first line contains two integers, 1 ≤ n ≤
 *         1000 and 0 ≤ k < n. The second line contains n integers indicating ai
 *         for all i. The third line contains n positive integers indicating bi
 *         for all i. It is guaranteed that 0 ≤ ai ≤ bi ≤ 1, 000, 000, 000. The
 *         end-of-file is marked by a test case with n = k = 0 and should not be
 *         processed.
 * 
 *         Output
 * 
 *         For each test case, write a single line with the highest cumulative
 *         average possible after dropping k of the given test scores. The
 *         average should be rounded to the nearest integer.
 * 
 *         Sample Input
 * 
 *         3 1 5 0 2 5 1 6 4 2 1 2 7 9 5 6 7 9 0 0 Sample Output
 * 
 *         83 100 Hint
 * 
 *         To avoid ambiguities due to rounding errors, the judge tests have
 *         been constructed so that all answers are at least 0.001 away from a
 *         decision boundary (i.e., you can assume that the average is never
 *         83.4997).
 *
 */
public class SolutionDay22_P2976 {
	
	static class Pair implements Comparable<Pair>{
		int a;
		int b;
		
		@Override
		public int compareTo(Pair o) {
			double ocmp = o.a - L * o.b;
			double tcmp = this.a - L * this.b;
			return ocmp == tcmp ? 0 : (ocmp > tcmp) ? 1 : -1;
		}
	}
	
	static Pair[] p;
	static double L;
	static int k;
	static int n;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		while (true){
			n = in.nextInt();
			k = in.nextInt();
			if (n == 0 && k == 0) break;
			p = new Pair[n];
			for (int i = 0; i < n; ++i){
				p[i] = new Pair();
				p[i].a = in.nextInt();
			}
			for (int i = 0; i < n; ++i){
				p[i].b = in.nextInt();
			}
			
			double lf = 0;
			double rt = 1;
			while (Math.abs(rt -lf) > 1e-4){
				double mid = (lf + rt) / 2;
				if (!valid(mid)) rt = mid;
				else lf = mid;
			}
			System.out.printf("%.0f\n",lf * 100);
		}
	}
	
	public static boolean valid(double mid){
		L = mid;
		Arrays.sort(p);
		double sum = 0;
		for (int i = 0; i < n - k; ++i){
			sum += (p[i].a - L * p[i].b);
		}
		return sum > 0;
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
