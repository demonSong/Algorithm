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
 *         3045.Cow Acrobats
 * 
 *         Description
 * 
 *         Farmer John's N (1 <= N <= 50,000) cows (numbered 1..N) are planning
 *         to run away and join the circus. Their hoofed feet prevent them from
 *         tightrope walking and swinging from the trapeze (and their last
 *         attempt at firing a cow out of a cannon met with a dismal failure).
 *         Thus, they have decided to practice performing acrobatic stunts.
 * 
 *         The cows aren't terribly creative and have only come up with one
 *         acrobatic stunt: standing on top of each other to form a vertical
 *         stack of some height. The cows are trying to figure out the order in
 *         which they should arrange themselves ithin this stack.
 * 
 *         Each of the N cows has an associated weight (1 <= W_i <= 10,000) and
 *         strength (1 <= S_i <= 1,000,000,000). The risk of a cow collapsing is
 *         equal to the combined weight of all cows on top of her (not including
 *         her own weight, of course) minus her strength (so that a stronger cow
 *         has a lower risk). Your task is to determine an ordering of the cows
 *         that minimizes the greatest risk of collapse for any of the cows.
 *         Input
 * 
 *         Line 1: A single line with the integer N.
 * 
 *         Lines 2..N+1: Line i+1 describes cow i with two space-separated
 *         integers, W_i and S_i. Output
 * 
 *         Line 1: A single integer, giving the largest risk of all the cows in
 *         any optimal ordering that minimizes the risk. Sample Input
 * 
 *         3 10 3 2 5 3 3 Sample Output
 * 
 *         2 Hint
 * 
 *         OUTPUT DETAILS:
 * 
 *         Put the cow with weight 10 on the bottom. She will carry the other
 *         two cows, so the risk of her collapsing is 2+3-3=2. The other cows
 *         have lower risk of collapsing.
 *
 */
public class SolutionDay22_P3045 {
	
	static class Cow implements Comparable<Cow>{
		int weight;
		int strength;
		public Cow(int weight, int strength){
			this.weight = weight;
			this.strength = strength;
		}
		
		@Override
		public int compareTo(Cow o) {
			return (o.weight + o.strength) - (this.weight + this.strength);
		}
	}
	
	static Cow[] cows;
	static int N;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		cows = new Cow[N];
		long sum = 0;
		for(int i = 0; i < N; ++i){
			int w = in.nextInt();
			int s = in.nextInt();
			cows[i] = new Cow(w, s);
			sum += cows[i].weight;
		}
		Arrays.sort(cows);
		long risk = Integer.MIN_VALUE;
		for (int i = 0; i < N; ++i){
			sum -= cows[i].weight;
			risk = Math.max(risk, sum - cows[i].strength);
		}
		System.out.println(risk);
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
