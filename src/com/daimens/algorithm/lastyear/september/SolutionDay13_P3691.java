package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class SolutionDay13_P3691 {
	
	String INPUT = "./data/judge/201709/P3691.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay13_P3691().run();
	}
	
	static final int MAX_N = 50 + 2;
	static final int INF   = 0x3f3f3f3f;
	
	static char[] ATCG = {'A','G','C','T'};
	
	void read() {
		int cnt = 0;	
		while (true) {
			int N = ni();
			if (N == 0) break;
			String[] p = new String[N];
			for (int i = 0; i < N; ++i) p[i] = ns();
			String match = ns();
			out.println("Case " + (++cnt) + ": " + solve(match, p));
		}
	}
	
	
	int solve(String match, String[] p) {
		Set<String> pfx = new HashSet<String>();
		pfx.add("");
		for (String forb : p) {
			for (int i = 0; i <= forb.length(); ++i) {
				pfx.add(forb.substring(0, i));
			}
		}
		
		int K = pfx.size();
		String[] pfxs = pfx.toArray(new String[0]);
		Map<String, Integer> mem = new HashMap<String, Integer>();
		for (int i = 0; i < K; ++i) mem.put(pfxs[i], i);
		
		boolean[] ng = new boolean[K + 1];
		
		//不可达状态
		for (int i = 0; i < K; ++i) {
			ng[i] = false;
			for (int j = 0; j < p.length; ++j) {
				ng[i] |= p[j].length() <= pfxs[i].length() 
						&& pfxs[i].substring(pfxs[i].length() - p[j].length() , pfxs[i].length()).equals(p[j]);
			}
		}
		
		int[][] next = new int[K + 1][4];
		for (int i = 0; i < K; ++i) {
			for (int j = 0; j < 4; ++j) {
				String s = pfxs[i] + ATCG[j];
				while (!mem.containsKey(s)) {
					s = s.substring(1);
				}
				next[i][j] = mem.get(s);
			}
		}
		
		int[][] dp = new int[match.length() + 16][K + 16];
		dp[0][0] = 1;
		for (int i = 1; i < K; ++i) dp[0][i] = 0;
		for (int i = 1; i <= match.length(); ++i) {
			for (int j = 0; j < K; ++j) dp[i][j] = INF;
			for (int j = 0; j < K; ++j) {
				if (ng[j]) continue;
				for (int l = 0; l < 4; ++l) {
					int ns = next[j][l];
					dp[i][ns] = Math.min(dp[i][ns], dp[i - 1][j] + (match.charAt(i - 1) == ATCG[l] ? 0 : 1));
				}
			}
		}
		
		int ans = INF;
		for (int i = 0; i < K; ++i) {
			if (ng[i]) continue;
			ans = Math.min(ans, dp[match.length()][i]);
		}
		
		return ans == INF ? -1 : ans;
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
		read();
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
	
	static class ArrayUtils {

		public static void fill(int[][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				Arrays.fill(f[i], value);
			}
		}
		
		public static void fill(int[][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}
		
		public static void fill(int[][][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}
	}
}

