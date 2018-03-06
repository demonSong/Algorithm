package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay05_P2068 {
	
	String INPUT = "./data/judge/201709/P2068.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay05_P2068().run();
	}
	
	static final int MAX_S = 1 << 13;
	boolean[][][] dp; //第k只队伍 第j个队员 剩余i个石头的状态
	
	void solve() {
		while (true) {
			int n = ni();
			if (n == 0) break;
			int s = ni();
			
			dp = new boolean[2][12][MAX_S + 16];
			
			int[] team = new int[2 * n];
			for (int i = 0; i < 2 * n; ++i) {
				team[i] = ni();
			}
			
			for (int i = 0; i < 2 * n; ++i) {
				dp[i & 1][i / 2 + 1][0] = true;
				dp[i & 1][i / 2 + 1][1] = false; // 必输态
			}
			
			for (int i = 2; i <= s; ++i) {
				for (int now = 0; now < 2 * n; ++now) {
					int nxt = (now + 1) % (2 * n);
					for (int j = team[now]; j >= 1; --j) {
						if (i - j >= 0) {
							dp[now & 1][now / 2 + 1][i] |= !dp[nxt & 1][nxt / 2 + 1][i - j];
							if (dp[now & 1][now / 2 + 1][i]) break;
						}
					}
				}
			}
			
//			for (int i = 2; i <= s; ++i) {
//				for (int now = 0; now < 2 * n; ++now) {
//					int nxt = (now + 1) % (2 * n);
//					for (int j = Math.max(0, i - team[now]); j < i; ++j) {
//						dp[now & 1][now / 2 + 1][i] |= !dp[nxt & 1][nxt / 2 + 1][j];
//						if (dp[now & 1][now / 2 + 1][i]) break;
//					}
//				}
//			}
			
			out.println(dp[0][1][s] ? "1" : "0");
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

