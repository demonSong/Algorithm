package com.daimens.algorithm.august;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 
 * @author DemonSong
 * 
 * 	GCJ Japan 2011 决赛B： 细菌繁殖
 *
 */
public class SolutionDay30_G011B {
	
	String INPUT = "./data/judge/201708/G011B.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay30_G011B().run();
	}
	
	static final int MAX_B = 1000 + 16;
	static final int MAX_C = 1000 + 16;
	
	int[][] bnm = new int[MAX_B][MAX_C];
	
	void solve() {
		int t = ni();
		int id = 0;
		while (t --> 0){
			int A = ni();
			int B = ni();
			int C = ni();
			
			for (int i = 1; i <= 1000; ++i) bnm[0][i] = A % i;
			
			
		}
	}
	
	public int mul(int a, int b, int mod){
		int res = 0;
		for (; b > 0; b >>= 1, a = (a + a) % mod){
			if ((b & 1) != 0){
				res = res + a;
				res %= mod;
			}
		}
		return res;
	}
	
	public int pow(int a, int b, int mod){
		int res = 1;
		for (; b > 0; b >>= 1, a = mul(a, a, mod)){
			if ((b & 1) != 0){
				res = mul(res, a, mod);
			}
		}
		return res;
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
