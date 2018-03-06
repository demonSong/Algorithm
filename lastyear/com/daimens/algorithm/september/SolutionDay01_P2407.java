package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay01_P2407 {

	String INPUT = "./data/judge/201709/P2407.txt";

	public static void main(String[] args) throws IOException {
		new SolutionDay01_P2407().run();
	}
	
	static final int MAX_N = 10000 + 16;
	int[] euler = new int[MAX_N];
	
	public void euler_phi(){
		for (int i = 0; i < MAX_N; ++i) euler[i] = i;
		for (int i = 2; i < MAX_N; ++i){
			if (euler[i] == i){
				for (int j = i; j < MAX_N; j += i){
					euler[j] = euler[j] / i * (i - 1);
				}
			}
		}
	}
	
	public int phi(int n){
		int res = n;
		for (int i = 2; i < n / i; ++i){
			if (n % i == 0){
				res = res / i * (i - 1);
				for (; n % i == 0; n /= i);
			}
		}
		if (n != 1){
			res = res / n * (n - 1);
		}
		return res;
	}
	
	void solve() {
		
		euler_phi();
		
		while (true){
			int n = ni();
			if (n == 0) break;
			if (n < MAX_N)
				out.println(euler[n]);
			else
				out.println(phi(n));
		}
	}
	
	FastScanner in;
	PrintWriter out;

	void run() throws IOException {
		boolean oj;
		try {
			oj = !System.getProperty("user.dir").equals("F:\\java_workspace\\leetcode");
		} catch (Exception e) {
			oj = System.getProperty("ONLINE_JUDGE") != null;
		}

		InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
		in = new FastScanner(is);
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		if (!oj) {
			System.out.println("[" + (System.currentTimeMillis() - s) + "ms]");
		}
	}

	public boolean more() {
		return in.hasNext();
	}

	public int ni() {
		return in.nextInt();
	}

	public long nl() {
		return in.nextLong();
	}

	public double nd() {
		return in.nextDouble();
	}

	public String ns() {
		return in.nextString();
	}

	public char nc() {
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

		public boolean hasNext() {
			next = nextToken();
			return hasNext;
		}

		public int nextInt() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return Integer.parseInt(more);
		}

		public long nextLong() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return Long.parseLong(more);
		}

		public double nextDouble() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return Double.parseDouble(more);
		}

		public String nextString() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return more;
		}

		public char nextChar() {
			if (next == null) {
				hasNext();
			}
			String more = next;
			next = null;
			return more.charAt(0);
		}
	}
}
