package com.daimens.algorithm.august;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay30_P2891 {
	
	String INPUT = "./data/judge/201708/P2891.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay30_P2891().run();
	}
	
	void solve() {
		while (more()){
			int n = ni();
			long[] a = new long[n];
			long[] m = new long[n];
			for (int i = 0; i < n; ++i){
				m[i] = ni();
				a[i] = ni();
			}
			out.println(CRT(a, m, n));
		}
	}
	
	class GCD{
		long d;
		long x;
		long y;
		
		public GCD(long d, long x, long y){
			this.d = d;
			this.x = x;
			this.y = y;
		}
	}
	
	public long gcd(long a, long b){
		if (b == 0) return a;
		else return gcd(b, a % b);
	}
	
	public GCD extgcd(long a, long b){
		if (b == 0) return new GCD(a, 1, 0);
		else{
			GCD g   = extgcd(b, a % b);
			GCD ans = new GCD(0, 0, 0);
			ans.d = g.d;
			ans.x = g.y;
			ans.y = g.x - (a / b) * g.y;
			return ans;
		}
	}
	
	public long mod_inverse(long a, long m){
		GCD g = extgcd(a, m);
		if (g.d != 1) return -1;
		return (g.x % m + m) % m;
	}
	
	class LL{
		long val;
		
		public LL(){}
		
		public LL(long val){
			this.val = val;
		}
		
		@Override
		public String toString() {
			return val + "";
		}
	}
	
	public boolean merge(long a1, long m1, long a2, long m2, LL a3, LL m3){
		GCD g = extgcd(m1, m2);
		long c = a2 - a1;
		long d = g.d;
		if (c % d != 0) return false;
		m1 /= d;
		m2 /= d;
		c  /= d;
		long inv = mod_inverse(m1, m2);
		c *= inv;
		c %=  m2;
		c *= m1 * d;
		c += a1;
		m3.val = m1 * m2 * d;
		a3.val = c;
		return true;
	}
	
	public long CRT(long[] a, long[] m, int n){
		long a1 = a[0];
		long m1 = m[0];
		for (int i = 1; i < n; ++i){
			long a2 = a[i];
			long m2 = m[i];
			
			LL a3 = new LL();
			LL m3 = new LL();
			if (!merge(a1, m1, a2, m2, a3, m3)) return -1;
			
			a1 = a3.val;
			m1 = m3.val;
		}
		return (a1 % m1 + m1) % m1;
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
