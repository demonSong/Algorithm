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
 *         1995.Raising Modulo Numbers
 * 
 *         Description
 * 
 *         People are different. Some secretly read magazines full of
 *         interesting girls' pictures, others create an A-bomb in their cellar,
 *         others like using Windows, and some like difficult mathematical
 *         games. Latest marketing research shows, that this market segment was
 *         so far underestimated and that there is lack of such games. This kind
 *         of game was thus included into the KOKODáKH. The rules follow:
 * 
 *         Each player chooses two numbers Ai and Bi and writes them on a slip
 *         of paper. Others cannot see the numbers. In a given moment all
 *         players show their numbers to the others. The goal is to determine
 *         the sum of all expressions AiBi from all players including oneself
 *         and determine the remainder after division by a given number M. The
 *         winner is the one who first determines the correct result. According
 *         to the players' experience it is possible to increase the difficulty
 *         by choosing higher numbers.
 * 
 *         You should write a program that calculates the result and is able to
 *         find out who won the game.
 * 
 *         Input
 * 
 *         The input consists of Z assignments. The number of them is given by
 *         the single positive integer Z appearing on the first line of input.
 *         Then the assignements follow. Each assignement begins with line
 *         containing an integer M (1 <= M <= 45000). The sum will be divided by
 *         this number. Next line contains number of players H (1 <= H <=
 *         45000). Next exactly H lines follow. On each line, there are exactly
 *         two numbers Ai and Bi separated by space. Both numbers cannot be
 *         equal zero at the same time. Output
 * 
 *         For each assingnement there is the only one line of output. On this
 *         line, there is a number, the result of expression (A1B1+A2B2+ ...
 *         +AHBH)mod M.
 * 
 *         Sample Input
 * 
 *         3 16 4 2 3 3 4 4 5 5 6 36123 1 2374859 3029382 17 1 3 18132 Sample
 *         Output
 * 
 *         2 13195 13
 *
 */
public class SolutionDay21_P1995 {
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int Z = in.nextInt();
		while ((Z--) != 0){
			int M = in.nextInt();
			int N = in.nextInt();
			long ans = 0;
			for (int i = 0; i < N; ++i){
				long a = in.nextLong();
				long b = in.nextLong();
				ans = (ans + quickPow(a, b, M)) % M;
			}
			System.out.println(ans);
		}
	}
	
	public static long quickMul(long a, long b, long mod){
		long res = 0;
		while (b != 0){
			if ((b & 1) != 0){
				res = (res + a) % mod;
			}
			b >>= 1;
			a = (a + a) % mod;
		}
		return res;
	}
	
	public static long quickPow(long a, long b, long mod){
		long res = 1;
		while (b != 0){
			if ((b & 1) != 0){
				res = quickMul(res, a, mod);
			}
			b >>= 1;
			a = quickMul(a, a, mod);
		}
		return res;
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
