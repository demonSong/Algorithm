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
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay14_P3690 {
	
	String INPUT = "./data/judge/201709/P3690.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay14_P3690().run();
	}
	
	void read() {
		int cnt = 0;
		while (true) {
			int n = ni();
			int m = ni();
			int k = ni();
			int p = ni();
			int q = ni();
			if (n + m + k + p + q == 0) break;
			out.println("Case " + (++cnt) + ": " + solve(n, m, k, p, q));
		}
	}
	
	int solve(int n, int m, int k, int p, int q) {
		char[][] maps = new char[n][m];
		for (int i = 0; i < n; ++i) {
			maps[i] = ns().toCharArray();
		}
		
		long L = 1000000007;
		long B = 107;
		long[][] hs = new long[n][m];
		for (int i = 0; i < n; ++i) {
			long pw = 1;
			for (int j = 0; j < q; ++j) {
				hs[i][j] = (j > 0 ? hs[i][j - 1] : 0) * B + maps[i][j];
				pw *= B;
			}
			
			for (int j = q; j < m; ++j) {
				hs[i][j] = hs[i][j - 1] * B + maps[i][j] - pw * maps[i][j - q];	
			}
		}
		
		long[][] hs2 = new long[n][m];
		for (int j = 0; j < m; ++j) {
			long pw = 1;
			for (int i = 0; i < p; ++i) {
				hs2[i][j] = (i > 0 ? hs2[i - 1][j] : 0) * L + hs[i][j];
				pw *= L;
			}
			
			for (int i = p; i < n; ++i) {
				hs2[i][j] = hs2[i - 1][j] * L + hs[i][j] - pw * hs[i - p][j];
			}
		}
		
		Map<Long, Integer> mem = new HashMap<Long, Integer>();
		for (int i = 0; i < k; ++i) {
			long num = process(nextCharMap(p, q), p, q, L, B);
			Num.inc(mem, num);
		}
		
		int cnt = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (i >= p - 1 && j >= q - 1) {
					if (mem.containsKey(hs2[i][j])) {
						cnt += mem.get(hs2[i][j]);
						mem.remove(hs2[i][j]);
					}
				}
			}
		}
		return cnt;
	}
	
	long process(char[][] map, int p, int q, long L, long B) {
		long[][] hs = new long[p][q];
		for (int i = 0; i < p; ++i) {
			for (int j = 0; j < q; ++j) {
				hs[i][j] = (j > 0 ? hs[i][j - 1] : 0) * B + map[i][j];
			}
		}
		
		for (int j = q - 1; j < q; ++j) {
			for (int i = 0; i < p; ++i) {
				hs[i][j] = (i > 0 ? hs[i - 1][j] : 0) * L + hs[i][j];
			}
		}
		
		return hs[p - 1][q - 1];
	}

	char[][] nextCharMap(int p, int q){
		char[][] maps = new char[p][q];
		for (int i = 0; i < p; ++i) {
			maps[i] = ns().toCharArray();
		}
		return maps;
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
	
	static class Num{
		public static <K> void inc(Map<K, Integer> mem, K k){
			if (!mem.containsKey(k)) mem.put(k, 0);
			mem.put(k, mem.get(k) + 1);
		}
	}
}

