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
 *         3641.Pseudoprime numbers
 * 
 *         Description
 * 
 *         Fermat's theorem states that for any prime number p and for any
 *         integer a > 1, ap = a (mod p). That is, if we raise a to the pth
 *         power and divide by p, the remainder is a. Some (but not very many)
 *         non-prime values of p, known as base-a pseudoprimes, have this
 *         property for some a. (And some, known as Carmichael Numbers, are
 *         base-a pseudoprimes for all a.)
 * 
 *         Given 2 < p ≤ 1000000000 and 1 < a < p, determine whether or not p is
 *         a base-a pseudoprime.
 * 
 *         Input
 * 
 *         Input contains several test cases followed by a line containing "0
 *         0". Each test case consists of a line containing p and a.
 * 
 *         Output
 * 
 *         For each test case, output "yes" if p is a base-a pseudoprime;
 *         otherwise output "no".
 * 
 *         Sample Input
 * 
 *         3 2 10 3 341 2 341 3 1105 2 1105 3 0 0 Sample Output
 * 
 *         no no yes no yes yes
 *
 * 
 */
public class SolutionDay21_P3641 {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		while (true) {
			int p = in.nextInt();
			int a = in.nextInt();
			if (p == 0 && a == 0)
				break;
			if (a != quickPow(a, p, p)){
				System.out.println("no");
				continue;
			}
			if (isPrime(p)) System.out.println("no");
			else System.out.println("yes");
		}
	}
	
	public static boolean isPrime(int num){
		for (int i = 2; i * i <= num; ++i){
			if (num % i == 0) return false;
		}
		return true;
	}

	public static long quickMul(long a, long b, long mod) {
		long ans = 0;
		while (b != 0) {
			if ((b & 1) != 0) {
				ans = (ans + a) % mod;
			}
			b >>= 1;
			a = (a + a) % mod;
		}
		return ans;
	}

	public static long quickPow(long a, long b, long mod) {
		long ans = 1;
		while (b != 0) {
			if ((b & 1) != 0) {
				ans = quickMul(ans, a, mod);
			}
			b >>= 1;
			a = quickMul(a, a, mod);
		}
		return ans;
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
