package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author DemonSong
 * 
 *         3273.Monthly Expense Description
 * 
 *         Farmer John is an astounding accounting wizard and has realized he
 *         might run out of money to run the farm. He has already calculated and
 *         recorded the exact amount of money (1 ≤ moneyi ≤ 10,000) that he will
 *         need to spend each day over the next N (1 ≤ N ≤ 100,000) days.
 * 
 *         FJ wants to create a budget for a sequential set of exactly M (1 ≤ M
 *         ≤ N) fiscal periods called "fajomonths". Each of these fajomonths
 *         contains a set of 1 or more consecutive days. Every day is contained
 *         in exactly one fajomonth.
 * 
 *         FJ's goal is to arrange the fajomonths so as to minimize the expenses
 *         of the fajomonth with the highest spending and thus determine his
 *         monthly spending limit.
 * 
 *         Input
 * 
 *         Line 1: Two space-separated integers: N and M Lines 2..N+1: Line i+1
 *         contains the number of dollars Farmer John spends on the ith day
 *         Output
 * 
 *         Line 1: The smallest possible monthly limit Farmer John can afford to
 *         live with. Sample Input
 * 
 *         7 5 100 400 300 100 500 101 400 Sample Output
 * 
 *         500 Hint
 * 
 *         If Farmer John schedules the months so that the first two days are a
 *         month, the third and fourth are a month, and the last three are their
 *         own months, he spends at most $500 in any month. Any other method of
 *         scheduling gives a larger minimum monthly limit.
 * 
 *
 */
public class SolutionDay22_P3273 {
	
	static int N;
	static int M;
	static int[] money;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		money = new int[N];
		
		int max = 0;
		int sum = 0;
		for (int i = 0; i < N; ++i){
			money[i] = in.nextInt();
			max = Math.max(max, money[i]);
			sum += money[i];
		}
		
		int lf = max;
		int rt = sum;
		
		while (lf < rt){
			int mid = lf + (rt - lf) / 2;
			if (valid(mid) > M) lf = mid + 1;
			else rt = mid;
		}
		if (valid(lf) <= M) System.out.println(lf);
		else System.out.println(0);
	}
	
	public static int valid(int m){
		int cnt = 0;
		int sum = 0;
		for (int i = 0; i < N; ++i){
			sum += money[i];
			if (sum == m){
				sum = 0;
				cnt++;
			}
			else if (sum > m){
				sum = money[i];
				cnt++;
			}
		}
		if (sum != 0) cnt++;
		return cnt;
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
