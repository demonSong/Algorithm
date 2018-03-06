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
 *         1930.Dead Fraction
 * 
 *         Description
 * 
 *         Mike is frantically scrambling to finish his thesis at the last
 *         minute. He needs to assemble all his research notes into vaguely
 *         coherent form in the next 3 days. Unfortunately, he notices that he
 *         had been extremely sloppy in his calculations. Whenever he needed to
 *         perform arithmetic, he just plugged it into a calculator and
 *         scribbled down as much of the answer as he felt was relevant.
 *         Whenever a repeating fraction was displayed, Mike simply reccorded
 *         the first few digits followed by "...". For instance, instead of
 *         "1/3" he might have written down "0.3333...". Unfortunately, his
 *         results require exact fractions! He doesn't have time to redo every
 *         calculation, so he needs you to write a program (and FAST!) to
 *         automatically deduce the original fractions. To make this tenable, he
 *         assumes that the original fraction is always the simplest one that
 *         produces the given sequence of digits; by simplest, he means the the
 *         one with smallest denominator. Also, he assumes that he did not
 *         neglect to write down important digits; no digit from the repeating
 *         portion of the decimal expansion was left unrecorded (even if this
 *         repeating portion was all zeroes). Input
 * 
 *         There are several test cases. For each test case there is one line of
 *         input of the form "0.dddd..." where dddd is a string of 1 to 9
 *         digits, not all zero. A line containing 0 follows the last case.
 *         Output
 * 
 *         For each case, output the original fraction. Sample Input
 * 
 *         0.2... 0.20... 0.474612399... 0 Sample Output
 * 
 *         2/9 1/5 1186531/2500000 Hint
 * 
 *         Note that an exact decimal fraction has two repeating expansions
 *         (e.g. 1/5 = 0.2000... = 0.19999...).
 *
 */
public class SolutionDay20_P1930 {
	
	static class Fraction{
		long fz;
		long fm;
		
		public Fraction(long fz, long fm){
			long d = gcd(fz, fm);
			fz /= d;
			fm /= d;
			this.fz = fz;
			this.fm = fm;
		}
		
		public Fraction add(Fraction that){
			long a1 = this.fz;
			long b1 = this.fm;
			long a2 = that.fz;
			long b2 = that.fm;
			return new Fraction(a1 * b2 + b1 * a2, b1 * b2);
		}
		
		public long gcd(long a, long b){
			if (b == 0) return a;
			else return gcd(b, a % b);
		}
		
		@Override
		public String toString() {
			return fz + "/" + fm;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		while (true){
			String str = in.next();
			if (str.equals("0")) break;
			str = str.substring(2, str.length() - 3);
			char[] digit = str.toCharArray();
			if (digit.length == 1 && digit[0] == '0'){
				System.out.println("0/1");
				continue;
			}
			Fraction min = new Fraction(1, Long.MAX_VALUE);
			for (int i = 0; i < digit.length - 1; ++i){
				long a1 = 0;
				long b1 = 1;
				for (int j = 0; j <= i; ++j){
					a1 = a1 * 10 + digit[j] - '0';
					b1 = b1 * 10;
				}
				Fraction f1 = new Fraction(a1, b1); 
				long a2 = 0;
				long b2 = 0;
				for (int j = i + 1; j < digit.length; ++j){
					a2 = a2 * 10 + digit[j] - '0';
					b2 = b2 * 10 + 9;
				}
				Fraction f2 = new Fraction(a2, b2 * b1);
				Fraction f3 = f1.add(f2);
				if (f3.fm < min.fm){
					min.fz = f3.fz;
					min.fm = f3.fm;
				}
			}
			
			long a1 = 0;
			long b1 = 0;
			for (int i = 0; i < digit.length; ++i){
				a1 = a1 * 10 + digit[i] - '0';
				b1 = b1 * 10 + 9;
			}
			Fraction f3 = new Fraction(a1, b1);
			if (f3.fm < min.fm){
				min.fz = f3.fz;
				min.fm = f3.fm;
			}
			System.out.println(min.toString());
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
