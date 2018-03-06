package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay05_P3688 {
	
	String INPUT = "./data/judge/201709/P3688.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay05_P3688().run();
	}
	
	
	static final int MAX_M = 100000 + 16;
	boolean[][] dp = new boolean[MAX_M][2];
	
	void solve() {
		while (true) {
			int n = ni();
			int m = ni();
			if (n == 0 && m == 0) break;
			
			if (n == 0) {
				out.println("0");
				continue;
			}
			
			int[] cards = new int[n];
			for (int i = 0; i < n; ++i) {
				cards[i] = ni();
			}
			
			dp = new boolean[MAX_M][2];
			dp[cards[0]][1] = true;
			
			for (int i = 1; i < n; ++i) {
				for (int j = m; j > cards[i]; --j) {
					if (dp[j - cards[i]][0]) {
						dp[j][1] = true;
					}
					if (dp[j - cards[i]][1]) {
						dp[j][0] = true;
					}
				}
				dp[cards[i]][1] = true;
			}
			
			int ans = 0;
			for (int i = 1; i <= m; ++i) {
				if (dp[i][1] && !dp[i][0]) ans ++;
			}
			
			out.println(ans);
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

//void solve() {
//	while (true) {
//		int n = ni();
//		int m = ni();
//		if (n == 0 && m == 0) break;
//		
//		dp = new boolean[m + 16];
//		
//		int[] card = new int[n];
//		for (int i = 0; i < n; ++i) {
//			card[i] = ni();
//		}
//		
//		int ans = 0;
//		for (int i = 0; i <= m; ++i) {
//			if (f(i, card, 0, new boolean[n + 1])) {
//				ans ++;
//			}
//		}
//		out.println(ans);
//	}
//}
//
//boolean[] dp;
//boolean f(int m, int[] card, int cnt, boolean[] visited) {
//	if (m < 0) return false;
//	if (m == 0 && cnt % 2 != 0) return true;
//	else {
//		boolean ans = false;
//		for (int i = 0; i < card.length; ++i) {
//			if (!visited[i]) {
//				visited[i] = true;
//				ans |= f(m - card[i], card, cnt + 1, visited);
//				visited[i] = false;
//			}
//		}
//		return ans;
//	}
//}
