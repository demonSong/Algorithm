package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * 
 * @author DemonSong
 * 
 *         2429.GCD & LCM Inverse
 * 
 *         Description
 * 
 *         Given two positive integers a and b, we can easily calculate the
 *         greatest common divisor (GCD) and the least common multiple (LCM) of
 *         a and b. But what about the inverse? That is: given GCD and LCM,
 *         finding a and b. Input
 * 
 *         The input contains multiple test cases, each of which contains two
 *         positive integers, the GCD and the LCM. You can assume that these two
 *         numbers are both less than 2^63. Output
 * 
 *         For each test case, output a and b in ascending order. If there are
 *         multiple solutions, output the pair with smallest a + b. Sample Input
 * 
 *         3 60 Sample Output
 * 
 *         12 15
 * 
 * 
 *
 */
public class SolutionDay19_P2429 {
	
	static class Pair{
		long fir;
		long sec;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		while (true){
			long gcd = in.nextLong();
			long lcm = in.nextLong();
			if (gcd == 0 && lcm == 0) break;
			map = new HashMap<>();
			Pair p = solve(gcd, lcm);
			System.out.println(p.fir * gcd + " " + p.sec * gcd);
		}
	}
	
	private static Pair solve(long a, long b){
		long c = b / a;
		find(c, 26632);
		
		int size = 0;
		for (long key : map.keySet()){
			size += map.get(key);
		}
		
		long[] factor = new long[size];
		int k = 0;
		for (long key : map.keySet()){
			int cnt = map.get(key);
			while ((cnt--) != 0){
				factor[k++] = key;
			}
		}
		
		int mask = 1 << size;
		long min = Integer.MAX_VALUE;
		Pair p = new Pair();
		for (int i = mask - 1; i>= 0; --i){
			long f1 = 1;
			for (int j = 0, m = 1; j < size; ++j, m <<= 1){
				if ((i & m) != 0){
					f1 *= factor[j];
				}
			}
			long f2 = c / f1;
			if (f1 < f2 && (f1 + f2) < min){
				p.fir = f1;
				p.sec = f2;
				min = f1 + f2;
			}
		}
		return p;
	}
	
	public static long quickMul(long a, long b, long mod){
		long ans = 0;
		while (b != 0){
			if ((b & 1) != 0){
				b--;
				ans = (ans + a) % mod;
			}
			b >>= 1;
			a = (a + a) % mod;
		}
		return ans;
	}
	
	public static long quickPow(long a, long b, long mod){
		long ans = 1;
		while (b != 0){
			if ((b & 1) != 0){
				b--;
				ans = quickMul(ans, a, mod);
			}
			b >>= 1;
			a = quickMul(a, a, mod);
		}
		return ans;
	}
	
	public static boolean witness(long a, long n){
		long tem = n - 1;
		int j = 0;
		while (tem % 2 == 0){
			tem /= 2;
			j++;
		}
		long x = quickPow(a, tem, n);
		if (x == 1 || x == n - 1) return true;
		
		while ((j--) != 0){
			x = quickMul(x, x, n);
			if (x == n - 1) return true;
		}
		return false;
	}
	
	private static long random(long n){
		return Math.abs(new Random().nextLong() % (n + 1));
	}
	
	public static boolean millerRabin(long n, int times){
		if (n == 2) return true;
		if (n < 2 || n % 2 == 0) return false;
		
		for (int i = 0; i < times; ++i){
			long a = random(n - 2) + 1;
			if (!witness(a, n)) return false;
		}
		return true;
	}
	
	public static long gcd(long a, long b){
		if (b == 0) return a;
		else return gcd(b, a % b);
	}
	
	public static long pollardRho(long n, long c){
		long x, y, d, i = 1, k = 2;
		x = random(n - 1) + 1; //[1,n]
		y = x;
		while (true){
			i++;
			x = (quickMul(x, x, n) + c) % n;
			d = gcd(y - x, n);
			if (1 < d && d < n) return d;
			if (y == x) return n;
			if (i == k){
				y = x;
				k <<= 1;
			}
		}
	}
	
	static Map<Long, Integer> map;
	public static void find(long n, long c){
		if (n == 1) return;
		if (millerRabin(n, 20)){
			//map.put(n, map.getOrDefault(n, 0) + 1);
			if (!map.containsKey(n)) map.put(n, 0);
			map.put(n, map.get(n) + 1);
			return;
		}
		long p = n;
		long k = c;
		while (p >= n) p = pollardRho(p, c--);
		find(p, k);
		find(n / p, k);
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
