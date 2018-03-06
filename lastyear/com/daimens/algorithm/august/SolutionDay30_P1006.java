package com.daimens.algorithm.august;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay30_P1006 {
	
	String INPUT = "./data/judge/201708/P1006.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay30_P1006().run();
	}
	
	void solve() {
		int t = 0;
		while (true){
			int p = ni();
			int e = ni();
			int i = ni();
			int d = ni();
			if (p == -1 && e == -1 && i == -1 && d == -1) break;
			int[] a = new int[3];
			int[] m = new int[3];
			a[0] = p;
			a[1] = e;
			a[2] = i;
			m[0] = 23;
			m[1] = 28;
			m[2] = 33;
			int ans = CRT(a, m, 3);
			if (ans <= d){
				ans += 21252;
			}
			out.println("Case " + (++t) + ": the next triple peak occurs in " + (ans - d) + " days.");
		}
	}
	
	class Pair{
		int d;
		int x;
		int y;
		public Pair(int d, int x, int y){
			this.d = d;
			this.x = x;
			this.y = y;
		}
	}
	
	public Pair extgcd(int a, int b){
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
	
	public int mod_inverse(int a, int m){
		Pair p = extgcd(a, m);
		if (p.d != 1) return -1;
		return (p.x % m + m) % m;
	}
	
	public int CRT(int[] a, int[] m, int n){
		int M = 1;
		int ans = 0;
		for (int i = 0; i < n; ++i){
			M *= m[i];
		}
		for (int i = 0; i < n; ++i){
			int inv = mod_inverse(M / m[i], m[i]);
			ans += a[i] * M / m[i] * inv;
			ans %= M;
		}
		return ans;
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
