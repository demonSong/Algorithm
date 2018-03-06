package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SolutionDay01_P2409 {
	
	String INPUT = "./data/judge/201709/P2409.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay01_P2409().run();
	}
	
	public Set<Integer> primes_factor(int n){
		Set<Integer> ans = new HashSet<Integer>();
		for (int i = 2; i <= n / i; ++i){
			if (n % i == 0){
				ans.add(i);
				for (; n % i == 0; n /= i);
			}
		}
		
		if (n != 1){
			ans.add(n);
		}
		
		return ans;
	}
	
	public Set<Integer> divisor(int n){
		Set<Integer> ans = new HashSet<Integer>();
		for (int i = 1; i <= n / i; ++i){
			if (n % i == 0){
				ans.add(i);
				ans.add(n / i);
			}
		}
		return ans;
	}
	
	public long pow(int a, int b){
		long res = 1;
		for (; b > 0; b >>= 1, a = a * a){
			if ((b & 1) != 0){
				res = res * a;
			}
		}
		return res;
	}
	
	public int gcd(int a, int b){
		return b == 0 ? a : gcd(b, a % b);
	}
	
	void solve() {
		while (true){
			int m = ni();
			int n = ni();
			
			if (m + n == 0) break;
			
			if (n == 0) out.println(0);
			else{
				Set<Integer> factors = divisor(n);
				Set<Integer> primes  = primes_factor(n);
				long count = 0;
				for (int d : factors){
					
					int phi = n / d;
					for (int p : primes){
						if ((n / d) % p == 0) phi = phi / p * (p - 1);
					}
					
					count += phi * pow(m, d);
				}
				
				if ((n & 1) != 0){
					count += n * pow(m, (n + 1) / 2);
				}
				else{
					count += n / 2 * (pow(m, n / 2 + 1) + pow(m, n / 2));
				}
				
				out.println(count / (2 * n));
			}
		}
	}

	FastScanner in;
	PrintWriter out;
	
	void run() throws IOException {
		boolean oj;
		try {
			oj = ! System.getProperty("user.dir").equals("F:\\java_workspace\\leetcode");
		} catch (Exception e) {
			oj = System.getProperty("ONLINE_JUDGE") != null;
		}
		
		InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
		in = new FastScanner(is);
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		if (!oj){
			System.out.println("[" + (System.currentTimeMillis() - s) + "ms]");
		}
	}
	
	public boolean more(){
		return in.hasNext();
	}
	
	public int ni(){
		return in.nextInt();
	}
	
	public long nl(){
		return in.nextLong();
	}
	
	public double nd(){
		return in.nextDouble();
	}
	
	public String ns(){
		return in.nextString();
	}
	
	public char nc(){
		return in.nextChar();
	}
	
	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean hasNext;

		public FastScanner(InputStream is) throws IOException {
			br = new BufferedReader(new InputStreamReader(is));
			hasNext = true;
		}

		public String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					hasNext = false;
					return "##";
				}
			}
			return st.nextToken();
		}
		
		String next = null;
		public boolean hasNext(){
			next = nextToken();
			return hasNext;
		}

		public int nextInt() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Integer.parseInt(more);
		}

		public long nextLong() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Long.parseLong(more);
		}

		public double nextDouble() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Double.parseDouble(more);
		}
		
		public String nextString(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more;
		}
		
		public char nextChar(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more.charAt(0);
		}
	}
}
