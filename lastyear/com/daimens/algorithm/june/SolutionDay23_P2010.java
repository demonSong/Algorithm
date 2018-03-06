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
 *         2010.Moo University - Financial Aid
 * 
 *         Description
 * 
 *         Bessie noted that although humans have many universities they can
 *         attend, cows have none. To remedy this problem, she and her fellow
 *         cows formed a new university called The University of
 *         Wisconsin-Farmside,"Moo U" for short.
 * 
 *         Not wishing to admit dumber-than-average cows, the founders created
 *         an incredibly precise admission exam called the Cow Scholastic
 *         Aptitude Test (CSAT) that yields scores in the range
 *         1..2,000,000,000.
 * 
 *         Moo U is very expensive to attend; not all calves can afford it.In
 *         fact, most calves need some sort of financial aid (0 <= aid
 *         <=100,000). The government does not provide scholarships to calves,so
 *         all the money must come from the university's limited fund (whose
 *         total money is F, 0 <= F <= 2,000,000,000).
 * 
 *         Worse still, Moo U only has classrooms for an odd number N (1 <= N <=
 *         19,999) of the C (N <= C <= 100,000) calves who have applied.Bessie
 *         wants to admit exactly N calves in order to maximize educational
 *         opportunity. She still wants the median CSAT score of the admitted
 *         calves to be as high as possible.
 * 
 *         Recall that the median of a set of integers whose size is odd is the
 *         middle value when they are sorted. For example, the median of the set
 *         {3, 8, 9, 7, 5} is 7, as there are exactly two values above 7 and
 *         exactly two values below it.
 * 
 *         Given the score and required financial aid for each calf that
 *         applies, the total number of calves to accept, and the total amount
 *         of money Bessie has for financial aid, determine the maximum median
 *         score Bessie can obtain by carefully admitting an optimal set of
 *         calves.
 * 
 *         Input
 * 
 *         Line 1: Three space-separated integers N, C, and F
 * 
 *         Lines 2..C+1: Two space-separated integers per line. The first is the
 *         calf's CSAT score; the second integer is the required amount of
 *         financial aid the calf needs Output
 * 
 *         Line 1: A single integer, the maximum median score that Bessie can
 *         achieve. If there is insufficient money to admit N calves,output -1.
 *         Sample Input
 * 
 *         3 5 70 30 25 50 21 20 20 5 18 35 30 Sample Output
 * 
 *         35 Hint
 * 
 *         Sample output:If Bessie accepts the calves with CSAT scores of 5, 35,
 *         and 50, the median is 35. The total financial aid required is 18 + 30
 *         + 21 = 69 <= 70.
 *
 */
public class SolutionDay23_P2010 {
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		
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
