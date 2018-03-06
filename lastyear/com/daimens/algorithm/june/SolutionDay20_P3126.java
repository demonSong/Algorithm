package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author DemonSong
 * 
 *         3126.Prime Path
 * 
 *         Description
 * 
 *         The ministers of the cabinet were quite upset by the message from the
 *         Chief of Security stating that they would all have to change the
 *         four-digit room numbers on their offices. — It is a matter of
 *         security to change such things every now and then, to keep the enemy
 *         in the dark. — But look, I have chosen my number 1033 for good
 *         reasons. I am the Prime minister, you know! — I know, so therefore
 *         your new number 8179 is also a prime. You will just have to paste
 *         four new digits over the four old ones on your office door. — No,
 *         it’s not that simple. Suppose that I change the first digit to an 8,
 *         then the number will read 8033 which is not a prime! — I see, being
 *         the prime minister you cannot stand having a non-prime number on your
 *         door even for a few seconds. — Correct! So I must invent a scheme for
 *         going from 1033 to 8179 by a path of prime numbers where only one
 *         digit is changed from one prime to the next prime.
 * 
 *         Now, the minister of finance, who had been eavesdropping, intervened.
 *         — No unnecessary expenditure, please! I happen to know that the price
 *         of a digit is one pound. — Hmm, in that case I need a computer
 *         program to minimize the cost. You don't know some very cheap software
 *         gurus, do you? — In fact, I do. You see, there is this programming
 *         contest going on... Help the prime minister to find the cheapest
 *         prime path between any two given four-digit primes! The first digit
 *         must be nonzero, of course. Here is a solution in the case above.
 *         1033 1733 3733 3739 3779 8779 8179 The cost of this solution is 6
 *         pounds. Note that the digit 1 which got pasted over in step 2 can not
 *         be reused in the last step – a new 1 must be purchased. Input
 * 
 *         One line with a positive number: the number of test cases (at most
 *         100). Then for each test case, one line with two numbers separated by
 *         a blank. Both numbers are four-digit primes (without leading zeros).
 *         Output
 * 
 *         One line for each case, either with a number stating the minimal cost
 *         or containing the word Impossible. Sample Input
 * 
 *         3 1033 8179 1373 8017 1033 1033 Sample Output
 * 
 *         6 7 0
 * 
 * 
 *
 */
public class SolutionDay20_P3126 {

	/**
	 * bfs + seive
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		seive();
		int N = in.nextInt();
		for (int i = 0; i < N; ++i){
			int from = in.nextInt();
			int to = in.nextInt();
			System.out.println(solve(from, to));
		}
	}
	
	static int MAX = 9999 + 16;
	static boolean[] isPrime;
	
	public static void seive(){
		isPrime = new boolean[MAX];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i < MAX; ++i){
			if (isPrime[i]){
				for (int j = 2 * i; j < MAX; j += i){
					isPrime[j] = false;
				}
			}
		}
	}
	
	public static int solve(int from, int to){
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(from);
		int[] dp = new int[MAX];
		Arrays.fill(dp, 1 << 30);
		dp[from] = 0;
		while (!queue.isEmpty()) {
			int prime = queue.poll();
			for (int j = 0; j < 4; ++j) {
				for (int k = 0; k < 10; ++k) {
					int next = next(prime, j, k);
					if (next == prime || next < 1000 || !isPrime[next] || dp[next] <= dp[prime])
						continue;
					dp[next] = dp[prime] + 1;
					queue.offer(next);
				}
			}
		}
		return dp[to];
	}
	
	public static int next(int prime, int digit, int change){
		switch (digit) {
		case 0:
			return prime / 10 * 10 + change;
		case 1:
			return prime / 100 * 100 + prime % 10 + change * 10;
		case 2:
			return prime / 1000 * 1000 + prime % 100 + change * 100;
		case 3:
			return prime % 1000 + change * 1000;
		}
		return 0;
	}
	
//	public static int solve(int from, int to){
//		Queue<int[]> queue = new LinkedList<int[]>();
//		queue.offer(toDigit(from));
//		int cost = 0;
//		while (!queue.isEmpty()){
//			int size = queue.size();
//			for (int i = 0; i < size; ++i){
//				int[] prime = queue.poll();
//				if (to == toInt(prime)) return cost; 
//				for (int j = 0; j < prime.length; ++j){
//					int[][] candicate = next(prime, j);
//					for (int k = 0; k < candicate.length; ++k){
//						if (isPrime[toInt(candicate[k])]) queue.offer(candicate[k]);
//					}
//				}
//				
//			}
//			cost++;
//		}
//		return cost;
//	}
//	
//	public static int[] toDigit(int from){
//		char[] digits = Integer.toString(from).toCharArray();
//		return new int[]{digits[0]-'0',digits[1]-'0',digits[2]-'0',digits[3]-'0'};
//	}
//	
//	public static int toInt(int[] nums){
//		int ans = 0;
//		for (int i = 0; i < nums.length; ++i){
//			ans = ans * 10 + nums[i];
//		}
//		return ans;
//	}
//	
//	public static int[][] next(int[] nums, int pos){
//		if (pos == 0){
//			int[][] ans = new int[8][nums.length];
//			for (int i = 1, k = 0; i <= 9; ++i){
//				if (i == nums[pos]) continue;
//				ans[k][0] = i;
//				for (int j = 1; j < nums.length; ++j){
//					ans[k][j] = nums[j];
//				}
//				k++;
//			}
//			return ans;
//		}
//		else{
//			int[][] ans = new int[9][nums.length];
//			for (int i = 0, k = 0; i <= 9; ++i){
//				if (i == nums[pos]) continue;
//				ans[k][pos] = i;
//				for (int j = 0; j < nums.length; ++j){
//					if (j == pos) continue;
//					ans[k][j] = nums[j];
//				}
//				k++;
//			}
//			return ans;
//		}
//	}
	
	
	
	
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
