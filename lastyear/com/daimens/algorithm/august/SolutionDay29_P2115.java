package com.daimens.algorithm.august;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay29_P2115 {
	
	String INPUT = "./data/judge/201708/P2115.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay29_P2115().run();
	}
	
	void solve() {
		while (true){
			int A = ni();
			int B = ni();
			int C = ni();
			int K = ni();
			if (A + B + C + K == 0) break;
			long ans = modular_linear_equation_solver(C, B - A, (long)1 << K);
			if (ans == -1){
				out.println("FOREVER");
			}
			else{
				out.println(ans);
			}
		}
	}
	
	class Pair{
		long d;
		long x;
		long y;
		// a * x + b * y = d
		public Pair(long d, long x, long y){
			this.d = d;
			this.x = x;
			this.y = y;
		}
	}
	
	public Pair extgcd(long a, long b){ // a > b or a < b
		if (b == 0){
			return new Pair(a, 1, 0);
		}
		else{
			Pair p = extgcd(b, a % b);
			Pair ans = new Pair(0, 0, 0);
			ans.d = p.d;
			ans.x = p.y;
			ans.y = p.x - (a / b) * p.y;
			return ans;
		}
	}
	
	public long mod_inverse(long a, long m){
		Pair p = extgcd(a, m);
		if (p.d != 1) return -1;
		return (p.x % m + m) % m;
	}
	
	public long modular_linear_equation_solver(long a, long b, long m){
		Pair p = extgcd(a, m);
		if (b % p.d != 0) return -1;
		long a0 = mod_inverse(a / p.d, m / p.d);
		return (a0 * (b / p.d) % (m / p.d) + (m / p.d)) % (m / p.d);
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
