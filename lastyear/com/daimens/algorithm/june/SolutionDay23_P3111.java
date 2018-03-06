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
 *         3111.K Best
 * 
 *         Description
 * 
 *         Demy has n jewels. Each of her jewels has some value vi and weight
 *         wi.
 * 
 *         Since her husband John got broke after recent financial crises, Demy
 *         has decided to sell some jewels. She has decided that she would keep
 *         k best jewels for herself. She decided to keep such jewels that their
 *         specific value is as large as possible. That is, denote the specific
 *         value of some set of jewels S = {i1, i2, …, ik} as
 * 
 *         .
 * 
 *         Demy would like to select such k jewels that their specific value is
 *         maximal possible. Help her to do so.
 * 
 *         Input
 * 
 *         The first line of the input file contains n — the number of jewels
 *         Demy got, and k — the number of jewels she would like to keep (1 ≤ k
 *         ≤ n ≤ 100 000).
 * 
 *         The following n lines contain two integer numbers each — vi and wi (0
 *         ≤ vi ≤ 106, 1 ≤ wi ≤ 106, both the sum of all vi and the sum of all
 *         wi do not exceed 107).
 * 
 *         Output
 * 
 *         Output k numbers — the numbers of jewels Demy must keep. If there are
 *         several solutions, output any one.
 * 
 *         Sample Input
 * 
 *         3 2 1 1 1 2 1 3 Sample Output
 * 
 *         1 2
 *
 */
public class SolutionDay23_P3111 {
	
	static class Pair implements Comparable<Pair>{
		int a;
		int b;
		int id;
		
		@Override
		public int compareTo(Pair o) {
			double that = o.a - L * o.b;
			double thiz = this.a - L * this.b;
			return that == thiz ? 0 : (that > thiz) ? 1 : -1;
		}
	}
	
	static double L;
	static Pair[] p;
	static int N;
	static int K;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		K = in.nextInt();
		p = new Pair[N];
		ans = new int[K];
		for (int i = 0; i < N; ++i) p[i] = new Pair();
		
		double max = 0;
		for (int i = 0; i < N; ++i){
			p[i].a = in.nextInt();
			p[i].b = in.nextInt();
			p[i].id = i + 1;
			max = Math.max(max, p[i].a / (p[i].b * 1.0 ));
		}
		
		double lf = 0.0;
		double rt = max;
		for (int i = 0; i < 50; ++i){
			double mid = (lf + rt) / 2;
			if (!valid(mid)) rt = mid;
			else lf = mid;
		}
		for (int i = 0; i < K; ++i) ans[i] = p[i].id;
		
		StringBuilder sb = new StringBuilder();
		Arrays.sort(ans);
		for (int i : ans){
			sb.append(i + " ");
		}
		System.out.println(sb.toString().trim());
	}
	
	static int[] ans;
	public static boolean valid(double mid){
		L = mid;
		Arrays.sort(p);
		double sum = 0.0;
		for (int i = 0; i < K; ++i){
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
